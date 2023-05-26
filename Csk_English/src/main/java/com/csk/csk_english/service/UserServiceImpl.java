package com.csk.csk_english.service;

import com.csk.csk_english.domain.User;
import com.csk.csk_english.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
    UserMapper userMapper;
    @Override
    //获得用户当前学习到的单词id
    public List<String> getUserRecond() {
        return userMapper.getUserRecord();
    }

    @Override
    public boolean login(User user) {
        User user1 = userMapper.getUser(user.getUsername());
        if(user1== null||user.getPassword()==null){
            return false;
        } System.out.println(user.getPassword()+user1.getPassword());
        if(user.getPassword().equals(user1.getPassword())){

            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean register(String username, String password) {
        List<String> list=userMapper.getUserRecord();
        if(username==null||password==null){
            return false;
        } else if (list.contains(username)) {
            return false;
        }
        return userMapper.insertUser(username,password);
    }

    //获取用户所有信息
    @Override
    public List<Map<String,Object>> queryAll(){
        return userMapper.queryAll();
    }
    //通过ID获取用户信息
    @Override
    public List<Map<String,Object>> queryById(String username){
        return userMapper.queryById(username);
    }
    //添加用户
    @Override
    public boolean addUser(String username,String password,String name){
        return userMapper.addUser(username,password,name);
    }
    //删除用户
    @Override
    public boolean deleteUser(int userid){
        userMapper.deleteWordUserByUser(userid);
        userMapper.deleteEssayUserByUser(userid);
        return userMapper.deleteUser(userid);
    }
    //修改用户信息
    @Override
    public boolean updateUser(String username,String password,String name,int userid){
        return userMapper.updateUser(username,password,name,userid);
    }

    @Override
    public boolean update(String username,String password,String name,String telephone,String mail,int userid){
        return userMapper.update(username,password,name,telephone,mail,userid);
    }




}
