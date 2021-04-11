package cn.gd.cz.hong.nio.util;

import cn.gd.cz.hong.nio.ChannelTest;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.net.URL;

/**
 * 文件工具类
 */

public class FileUtils {

    public static RandomAccessFile getRandomAccessFile(String filePath) throws FileNotFoundException {
        // 将文件放置到resource/data目录下
        // 通过classloader获取路径
        URL data = ChannelTest.class.getClassLoader().getResource(filePath);
        assert data != null;
        return new RandomAccessFile(data.getPath(), "rw");
    }
}
