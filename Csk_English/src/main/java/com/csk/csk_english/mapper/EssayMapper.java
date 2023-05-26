package com.csk.csk_english.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EssayMapper {

    //获取文章ID
    @Select("SELECT essayid FROM essay")
    public List<Integer> getEssayID();
    //查找文章
    @Select("select * from essay")
    public List<Map<String,Object>> find();
    //查找单篇文章
    @Select("select * from essay where essayid=#{essayid}")
    public List<Map<String,Object>> queryBytitle(int essayid);
    //增加文章
    @Insert("INSERT INTO `essay` (`title`, `classify`, `status`, `essayPath`) VALUES (#{title}, #{classify}, '1', #{essayPath})")
    public boolean addEssay(String title,String classify,String essayPath);
    //删除文章
    @Delete("DELETE FROM `essay` WHERE (`essayid`=#{id})")
    public boolean deleteEssay(int id);
    //修改文章
    @Update("UPDATE `essay` SET `title`=#{title}, `classify`= #{classify}, `essayPath`= #{essayPath} WHERE (`essayid`= #{essayid})")
    public boolean updataEssay(String title,String classify,String essayPath,int essayid);
    //    获得首篇未阅读文章的ID
    @Select("select essay from userEssay a where a.isRead=0 and a.user=#{userid} LIMIT 0,1")
    public String getEssaynotReadID(String user);
    //通过文章id获取文章详情

    @Select("select note from userEssay a where a.essayid=#{essayid} and a.user=#{userid} LIMIT 0,1")
    public String getNote(String user);

    //更新文章评论
    @Update("UPDATE useressay SET node = CONCAT(node,#{note}) WHERE userid = #{userid} AND essayid =#{essayid}")
    public void updateNote(String note,int userid,int essayid);

    //获取所有文章信息
    @Select("select * from essay")
    public List<Map<String,Object>> getAllEssay();
    //获取用户文章id，获取了笔记内容
    @Select("select * from userEssay where  userid=#{userid} ")
    public List<Map<String,Object>> getUserEssay(int userid);

    //获取文章信息，通过文章id
    @Select("select * from essay where essayid=#{essayid}")
    public List<Map<String,Object>> getEssayDetail(int essayid);

}
