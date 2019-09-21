package com.example.comm.DTO;

public class GithubUser {

    private String login;
    private long id;

    public String getLogin() {
        return login;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
