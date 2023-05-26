package com.csk.csk_english.service;

import com.csk.csk_english.domain.Word;
import com.csk.csk_english.domain.WordHttp;
import com.csk.csk_english.mapper.UserMapper;
import com.csk.csk_english.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordMapper wordMapper;
    @Autowired
    private UserMapper userMapper;

    //修改单词
    @Override
    public boolean updatetWord(WordHttp word){
        return wordMapper.updateWord(word.getComponet(),word.getWord_id(),word.getMeaning(),word.getWordGroup(),word.getWordGroupMeaning(),word.getWordGroup1(),word.getWordGroupMeaning1(),word.getExplain());
    }
    //查找单词通过内容
    @Override
    public List<Map<String,Object>>  getWordByComponet(String componet) {
        return wordMapper.getWordByComponet(componet);
    }

    //查找单词
    @Override
    public List<Map<String,Object>>  queryWord(){
        return wordMapper.queryWord();
    }
    //删除单词
    @Override
    public boolean deleteWord(Word word){
        int id=wordMapper.getWordId(word.getComponet());
        userMapper.deleteWordUserByWord(id);
        return wordMapper.deleteWord(word.getComponet());
    }
    //添加单词
    @Override
    public boolean addWord(Word word){
        return wordMapper.insert(word.getComponet());
    }


    //获取基础词库的所有单词内容
    @Override
    public List<Map<String, Object>> getAllword() {

        System.out.println(123);
        System.out.println(wordMapper.getAllWord());return wordMapper.getAllWord();
    }


    @Override
    public void insertWord(Word word) {
        wordMapper.insertWord(word);
    }
    //插入某个单词
    @Override
    public void insert(String content){
        wordMapper.insert(content);
    }

    //删除用户单词映射记录
    @Override
    public void wordIsExist(int userid) {
        wordMapper.wordIsExist(userid);
    }
    //删除用户单词映射记录(单条记录)
    public void deleteUserWord(int userid,int wordid){
        wordMapper.deleteUserWord(userid,wordid);
    }

    //建立用户单词映射
    @Override
    public void buildUserWord(int userid, int wordId) {
        wordMapper.buildUserWord(userid,wordId);

    }

    //获取单词id
    @Override
    public int getWordId(String content) {

        System.out.println(content);
        return wordMapper.getWordId(content);
    }

    //获得未学习单词的ID
    @Override
    public List getNoWord(int userid) {
        return wordMapper.getNoWord(userid);
    }

    //获取用户词库的单词id
    @Override
    public List<Map<String, Object>> getWordAll(int userid) {

        return wordMapper.getWordAll(userid);
    }

    //获得所有单词的ID及其内容
    @Override
    public List<Map<String, Object>> getAllWord() {
        return wordMapper.getAllWord();
    }

    //获得某个单词的详细信息
    @Override
    public Map<String, Object> getWordDetail(int wordId) {
        return wordMapper.getWordDetail(wordId);
    }

    //获取所有单词
    @Override
    public List<String> getAllContent(){
        return wordMapper.getAllContent();
    }

    @Override
    public void lastWord(int wordID, int userID) {
        wordMapper.wordUserDel(wordID,userID);
        wordMapper.buildUserWord(userID,wordID);
    }

    @Override
    public void learnWord(int wordID, int userID,String date) {
        wordMapper.update(wordID,userID,date);
    }

    @Override
    public List<String> getMeaning(int wordID) {
        return wordMapper.selectMeaning(wordID);
    }

    //获取对应日期学会单词数
    @Override
    public List<Map<String,Object>> datelist(int userid){
        return wordMapper.datelist(userid);
    }
    //获取学习进度
    @Override
    public Object getProcess(int userid){
        List<Map<String,Object>> list=wordMapper.getProcess(userid);
        int num1=Integer.parseInt(list.get(0).get("num").toString());
        int num2=Integer.parseInt(list.get(1).get("num").toString());
        double n1=(double)num1;
        double n2=(double)num2;
        System.out.println(n1);
        System.out.println(n2);
        return n2/(n1+n2)*100;

    }



}
