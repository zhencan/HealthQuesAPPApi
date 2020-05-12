package com.secondgroup.pojo;

public class HealthyMessage {

    //新增QuestionnaireTemplate对象返回给前端
    private QuestionnaireTemplate questionnaireTemplate;

    public QuestionnaireTemplate getQuestionnaireTemplate() {
        return questionnaireTemplate;
    }

    public void setQuestionnaireTemplate(QuestionnaireTemplate questionnaireTemplate) {
        this.questionnaireTemplate = questionnaireTemplate;
    }

    private Integer id;

    private Integer userId;

    private Integer quesTplId;

    private String quesOption;

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

    public String getQuesOption() {
        return quesOption;
    }

    public void setQuesOption(String quesOption) {
        this.quesOption = quesOption == null ? null : quesOption.trim();
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