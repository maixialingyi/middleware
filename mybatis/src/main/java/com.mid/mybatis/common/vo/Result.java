package com.mid.project.common.vo;

import com.jsy.service.common.exception.BizCode;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Result<T> implements Serializable {

    private String code;
    private String message;
    private T data;

    //系统级无返回值成功
    public static <T> Result success() {
        Result re = new Result();
        re.setCode(BizCode.SUCCESS.getCode());
        re.setMessage(BizCode.SUCCESS.getMessage());
        re.setData(null);
        return re;
    }
    //系统级有返回值成功
    public static <T> Result success(T data) {
        Result re = new Result();
        re.setCode(BizCode.SUCCESS.getCode());
        re.setMessage(BizCode.SUCCESS.getMessage());
        re.setData(data);
        return re;
    }

    //默认系统错误返回
    public static <T> Result fail() {
        Result re = new Result();
        re.setCode(BizCode.ERROR.getCode());
        re.setMessage(BizCode.ERROR.getMessage());
        re.setData(null);
        return re;
    }

    public static <T> Result fail(String code, String message) {
        Result re = new Result();
        re.setCode(code);
        re.setMessage(message);
        re.setData(null);
        return re;
    }

    public static <T> Result fail(String code, String message, T data) {
        Result re = new Result();
        re.setCode(code);
        re.setMessage(message);
        re.setData(data);
        return re;
    }

}
