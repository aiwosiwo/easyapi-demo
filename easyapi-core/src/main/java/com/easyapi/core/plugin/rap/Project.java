package com.easyapi.core.plugin.rap;


import com.easyapi.core.DocContext;
import com.easyapi.core.IResponseWrapper;
import com.easyapi.core.utils.ParseUtils;
import com.easyapi.core.utils.Utils;
import com.easyapi.core.parser.*;
import lombok.Data;

import java.util.*;

@Data
class Project {

    private int id;
    private int userId;
    private String name;
    private Date createDate;
    private Date updateTime;
    private String introduction;
    private int workspaceModeInt;
    private String relatedIds = "";
    private int groupId;
    private int mockNum;
    private int teamId;
    private short accessType;
    private Set<Module> moduleList = new HashSet<Module>();
    private String projectData;
    private List<String> memberAccountList;
    private String version;

    public static Project valueOf(int id, List<ControllerNode> controllerNodeList) {
        Project project = new Project();
        project.setId(id);
        Module module = Module.newModule();
        project.getModuleList().add(module);

        for (ControllerNode controllerNode : controllerNodeList) {
            Page page = Page.newPage();
            module.getPageList().add(page);
            page.setName(controllerNode.getDescription());

            for (RequestNode requestNode : controllerNode.getRequestNodes()) {
                Action action = Action.newAction();
                action.setName(requestNode.getDescription());
                String requestUrl = requestNode.getUrl();
                action.setRequestUrl(supportRestfulUrl(requestUrl));

                List<String> methods = requestNode.getMethod();
                action.setRequestType(ActionTypeEnum.valueOf(methods.get(0)).type);

                for (ParamNode paramNode : requestNode.getParamNodes()) {
                    Parameter parameter = Parameter.newParameter();
                    if (DataType.isArrayType(paramNode.getType())) {
                        parameter.setIdentifier(getArrayIdentifier(paramNode.getName()));
                    } else {
                        parameter.setIdentifier(paramNode.getName());
                    }
                    parameter.setName(paramNode.getDescription());
                    parameter.setDataType(DataType.rapTypeOfNode(paramNode.getType()));
//                  parameter.setRemark(DataType.mockTypeOfNode(paramNode.getType()));
                    action.getRequestParameterList().add(parameter);
                }

                IResponseWrapper responseWrapper = DocContext.getResponseWrapper();
                Map<String, Object> resultMap = responseWrapper.wrapResponse(requestNode.getResponseNode());
                setResultMapToAction(resultMap, action.getResponseParameterList());

                page.getActionList().add(action);
            }
        }

        return project;
    }

    private static void setResultMapToAction(Map<String, Object> resultMap, Set<Parameter> parameterSet) {
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            Parameter parameter = Parameter.newParameter();
            parameter.setIdentifier(entry.getKey());
            if (Utils.isValueType(entry.getValue())) {
                String uType = unifyType(entry.getValue());
                parameter.setDataType(DataType.rapTypeOfNode(uType));
                parameter.setRemark(DataType.mockValue(entry.getValue()));
                parameterSet.add(parameter);
            } else if (entry.getValue() instanceof Map) {
                parameter.setDataType(DataType.OBJECT);
                parameterSet.add(parameter);
                setResultMapToAction((Map) entry.getValue(), parameter.getParameterList());
            } else if (entry.getValue() instanceof ResponseNode) {
                ResponseNode responseNode = (ResponseNode) entry.getValue();
                if (responseNode.isList()) {
                    parameter.setIdentifier(getArrayIdentifier(entry.getKey()));
                    parameter.setDataType(DataType.ARRAY_OBJECT);
                } else {
                    parameter.setDataType(DataType.OBJECT);
                }
                parameterSet.add(parameter);
                setResponseToAction(responseNode, parameter.getParameterList());
            }
        }
    }

    private static void setResponseToAction(ClassNode responseNode, Set<Parameter> parameterSet) {
        for (FieldNode fieldNode : responseNode.getChildNodes()) {
            Parameter parameter = Parameter.newParameter();
            parameter.setName(fieldNode.getDescription());
            MockNode mockNode = fieldNode.getMockNode();

            if (DataType.isArrayType(fieldNode.getType())) {
                parameter.setIdentifier(getArrayIdentifier(fieldNode.getName()));
            } else {
                parameter.setIdentifier(fieldNode.getName());
            }
            parameter.setRemark(DataType.mockTypeOfNode(fieldNode.getType()));
            parameter.setDataType(DataType.rapTypeOfNode(fieldNode.getType()));

            // cover
            if (mockNode != null) {
                if (Utils.isNotEmpty(mockNode.getLimit())) {
                    parameter.setIdentifier(String.format("%s|%s", fieldNode.getName(), mockNode.getLimit()));
                }
                if (Utils.isNotEmpty(mockNode.getValue())) {
                    parameter.setRemark(DataType.mockValue(mockNode.getValue()));
                }
            }

            parameterSet.add(parameter);
            if (fieldNode.getChildNode() != null) {
                setResponseToAction(fieldNode.getChildNode(), parameter.getParameterList());
            }
        }
    }

    private static String unifyType(Object value) {
        return ParseUtils.unifyType(value.getClass().getSimpleName());
    }

    private static String getArrayIdentifier(String name) {
        return String.format("%s|1-10", name);
    }

    private static String supportRestfulUrl(String url) {
        if (url.contains("{") && url.contains("}")) {
            url = "reg:" + url.replaceAll("\\{.+?\\}", ".+");
        }
        return url;
    }
}
