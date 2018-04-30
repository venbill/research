package com.yuwb.enums;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author: BillYu
 * @Description:
 * @Date: Created in 下午2:46 2018/4/17.
 */
public class EnumUtil {
    public static List<EnumModel> getReceiveTypeList(){
        List<EnumModel> list = new ArrayList<>();
        ReceiveType[] receiveTypes = ReceiveType.values();
        for (ReceiveType rt:receiveTypes) {
            EnumModel em = new EnumModel(rt.getCode(),rt.getName());
            list.add(em);
        }
        return list;
    }
}
