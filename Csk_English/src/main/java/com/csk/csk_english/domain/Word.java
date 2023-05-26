package com.csk.csk_english.domain;

public class Word {
     private String componet;
     private int id;
     private boolean islearned;
     //翻译
     private String meaning;
     //词组1
     private String wordGroup1;
     //词组翻译1
     private String wordGroupMeaning1;
     //词组2
     private String wordGroup2;
     //词组翻译2
     private String wordGroupMeaning2;
     //英式发音
     private String usSpeech;
     private String usPhonetic;
     //美式发音
     private String ukSpeech;
     private String uKPhonetic;

     @Override
     public String toString() {
          return "Word{" +
                  "componet='" + componet + '\'' +
                  ", id=" + id +
                  ", islearned=" + islearned +
                  ", meaning='" + meaning + '\'' +
                  ", wordGroup1='" + wordGroup1 + '\'' +
                  ", wordGroupMeaning1='" + wordGroupMeaning1 + '\'' +
                  ", wordGroup2='" + wordGroup2 + '\'' +
                  ", wordGroupMeaning2='" + wordGroupMeaning2 + '\'' +
                  ", usSpeech='" + usSpeech + '\'' +
                  ", usPhonetic='" + usPhonetic + '\'' +
                  ", ukSpeech='" + ukSpeech + '\'' +
                  ", uKPhonetic='" + uKPhonetic + '\'' +
                  ", explain='" + explain + '\'' +
                  '}';
     }

     public Word(String componet, String meaning, String wordGroup1, String wordGroupMeaning1, String wordGroup2, String wordGroupMeaning2, String usSpeech, String usPhonetic, String ukSpeech, String uKPhonetic, String explain) {
          this.componet = componet;
          this.meaning = meaning;
          this.wordGroup1 = wordGroup1;
          this.wordGroupMeaning1 = wordGroupMeaning1;
          this.wordGroup2 = wordGroup2;
          this.wordGroupMeaning2 = wordGroupMeaning2;
          this.usSpeech = usSpeech;
          this.usPhonetic = usPhonetic;
          this.ukSpeech = ukSpeech;
          this.uKPhonetic = uKPhonetic;
          this.explain = explain;
     }
     public Word(){}


     public String getUsPhonetic() {
          return usPhonetic;
     }

     public void setUsPhonetic(String usPhonetic) {
          this.usPhonetic = usPhonetic;
     }

     public String getuKPhonetic() {
          return uKPhonetic;
     }

     public void setuKPhonetic(String uKPhonetic) {
          this.uKPhonetic = uKPhonetic;
     }

     //解释
     private String explain;

     public String getComponet() {
          return componet;
     }

     public void setComponet(String componet) {
          this.componet = componet;
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public boolean isIslearned() {
          return islearned;
     }

     public void setIslearned(boolean islearned) {
          this.islearned = islearned;
     }

     public String getMeaning() {
          return meaning;
     }

     public void setMeaning(String meaning) {
          this.meaning = meaning;
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

     public String getWordGroup2() {
          return wordGroup2;
     }

     public void setWordGroup2(String wordGroup2) {
          this.wordGroup2 = wordGroup2;
     }

     public String getWordGroupMeaning2() {
          return wordGroupMeaning2;
     }

     public void setWordGroupMeaning2(String wordGroupMeaning2) {
          this.wordGroupMeaning2 = wordGroupMeaning2;
     }

     public String getUsSpeech() {
          return usSpeech;
     }

     public void setUsSpeech(String usSpeech) {
          this.usSpeech = usSpeech;
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
}
