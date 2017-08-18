package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2016/10/20.
 */
public class SearchValue {

    private String name;
    private String txt;
    private String value;
    private String groupkey;
    private String low;
    private String hig;
private String typename;

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getGroupkey() {
        return groupkey;
    }

    public void setGroupkey(String groupkey) {
        this.groupkey = groupkey;
    }

    public String getHig() {
        return hig;
    }

    public void setHig(String hig) {
        this.hig = hig;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "SearchKeyword{" +
                "name='" + name + '\'' +
                ", txt='" + txt + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
