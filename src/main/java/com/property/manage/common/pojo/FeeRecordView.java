package com.property.manage.common.pojo;

/**
 * @author 管辉俊
 * @date 2018-06-10
 */
public class FeeRecordView extends FeeRecord {

    private String itemName;

    private String companyName;

    private String unitNumber;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
}