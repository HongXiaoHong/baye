package cn.gd.cz.hong.springbootlearn.controller;

import cn.gd.cz.hong.springbootlearn.param.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试前段与后端之间交互
 */
@RestController
@RequestMapping("/test/param")
public class TestParamController {

    @Autowired
    private Food food;

    @PostConstruct
    public void init() {
        System.out.println(" food : " + food);
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
}

