package cn.gd.cz.hong.springbootlearn.service.impl;

import cn.gd.cz.hong.springbootlearn.entity.Role;
import cn.gd.cz.hong.springbootlearn.mapper.RoleMapper;
import cn.gd.cz.hong.springbootlearn.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 洪晓鸿
 * @since 2021-05-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
