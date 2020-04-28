package com.secondgroup.pojo;

public class School {
    private Integer id;

    private String schoolName;

    private Integer atCreate;

    private Integer atUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
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