package com.yuwb.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A WeUser.
 */
@Entity
@Table(name = "we_user")
public class WeUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "nick")
    private String nick;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "tel")
    private String tel;

    @Column(name = "photo")
    private String photo;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public WeUser openId(String openId) {
        this.openId = openId;
        return this;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNick() {
        return nick;
    }

    public WeUser nick(String nick) {
        this.nick = nick;
        return this;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public WeUser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public WeUser sex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public WeUser tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhoto() {
        return photo;
    }

    public WeUser photo(String photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public WeUser createTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public WeUser status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        WeUser weUser = (WeUser) o;
        if (weUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), weUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WeUser{" +
            "id=" + getId() +
            ", openId=" + getOpenId() +
            ", nick='" + getNick() + "'" +
            ", name='" + getName() + "'" +
            ", sex=" + getSex() +
            ", tel='" + getTel() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
