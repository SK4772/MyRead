package com.csk.csk_english.controller;

import com.csk.csk_english.domain.User;
import com.csk.csk_english.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AdminUserController {

    @Autowired
    private UserService userService;

    //获取用户所有信息
    @GetMapping("admin/user/queryAll")
    public Object queryAll(@RequestParam(value = "pageindex" ,defaultValue ="1"  ) Integer pageIndex,
                           @RequestParam(value = "pagesize" ,defaultValue ="1"  ) Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> list=userService.queryAll();
        PageInfo pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
    //通过ID获取用户信息
    @PostMapping("/admin/user/queryByID")
    public List<Map<String,Object>> queryById(@RequestBody User user){
        return userService.queryById(user.getUsername());
    }
    //添加用户
    @PostMapping("/admin/user/addUser")
    public boolean addUser(@RequestBody User user){
        List<Map<String,Object>> list= userService.queryAll();
        for(int x=0; x<list.size(); x++) {
            Map<String,Object> s = list.get(x);
            if(user.getUsername().equals(s.get("username"))){
                System.out.println(s.get("username"));
                  return false;
            }
            //System.out.println(s);

        }
        return      userService.addUser(user.getUsername(), user.getPassword(), user.getName());


    }
    //删除用户

    @PostMapping("/admin/user/deleteUser")
    public boolean deleteUser(@RequestBody User user){

        return userService.deleteUser(user.getUserid());
    }
    //修改用户信息
    @PostMapping("/admin/user/updateUser")
    public boolean updateUser(@RequestBody User user){

        return userService.updateUser(user.getUsername(),user.getPassword(),user.getName(),user.getUserid());
    }
}

