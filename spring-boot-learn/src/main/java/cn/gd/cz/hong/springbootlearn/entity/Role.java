package cn.gd.cz.hong.springbootlearn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author 洪晓鸿
 * @since 2021-05-29
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 1761412061975694085L;
    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    @NotNull
    private Integer roleId;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名", dataType = "String", name = "roleName", example = "超级管理员")
    @NotBlank
    private String roleName;
    /*
     * -----------------------------------
     *
     * 全局异常处理
     * 请求报文 输出报文打印
     * -----------------------------------
     * */

    public Role() {
    }

    public Role(@NotNull Integer roleId, @NotBlank String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName=" + roleName +
                "}";
    }
}
