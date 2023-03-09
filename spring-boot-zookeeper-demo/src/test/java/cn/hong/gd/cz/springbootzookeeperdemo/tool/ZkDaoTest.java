package cn.hong.gd.cz.springbootzookeeperdemo.tool;

import cn.hong.gd.cz.springbootzookeeperdemo.dao.ZkDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest()
class ZkDaoTest {
    @Autowired
    ZkDao zkTools;

    @Test
    void exists() {
    }

    @Test
    void testExists() {
    }

    @Test
    void createNode() {
        /*
         * 存在了 会有异常
         * org.apache.zookeeper.KeeperException$NodeExistsException: KeeperErrorCode = NodeExists
         */
        String path = "/abc";
        String data = "abc";
        log.debug("ZookeeperController create node {},{}", path, data);
        zkTools.createNode(path, data);
    }

    @Test
    void updateNode() {
    }

    @Test
    void deleteNode() {
    }

    @Test
    void getChildren() {
    }

    @Test
    void getData() {
    }
}