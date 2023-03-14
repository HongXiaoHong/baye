package cn.gd.cz.hong.demo.springboot.shiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class UserRealm extends AuthorizingRealm {
    //执行授权逻辑
    @Override
    protected AuthorizationInfo
    doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了=>授权逻辑PrincipalCollection");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//添加资源的授权字符串
        info.addStringPermission("user:add");
        return info;
    }

    //执行认证逻辑
    @Override
    protected AuthenticationInfo
    doGetAuthenticationInfo(AuthenticationToken token) throws
            AuthenticationException {
        System.out.println("执行了=>认证逻辑AuthenticationToken");
//假设数据库的用户名和密码
        String name = "root";
        String password = "123456";
//1.判断用户名
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        if (!userToken.getUsername().equals(name)) {
//用户名不存在
            return null; //shiro底层就会抛出 UnknownAccountException

        }

        Map<String, String> user = new HashMap<>();
        user.put("name", name);
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("loginUser", user);
//2. 验证密码,我们可以使用一个AuthenticationInfo实现类 SimpleAuthenticationInfo
// shiro会自动帮我们验证！重点是第二个参数就是要验证的密码！
        return new SimpleAuthenticationInfo("", password, "");


    }
}
