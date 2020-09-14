package person.hong.learn.api.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.hong.learn.api.entity.Question;

/**
 * @description:
 * @author: 洪晓鸿
 * @time: 2020/9/14 23:06
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @RequestMapping("/add")
    public String addQuestion(@RequestBody Question question) {
        System.out.println(question);
        return "success";
    }
}
