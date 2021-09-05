package cn.gd.cz.hong.encrypt.coding;

import cn.hutool.core.util.HexUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import sun.nio.cs.ext.GBK;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * ASCII码、扩展ASCII码
 * 2.1 ASCII码（128个字符码位）
 * 字符集（ASCII字符集）：128个字符。
 * 码位：128个。
 * 编码（ASCII码）：码位的二进制。
 * 解决美国字符编码问题。
 * <p>
 * 2.2 LATIN1（256个字符码位）
 * 遵循ISO-8859-1。
 * 扩展的ASCII码。
 * 在ASCII码的基础上，新增了128个字符，解决欧洲一些国家的字符编码问题
 */
@Slf4j
public class CharacterCoding {

    /**
     * 编码 用各种字符集记录会是一个怎么样的结果呢
     * <p>
     * 结论:
     * GBK是支持 ASCII 但是不兼容 ISO8859-1
     * 如果需要支持更多国际化的符号 我们还是需要使用UTF-8
     */
    public static void encode() {
        String src = "ð_µ";
        logSomething(src);

        src = "中";
        logSomething(src);
    }/* result-->
     src : ð_µ
     ISO_8859_1 getbytes:f05fb5
     US_ASCII getbytes:3f5f3f
     GBK getbytes:3f5f3f
     UTF_8 getbytes:c3b05fc2b5
     src : 中
     ISO_8859_1 getbytes:3f
     US_ASCII getbytes:3f
     GBK getbytes:d6d0
     UTF_8 getbytes:e4b8ad
     *///:~


