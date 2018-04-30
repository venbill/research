package com.yuwb.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A OrderRecord.
 */
@Entity
@Table(name = "order_record")
public class OrderRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "prize_id")
    private Long prizeId;

    @Column(name = "detail_price")
    private Double detailPrice;

    @Column(name = "research_id")
    private Long researchId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "address_id")
    private Long addressId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderRecord orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public OrderRecord prizeId(Long prizeId) {
        this.prizeId = prizeId;
        return this;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public Double getDetailPrice() {
        return detailPrice;
    }

    public OrderRecord detailPrice(Double detailPrice) {
        this.detailPrice = detailPrice;
        return this;
    }

    public void setDetailPrice(Double detailPrice) {
        this.detailPrice = detailPrice;
    }

    public Long getResearchId() {
        return researchId;
    }

    public OrderRecord researchId(Long researchId) {
        this.researchId = researchId;
        return this;
    }

    public void setResearchId(Long researchId) {
        this.researchId = researchId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OrderRecord createTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public OrderRecord userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public OrderRecord addressId(Long addressId) {
        this.addressId = addressId;
        return this;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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
        OrderRecord orderRecord = (OrderRecord) o;
        if (orderRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderRecord{" +
            "id=" + getId() +
            ", orderId=" + getOrderId() +
            ", prizeId=" + getPrizeId() +
            ", detailPrice=" + getDetailPrice() +
            ", researchId=" + getResearchId() +
            ", createTime='" + getCreateTime() + "'" +
            ", userId=" + getUserId() +
            ", addressId=" + getAddressId() +
            "}";
    }
}
