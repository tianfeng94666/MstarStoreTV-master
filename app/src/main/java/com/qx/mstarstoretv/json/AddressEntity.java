package com.qx.mstarstoretv.json;

public class AddressEntity {
    /**
     * phone : 13888888888
     * name : 杨明智
     * id : 7
     * addr : 辽宁省 丹东市 振兴区 内蒙古
     */
    private String phone;
    private String name;
    private String id;
    private String addr;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAddr() {
        return addr;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}