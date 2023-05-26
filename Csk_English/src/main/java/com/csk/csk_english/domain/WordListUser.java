package com.csk.csk_english.domain;

import java.util.List;

public class WordListUser {
    private List<String> list;
    private int userid;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
