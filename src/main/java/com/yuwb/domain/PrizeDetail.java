package com.yuwb.domain;

import java.util.Date;

/**
 * @Author: BillYu
 * @Description:
 * @Date: Created in 下午3:11 2018/4/22.
 */
public class PrizeDetail {
    private String prizeName;
    private Date createTime;


    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
