package com.easyapi.core.http;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class DHttpRequest {

    private String url;
    private boolean autoRedirect = true; //自动重定向
    private Map<String, String> params = new HashMap<>();
    private Map<String, String> headers;

    public String getUrl() {
        return url;
    }

    public void addParam(String name, String value) {
        params.put(name, value);
    }


}
