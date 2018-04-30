package com.yuwb.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A Commit.
 */
@Entity
@Table(name = "commit")
public class Commit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commit_id")
    private Long commitId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "research_id")
    private Long researchId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "order_id")
    private Long orderId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommitId() {
        return commitId;
    }

    public Commit commitId(Long commitId) {
        this.commitId = commitId;
        return this;
    }

    public void setCommitId(Long commitId) {
        this.commitId = commitId;
    }

    public Long getUserId() {
        return userId;
    }

    public Commit userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getResearchId() {
        return researchId;
    }

    public Commit researchId(Long researchId) {
        this.researchId = researchId;
        return this;
    }

    public void setResearchId(Long researchId) {
        this.researchId = researchId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Commit createTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Commit orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
        Commit commit = (Commit) o;
        if (commit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Commit{" +
            "id=" + getId() +
            ", commitId=" + getCommitId() +
            ", userId=" + getUserId() +
            ", researchId=" + getResearchId() +
            ", createTime='" + getCreateTime() + "'" +
            ", orderId=" + getOrderId() +
            "}";
    }
}
