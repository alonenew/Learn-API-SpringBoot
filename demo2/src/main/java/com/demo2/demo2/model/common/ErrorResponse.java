package com.demo2.demo2.model.common;

import java.util.Date;

import com.demo2.demo2.constant.ResultCode;
import com.demo2.demo2.util.DateUtil;

public class ErrorResponse {
    private String code;
    private String desc;
    private String time;

    public ErrorResponse(){
        this.code = ResultCode.ERR_CODE_500;
        this.desc = ResultCode.ERR_CODE_500;
        this.time = DateUtil.responseDateTime(new Date());
    }

    public ErrorResponse(String code, String desc){
        this.code = code;
        this.desc = desc;
        this.time = DateUtil.responseDateTime(new Date());
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
}
