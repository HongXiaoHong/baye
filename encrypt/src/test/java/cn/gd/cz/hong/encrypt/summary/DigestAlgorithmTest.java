package cn.gd.cz.hong.encrypt.summary;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class DigestAlgorithmTest {

    @Test
    void md5encode() {
        try {
            DigestAlgorithm.md5encode();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    void ripeMd160Encode() {
        try {
            DigestAlgorithm.ripeMd160Encode();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void hmacMD5Encode() {
        try {
            DigestAlgorithm.hmacMD5Encode();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}