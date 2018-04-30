package com.yuwb.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.time.Instant;
import java.util.List;

/**
 * @Author: BillYu
 * @Description:
 * @Date: Created in 上午10:55 2018/4/22.
 */
public class QuestionDetail extends Question {

    private List<SelectOption> optionList;

    public List<SelectOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<SelectOption> optionList) {
        this.optionList = optionList;
    }


    public QuestionDetail(Question question) {
        this.setId(question.getId());
        this.setResearchId(question.getResearchId());
        this.setMainContent(question.getMainContent());
        this.setOrderNo(question.getOrderNo());
        this.setCreateTime(question.getCreateTime());




    }


    public QuestionDetail(Question question,List<SelectOption> optionList) {
        this(question);
        this.optionList = optionList;
    }
}
