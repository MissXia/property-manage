package com.property.manage.app.model.po.user;


import com.google.common.collect.Lists;
import com.property.manage.base.model.model.BaseParams;
import com.property.manage.base.model.utils.CastUtils;
import com.property.manage.base.model.utils.ListUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class UserListParams extends BaseParams {

    private Integer userType;

    private String nickName;

    private String phoneNumber;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static void main(String[] a) {
        File file = new File("/Users/guanhuijun/ideaProjects/property-manage/src/main/java/com/property/manage/app/model/po/111.txt");
        try {
            // 读取文件内容 (输入流)
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            List<Integer> ll = Lists.newArrayList();
            int max = 0;
            int min = 0;
            while ((temp = reader.readLine()) != null) {
                Integer val = CastUtils.castToInt(temp);
                if (max == 0 || val > max) {
                    max = val;
                }
                if (min == 0 || val < min) {
                    min = val;
                }
                ll.add(val);
                //System.out.println(temp);
            }
            System.out.println(min);
            System.out.println(max);
            List<List<Integer>> dataList = ListUtils.splitArray(ll, 500);
            for (List<Integer> datas : dataList) {
                String s = StringUtils.join(datas, ",");
                System.out.println("DELETE FROM business_deploy_list_field where id in (" + s + ");");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }

    }
}