package com.example.comm.mapper;


import com.example.comm.DTO.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionMapper {

    @Insert("insert into question(title,description,tags,gmt_create,gmt_modified,creator,viewCount,likeCount,commentCount) values" +
            "(#{title},#{description},#{tags},#{gmt_create},#{gmt_modified},#{creator},#{viewCount},#{likeCount},#{commentCount})")
    void create(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> listQuestion(Integer offset, int size);

    @Select("select count(*) from question")
    Integer totalCount();

    @Select("select count(*) from question where creator=#{id}")
    Integer totalCountByCreator(Integer id);

    @Select("select * from question where creator=#{id} limit #{offset},#{size}")
    List<Question> listQuestionById(@Param("offset")Integer offset, @Param("size") int size, @Param("id") Integer id);
}
