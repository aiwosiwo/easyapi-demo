package com.easyapi.core.plugin.rap;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
class Parameter {

    private int id;
    private String mockData;
    private String name;
    private String identifier;
    private String identifierChange;
    private String remarkChange;
    private String dataType;
    private String remark;
    private Set<Action> actionRequestList = new HashSet<Action>();
    private Set<Action> actionResponseList = new HashSet<Action>();
    private String validator = "";
    private Set<Parameter> parameterList = new HashSet<Parameter>();
    private Set<Parameter> complexParamerterList = new HashSet<Parameter>();

    public static Parameter newParameter() {
        Parameter p = new Parameter();
        p.setId(-1);
        return p;
    }
}
