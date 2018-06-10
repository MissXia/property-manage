package com.property.manage.app.model.po.fee.item;


import com.property.manage.base.model.model.BaseParams;

public class FeeItemUpdParams extends BaseParams {

    private Long itemId;

    private String itemName;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}