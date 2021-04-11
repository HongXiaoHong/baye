package cn.gd.cz.hong.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import static cn.gd.cz.hong.nio.util.FileUtils.getRandomAccessFile;

/**
 *
 */

public class TransformTest {

    /**
     * transferFrom transferTo 直接从一个channel传输到另一个channel
     *
     * @throws IOException
     */
    @Test
    public void transferFrom() throws IOException {
        RandomAccessFile fromFile = getRandomAccessFile("data/fromFile.txt");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = getRandomAccessFile("data/toFile.txt");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
        toChannel.force(true);
        toChannel.close();
        fromChannel.close();
        toFile.close();
        fromFile.close();
    }
}
