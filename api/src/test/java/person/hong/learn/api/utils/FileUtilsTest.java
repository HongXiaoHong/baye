package person.hong.learn.api.utils;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;

/**
 * @description:
 * @author: 洪晓鸿
 * @time: 2020/8/30 17:50
 */

public class FileUtilsTest {

    @Test
    public void getFilePaths() {
        FileUtils.getFilePaths();
    }

    @Test
    public void fileEncodingTest() {
        String path = "D:/temp/test/gbk.txt";
        try {
            String gbk = FileUtils.readFileWithEncoding(path, "GBK");
            System.out.println("gbk : " + gbk);
            path = "D:/temp/test/utf8.txt";
            String utf8 = FileUtils.readFileWithEncoding(path, "UTF-8");
            System.out.println("utf8 : " + utf8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
