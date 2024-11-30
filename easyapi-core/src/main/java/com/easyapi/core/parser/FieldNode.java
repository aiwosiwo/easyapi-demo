package com.easyapi.core.parser;


import lombok.Data;

@Data
public class FieldNode {

    private String name;
    private String type;
    private String description;
    private MockNode mockNode;
    private ClassNode childNode;
    private ClassNode classNode;
    private Boolean loopNode = Boolean.FALSE;
    private Boolean notNull = Boolean.FALSE;

}
