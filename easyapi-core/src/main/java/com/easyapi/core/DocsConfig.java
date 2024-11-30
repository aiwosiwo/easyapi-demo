package com.easyapi.core;

import com.easyapi.core.constant.CoreConstants;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
public class DocsConfig {

    String projectPath;
    List<String> javaSrcPaths = new ArrayList<>();
    String docsPath;
    String resourcePath;
    String mvcFramework;
    String apiVersion;
    String projectName;
    Boolean autoGenerate = Boolean.FALSE;
    Locale locale = Locale.getDefault();
    Boolean openReflection = Boolean.TRUE;

    String rapHost;
    String rapLoginCookie;
    String rapProjectId;
    String rapAccount;
    String rapPassword;

    String watermark = CoreConstants.DEFAULT_WATERMARK;

    List<IPluginSupport> plugins = new ArrayList<>();

    public void addPlugin(IPluginSupport plugin) {
        this.plugins.add(plugin);
    }
    public void addJavaSrcPath(String javaSrcPath) {
        javaSrcPaths.add(javaSrcPath);
    }
    boolean isSpringMvcProject() {
        return mvcFramework != null && mvcFramework.equals("spring");
    }
    boolean isPlayProject() {
        return mvcFramework != null && mvcFramework.equals("play");
    }
    boolean isJfinalProject() {
        return mvcFramework != null && mvcFramework.equals("jfinal");
    }
    boolean isGeneric() {
        return mvcFramework != null && mvcFramework.equals("generic");
    }



}
