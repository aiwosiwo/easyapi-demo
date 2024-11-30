package com.easyapi.test.model;

import com.easyapi.core.annotation.DocIgnore;
import lombok.Data;

@Data
public class User {
    @DocIgnore
    private Long Id;
    private String name;
}