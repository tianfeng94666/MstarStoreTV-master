package com.qx.mstarstoretv.json;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class ALipayResult {

    /**
     * response :
     * error : 0
     * message :
     * data : app_id=2017051107199200&biz_content={"body":"我是测试数据","subject":"App支付测试DoNet","total_amount":"0.01","product_code":"QUICK_MSECURITY_PAY","out_trade_no":"330548174291981010","timeout_express":"120m"}&charset=utf-8&format=json&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2017-05-15 14:47:08&version=1.0&sign=C3AkgRxY25h2grbPa6m4F9rZWTW1VKQw4Ys1BLzqJiHtYGkir52aC5sgsWFXIfcuVvT7rZxFIHF7IFn1gNIhiiZ+TquYCzLg+E8wowTruRHM4Dbk0veEuBQMG7Ek+nkhfVhtaR5ocWWVt50tSNXo8PyLZG8b8cnnvqQ7t6m2YPZ8c8XqG+6b+T9YG0k7HshwBFvf4Ms9jK0qFiEEQd0GPQn4UJKqXFyb4eU9Qc2nKii5o5n1Ra1FfoHbAAOQ2oGzFH3UTdet7nPQH52PBkkjxHCt4vKdoIGUyeZ4QOheJOMyOP9xFwfkW/O10fItGRMXVV6t870TKlmFiXgG7SVjoQ==
     */

    private String response;
    private int error;
    private String message;
    private String data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
