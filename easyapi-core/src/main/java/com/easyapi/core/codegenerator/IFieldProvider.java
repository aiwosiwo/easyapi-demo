package com.easyapi.core.codegenerator;


import com.easyapi.core.codegenerator.model.FieldModel;
import com.easyapi.core.parser.ClassNode;

import java.util.List;


public interface IFieldProvider {
    List<FieldModel> provideFields(ClassNode respNode);
}
