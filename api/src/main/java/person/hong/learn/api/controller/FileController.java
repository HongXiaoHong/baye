package person.hong.learn.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: 洪晓鸿
 * @time: 2020/9/1 0:11
 */
@Controller
@RequestMapping("file")
public class FileController {
    private static final Logger logger = LogManager.getLogger(FileController.class);
    /**
     * @description 上传单个文件
     * @param file
     * @return
     */
    @PostMapping("/upload/alone")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "D:/temp/picture/learn/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            System.out.println("上传成功");
            return "上传成功";
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return "上传失败！";
    }
}
