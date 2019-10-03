package com.example.comm.service;

import com.example.comm.DTO.Pagination;
import com.example.comm.DTO.Question;
import com.example.comm.DTO.QuestionExt;
import com.example.comm.mapper.QuestionMapper;
import com.example.comm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("questionService")
public class QuestionService implements QuestionServiceI{

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    public Pagination listQuestion(int page, int size) {
        Integer currentPage;
        Pagination pagination=new Pagination();
        Integer totalCount=questionMapper.totalCount();
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }
        pagination.setTotalPages(totalPage);
        if(page<1){
            currentPage=1;
        }else if(page>totalPage){
            currentPage=totalPage;
        }else{
            currentPage=page;
        }
        pagination.setPage(currentPage);
        Integer offset=(currentPage-1)*size;
        List<QuestionExt> list=new ArrayList<QuestionExt>();
        List<Question> list2=questionMapper.listQuestion(offset,size);
        for(Question q:list2){

            QuestionExt questionExt=new QuestionExt();
            questionExt.setQuestion(q);
            questionExt.setUser(userMapper.findUserByID(q.getCreator()));
            list.add(questionExt);
        }
        pagination.setQuestionExts(list);
        pagination.setpagination(totalPage,size);
        return pagination;
    }

    @Override
    public Pagination findQuestionByUserId(Integer page, Integer id) {
        int size=2;
        Integer currentPage;
        Pagination pagination=new Pagination();
        Integer totalCount=questionMapper.totalCountByCreator(id);
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }
        pagination.setTotalPages(totalPage);
        if(totalPage==0){
            currentPage=1;
        }
        else if(page<1){
            currentPage=1;
        }else if(page>totalPage){
            currentPage=totalPage;
        }else{
            currentPage=page;
        }
        pagination.setPage(currentPage);
        Integer offset=(currentPage-1)*size;
        List<QuestionExt> list=new ArrayList<QuestionExt>();
        List<Question> list2=questionMapper.listQuestionById(offset,size,id);
        for(Question q:list2){

            QuestionExt questionExt=new QuestionExt();
            questionExt.setQuestion(q);
            questionExt.setUser(userMapper.findUserByID(q.getCreator()));
            list.add(questionExt);
        }
        pagination.setQuestionExts(list);
        pagination.setpagination(totalPage,size);
        return pagination;


    }
}
