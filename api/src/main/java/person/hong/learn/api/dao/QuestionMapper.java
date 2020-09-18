package person.hong.learn.api.dao;

import org.apache.ibatis.annotations.*;
import person.hong.learn.api.entity.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("select * from question")
    List<Question> getAllQuestions();

    @Select("select * from question where create_time between current_date and (current_date+1)")
    List<Question> getTodayQuestions();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content")
    })
    @Select("select * from question where id=#{id}")
    Question getQuestionById(Long id);

    @Select("select * from question where title like concat('%',#{title},'%')")
    List<Question> getQuestionsByTitle(String title);

    @Insert({"insert into question(title,content) values(#{title},#{content})"})
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
    Integer addQuestion(Question question);

    @Update("update question set title=#{title},content=#{content} where id=#{id}")
    Integer updateQuestionById(Question question);

    @Delete("delete from question where id=#{id}")
    Integer deleteQuestionById(Integer id);
}
