package cn.gd.cz.hong.springbootsmsdemo.service.impl;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.profile.Language;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;

/**
 * 发送短信测试
 * <p>
 * 这里使用的是腾讯云
 * 先注册腾讯云 购买腾讯云服务器
 * <p>
 * 1. 建立用户组跟用户
 * 2. 新建签名 短信模板
 * 3. 导入官方指定的sdk 方便调用
 * <p>
 * 可参见 https://www.bilibili.com/video/BV1c64y1M7qN
 * 不过这个是阿里云的
 */
public class SendSmsInstances {
    private static final String SECRET_ID = "AKIDA9tNaWDckbBUWIn1zg6GDuKTLvEH3Ymi";
    private static final String SECRET_KEY = "nMegRv84k9H8h02mFSO3QXNwEFzSdXBY";

    public static void main(String[] args) {
        try {
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            Credential cred = new Credential(SECRET_ID, SECRET_KEY);

            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            //  从3.1.16版本开始, 单独设置 HTTP 代理
            // httpProfile.setProxyHost("真实代理ip");
            // httpProfile.setProxyPort(真实代理端口);
            httpProfile.setReqMethod("POST"); // get请求(默认为post请求)
            httpProfile.setConnTimeout(30); // 请求连接超时时间，单位为秒(默认60秒)
            httpProfile.setWriteTimeout(30);  // 设置写入超时时间，单位为秒(默认0秒)
            httpProfile.setReadTimeout(30);  // 设置读取超时时间，单位为秒(默认0秒)
            httpProfile.setEndpoint("sms.ap-guangzhou.tencentcloudapi.com"); // 指定接入地域域名(默认就近接入)

            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256"); // 指定签名算法(默认为HmacSHA256)
            // 自3.1.80版本开始，SDK 支持打印日志。
            clientProfile.setHttpProfile(httpProfile);
            clientProfile.setDebug(true);
            // 从3.1.16版本开始，支持设置公共参数 Language, 默认不传，选择(ZH_CN or EN_US)
            clientProfile.setLanguage(Language.EN_US);
            // 实例化要请求产品(以cvm为例)的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);

            // 实例化一个cvm实例信息查询请求对象,每个接口都会对应一个request对象。
            SendSmsRequest req = new SendSmsRequest();
            req.setSmsSdkAppId("1400628596");
            req.setTemplateId("1296563");
            req.setSignName("信院就业部落");
            req.setPhoneNumberSet(new String[]{"13435550480"});
            req.setTemplateParamSet(new String[]{"666666"});
            // 通过client对象调用DescribeInstances方法发起请求。注意请求方法名与请求对象是对应的
            // 返回的resp是一个DescribeInstancesResponse类的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);

            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}
