package com.easyapi.core;

import com.easyapi.core.parser.ResponseNode;

import java.util.Map;


public interface IResponseWrapper {
    Map<String, Object> wrapResponse(ResponseNode responseNode);

}
