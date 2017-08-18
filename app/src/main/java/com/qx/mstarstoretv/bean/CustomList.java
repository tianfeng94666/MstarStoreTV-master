package com.qx.mstarstoretv.bean;

public class CustomList {
    /**
     * id : 7
     * title : 女戒
     * value : 7
     * groupKey : category
     */
    private String id;
    private String title;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "CustomList{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
