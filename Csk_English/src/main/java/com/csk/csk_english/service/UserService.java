package com.csk.csk_english.service;

import com.csk.csk_english.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public List<String> getUserRecond();

    public boolean login(User user);

    public boolean register(String username,String password);

    //获取用户所有信息
    public List<Map<String,Object>> queryAll();
    //通过ID获取用户信息
    public List<Map<String,Object>> queryById(String username);
    //添加用户
    public boolean addUser(String username,String password,String name);
    //删除用户
    public boolean deleteUser(int userid);
    //修改用户信息
    public boolean updateUser(String username,String password,String name,int userid);
    //用户修改信息
    public boolean update(String username,String password,String name,String telephone,String mail,int userid);

}
