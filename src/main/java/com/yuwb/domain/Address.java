package com.yuwb.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A Address.
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "tel")
    private String tel;

    @Column(name = "address")
    private String address;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "person_name")
    private String personName;



    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressId() {
        return addressId;
    }

    public Address addressId(Long addressId) {
        this.addressId = addressId;
        return this;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getTel() {
        return tel;
    }

    public Address tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public Address address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Address createTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Address updateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public Address userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        Address address = (Address) o;
        if (address.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Address{" +
            "id=" + getId() +
            ", addressId=" + getAddressId() +
            ", tel='" + getTel() + "'" +
            ", address='" + getAddress() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
