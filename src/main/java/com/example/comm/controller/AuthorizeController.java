package com.example.comm.controller;


import com.alibaba.fastjson.JSON;
import com.example.comm.DTO.AccessTokenDTO;
import com.example.comm.DTO.GithubUser;
import com.example.comm.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @RequestMapping("callback")
    public @ResponseBody String callback(String code, String state){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url(redirect_url);
        accessTokenDTO.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser  user=githubProvider.getUser(accessToken);
        return JSON.toJSONString(user);
    }


}
