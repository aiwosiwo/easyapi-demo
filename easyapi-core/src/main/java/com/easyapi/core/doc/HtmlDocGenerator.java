package com.easyapi.core.doc;

import com.easyapi.core.DocContext;
import com.easyapi.core.utils.LogUtils;
import com.easyapi.core.Resources;
import com.easyapi.core.utils.Utils;
import com.easyapi.core.parser.ControllerNode;
import com.easyapi.core.parser.RequestNode;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.easyapi.core.constant.CoreConstants.*;


public class HtmlDocGenerator extends AbsDocGenerator {

    public HtmlDocGenerator() {
        super(DocContext.controllerParser(), new HtmlControllerDocBuilder());
    }

    @Override
    void generateIndex(List<ControllerNode> controllerNodeList) {
        FileWriter docFileWriter = null;
        try {
            LogUtils.info("easyapi generate index start !");
            final Template ctrlTemplate = getIndexTpl();
            final File docFile = new File(DocContext.getDocPath(), "index.html");
            docFileWriter = new FileWriter(docFile);
            Map<String, Object> data = new HashMap<>();
            controllerNodeList.forEach(controllerNode -> {
                List<RequestNode> requestNodes = controllerNode.getRequestNodes();
                requestNodes.forEach(requestNode -> {
                    String codeFileUrl = requestNode.getCodeFileUrl();
                    requestNode.setCodeFileUrl(HTML_PATH + SEPATOR + codeFileUrl);
                });
            });
            data.put("controllerNodeList", controllerNodeList);
            data.put("currentApiVersion", DocContext.getCurrentApiVersion());
            data.put("apiVersionList", DocContext.getApiVersionList());
            data.put("projectName", DocContext.getDocsConfig().getProjectName());
            data.put("i18n", DocContext.getI18n());
            data.put("watermark", DocContext.getDocsConfig().getWatermark());
            ctrlTemplate.process(data, docFileWriter);
            LogUtils.info("easyapi generate index success !");
        } catch (TemplateException | IOException ex) {
            LogUtils.error("easyapi generate index fail !", ex);
        } finally {
            Utils.closeSilently(docFileWriter);
        }
    }

    private Template getIndexTpl() throws IOException {
        return Resources.getFreemarkerTemplate("api-index.html.ftl");
    }
}
