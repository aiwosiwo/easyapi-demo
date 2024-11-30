package com.easyapi.core.utils;


import com.easyapi.core.DocContext;
import com.easyapi.core.parser.ClassNode;
import com.easyapi.core.parser.ControllerNode;
import com.easyapi.core.parser.ResponseNode;
import com.easyapi.core.constant.CoreConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class CacheUtils {

    public static void saveControllerNodes(List<ControllerNode> controllerNodes) {
        try {
            controllerNodes.forEach(controllerNode -> {
                controllerNode.getRequestNodes().forEach(requestNode -> {
                    requestNode.setControllerNode(null);
                    requestNode.setLastRequestNode(null);
                    ResponseNode responseNode = requestNode.getResponseNode();
                    responseNode.setRequestNode(null);
                    removeLoopNode(responseNode);
                });
            });
            Utils.writeToDisk(new File(DocContext.getDocPath(), CoreConstants.CACHE_FILE), Utils.toJson(controllerNodes));
        } catch (Exception ex) {
            LogUtils.error("saveControllerNodes error!!!", ex);
        }
    }

    private static void removeLoopNode(ClassNode classNode) {
        classNode.setParentNode(null);
        classNode.setGenericNodes(null);
        classNode.getChildNodes().forEach(fieldNode -> {
            fieldNode.setClassNode(null);
            if (fieldNode.getChildNode() != null) {
                removeLoopNode(fieldNode.getChildNode());
            }
        });
    }

    public static List<ControllerNode> getControllerNodes(String apiVersion) {
        File apiRootPath = new File(new File(DocContext.getDocPath()).getParentFile(), apiVersion);
        if (!apiRootPath.exists()) {
            return null;
        }
        File cacheFile = new File(apiRootPath, CoreConstants.CACHE_FILE);
        if (!cacheFile.exists()) {
            return null;
        }
        try {
            ControllerNode[] controllerNodes = Utils.jsonToObject(Utils.streamToString(new FileInputStream(cacheFile)), ControllerNode[].class);
            return Arrays.asList(controllerNodes);
        } catch (IOException ex) {
            LogUtils.error("get ControllerNodes error!!!", ex);
            return null;
        }
    }
}
