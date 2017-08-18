package com.qx.mstarstoretv.json;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class IdMessage implements Serializable{
    String id;
    String percent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
