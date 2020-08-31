package com.mid.project.controller;

import com.alibaba.fastjson.JSON;

import com.mid.project.common.dto.ExceptionDTO;
import com.mid.project.common.exception.BizCode;
import com.mid.project.common.exception.UserBizException;
import com.mid.project.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@Slf4j
@Api(tags = "全局异常测试")
@RestController("/exception")
public class ExceptionController {

    @ApiOperation(value = "测试全局异常")
    @GetMapping(value = "/test1")
    public Result<BigDecimal> testException() {
        int i = 1/0;
        return Result.success(1);
    }

    @ApiOperation(value = "测试自定义异常")
    @GetMapping(value = "/test2")
    public Result<BigDecimal> testTemplateException() throws UserBizException {
        if(1==1){
            throw new UserBizException(BizCode.USER_DOES_NOT_EXIST,BizCode.USER_DOES_NOT_EXIST.getMessage());
        }
        return Result.success(1);
    }

    @ApiOperation(value = "异常传递")
    @GetMapping(value = "/test3")
    public Result<BigDecimal> testExceptiontransfer() throws Exception {
        if(1==1){
            try {
                throw new UserBizException(BizCode.USER_DOES_NOT_EXIST,BizCode.USER_DOES_NOT_EXIST.getMessage());
            } catch (UserBizException e) {
                throw new Exception(e);
            }
        }
        return Result.success(1);
    }

    @ApiOperation(value = "post @RequestBody   Content-Type = application/json json字符串 这个参数校验异常处理")
    @PostMapping(value = "/test4")
    public Result<BigDecimal> testApplicationJsonException(@Valid @RequestBody ExceptionDTO dto) {
        log.info("testApplicationJsonException  --> {}", JSON.toJSONString(dto));
        return Result.success(1);
    }

    @ApiOperation(value = "post 不加@RequestBody  application/x-www-form-urlencoded json对象 参数校验异常处理")
    @PostMapping(value = "/test5")
    public Result<BigDecimal> testApplicationWwwException(@Valid @RequestBody ExceptionDTO dto) {
        log.info("testApplicationJsonException  --> {}", JSON.toJSONString(dto));
        return Result.success(1);
    }

}
