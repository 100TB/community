package com.example.comm.controller;

import com.example.comm.DTO.Question;
import com.example.comm.DTO.User;
import com.example.comm.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {
    @Autowired
    QuestionMapper questionMapper;


    @RequestMapping("/create")
    public String create(Question question,HttpServletRequest request){
        User user =(User) request.getSession().getAttribute("user");
        System.out.println(user);
        System.out.println(user);
        question.setCreator(user.getId());
        System.out.println(user.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        questionMapper.create(question);

        return "index";
    }

}
