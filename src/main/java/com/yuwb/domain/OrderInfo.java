package com.yuwb.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A OrderInfo.
 */
@Entity
@Table(name = "order_info")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "express_type")
    private Integer expressType;

    @Column(name = "express_no")
    private String expressNo;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "create_user")
    private Long createUser;

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

    public OrderInfo orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderInfo status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getExpressType() {
        return expressType;
    }

    public OrderInfo expressType(Integer expressType) {
        this.expressType = expressType;
        return this;
    }

    public void setExpressType(Integer expressType) {
        this.expressType = expressType;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public OrderInfo expressNo(String expressNo) {
        this.expressNo = expressNo;
        return this;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public OrderInfo createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public OrderInfo createUser(Long createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
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
        OrderInfo orderInfo = (OrderInfo) o;
        if (orderInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
            "id=" + getId() +
            ", orderId=" + getOrderId() +
            ", status=" + getStatus() +
            ", expressType=" + getExpressType() +
            ", expressNo='" + getExpressNo() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser=" + getCreateUser() +
            "}";
    }
}
