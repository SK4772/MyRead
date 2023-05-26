package com.csk.csk_english.service;

import java.util.List;
import java.util.Map;

public interface NoteService {
    //查询笔记
    public List<Map<String,Object>> queryAll();
    //查询笔记通过用户ID
    public List<Map<String,Object>> queryByID(int userid);
    //查询笔记通过用户ID
    public List<Map<String,Object>> queryByEssayID(int essayid);
    //查询笔记通过用户ID和文章ID
    public List<Map<String,Object>> queryByIDs(int userid,int essayid);
    //增加笔记
    public boolean addNote(int userid,int essayid,String note);
    //修改笔记
    public boolean updateNote(String note,int userid,int essayid);
    //删除笔记
    public boolean deleteNote(int userid,int essayid);

}
