package cn.gd.cz.hong.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

import static cn.gd.cz.hong.nio.util.FileUtils.getRandomAccessFile;

/**
 * 缓存区测试
 */

public class BufferTest {

    /**
     * 使用Buffer读写数据一般遵循以下四个步骤：
     *
     * <ol>
     * <li>写入数据到缓冲区</li>
     * <li>调用flip()方法</li>
     * <li>从Buffer中读取数据</li>
     * <li>调用clear()方法或者compact()方法</li>
     * </ol>
     *
     * @throws IOException
     */
    @Test
    public void hello() throws IOException {
        RandomAccessFile aFile = getRandomAccessFile("data/nio-data.txt");
        FileChannel inChannel = aFile.getChannel();

        // 创建一个容量为48的缓存
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf); //read into buffer.
        while (bytesRead != -1) {

            buf.flip();  // 将buffer从写模式转换为读模式

            while (buf.hasRemaining()) {
                System.out.print((char) buf.get()); // 每次读取一个byte字节
            }

            buf.clear(); // 为写模式做准备
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    /**
     * 测试charBuffer
     * @throws IOException
     */
    @Test
    public void helloCharBuffer() throws IOException {
        RandomAccessFile aFile = getRandomAccessFile("data/nio-data.txt");
        FileChannel inChannel = aFile.getChannel();

        // 创建一个容量为128的缓存
        CharBuffer buf = CharBuffer.allocate(128);
//        inChannel.read(buf);
    }
}
