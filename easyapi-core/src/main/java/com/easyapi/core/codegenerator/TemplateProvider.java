package com.easyapi.core.codegenerator;


import com.easyapi.core.Resources;
import com.easyapi.core.utils.Utils;

import java.io.IOException;


public class TemplateProvider {
    public static String provideTemplateForName(String templateName) throws IOException {
        return Utils.streamToString(Resources.getCodeTemplateFile(templateName));
    }
}
