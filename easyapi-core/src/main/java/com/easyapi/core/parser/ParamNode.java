package com.easyapi.core.parser;


import lombok.Data;

@Data
public class ParamNode {

    private String name;
    private String type;
    private Boolean required = Boolean.FALSE;
    private String description;
    private Boolean jsonBody = Boolean.FALSE;// when true ,the json body set to description

}
