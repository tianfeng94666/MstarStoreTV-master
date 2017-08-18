package com.qx.mstarstoretv.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */

public class StoneDetail implements Serializable{
    List<ModelDetailResult.DataEntity.StoneTypeEntity> stoneTypeItme; //类型
    List<ModelDetailResult.DataEntity.StoneColorEntity> stoneColorItme;  //颜色
    List<ModelDetailResult.DataEntity.StonePurityEntity> stonePurityItme; //净度
    List<ModelDetailResult.DataEntity.StoneSpecEntity> stoneSpecItme;  //规格
    List<ModelDetailResult.DataEntity.StoneShapeEntity> stoneShapeItem;  //形状

    public StoneDetail(List<ModelDetailResult.DataEntity.StoneTypeEntity> stoneTypeItme, List<ModelDetailResult.DataEntity.StoneColorEntity> stoneColorItme, List<ModelDetailResult.DataEntity.StonePurityEntity> stonePurityItme, List<ModelDetailResult.DataEntity.StoneSpecEntity> stoneSpecItme, List<ModelDetailResult.DataEntity.StoneShapeEntity> stoneShapeItem) {
        this.stoneTypeItme = stoneTypeItme;
        this.stoneColorItme = stoneColorItme;
        this.stonePurityItme = stonePurityItme;
        this.stoneSpecItme = stoneSpecItme;
        this.stoneShapeItem = stoneShapeItem;
    }

    public List<ModelDetailResult.DataEntity.StoneTypeEntity> getStoneTypeItme() {
        return stoneTypeItme;
    }

    public void setStoneTypeItme(List<ModelDetailResult.DataEntity.StoneTypeEntity> stoneTypeItme) {
        this.stoneTypeItme = stoneTypeItme;
    }

    public List<ModelDetailResult.DataEntity.StoneColorEntity> getStoneColorItme() {
        return stoneColorItme;
    }

    public void setStoneColorItme(List<ModelDetailResult.DataEntity.StoneColorEntity> stoneColorItme) {
        this.stoneColorItme = stoneColorItme;
    }

    public List<ModelDetailResult.DataEntity.StonePurityEntity> getStonePurityItme() {
        return stonePurityItme;
    }

    public void setStonePurityItme(List<ModelDetailResult.DataEntity.StonePurityEntity> stonePurityItme) {
        this.stonePurityItme = stonePurityItme;
    }

    public List<ModelDetailResult.DataEntity.StoneSpecEntity> getStoneSpecItme() {
        return stoneSpecItme;
    }

    public void setStoneSpecItme(List<ModelDetailResult.DataEntity.StoneSpecEntity> stoneSpecItme) {
        this.stoneSpecItme = stoneSpecItme;
    }

    public List<ModelDetailResult.DataEntity.StoneShapeEntity> getStoneShapeItem() {
        return stoneShapeItem;
    }

    public void setStoneShapeItem(List<ModelDetailResult.DataEntity.StoneShapeEntity> stoneShapeItem) {
        this.stoneShapeItem = stoneShapeItem;
    }
}
