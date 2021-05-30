package cn.gd.cz.hong.springbootlearn.service.impl;

import cn.gd.cz.hong.springbootlearn.entity.Role;
import cn.gd.cz.hong.springbootlearn.mapper.RoleMapper;
import cn.gd.cz.hong.springbootlearn.service.RoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 查询角色服务实现类
 * </p>
 *
 * @author 洪晓鸿
 * @since 2021-05-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    @Cacheable("role_list")
    public List<Role> findAll() {
        return baseMapper.selectList(Wrappers.emptyWrapper());
    }
}
