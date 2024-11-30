package com.easyapi.core.codegenerator.model;


import com.easyapi.core.codegenerator.provider.DocFieldHelper;
import lombok.Data;

@Data
public class FieldModel {

    private String remoteFieldName;
    private String caseFieldName;
    private String fieldName;
    private String fieldType;
    private String comment;


    public String getAssign() {
        if (fieldType != null && fieldType.equalsIgnoreCase("string")) {
            return "assign";
        } else {
            return "strong";
        }
    }
    public String getComment() {
        return comment == null ? "" : comment;
    }
    public String getIFieldType() {
        return DocFieldHelper.getIosFieldType(fieldType);
    }


}
