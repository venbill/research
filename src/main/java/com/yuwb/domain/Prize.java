package com.yuwb.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A Prize.
 */
@Entity
@Table(name = "prize")
public class Prize implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prize_id")
    private Long prizeId;

    @Column(name = "prize_type")
    private Integer prizeType;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "picture")
    private String picture;

    @Column(name = "min_price")
    private Double minPrice;

    @Column(name = "max_price")
    private Double maxPrice;

    @Column(name = "good_price")
    private Double goodPrice;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_user")
    private Long createUser;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_user")
    private Long updateUser;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "total_money")
    private Double totalMoney;

    @Column(name = "total_no")
    private Long totalNo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public Prize prizeId(Long prizeId) {
        this.prizeId = prizeId;
        return this;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public Integer getPrizeType() {
        return prizeType;
    }

    public Prize prizeType(Integer prizeType) {
        this.prizeType = prizeType;
        return this;
    }

    public void setPrizeType(Integer prizeType) {
        this.prizeType = prizeType;
    }

    public String getName() {
        return name;
    }

    public Prize name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Prize description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public Prize picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Prize minPrice(Double minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public Prize maxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getGoodPrice() {
        return goodPrice;
    }

    public Prize goodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
        return this;
    }

    public void setGoodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Prize createTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public Prize createUser(Long createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Prize updateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public Prize updateUser(Long updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Prize startTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Prize endTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public Prize totalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
        return this;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getTotalNo() {
        return totalNo;
    }

    public Prize totalNo(Long totalNo) {
        this.totalNo = totalNo;
        return this;
    }

    public void setTotalNo(Long totalNo) {
        this.totalNo = totalNo;
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
        Prize prize = (Prize) o;
        if (prize.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prize.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Prize{" +
            "id=" + getId() +
            ", prizeId=" + getPrizeId() +
            ", prizeType=" + getPrizeType() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", picture='" + getPicture() + "'" +
            ", minPrice=" + getMinPrice() +
            ", maxPrice=" + getMaxPrice() +
            ", goodPrice=" + getGoodPrice() +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser=" + getCreateUser() +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser=" + getUpdateUser() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", totalMoney=" + getTotalMoney() +
            ", totalNo=" + getTotalNo() +
            "}";
    }
}
