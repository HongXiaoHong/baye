package cn.gd.cz.hong.encrypt.coding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Base64CodingTest {

    @Test
    void encode() {
        Base64Coding.encode();
    }

    @Test
    void decode() {
        Base64Coding.decode();
    }

    @Test
    void encodeNot3MultiBytes() {
        Base64Coding.encodeNot3MultiBytes();
    }
}