package com.qx.mstarstoretv.bean;

public class ModelListEntity {
    /**
     * id : 21
     * pic : http://appapi.fanerweb.com/Uploads/Pics/2016-09-21/577dd3b0N5baaf195.jpg
     * title : 18K金气质锁骨链群镶钻石链牌 弯弯的月亮女款K金项链吊坠 18K玫瑰金
     */
    private String id;
    private String pic;
    private String title;
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getPic() {
        return pic;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "ModelListEntity{" +
                "id='" + id + '\'' +
                ", pic='" + pic + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}