package com.qx.mstarstoretv.inter;

import com.qx.mstarstoretv.json.OrderListResult;

import java.util.Map;

public interface AdapterCallBack {

    void changeState(Map<String, OrderListResult.DataEntity.CurrentOrderlListEntity.ListEntity> checked);

}
