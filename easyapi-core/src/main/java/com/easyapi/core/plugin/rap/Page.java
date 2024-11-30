package com.easyapi.core.plugin.rap;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
class Page {

    private int id;
    private String name;
    private String introduction;
    private Module module;
    private Set<Action> actionList = new HashSet<Action>();
    private String template;

    public static Page newPage() {
        Page page = new Page();
        page.setId(-1);
        return page;
    }
}
