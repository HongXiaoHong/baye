package person.hong.learn.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.hong.learn.api.utils.FileUtils;
import person.hong.learn.api.utils.MemoryRandomImage;
import person.hong.learn.api.utils.intf.RandomImage;

import java.io.File;
import java.util.Date;

/**
 * @description:
 * @author: 洪晓鸿
 * @time: 2020/8/30 22:32
 */

@RestController
@RequestMapping("image")
public class ImageController {
    private static final Logger logger = LogManager.getLogger(ImageController.class);

    @RequestMapping("/random/memory/{id}")
    public ResponseEntity<FileSystemResource> getRandomImageFromMemory(@PathVariable(name = "id") String id) {
        logger.info("id : " + id);
        RandomImage randomImage = new MemoryRandomImage();
        File image = randomImage.getImage();
        logger.info(new Date());
        return export(image);
    }

    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        String absolutePath = file.getAbsolutePath();
        String suffix = absolutePath.substring(absolutePath.length() - 3);
        String mimeType = FileUtils.getMimeType(suffix);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(mimeType))
                .body(new FileSystemResource(file));
    }
}
