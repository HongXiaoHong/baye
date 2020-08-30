package person.hong.learn.api.utils;

import person.hong.learn.api.utils.intf.RandomImage;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: 洪晓鸿
 * @time: 2020/8/30 22:04
 */

public class MemoryRandomImage implements RandomImage {
    private static String basePath = "D:\\documents\\photo\\secondary\\20200424";
    private static List<String> subPaths = new LinkedList<>();

    static {
        scanPath_add2subPaths();
    }

    @Override
    public File getImage() {
        Random random = new Random();
        int imageIndex = random.nextInt(subPaths.size());
        String subPath = subPaths.get(imageIndex);
//        String completePath = basePath + subPath;
//        System.out.println(completePath);
        return new File(subPath);
    }

    private static void scanPath_add2subPaths() {
        File baseFile = new File(basePath);
        searchFile(baseFile);
    }

    /***
     * @Description 遍历文件夹将文件名加入到列表中
     * @Time 2020/8/30 18:02
     * @Author 洪晓鸿
     */
    public static void searchFile(File file) {
        if (file.isFile()) {
            subPaths.add(file.getAbsolutePath());
            return;
        }
        File[] sons = file.listFiles();
        for (File son : sons) {
            searchFile(son);
        }
    }

}
