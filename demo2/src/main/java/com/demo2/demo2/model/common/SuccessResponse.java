package com.demo2.demo2.model.common;

import java.util.Date;

import com.demo2.demo2.constant.ResultCode;
import com.demo2.demo2.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

public class SuccessResponse<T> {
	private String code;
    private String desc;
    private String time;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public SuccessResponse(){
        this.code = ResultCode.SUC_CODE_200;
        this.desc = ResultCode.SUC_DESC_200;
        this.time = DateUtil.responseDateTime(new Date());
    }

    public SuccessResponse(T data){
        this.code = ResultCode.SUC_CODE_200;
        this.desc = ResultCode.SUC_DESC_200;
        this.time = DateUtil.responseDateTime(new Date());
        this.data = data;
    }

    public SuccessResponse(String code, String desc, T data){
        this.code = code;
        this.desc = desc;
        this.time = DateUtil.responseDateTime(new Date());
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
