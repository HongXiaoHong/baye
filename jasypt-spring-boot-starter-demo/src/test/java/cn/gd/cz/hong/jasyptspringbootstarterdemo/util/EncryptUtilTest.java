package cn.gd.cz.hong.jasyptspringbootstarterdemo.util;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Test;

/**
 * 生成密文的工具代码
 */
public class EncryptUtilTest {

    @Test
    public void test() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt
        textEncryptor.setPassword("salt_hong");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("123");
        System.out.println("username:" + username);
        System.out.println("password:" + password);
    }
}
