package person.hong.learn.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.hong.learn.api.entity.Question;
import person.hong.learn.api.service.QuestionService;

import java.util.List;

/**
 * @description:
 * @author: 洪晓鸿
 * @time: 2020/9/14 23:06
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/add")
    public String addQuestion(@RequestBody Question question) {
        System.out.println(question);
        questionService.addQuestion(question);
        return "success";
    }

    @RequestMapping("/query/today")
    public List<Question> getTodayQuestions() {
        return questionService.getTodayQuestions();
    }
}
