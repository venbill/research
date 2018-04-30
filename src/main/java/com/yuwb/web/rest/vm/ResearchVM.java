package com.yuwb.web.rest.vm;

/**
 * @Author: BillYu
 * @Description:
 * @Date: Created in 下午9:46 2018/4/20.
 */
public class ResearchVM {
    private String name;
    private String openId;

    private Long researchId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "ResearchVM{" +
            "name='" + name + '\'' +
            ", openId='" + openId + '\'' +
            ", researchId=" + researchId +
            '}';
    }
}
