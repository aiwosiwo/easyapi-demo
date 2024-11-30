package com.easyapi.core.plugin.rap;


enum ActionTypeEnum {

    GET("1"),
    POST("2"),
    PUT("3"),
    DELETE("4");

    public final String type;

    ActionTypeEnum(String type) {
        this.type = type;
    }
}
