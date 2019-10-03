package com.example.comm.controller;


import com.example.comm.DTO.Pagination;
import com.example.comm.DTO.User;
import com.example.comm.mapper.UserMapper;
import com.example.comm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Resource(name="questionService")
    QuestionService questionService;
    @Autowired
    UserMapper userMapper;



    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name="action") String action,
                       @RequestParam(defaultValue = "1") Integer page,
                       Model model,
                       HttpServletRequest request
    ){
        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        if("question".equals(action)){
            model.addAttribute("sectionName","我的问题");
            model.addAttribute("section","question");
            Pagination pagination=questionService.findQuestionByUserId(page,user.getId());
            model.addAttribute("pagination",pagination);;

        }



        return "profile";
    }
}
