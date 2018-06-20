package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23 0023.
 */

public  class SelectProItemBean {
    /**
     * id : 104930
     * title : 三爪圆形中圈
     * price : 0
     * handSize :
     * word :
     * modelPic : [{"pics":"http://appapi1.fanerweb.com/","picm":"http://appapi1.fanerweb.com/","picb":"http://appapi1.fanerweb.com/"}]
     */

    private String id;
    private String title;
    private String price;
    private String handSize;
    private String word;
    private List<ModelPicBean> modelPic;
    private ValueRange stoneWeightRange;

    public ValueRange getStoneWeightRange() {
        return stoneWeightRange;
    }

    public void setStoneWeightRange(ValueRange stoneWeightRange) {
        this.stoneWeightRange = stoneWeightRange;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHandSize() {
        return handSize;
    }

    public void setHandSize(String handSize) {
        this.handSize = handSize;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<ModelPicBean> getModelPic() {
        return modelPic;
    }

    public void setModelPic(List<ModelPicBean> modelPic) {
        this.modelPic = modelPic;
    }


}
