package cn.gd.cz.hong.encrypt.coding;

import java.util.Arrays;
import java.util.Base64;

/**
 * base64编码测试
 */
public class Base64Coding {

    public static void encode() {
        byte[] input = new byte[]{(byte) 0xe4, (byte) 0xb8, (byte) 0xad};
        String b64encoded = Base64.getEncoder().encodeToString(input);
        System.out.println(b64encoded); // 5Lit
    }

    public static void encodeNot3MultiBytes() {
        byte[] input = new byte[]{(byte) 0xe4, (byte) 0xb8, (byte) 0xad, 0x21};
        String b64encoded = Base64.getEncoder().encodeToString(input);
        String b64encoded2 = Base64.getEncoder().withoutPadding().encodeToString(input); // 编码的时候不补充 0x00
        System.out.println(b64encoded); // 5LitIQ==
        System.out.println(b64encoded2); // 5LitIQ
        byte[] output = Base64.getDecoder().decode(b64encoded2); // 没有补充0x00也可以解出来
        System.out.println(Arrays.toString(output)); // [-28, -72, -83, 33]
    }


    public static void decode() {
        byte[] output = Base64.getDecoder().decode("5Lit");
        System.out.println(Arrays.toString(output)); // [-28, -72, -83]
    }
}
