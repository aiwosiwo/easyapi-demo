package com.easyapi.core.doc;


import com.easyapi.core.DocContext;
import com.easyapi.core.utils.LogUtils;
import com.easyapi.core.utils.Utils;
import com.easyapi.core.parser.AbsControllerParser;
import com.easyapi.core.parser.ControllerNode;
import com.easyapi.core.parser.RequestNode;
import com.easyapi.core.constant.CoreConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class AbsDocGenerator {

    private AbsControllerParser controllerParser;
    private IControllerDocBuilder controllerDocBuilder;
    private List<Link> docFileLinkList = new ArrayList<>();
    private List<ControllerNode> controllerNodeList = new ArrayList<>();

    AbsDocGenerator(AbsControllerParser controllerParser, IControllerDocBuilder controllerDocBuilder) {
        this.controllerParser = controllerParser;
        this.controllerDocBuilder = controllerDocBuilder;
        this.initControllerNodes();
    }

    public void generateDocs() {
        LogUtils.info("easyapi auto generate start...");
        generateControllersDocs();
        generateIndex(controllerNodeList);
        // 生成静态文件 图片,js及css等
        generateStaticFiles(CoreConstants.IMG_PATH, CoreConstants.LOGO_NAME);
        generateStaticFiles(CoreConstants.JS_PATH, CoreConstants.JS_NAMES);
        generateStaticFiles(CoreConstants.CSS_PATH, CoreConstants.CSS_NAMES);
        generateStaticFiles(CoreConstants.FONT_PATH, CoreConstants.FONT_NAMES);
        LogUtils.info("easyapi auto generate done!");
    }

    private void initControllerNodes() {
        File[] controllerFiles = DocContext.getControllerFiles();
        for (File controllerFile : controllerFiles) {
            LogUtils.info("start to parse controller file : %s", controllerFile.getName());
            ControllerNode controllerNode = controllerParser.parse(controllerFile);
            if (controllerNode.getRequestNodes().isEmpty()) {
                continue;
            }

            controllerNode.setSrcFileName(controllerFile.getAbsolutePath());
            final String docFileName = String.format("%s_%s.html", controllerNode.getPackageName().replace(".", "_"), controllerNode.getClassName());
            controllerNode.setDocFileName(docFileName);
            for (RequestNode requestNode : controllerNode.getRequestNodes()) {
                requestNode.setCodeFileUrl(String.format("%s#%s", docFileName, requestNode.getMethodName()));
            }

            controllerNodeList.add(controllerNode);
            LogUtils.info("success to parse controller file : %s", controllerFile.getName());
        }
    }

    private void generateControllersDocs() {
        File docPath = new File(DocContext.getDocPath());
        for (ControllerNode controllerNode : controllerNodeList) {
            try {
                LogUtils.info("start to generate docs for controller file : %s", controllerNode.getSrcFileName());
                final String controllerDocs = controllerDocBuilder.buildDoc(controllerNode);
                docFileLinkList.add(new Link(controllerNode.getDescription(), String.format("%s", controllerNode.getDocFileName())));
                String path = docPath + File.separator + CoreConstants.HTML_PATH;
                File htmlDir = new File(path);
                if (!htmlDir.exists()) {
                    boolean mkdir = htmlDir.mkdir();
                    if (!mkdir) {
                        LogUtils.error("create html docs dir fail");
                    }
                }
                Utils.writeToDisk(new File(path, controllerNode.getDocFileName()), controllerDocs);
                LogUtils.info("success to generate docs for controller file : %s", controllerNode.getSrcFileName());
            } catch (IOException e) {
                LogUtils.error("generate docs for controller file : " + controllerNode.getSrcFileName() + " fail", e);
            }
        }
    }

    public List<ControllerNode> getControllerNodeList() {
        return controllerNodeList;
    }

    abstract void generateIndex(List<ControllerNode> controllerNodeList);


    private void generateStaticFiles(String filePath, String fileNames) {
        // 生成静态资源文件存放目录
        Path jsPath = Paths.get(DocContext.getDocPath(), filePath);
        if (!Files.exists(jsPath)) {
            try {
                Files.createDirectories(jsPath);
            } catch (IOException e) {
                LogUtils.error("generate static file path fail", e);
            }
        }

        // 批量生成静态资源文件
        Arrays.stream(fileNames.split(CoreConstants.COMMA_SIGN)).forEach(fileName -> Utils.getThirdPartJarFile(fileName, filePath));
    }

}
