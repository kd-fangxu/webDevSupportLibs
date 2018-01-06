package com.xqSupport.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by xu on 2017/6/25.
 */
public class BaseResponse {
    Object data;
    private Integer succeed;
    private String msg;
    private Long timeStamp;

    public static BaseResponse create(Integer succeed, String msg, Object data) {

        BaseResponse response = new BaseResponse();
        response.setSucceed(succeed);
        response.setMsg(msg);
        response.setData(data);
        return response;

    }

    public Long getTimeStamp() {
        timeStamp = System.currentTimeMillis();
        return timeStamp;
    }

    public Integer getSucceed() {
        return succeed;
    }

    public void setSucceed(Integer succeed) {
        this.succeed = succeed;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        if (data == null) {
            return "";
        }
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setFailed() {
        setSucceed(0);
        setMsg("数据请求失败");
        setData("");
    }

    public String toJsonString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return toString();
    }

    public void showLog() {
        System.out.println(this.toJsonString());
    }
}
