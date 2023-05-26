package com.csk.csk_english.domain;

public class WordHttp {
    private String componet;
    private int word_id;

    //翻译
    private String meaning;
    //词组1
    private String wordGroup;
    //词组翻译1
    private String wordGroupMeaning;
    //词组2
    private String wordGroup1;
    //词组翻译2
    private String wordGroupMeaning1;
    //英式发音
    private String usSpeech;
    private String usPhonetic;
    //美式发音
    private String ukSpeech;

    public String getComponet() {
        return componet;
    }

    public void setComponet(String componet) {
        this.componet = componet;
    }

    public int getWord_id() {
        return word_id;
    }

    public void setWord_id(int word_id) {
        this.word_id = word_id;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getWordGroup() {
        return wordGroup;
    }

    public void setWordGroup(String wordGroup) {
        this.wordGroup = wordGroup;
    }

    public String getWordGroupMeaning() {
        return wordGroupMeaning;
    }

    public void setWordGroupMeaning(String wordGroupMeaning) {
        this.wordGroupMeaning = wordGroupMeaning;
    }

    public String getWordGroup1() {
        return wordGroup1;
    }

    public void setWordGroup1(String wordGroup1) {
        this.wordGroup1 = wordGroup1;
    }

    public String getWordGroupMeaning1() {
        return wordGroupMeaning1;
    }

    public void setWordGroupMeaning1(String wordGroupMeaning1) {
        this.wordGroupMeaning1 = wordGroupMeaning1;
    }

    public String getUsSpeech() {
        return usSpeech;
    }

    public void setUsSpeech(String usSpeech) {
        this.usSpeech = usSpeech;
    }

    public String getUsPhonetic() {
        return usPhonetic;
    }

    public void setUsPhonetic(String usPhonetic) {
        this.usPhonetic = usPhonetic;
    }

    public String getUkSpeech() {
        return ukSpeech;
    }

    public void setUkSpeech(String ukSpeech) {
        this.ukSpeech = ukSpeech;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    private String explain;
}