    /**
     * 记录一个字符在不同字符集的十六进制表现
     *
     * @param src
     */
    private static void logSomething(String src) {
        log.info("src : {}", src);

        log.info("ISO_8859_1 getbytes:{}",
                HexUtil.encodeHexStr(src.getBytes(StandardCharsets.ISO_8859_1)));
        log.info("US_ASCII getbytes:{}",
                HexUtil.encodeHexStr(src.getBytes(StandardCharsets.US_ASCII)));
        log.info("GBK getbytes:{}",
                HexUtil.encodeHexStr(src.getBytes(new GBK())));
        log.info("UTF_8 getbytes:{}",
                HexUtil.encodeHexStr(src.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 编码之间的转换
     * <p>
     * 只要在转换的过程 原字节不会发生变化 还是可以还原出原来的结果的
     */
    public static void conversion() {
        List<String> srcStrings = Arrays.asList("少年", "少µ年", "少");
        Map<Charset, Charset[]> charsetMap = new LinkedHashMap<Charset, Charset[]>() {{
            put(StandardCharsets.UTF_8, new Charset[]{StandardCharsets.UTF_8, new GBK(), StandardCharsets.ISO_8859_1});
            put(new GBK(), new Charset[]{StandardCharsets.UTF_8, new GBK(), StandardCharsets.ISO_8859_1});
            put(StandardCharsets.ISO_8859_1, new Charset[]{StandardCharsets.UTF_8, new GBK(), StandardCharsets.ISO_8859_1});
        }};
        charsetMap.forEach((key, value) -> {
            Arrays.stream(value).forEach(
                    middleCharset -> srcStrings.forEach(e -> translate(e, key, middleCharset)));
        });
    }/*
    src :少年, srcCharset: UTF-8, middleCharset: UTF-8
src bytes:e5b091e5b9b4
after decode : 少年
after middle conversion bytes:e5b091e5b9b4
final String:少年
[equals]--- src :少年, srcCharset: UTF-8, middleCharset: UTF-8
src :少µ年, srcCharset: UTF-8, middleCharset: UTF-8
src bytes:e5b091c2b5e5b9b4
after decode : 少µ年
after middle conversion bytes:e5b091c2b5e5b9b4
final String:少µ年
[equals]--- src :少µ年, srcCharset: UTF-8, middleCharset: UTF-8
src :少, srcCharset: UTF-8, middleCharset: UTF-8
src bytes:e5b091
after decode : 少
after middle conversion bytes:e5b091
final String:少
[equals]--- src :少, srcCharset: UTF-8, middleCharset: UTF-8
src :少年, srcCharset: UTF-8, middleCharset: GBK
src bytes:e5b091e5b9b4
after decode : 灏戝勾
after middle conversion bytes:e5b091e5b9b4
final String:少年
[equals]--- src :少年, srcCharset: UTF-8, middleCharset: GBK
src :少µ年, srcCharset: UTF-8, middleCharset: GBK
src bytes:e5b091c2b5e5b9b4
after decode : 灏懧靛勾
after middle conversion bytes:e5b091c2b5e5b9b4
final String:少µ年
[equals]--- src :少µ年, srcCharset: UTF-8, middleCharset: GBK
src :少, srcCharset: UTF-8, middleCharset: GBK
src bytes:e5b091
after decode : 灏�
after middle conversion bytes:e5b03f
final String:�?
src :少年, srcCharset: UTF-8, middleCharset: ISO-8859-1
src bytes:e5b091e5b9b4
after decode : å°å¹´
after middle conversion bytes:e5b091e5b9b4
final String:少年
[equals]--- src :少年, srcCharset: UTF-8, middleCharset: ISO-8859-1
src :少µ年, srcCharset: UTF-8, middleCharset: ISO-8859-1
src bytes:e5b091c2b5e5b9b4
after decode : å°Âµå¹´
after middle conversion bytes:e5b091c2b5e5b9b4
final String:少µ年
[equals]--- src :少µ年, srcCharset: UTF-8, middleCharset: ISO-8859-1
src :少, srcCharset: UTF-8, middleCharset: ISO-8859-1
src bytes:e5b091
after decode : å°
after middle conversion bytes:e5b091
final String:少
[equals]--- src :少, srcCharset: UTF-8, middleCharset: ISO-8859-1
src :少年, srcCharset: GBK, middleCharset: UTF-8
src bytes:c9d9c4ea
after decode : ����
after middle conversion bytes:efbfbdefbfbdefbfbdefbfbd
final String:锟斤拷锟斤拷
src :少µ年, srcCharset: GBK, middleCharset: UTF-8
src bytes:c9d93fc4ea
after decode : ��?��
after middle conversion bytes:efbfbdefbfbd3fefbfbdefbfbd
final String:锟斤拷?锟斤拷
src :少, srcCharset: GBK, middleCharset: UTF-8
src bytes:c9d9
after decode : ��
after middle conversion bytes:efbfbdefbfbd
final String:锟斤拷
src :少年, srcCharset: GBK, middleCharset: GBK
src bytes:c9d9c4ea
after decode : 少年
after middle conversion bytes:c9d9c4ea
final String:少年
[equals]--- src :少年, srcCharset: GBK, middleCharset: GBK
src :少µ年, srcCharset: GBK, middleCharset: GBK
src bytes:c9d93fc4ea
after decode : 少?年
after middle conversion bytes:c9d93fc4ea
final String:少?年
src :少, srcCharset: GBK, middleCharset: GBK
src bytes:c9d9
after decode : 少
after middle conversion bytes:c9d9
final String:少
[equals]--- src :少, srcCharset: GBK, middleCharset: GBK
src :少年, srcCharset: GBK, middleCharset: ISO-8859-1
src bytes:c9d9c4ea
after decode : ÉÙÄê
after middle conversion bytes:c9d9c4ea
final String:少年
[equals]--- src :少年, srcCharset: GBK, middleCharset: ISO-8859-1
src :少µ年, srcCharset: GBK, middleCharset: ISO-8859-1
src bytes:c9d93fc4ea
after decode : ÉÙ?Äê
after middle conversion bytes:c9d93fc4ea
final String:少?年
src :少, srcCharset: GBK, middleCharset: ISO-8859-1
src bytes:c9d9
after decode : ÉÙ
after middle conversion bytes:c9d9
final String:少
[equals]--- src :少, srcCharset: GBK, middleCharset: ISO-8859-1
src :少年, srcCharset: ISO-8859-1, middleCharset: UTF-8
src bytes:3f3f
after decode : ??
after middle conversion bytes:3f3f
final String:??
src :少µ年, srcCharset: ISO-8859-1, middleCharset: UTF-8
src bytes:3fb53f
after decode : ?�?
after middle conversion bytes:3fefbfbd3f
final String:?ï¿½?
src :少, srcCharset: ISO-8859-1, middleCharset: UTF-8
src bytes:3f
after decode : ?
after middle conversion bytes:3f
final String:?
src :少年, srcCharset: ISO-8859-1, middleCharset: GBK
src bytes:3f3f
after decode : ??
after middle conversion bytes:3f3f
final String:??
src :少µ年, srcCharset: ISO-8859-1, middleCharset: GBK
src bytes:3fb53f
after decode : ?�?
after middle conversion bytes:3f3f3f
final String:???
src :少, srcCharset: ISO-8859-1, middleCharset: GBK
src bytes:3f
after decode : ?
after middle conversion bytes:3f
final String:?
src :少年, srcCharset: ISO-8859-1, middleCharset: ISO-8859-1
src bytes:3f3f
after decode : ??
after middle conversion bytes:3f3f
final String:??
src :少µ年, srcCharset: ISO-8859-1, middleCharset: ISO-8859-1
src bytes:3fb53f
after decode : ?µ?
after middle conversion bytes:3fb53f
final String:?µ?
src :少, srcCharset: ISO-8859-1, middleCharset: ISO-8859-1
src bytes:3f
after decode : ?
after middle conversion bytes:3f
final String:?
Disconnected from the target VM, address: '127.0.0.1:3447', transport: 'socket'

Process finished with exit code 0

    */

    private static void translate(String src, Charset srcCharset, Charset middleCharset) {
        log.info("src :{}, srcCharset: {}, middleCharset: {}",
                src, srcCharset.name(), middleCharset.name());
        byte[] uBytes = src.getBytes(srcCharset);
        log.info("src bytes:{}",
                HexUtil.encodeHexStr(uBytes));
        String gStr = new String(uBytes, middleCharset);
        log.info("after decode : {}", gStr);
        byte[] afterGbkDecodeBytes = gStr.getBytes(middleCharset);
        log.info("after middle conversion bytes:{}", HexUtil.encodeHexStr(afterGbkDecodeBytes));
        String finalStr = new String(afterGbkDecodeBytes, srcCharset);
        log.info("final String:{}", finalStr);

        if (src.equals(finalStr)) {
            log.error("[equals]--- src :{}, srcCharset: {}, middleCharset: {}",
                    src, srcCharset.name(), middleCharset.name());
        }
    }
}
/**
 * new String(new byte[]{0x3f}, "GBK");  结果为 ？
 */