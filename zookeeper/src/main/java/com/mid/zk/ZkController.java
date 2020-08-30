package com.mid.zk;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "zk客户端测试")
@RestController
public class ZkController {

    @Autowired
    private ZkApi zkApi;

    @ApiOperation(value = "判断节点是否存在")
    @GetMapping("/existsTest")
    public Stat existsTest(String path, Boolean bool){
        Stat stat = zkApi.exists(path,bool);
        return stat;
    }

    @ApiOperation(value = "检测结点是否存在 并设置监听事件")
    @GetMapping("/existsOrSetWatchTest")
    public Stat existsOrSetWatchTest(String path){
        Watcher watcher = new WatcherApi();
        Stat stat = zkApi.exists(path,watcher);
        return stat;
    }

    @ApiOperation(value = "创建持久化节点")
    @GetMapping("/createNode")
    public Boolean createNode(String path, String data){
        return zkApi.createNode(path,data);
    }

    @ApiOperation(value = "删除持久化节点")
    @GetMapping("/deleteNode")
    public Boolean deleteNode(String path){
        return zkApi.deleteNode(path);
    }
}
