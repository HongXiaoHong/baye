package cn.gd.cz.hong.encrypt.encryption;

import org.junit.jupiter.api.Test;

import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.*;

class RsaEncryptTest {

    @Test
    void sha1withRSA() {
        try {
            RsaEncrypt.sha1withRSA();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}