package com.easyapi.core.plugin.rap;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
class Module {

    public static final String NAME = "API List";

    private int id;
    private int projectId;
    private String name;
    private String introduction;
    private Project project;
    private Set<Page> pageList = new HashSet<Page>();

    public static Module newModule() {
        Module module = new Module();
        module.setId(-1);
        module.setName(NAME);
        return module;
    }

}
