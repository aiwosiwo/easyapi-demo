package com.easyapi.core.codegenerator.provider;


import com.easyapi.core.codegenerator.IFieldProvider;


public class ProviderFactory {

    public static IFieldProvider createProvider() {
        return new DocFieldProvider();
    }
}
