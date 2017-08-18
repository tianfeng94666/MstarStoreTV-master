package com.qx.mstarstoretv.bean;

import com.qx.mstarstoretv.json.AddressEntity;
import com.qx.mstarstoretv.json.CustomerEntity;
import com.qx.mstarstoretv.json.ModelWeightRange;
import com.qx.mstarstoretv.json.StoneSearchInfoResult;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class Ring {
    private String itemId;
    private String handSize;
    private String remarks;
    private String imageUrl;
    private String totalPrice;
    private String ringNumber;//戒托编号
    private String ringPurity;
    private String ringPurityId;
    private String ringPrice;
    private String number;
    private String word;
    private CustomerEntity customerEntity;
    ModelWeightRange ringWeightRange;

    public ModelWeightRange getRingWeightRange() {
        return ringWeightRange;
    }

    public void setRingWeightRange(ModelWeightRange ringWeightRange) {
        this.ringWeightRange = ringWeightRange;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private AddressEntity addressEntity;

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public String getRingPrice() {
        return ringPrice;
    }

    public void setRingPrice(String ringPrice) {
        this.ringPrice = ringPrice;
    }

    public String getRingPurityId() {
        return ringPurityId;
    }

    public void setRingPurityId(String ringPurityId) {
        this.ringPurityId = ringPurityId;
    }

    public String getRingPurity() {
        return ringPurity;
    }

    public void setRingPurity(String ringPurity) {
        this.ringPurity = ringPurity;
    }

    public String getRingNumber() {
        return ringNumber;
    }

    public void setRingNumber(String ringNumber) {
        this.ringNumber = ringNumber;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    StoneSearchInfoResult.DataBean.StoneBean.ListBean selectedStone;

    public String getStoneDetail() {
        return selectedStone == null ? "" : changeSelectedEnityToString();
    }

    public StoneSearchInfoResult.DataBean.StoneBean.ListBean getStoneEntity() {
        return selectedStone;
    }

    public void setStoneEntity(StoneSearchInfoResult.DataBean.StoneBean.ListBean stoneEntity) {
        this.selectedStone = stoneEntity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getHandSize() {
        return handSize;
    }

    public void setHandSize(String handSize) {
        this.handSize = handSize;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    private String changeSelectedEnityToString() {
        return "规格：" + selectedStone.getWeight() + ";形状：" + selectedStone.getShape() + ";颜色：" + selectedStone.getColor() + ";净度：" + selectedStone.getPurity() + ";证书编号：" + selectedStone.getCertCode();
    }
}
