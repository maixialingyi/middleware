package com.test.spring.aop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "api")
@RestController("/aop")
public class AopController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "aop测试")
    @GetMapping(value = "/test")
    public void nullParam(){
        userService.add();
    }
}
