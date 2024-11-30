package com.easyapi.test.model;

import lombok.Data;

@Data
public class Document {
    /**
     * main key
     */
    private Long id;
    /**
     * doc title
     */
    private String title;
    /**
     * doc content
     */
    private String content;
}
