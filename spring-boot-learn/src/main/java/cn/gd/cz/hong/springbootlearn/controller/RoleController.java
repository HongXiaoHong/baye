package cn.gd.cz.hong.springbootlearn.controller;


import cn.gd.cz.hong.springbootlearn.entity.Role;
import cn.gd.cz.hong.springbootlearn.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 角色控制
 * </p>
 *
 * @author 洪晓鸿
 * @since 2021-05-29
 */
@Api
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("通过roleId或者roleName获取角色")
    @GetMapping("getRole")
    public Map<String, Object> getRole(@Validated Role condition) {
        Map<String, Object> result = new HashMap<String, Object>() {{
            put("code", "200");
            put("message", "success");
        }};
        Role role = roleService.getById(condition.getRoleId());
        result.put("body", role);
        return result;
    }

    @ApiOperation("通过roleId或者roleName获取角色条数")
    @PostMapping("getRoleCount")
    public Map<String, Object> getRoleCount(@Validated @RequestBody Role condition) {
        Map<String, Object> result = new HashMap<String, Object>() {{
            put("code", "200");
            put("message", "success");
        }};
        // QueryWrapper 可以
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("role_name", condition.getRoleName());
        int count = roleService.count(queryWrapper);
        result.put("count", count);
        return result;
    }
}

