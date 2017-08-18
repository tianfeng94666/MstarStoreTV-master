package com.qx.mstarstoretv.json;

import org.xml.sax.AttributeList;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/20.
 */
public class TypeFiler {
    /**
     * id : 2
     * pic : http://192.168.1.240:9112/Uploads/Pics/2016-09-21/dddd0.jpg
     */
    private String id;
    private String title;
    private String groupKey;
    public String value;
    public boolean isCheck;
    public String name;
    private int mulSelect;
    private String sign;
    private ArrayList<Attribute> attributeList;

    public int getMulSelect() {
        return mulSelect;
    }

    public void setMulSelect(int mulSelect) {
        this.mulSelect = mulSelect;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public ArrayList<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(ArrayList<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public TypeFiler() {
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeFiler(String name, String value, boolean isCheck) {
        this.value = value;
        this.isCheck = isCheck;
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TypeFiler{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", tag='" + groupKey + '\'' +
                ", value='" + value + '\'' +
                ", isCheck=" + isCheck +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeFiler)) return false;

        TypeFiler typeFiler = (TypeFiler) o;

        if (!getId().equals(typeFiler.getId())) return false;
        return getName().equals(typeFiler.getName());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
