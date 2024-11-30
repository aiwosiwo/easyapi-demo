package com.easyapi.core.plugin.rap;


import lombok.Data;

@Data
class ModelResponse {

    private int code;
    private String msg;
    private Project model;
}
