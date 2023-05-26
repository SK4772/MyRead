package com.csk.csk_english.service;

import com.csk.csk_english.mapper.EssayMapper;
import com.csk.csk_english.mapper.NoteMapper;
import com.csk.csk_english.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EssayMapper essayMapper;

    //查询笔记
    @Override
    public List<Map<String,Object>> queryAll(){
        return noteMapper.queryAll();
    }
    //查询笔记通过用户ID
    @Override
    public List<Map<String,Object>> queryByID(int userid){
        return  noteMapper.queryByID(userid);
    }
    //查询笔记通过用户ID
    @Override
    public List<Map<String,Object>> queryByEssayID(int essayid){
        return noteMapper.queryByEssayID(essayid);
    }
    //查询笔记通过用户ID和文章ID
    @Override
    public List<Map<String,Object>> queryByIDs(int userid,int essayid){
        return  noteMapper.queryByIDs(userid,essayid);
    }
    //增加笔记
    @Override
    public boolean addNote(int userid,int essayid,String note){
        List<Integer> userIdList=userMapper.getUserID();
        List<Integer> essayIdList=essayMapper.getEssayID();
        if(!userIdList.contains(userid)||!essayIdList.contains(essayid)){
            return false;
        }else{
            return noteMapper.addNote(userid,essayid,note);
        }

    }
    //修改笔记
    @Override
    public boolean updateNote(String note,int userid,int essayid){
        return noteMapper.updateNote(note, userid, essayid);
    }
    //删除笔记
    @Override
    public boolean deleteNote(int userid,int essayid){
        return noteMapper.deleteNote(userid,essayid);
    }

}
