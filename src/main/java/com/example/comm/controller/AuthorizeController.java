package com.example.comm.controller;


import com.example.comm.DTO.AccessTokenDTO;
import com.example.comm.DTO.GithubUser;
import com.example.comm.DTO.User;
import com.example.comm.mapper.UserMapper;
import com.example.comm.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client_id}")
    private String client_id;
    @Value("${github.client_secret}")
    private String client_secret;
    @Value("${github.redirect_url}")
    private String redirect_url;
    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/callback")
    public String callback(String code, String state, HttpServletRequest request, HttpServletResponse response){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url(redirect_url);
        accessTokenDTO.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser=githubProvider.getUser(accessToken);
        User user=new User();
        user.setAccount_id(String.valueOf(githubUser.getId()));
        user.setGmt_create(System.currentTimeMillis());
        user.setGmt_modified(user.getGmt_create());
        user.setName(githubUser.getLogin());
        user.setAvatarUrl(githubUser.getAvatarUrl());
        String token=UUID.randomUUID().toString();
        user.setToken(token);
        Cookie cookie=new Cookie("token",token);
        cookie.setMaxAge(60*60*24*30);
        response.addCookie(cookie);
        userMapper.add(user);
        return "redirect:/";
    }


}
