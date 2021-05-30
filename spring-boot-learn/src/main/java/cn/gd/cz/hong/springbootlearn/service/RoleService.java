package cn.gd.cz.hong.springbootlearn.service;

import cn.gd.cz.hong.springbootlearn.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 洪晓鸿
 * @since 2021-05-29
 */
public interface RoleService extends IService<Role> {

    List<Role> findAll();
}
