package cn.gd.cz.hong.springbootconfigurationprocessordemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 第三方接口配置
 */
@Component
@PropertySource(value = "classpath:static/config/thirdPartyInterfaceProp.properties",
        encoding = "UTF-8")
@ConfigurationProperties(prefix = "third.party.baidu")
public class ThirdPartyInterfaceProp {
    private String url;
    private String token;
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ThirdPartyInterfaceProp{" +
                "url='" + url + '\'' +
                ", token='" + token + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                '}';
    }
}
