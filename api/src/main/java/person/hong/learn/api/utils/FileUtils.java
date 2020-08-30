package person.hong.learn.api.utils;

import java.io.File;
import java.util.*;

/**
 * @description:
 * @author: 洪晓鸿
 * @time: 2020/8/30 16:50
 */

public class FileUtils {

    private static Map<String, String> mimeTypes = new HashMap<>();

    static {
        mimeTypes.put("jpg", "image/jpeg");
        mimeTypes.put("png", "image/png");
    }

    public static String getMimeType(String suffix) {
        return mimeTypes.get(suffix);
    }
    /**
     * 页面展示用到的获取本地路径的方法
     * @return
     */
    public static List<Map> getFilePaths(){
        String path = "D:\\temp\\洪铨鸿";
        File file = new File(path);
        List<Map> chirldren = new LinkedList<>();
        searchFile(file, chirldren);
//        System.out.println(chirldren);
        return chirldren;
    }

    /***
     * @Description 遍历文件夹将文件名加入到列表中
     * @Time 2020/8/30 18:02
     * @Author 洪晓鸿
     */
    public static void searchFile(File file, List<Map> chirldren) {
        Map current = new LinkedHashMap();
        current.put("label", file.getName());
        chirldren.add(current);
        if (file.isFile()) {
            return;
        }
        List<Map> subsFilePath = new LinkedList<>();
        current.put("chirldren", subsFilePath);
        File[] sons = file.listFiles();
        for (File son: sons) {
            searchFile(son, subsFilePath);
        }
    }
}
