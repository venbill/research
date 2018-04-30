package com.yuwb.web.rest.vm;

import com.yuwb.domain.Address;

/**
 * @Author: BillYu
 * @Description:
 * @Date: Created in 下午1:54 2018/4/22.
 */
public class AddressVM extends Address{
    private String openId;
    private Long researchId;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getResearchId() {
        return researchId;
    }

    public void setResearchId(Long researchId) {
        this.researchId = researchId;
    }
}
