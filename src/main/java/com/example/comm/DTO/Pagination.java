package com.example.comm.DTO;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    private int page;
    private  int size;
    private int totalCount;
    private int totalPages;
    private List<Integer> arrays;
    private List<QuestionExt> questionExts;
    private boolean showPrevious=true;
    private  boolean showNext=true;
    private boolean showFirst=true;
    private boolean showEnd=true;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Integer> getArrays() {
        return arrays;
    }

    public void setArrays(List<Integer> arrays) {
        this.arrays = arrays;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowFirst() {
        return showFirst;
    }

    public void setShowFirst(boolean showFirst) {
        this.showFirst = showFirst;
    }

    public boolean isShowEnd() {
        return showEnd;
    }

    public void setShowEnd(boolean showEnd) {
        this.showEnd = showEnd;
    }

    public void setpagination(Integer totalPage, int size) {
        this.totalPages=totalPage;
        this.size=size;
        arrays=new ArrayList<Integer>();
        arrays.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
                arrays.add(0,page-i);
            }
            if(page+i<=totalPage){
             arrays.add(page+i);
            }
        }

        if(arrays.contains(1)){
            showPrevious=false;
        }
        if(arrays.contains(totalPage)){
            showNext=false;
        }
        if(arrays.contains(1)){
            showFirst=false;
        }
        if(arrays.contains(totalPage)){
            showEnd=false;
        }
    }

    public List<QuestionExt> getQuestionExts() {
        return questionExts;
    }

    public void setQuestionExts(List<QuestionExt> questionExts) {
        this.questionExts = questionExts;
    }
}
