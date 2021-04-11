package cn.gd.cz.hong.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static cn.gd.cz.hong.nio.util.FileUtils.getRandomAccessFile;

/**
 * channel测试
 */
public class ChannelTest {
    @Test
    public void test() throws IOException {
        RandomAccessFile aFile = getRandomAccessFile("data/nio-data.txt");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(128);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}