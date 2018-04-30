package com.yuwb.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.SQLInsert;
import org.mapstruct.Mapper;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * A Research.
 */
@Entity
@Table(name = "research")
public class Research implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "research_id")
    private Long researchId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "picture")
    private String picture;

    @Column(name = "receive")
    private Integer receive;

    @Column(name = "publisher")
    private Long publisher;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "prize_id")
    private Long prizeId;


    /**
     *
     * @Transient是忽略字段和数据库表的映射(但是json序列化会忽略掉，导致前端无法接收此字段)
     * @JsonProperty添加json字段
     *
     *
     *
     * @return
     */
//    @Transient
//    @JsonProperty("questionList")
//    private List<Question> questionList;



    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResearchId() {
        return researchId;
    }

    public Research researchId(Long researchId) {
        this.researchId = researchId;
        return this;
    }

    public void setResearchId(Long researchId) {
        this.researchId = researchId;
    }

    public String getName() {
        return name;
    }

    public Research name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Research description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public Research picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getReceive() {
        return receive;
    }

    public Research receive(Integer receive) {
        this.receive = receive;
        return this;
    }

    public void setReceive(Integer receive) {
        this.receive = receive;
    }

    public Long getPublisher() {
        return publisher;
    }

    public Research publisher(Long publisher) {
        this.publisher = publisher;
        return this;
    }

    public void setPublisher(Long publisher) {
        this.publisher = publisher;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Research createTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Research updateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Research startTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Research endTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public Research prizeId(Long prizeId) {
        this.prizeId = prizeId;
        return this;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


}
