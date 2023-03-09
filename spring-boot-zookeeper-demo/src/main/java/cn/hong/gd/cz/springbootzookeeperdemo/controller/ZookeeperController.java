package cn.hong.gd.cz.springbootzookeeperdemo.controller;


import cn.hong.gd.cz.springbootzookeeperdemo.dao.ZkDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@Slf4j
public class ZookeeperController {

    @Autowired
    private ZkDao zkTools;

    @GetMapping(value = "createNode")
    public boolean createNode(String path, String data) {
        log.debug("ZookeeperController create node {},{}", path, data);
        return zkTools.createNode(path, data);
    }

}
