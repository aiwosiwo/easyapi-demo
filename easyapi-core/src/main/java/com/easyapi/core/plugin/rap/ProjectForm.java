package com.easyapi.core.plugin.rap;


import lombok.Data;

@Data
class ProjectForm {

    private Integer id;
    private String projectData;
    private String deletedObjectListData;
    private String versionPosition;
    private String description;

}
