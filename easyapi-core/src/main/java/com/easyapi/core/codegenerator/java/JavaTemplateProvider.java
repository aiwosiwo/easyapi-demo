package com.easyapi.core.codegenerator.java;


import com.easyapi.core.codegenerator.TemplateProvider;

import java.io.IOException;


public class JavaTemplateProvider {

    public String provideTemplateForName(String templateName) throws IOException {
        return TemplateProvider.provideTemplateForName(templateName);
    }

}
