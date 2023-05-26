package com.csk.csk_english.mapper;

import com.csk.csk_english.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    //删除worduser表里的数据通过wordid
    @Delete("DELETE FROM `studyrecord` WHERE  `wordID`=#{wordID}")
    public void deleteWordUserByWord(int wordID);
    //删除worduser表里的数据通过userid

    @Delete("DELETE FROM `studyrecord` WHERE `userID`=#{userID}")
    public void deleteWordUserByUser(int userID);
    //删除essayuser表里的数据通过essayid
    @Delete("DELETE FROM `useressay` WHERE `essayid`=#{essayID}")
    public void deleteEssayUserByEssay(int essayID);
    //删除essayuser表里的数据通过userid
    @Delete("DELETE FROM `useressay` WHERE `userid`=#{userID}")
    public void deleteEssayUserByUser(int userID);
    @Select("Select username from user ")
    public List<String> getUserRecord();

    @Select("SELECT userid FROM `user` ")
    public List<Integer> getUserID();


    @Select("SELECT `password`,`username` FROM `user` WHERE username=#{username}")
    public User getUser(String username);

    @Insert("INSERT INTO `user` (username,password) VALUES (#{username},#{password})")
    public boolean insertUser(String username,String password);

    //获取用户所有信息
     @Select("SELECT * FROM user")
    public List<Map<String,Object>> queryAll();
    //通过ID获取用户信息
    @Select("SELECT * FROM user WHERE username = #{username}")
    public List<Map<String,Object>> queryById(String username);
    //添加用户
    @Insert("INSERT INTO `user` (`username`, `password`, `name`) VALUES (#{username}, #{password}, #{name})")
    public boolean addUser(String username,String password,String name);
    //删除用户
    @Delete("DELETE FROM `user` WHERE (`userid`=#{userid})")
    public boolean deleteUser(int userid);
    //修改用户信息
    @Update("UPDATE `user` SET `username`=#{username}, `password`=#{password}, `name`=#{name} WHERE (`userid`=#{userid})")
    public boolean updateUser(String username,String password,String name,int userid);
    //用户修改信息
    @Update("UPDATE `user` SET `username`=#{username}, `password`=#{password}, `name`=#{name}, `telephone`=#{telephone}, `mail`=#{mail} WHERE (`userid`=#{userid})")
    public boolean update(String username,String password,String name,String telephone,String mail,int userid);
}
