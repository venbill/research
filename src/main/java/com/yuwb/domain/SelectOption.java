package com.yuwb.domain;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A SelectOption.
 */
@Entity
@Table(name = "select_option")
public class SelectOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "option_id")
    private Long optionId;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "content")
    private String content;

    @Column(name = "is_answer")
    private Integer isAnswer;

    @Column(name = "order_no")
    private Integer orderNo;






    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOptionId() {
        return optionId;
    }

    public SelectOption optionId(Long optionId) {
        this.optionId = optionId;
        return this;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public SelectOption questionId(Long questionId) {
        this.questionId = questionId;
        return this;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public SelectOption content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsAnswer() {
        return isAnswer;
    }

    public SelectOption isAnswer(Integer isAnswer) {
        this.isAnswer = isAnswer;
        return this;
    }

    public void setIsAnswer(Integer isAnswer) {
        this.isAnswer = isAnswer;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public SelectOption orderNo(Integer orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SelectOption selectOption = (SelectOption) o;
        if (selectOption.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), selectOption.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SelectOption{" +
            "id=" + getId() +
            ", optionId=" + getOptionId() +
            ", questionId=" + getQuestionId() +
            ", content='" + getContent() + "'" +
            ", isAnswer=" + getIsAnswer() +
            ", orderNo=" + getOrderNo() +
            "}";
    }
}
