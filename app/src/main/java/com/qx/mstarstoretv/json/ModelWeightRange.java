package com.qx.mstarstoretv.json;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class ModelWeightRange implements Serializable {
    String key ;
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
