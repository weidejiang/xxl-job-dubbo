package com.xxl.job.core.glue;

/**
 * @author weidejiang
 */
public enum TypeEnum {
    Dubbo(0,"dubbo"),
    Spring(1,"spring");
    private int code;
    private String msg;

    TypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
