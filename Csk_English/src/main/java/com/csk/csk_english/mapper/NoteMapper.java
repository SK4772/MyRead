package com.csk.csk_english.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoteMapper {
    //查询笔记
    @Select("SELECT * FROM useressay ")
    public List<Map<String,Object>> queryAll();
    //查询笔记通过用户ID
    @Select("SELECT * FROM useressay where userid =#{userid}")
    public List<Map<String,Object>> queryByID(int userid);
    //查询笔记通过文章ID
    @Select("SELECT * FROM useressay where essayid=#{essayid}")
    public List<Map<String,Object>> queryByEssayID(int userid);
    //查询笔记通过用户ID和文章ID
    @Select("SELECT * FROM useressay where userid =#{userid} and essayid=#{essayid}")
    public List<Map<String,Object>> queryByIDs(int userid,int essayid);
    //增加笔记
    @Insert("INSERT INTO `useressay` (`userid`, `essayid`, `isRead`, `node`) VALUES (#{userid}, #{essayid}, '1', #{note})")
    public boolean addNote(int userid,int essayid,String note);
    //修改笔记
    @Update("UPDATE `useressay` SET `node`=#{note} WHERE (`userid`=#{userid}) AND (`essayid`=#{essayid})")
    public boolean updateNote(String note,int userid,int essayid);
    //删除笔记
    @Delete("DELETE FROM `useressay` WHERE (`userid`=#{userid}) AND (`essayid`=#{essayid})")
    public boolean deleteNote(int userid,int essayid);
}
