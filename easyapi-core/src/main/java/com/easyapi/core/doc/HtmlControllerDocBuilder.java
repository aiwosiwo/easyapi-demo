package com.easyapi.core.doc;

import com.easyapi.core.DocContext;
import com.easyapi.core.utils.LogUtils;
import com.easyapi.core.Resources;
import com.easyapi.core.utils.Utils;
import com.easyapi.core.codegenerator.java.JavaCodeGenerator;
import com.easyapi.core.parser.ControllerNode;
import com.easyapi.core.parser.RequestNode;
import com.easyapi.core.constant.CoreConstants;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * html controller doc builder
 * <p>
 * licence Apache 2.0,AGPL-3.0, from japidoc and doc-apis originated
 **/
public class HtmlControllerDocBuilder implements IControllerDocBuilder {

    @Override
    public String buildDoc(ControllerNode controllerNode) throws IOException {

        for (RequestNode requestNode : controllerNode.getRequestNodes()) {
            if (requestNode.getResponseNode() != null && !requestNode.getResponseNode().getChildNodes().isEmpty()) {
                JavaCodeGenerator javaCodeGenerator = new JavaCodeGenerator(requestNode.getResponseNode());
                final String javaSrcUrl = javaCodeGenerator.generateCode();
                requestNode.setAndroidCodePath(javaSrcUrl);
            }
        }

        final Template ctrlTemplate = getControllerTpl();
        String path = DocContext.getDocPath() + File.separator + CoreConstants.HTML_PATH;
        File htmlDir = new File(path);
        if (!htmlDir.exists()) {
            boolean mkdir = htmlDir.mkdir();
            if (!mkdir) {
                LogUtils.error("create html docs dir fail");
            }
        }
        final File docFile = new File(path, controllerNode.getDocFileName());
        FileWriter docFileWriter = new FileWriter(docFile);
        Map<String, Object> data = new HashMap<>();
        data.put("controllerNodeList", DocContext.getControllerNodeList());
        data.put("controller", controllerNode);
        data.put("currentApiVersion", DocContext.getCurrentApiVersion());
        data.put("apiVersionList", DocContext.getApiVersionList());
        data.put("projectName", DocContext.getDocsConfig().getProjectName());
        data.put("i18n", DocContext.getI18n());
        data.put("watermark", DocContext.getDocsConfig().getWatermark());
        data.put("classificationLevel", DocContext.getDocsConfig().getWatermark());
        try {
            ctrlTemplate.process(data, docFileWriter);
        } catch (TemplateException ex) {
            ex.printStackTrace();
        } finally {
            Utils.closeSilently(docFileWriter);
        }
        return Utils.streamToString(new FileInputStream(docFile));
    }

    private Template getControllerTpl() throws IOException {
        return Resources.getFreemarkerTemplate("api-controller.html.ftl");
    }

}
