package com.csk.csk_english.service;

import com.csk.csk_english.domain.Word;
import com.csk.csk_english.domain.WordHttp;

import java.util.List;
import java.util.Map;

public interface WordService {
    public List<Map<String,Object>> getAllword();
    //获取某个单词的单词详情
    public List<Map<String,Object>>  getWordByComponet(String componet);
    //修改单词
    public boolean updatetWord(WordHttp word);
    //查找单词
    public List<Map<String,Object>>  queryWord();
    //删除单词
    public boolean deleteWord(Word word);
    //添加单词
    public boolean addWord(Word word);




    public void insertWord(Word word);
    //获得未学习单词的ID
    public List getNoWord(int userid);
    //获取用户词库的单词id
    public List<Map<String,Object>> getWordAll(int userid);
    //获取基础词库的所有单词内容
    public  List<Map<String,Object>> getAllWord();
    //获得某个单词的详细信息
    public Map<String,Object> getWordDetail(int wordId);

    //插入某个单词
    public  void insert(String content);
    //删除用户单词映射记录
    public void wordIsExist(int userid);
    //建立用户单词映射
    public void buildUserWord(int userid,int wordId);
    //获取单词id
    public int getWordId(String content);
    //获取所有单词
    public List<String> getAllContent();
    //将未学会的单词靠后
    public void lastWord(int wordID, int userID);
    //将未学会的单词靠后
    public void learnWord(int wordID, int userID,String date);
    //随机查询3个单词
    public List<String> getMeaning(int wordID);
    //获取对应日期学会单词数
    public List<Map<String,Object>> datelist(int userid);
    //获取学习进度
    public Object getProcess(int userid);
    //删除用户单词映射记录(单条记录)
    public void deleteUserWord(int userid,int wordid);
}
