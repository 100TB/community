package com.example.comm.service;

import com.example.comm.DTO.Pagination;
import com.example.comm.DTO.QuestionExt;

import java.util.List;

public interface QuestionServiceI {
    Pagination listQuestion(int page, int size);
    public Pagination findQuestionByUserId(Integer page, Integer id);

}
