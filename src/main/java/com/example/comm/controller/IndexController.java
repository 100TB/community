package com.example.comm.controller;




import com.example.comm.DTO.Pagination;
import com.example.comm.DTO.Question;
import com.example.comm.DTO.User;
import com.example.comm.mapper.QuestionMapper;
import com.example.comm.mapper.UserMapper;
import com.example.comm.service.QuestionServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Resource(name="questionService")
    QuestionServiceI questionService;
    @GetMapping("/")
    public String hello(HttpServletRequest request,
                     Model model,
                     @RequestParam(name="page",defaultValue = "1") int page,
                     @RequestParam(name = "size",defaultValue = "2") int size){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                User user=userMapper.findUserByToken(cookie.getValue());
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                    break;
                }
            }
        }
        Pagination pagination=questionService.listQuestion(page,size);
         model.addAttribute("pagination",pagination);

        return "index";
    }
    @RequestMapping("/publish")
    public String publish(){
        return "publish";
    }
}
