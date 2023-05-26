package com.csk.csk_english.domain;

public class Essay {
    private String title;
    private String address;

    private int essayid;
    private String essayPath;
    private String classify;

    public int getEssayid() {
        return essayid;
    }

    public void setEssayid(int essayid) {
        this.essayid = essayid;
    }

    public String getEssayPath() {
        return essayPath;
    }

    public void setEssayPath(String essayPath) {
        this.essayPath = essayPath;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
