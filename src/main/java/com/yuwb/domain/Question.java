package com.yuwb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Employee entity.
 */
@ApiModel(description = "The Employee entity.")
@Entity
@Table(name = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The firstname attribute.
     */
    @ApiModelProperty(value = "The firstname attribute.")
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "research_id")
    private Long researchId;

    @Column(name = "main_content")
    private String mainContent;

    @Column(name = "order_no")
    private Integer orderNo;

    @Column(name = "create_time")
    private Instant createTime;

//    @OneToMany


    @Transient
    private List<SelectOption> optionList;





    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Question questionId(Long questionId) {
        this.questionId = questionId;
        return this;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getResearchId() {
        return researchId;
    }

    public Question researchId(Long researchId) {
        this.researchId = researchId;
        return this;
    }

    public void setResearchId(Long researchId) {
        this.researchId = researchId;
    }

    public String getMainContent() {
        return mainContent;
    }

    public Question mainContent(String mainContent) {
        this.mainContent = mainContent;
        return this;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public Question orderNo(Integer orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public Question createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public List<SelectOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<SelectOption> optionList) {
        this.optionList = optionList;
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
        Question question = (Question) o;
        if (question.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), question.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Question{" +
            "id=" + id +
            ", questionId=" + questionId +
            ", researchId=" + researchId +
            ", mainContent='" + mainContent + '\'' +
            ", orderNo=" + orderNo +
            ", createTime=" + createTime +
            '}';
    }
}
