package com.test.spring.aspectj;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "api")
@RestController("/aspect")
public class AspectjController {

    @Autowired
    private AspectServiceImpl aspectServiceImpl;

    @ApiOperation(value = "无参")
    @GetMapping(value = "/nullParam")
    public void nullParam(){
        aspectServiceImpl.nullParam();
    }

    @ApiOperation(value = "一对象参数")
    @GetMapping(value = "/oneParam")
    public void oneParam(){
        User user = new User();
        user.setAge(1);
        user.setName("1");
        aspectServiceImpl.oneParam(user);
    }

    @ApiOperation(value = "多对象参数")
    @GetMapping(value = "/moreParam")
    public void moreParam(){
        User user = new User();
        user.setAge(1);
        user.setName("1");
        //Person person = new Person();
        Person person =null;
        aspectServiceImpl.moreParam(user,person);
    }
}
