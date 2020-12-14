package com.mid.zxadapter.controller;

import com.mid.zxadapter.aspect.AdapterRequset;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "api")
@RestController("/api")
public class ApiController {

    @ApiOperation(value = "request测试")
    @AdapterRequset
    @GetMapping(value = "/adapterRequest")
    public Object ApiTest(){
        return null;
    }
}
