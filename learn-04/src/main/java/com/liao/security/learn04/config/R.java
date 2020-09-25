package com.liao.security.learn04.config;

import lombok.Data;


/**
 * @author liao
 */
@Data
public class R {

    private R() {
    }

    private int code;
    private String message;
    private Object data;

    public static R success() {
        R r = new R();
        r.setCode(200);
        r.setMessage("成功");
        return r;
    }

    public R data(Object data) {
        this.setData(data);
        return this;
    }

    public static R error() {
        R r = new R();
        r.setCode(500);
        r.setMessage("失败");
        return r;
    }


}
