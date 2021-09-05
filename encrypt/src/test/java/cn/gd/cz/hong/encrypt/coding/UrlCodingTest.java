package cn.gd.cz.hong.encrypt.coding;

import org.junit.jupiter.api.Test;
import org.springframework.beans.propertyeditors.URLEditor;

import static org.junit.jupiter.api.Assertions.*;

class UrlCodingTest {

    @Test
    void encode() {
        UrlCoding.encode("摘要");
        UrlCoding.encode("韩国尊称");
    }

    @Test
    void decode() {
        String urlParam = "%E6%91%98%E8%A6%81"; // 摘要
        UrlCoding.decode(urlParam);
        urlParam = "%E9%9F%A9%E5%9B%BD%E5%B0%8A%E7%A7%B0"; // 韩国尊称
        UrlCoding.decode(urlParam);
    }
}