package com.csk.csk_english.domain;

public class Node {
    private int userid;
    private int essayid;

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

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    private String node;
}
