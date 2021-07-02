package cn.gd.cz.hong.springjdademo.controller;

import cn.gd.cz.hong.springjdademo.dao.OperatorDao;
import cn.gd.cz.hong.springjdademo.entity.Operator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * hello
 */
@Slf4j
@RestController
@RequestMapping("/operator")
public class HelloController {
    @Autowired
    OperatorDao operatorDao;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @Transactional
    @PostMapping("/add")
    public String addUser(Operator operator) {
        log.info("新增用户:{}", operator);
        operator = operatorDao.save(operator);
        return "新增成功，返回用户id为：" + operator.getId();
    }

    @GetMapping("/find/{id}")
    public Operator findUser(@PathVariable Long id) {
        log.info("查找用户ID:{}", id);
        return operatorDao.findById(id).get();
    }

    @PostMapping("/del/{id}")
    public String delUser(Long id) {
        log.info("删除用户ID:{}", id);
        Operator operator = new Operator();
        operator.setId(id);
        operatorDao.delete(operator);
        return "用户id为：" + id + "，已被删除!";
    }

    @GetMapping("/find/{code}/{name}")
    public List<Operator> findUserByCodeAndName(@PathVariable("code") String code, @PathVariable("name") String name) {
        log.info("命名规则方式，查找用户:编码：{}，名称：{}", code, name);
        return operatorDao.findByCodeAndName(code, name);
    }

    @GetMapping("/find/paging/{code}")
    public Page<Operator> findUserByCodePagin(@PathVariable("code") String code) {
        log.info("分页模式，查找用户:编码：{}", code);
        //这里注意 page是从0开始的
        return operatorDao.findByCode(code, PageRequest.of(0, 10));
    }

    @GetMapping("/find/sql/{code}")
    public List<Operator> findUserByQuerySql(@PathVariable("code") String code) {
        log.info("自定义sql方式，查找用户:编码：{}", code);
        return operatorDao.queryByCode(code);
    }
}
