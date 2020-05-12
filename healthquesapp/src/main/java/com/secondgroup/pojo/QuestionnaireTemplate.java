package com.secondgroup.pojo;

public class QuestionnaireTemplate {
    private Integer id;

    private String templateName;

    private String description;

    private Integer atCreate;

    private Integer atUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getAtCreate() {
        return atCreate;
    }

    public void setAtCreate(Integer atCreate) {
        this.atCreate = atCreate;
    }

    public Integer getAtUpdate() {
        return atUpdate;
    }

    public void setAtUpdate(Integer atUpdate) {
        this.atUpdate = atUpdate;
    }
}