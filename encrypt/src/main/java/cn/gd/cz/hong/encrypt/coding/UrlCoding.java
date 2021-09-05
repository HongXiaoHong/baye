package cn.gd.cz.hong.encrypt.coding;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * URL编码是浏览器发送数据给服务器时使用的编码，它通常附加在URL的参数部分
 * <p>
 * 可参 https://www.liaoxuefeng.com/wiki/1252599548343744/1304227703947297
 */
@Slf4j
public class UrlCoding {

    public static void encode(String urlParam) {
        log.info("urlParam: {}", urlParam);
        try {
            log.info("java.net.URLEncoder : {}", java.net.URLEncoder.encode(urlParam, "UTF8"));
        } catch (UnsupportedEncodingException e) {
            log.error("请检查URL设置编码", e);
        }
        log.info("cn.hutool.core.net.URLEncoder : {}", cn.hutool.core.net.URLEncoder.createAll().encode(urlParam, StandardCharsets.UTF_8));
    }

    public static void decode(String urlParam) {
        log.info("urlParam: {}", urlParam);
        try {
            log.info("java.net.URLDecoder.decode(urlParam, \"UTF8\"): {}", java.net.URLDecoder.decode(urlParam, "UTF8"));
        } catch (UnsupportedEncodingException e) {
            log.error("请检查URL设置编码", e);
        }
        log.info("cn.hutool.core.net.URLDecoder.decode(urlParam, StandardCharsets.UTF_8): {}", cn.hutool.core.net.URLDecoder.decode(urlParam, StandardCharsets.UTF_8));
    }
}
