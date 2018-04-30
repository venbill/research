package com.yuwb.web.rest.vm;

import java.util.List;

/**
 * @Author: BillYu
 * @Description:
 * @Date: Created in 上午3:21 2018/4/21.
 */
public class AnswersVM {

    private Long researchId;

    private List<String> answerList;
    private String openId;

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    public Long getResearchId() {
        return researchId;
    }

    public void setResearchId(Long researchId) {
        this.researchId = researchId;
    }
}
