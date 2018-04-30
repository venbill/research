package com.yuwb.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A Answer.
 */
@Entity
@Table(name = "answer")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "commit_id")
    private Long commitId;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "option_id")
    private Long optionId;

    @Column(name = "create_time")
    private Date createTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public Answer answerId(Long answerId) {
        this.answerId = answerId;
        return this;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getCommitId() {
        return commitId;
    }

    public Answer commitId(Long commitId) {
        this.commitId = commitId;
        return this;
    }

    public void setCommitId(Long commitId) {
        this.commitId = commitId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Answer questionId(Long questionId) {
        this.questionId = questionId;
        return this;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public Answer optionId(Long optionId) {
        this.optionId = optionId;
        return this;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Answer createTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        Answer answer = (Answer) o;
        if (answer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), answer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Answer{" +
            "id=" + getId() +
            ", answerId=" + getAnswerId() +
            ", commitId=" + getCommitId() +
            ", questionId=" + getQuestionId() +
            ", optionId=" + getOptionId() +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
