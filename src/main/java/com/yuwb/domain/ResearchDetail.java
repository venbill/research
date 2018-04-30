package com.yuwb.domain;

import com.sun.org.apache.regexp.internal.RE;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;
import java.util.List;

/**
 * @Author: BillYu
 * @Description:
 * @Date: Created in 下午10:27 2018/4/20.
 */
public class ResearchDetail extends Research {


    private Boolean answered;



    private Long answerNo;

    private Boolean received;

    private Long orderId;


    public Boolean getAnswered() {
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public Long getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(Long answerNo) {
        this.answerNo = answerNo;
    }


    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public ResearchDetail(Research research) {

        this.setId(research.getId());

        this.setResearchId(research.getResearchId());

        this.setName(research.getName());

        this.setDescription(research.getDescription());

        this.setPicture(research.getPicture());

        this.setReceive(research.getReceive());



        this.setPublisher(research.getPublisher());

        this.setCreateTime(research.getCreateTime());

        this.updateTime(research.getUpdateTime());

        this.setStartTime(research.getStartTime());

        this.setEndTime(research.getEndTime());

        this.setPrizeId(research.getPrizeId());
        this.setQuestionList(research.getQuestionList());


    }

    public ResearchDetail(Research research,Boolean answered, Long answerNo) {
        this(research);
        this.answered = answered;
        this.answerNo = answerNo;
    }
    public ResearchDetail(Research research,Long orderId) {

        this(research);
        this.orderId = orderId;
        if(orderId!=null&&orderId>0){

            this.received = true;
        }else{
            this.received = false;
        }
    }

}
