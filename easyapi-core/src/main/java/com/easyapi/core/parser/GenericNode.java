package com.easyapi.core.parser;

import com.github.javaparser.ast.type.Type;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class GenericNode {

    private Type classType;
    private Class modelClass;
    private String placeholder;
    private File fromJavaFile;
    private List<GenericNode> childGenericNode = new ArrayList<>();

}
