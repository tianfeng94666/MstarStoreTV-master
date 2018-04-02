package com.qx.mstarstoretv.bean;

import com.qx.mstarstoretv.utils.StringUtils;

import java.io.Serializable;

public class Type implements Serializable {
    String id;
    String typeName;
    String content;
    private String title;
    private String groupKey;
    public String value;
    int type;

    public String getTitle() {
        return StringUtils.isEmpty(title)?typeName:title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private boolean isSelect = false;

    public Type() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return StringUtils.isEmpty(typeName)?title:typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
    }