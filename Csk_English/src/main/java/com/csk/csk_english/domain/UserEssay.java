package com.csk.csk_english.domain;

public class UserEssay {
    private int userid;
    private int essayid;
    private String note;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getEssayid() {
        return essayid;
    }

    public void setEssayid(int essayid) {
        this.essayid = essayid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
