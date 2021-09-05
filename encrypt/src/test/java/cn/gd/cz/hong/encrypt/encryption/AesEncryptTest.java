package cn.gd.cz.hong.encrypt.encryption;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AesEncryptTest {

    @Test
    void ecb() throws Exception {
        AesEncrypt.ecb();
    }

    @Test
    void cbc() throws Exception {
        AesEncrypt.cbc();
    }
}