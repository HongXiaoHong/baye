package person.hong.learn.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.hong.learn.api.dao.QuestionMapper;
import person.hong.learn.api.entity.Question;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: 洪晓鸿
 * @time: 2020/9/15 23:26
 */

@Service
public class QuestionService {

    @Resource
    private QuestionMapper quesMapper;

    public void addQuestion(Question question) {
        quesMapper.addQuestion(question);
    }

    public List<Question> getTodayQuestions() {
        return quesMapper.getTodayQuestions();
    }
}
