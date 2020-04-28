package com.secondgroup.pojo;

public class Message {
    private Integer id;

    private Integer userId;

    private Integer quesTplId;

    private String title;

    private String content;

    private Integer isOccupy;

    private Integer atCreate;

    private Integer atUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuesTplId() {
        return quesTplId;
    }

    public void setQuesTplId(Integer quesTplId) {
        this.quesTplId = quesTplId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getIsOccupy() {
        return isOccupy;
    }

    public void setIsOccupy(Integer isOccupy) {
        this.isOccupy = isOccupy;
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