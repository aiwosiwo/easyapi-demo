package com.easyapi.core.plugin.rap;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
class Action {

    private int id;
    private int disableCache;
    private String name;
    private String description;
    private String requestType = "1";
    private String requestUrl;
    private Set<Parameter> requestParameterList = new HashSet<Parameter>();
    private Set<Parameter> responseParameterList = new HashSet<Parameter>();
    private String responseTemplate;
    private Set<Page> pageList = new HashSet<Page>();
    private String remarks;

    public static Action newAction() {
        Action action = new Action();
        action.setId(-1);
        return action;
    }
}
