package cn.gd.cz.hong.springbootlearn.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试前段与后端之间交互
 */
@RestController
@RequestMapping("/test/param")
public class TestParamController {

    @GetMapping("/get/complex")
    public Map<String, String> get(@RequestBody Map<String, String> param) {
        return new HashMap<String, String>() {{
            put("code", "200");
            putAll(param);
        }};
    }

    /**
     * req
     * http://localhost:8011/test/param/post/simple
     * <p>
     * form-data
     * id f34yfhgf
     * <p>
     * response
     * {
     * "code": "200",
     * "id": "f34yfhgf"
     * }
     */
    @RequestMapping("/post/simple")
    public Map<String, String> simple(String id) {
        return new HashMap<String, String>() {{
            put("code", "200");
            put("id", id);
        }};
    }

    /**
     * req
     * http://localhost:8011/test/param/post/complex
     * <p>
     * <p>
     * Content-Type application/json
     * <p>
     * {
     * "a": "a",
     * "b": "b"
     * }
     * <p>
     * response
     * {
     * "a": "a",
     * "b": "b",
     * "code": "200"
     * }
     */
    @RequestMapping("/post/complex")
    public Map<String, String> complex(@RequestBody Map<String, String> param) {
        return new HashMap<String, String>() {{
            put("code", "200");
            putAll(param);
        }};
    }

    @DeleteMapping("/delete/complex")
    public Map<String, String> delete(@RequestBody Map<String, String> param) {
        return new HashMap<String, String>() {{
            put("code", "200");
            putAll(param);
        }};
    }

    @PutMapping("/put/complex")
    public Map<String, String> put(@RequestBody Map<String, String> param) {
        return new HashMap<String, String>() {{
            put("code", "200");
            putAll(param);
        }};
    }
}

