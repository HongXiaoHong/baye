package cn.gd.cz.hong.encrypt.summary;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * hmac 帮助类
 * HMAC 算法实例mac在多线程环境下是不安全的。需要在多线程访问时，使用ThreadLocal为每个线程缓存一个实例可解决
 */
@Slf4j
public class HMacHelper {
    /**
     * MAC算法可选以下多种算法
     * HmacMD5/HmacSHA1/HmacSHA256/HmacSHA384/HmacSHA512
     */
    private static final String KEY_MAC = "HmacMD5";
    private Mac mac;

    /**
     * 构造秘钥 获取摘要算法实例
     */
    public HMacHelper(String key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), KEY_MAC);
            mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.info("init HMacHelper failed", e);
        }
    }

    /**
     * 获取签名
     */
    public byte[] sign(byte[] content) {
        return mac.doFinal(content);
    }

    /**
     * 验证签名是否正确
     */
    public boolean verify(byte[] signature, byte[] content) {
        byte[] result = mac.doFinal(content);
        return Arrays.equals(signature, result);
    }
}

