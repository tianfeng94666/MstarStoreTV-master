package com.qx.mstarstoretv.json;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public class CityResult {

    /**
     * data : [{"name":"石家庄市","id":"73"},{"name":"唐山市","id":"74"},{"name":"秦皇岛市","id":"75"},{"name":"邯郸市","id":"76"},{"name":"邢台市","id":"77"},{"name":"保定市","id":"78"},{"name":"张家口市","id":"79"},{"name":"承德市","id":"80"},{"name":"衡水市","id":"81"},{"name":"廊坊市","id":"82"},{"name":"沧州市","id":"83"}]
     * response :
     * error : 0
     * message :
     */
    private List<ProvinceListEntity> data;
    private String response;
    private int error;
    private String message;

    public void setData(List<ProvinceListEntity> data) {
        this.data = data;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setError(int error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProvinceListEntity> getData() {
        return data;
    }

    public String getResponse() {
        return response;
    }

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
