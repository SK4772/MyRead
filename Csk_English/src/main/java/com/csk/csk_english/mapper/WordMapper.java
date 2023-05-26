package com.csk.csk_english.mapper;

import com.csk.csk_english.domain.Word;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface WordMapper {


    //获取学习进度
    @Select("SELECT COUNT(wordID) as num,isRead as learn FROM studyrecord where userID =  #{userid} GROUP BY(isRead) ")
    public List<Map<String,Object>> getProcess(int userid);

    //@Insert("INSERT INTO `word` (`componet`) VALUES (#{componet})")

    @Insert("INSERT INTO `word` (`componet`, `meaning`, `wordGroup`, `wordGroupMeaning`, `wordGroup1`, `wordGroupMeaning1`, `usSpeech`, `usPhonetic`, `ukSpeech`, `ukPhonetic`, `explain`) VALUES (#{componet}, #{meaning}, #{wordGroup1}, #{wordGroupMeaning1}, #{wordGroup2}, #{wordGroupMeaning2},#{usSpeech}, #{usPhonetic}, #{ukSpeech}, #{uKPhonetic},#{explain})")
    public void insertWord(Word word);

    //获取未学习的单词id
    @Select("SELECT wordID FROM studyrecord WHERE userID = #{userid} and isRead = 0")
    public List<Integer> getNoWord(int userid);

    //获取用户词库的单词id
    @Select("SELECT wordID FROM studyrecord WHERE userID = #{userid}")
    public List<Map<String,Object>> getWordAll(int userid);

    //获取基础词库的所有单词内容
    @Select("select * from word")
    public List<Map<String,Object>> getAllWord();
    //获取某个单词的单词详情
    @Select("SELECT * FROM word WHERE word_id = #{wordId}")
    public Map<String,Object> getWordDetail(int wordId);

    //删除用户单词映射记录
    @Delete("DELETE from studyrecord where userID = #{userid}")
    public void wordIsExist(int userid);

    //删除用户单词映射记录(单条记录)
    @Delete("DELETE from studyrecord where userID = #{userid} and wordID=#{wordid}")
    public void deleteUserWord(int userid,int wordid);

    //建立用户单词映射
    @Insert("INSERT INTO studyrecord (wordID,userID) VALUES (#{wordId},#{userid}) ")
    public void buildUserWord(int userid,int wordId);
    //获取单词id
    @Select("SELECT word_id from word where componet = #{content}")
    public Integer getWordId(String content);

    //获取所有单词
    @Select("select word_id from word")
    public List<Integer> getAllID();
    //获取所有单词
    @Select("select componet from word")
    public List<String> getAllContent();

    @Update("UPDATE studyrecord SET isRead = 1, `date`=#{date} where wordID = #{wordID} and userID = #{userID}")
    public boolean update(int wordID, int userID,String date);

    //获取对应日期学会单词数
    @Select("SELECT date,COUNT(date)  as content from studyrecord where userID = #{userid} and isRead =1 GROUP BY(date)")
    public List<Map<String,Object>> datelist(int userid);

    //删除未学会的单词id
    @Delete("DELETE from studyrecord where wordID = #{wordID} and userID = #{userID}")
    public void wordUserDel(int wordID, int userID);

    //随机查询3个单词
    @Select("SELECT meaning from word where word_id != #{wordID} GROUP BY RAND() LIMIT 3")
    public List<String> selectMeaning(int WordID);

    //获取所有单词
    @Select("select * from word")
    public List<Map<String,Object>> queryWord();
    //修改单词内容
    @Update("UPDATE `word` SET `componet`=#{componet},  `meaning`=#{meaning}, `wordGroup`=#{wordGroup}, `wordGroupMeaning`=#{wordGroupMeaning}, `wordGroup1`=#{wordGroup1}, `wordGroupMeaning1`=#{wordGroupMeaning1},  `explain`=#{explain} WHERE (`word_id`=#{word_id})")
    public boolean updateWord(String componet, int word_id, String meaning, String wordGroup,String wordGroupMeaning, String wordGroup1, String wordGroupMeaning1, String explain);

    //删除单词
    @Delete("DELETE FROM `word` WHERE (`componet`=#{componet})")
    public boolean deleteWord(String componet);

    //插入某个单词
    @Insert("INSERT INTO `word` (`componet`) VALUES (#{content})")
    public  boolean insert(String content);

    //获取某个单词的单词详情
    @Select("SELECT * FROM word WHERE componet = #{componet}")
    public List<Map<String,Object>>  getWordByComponet(String componet);
}
