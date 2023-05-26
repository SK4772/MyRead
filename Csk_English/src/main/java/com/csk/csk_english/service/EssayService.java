package com.csk.csk_english.service;

import com.csk.csk_english.domain.Essay;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface EssayService {
    public List<Map<String,Object>> find();

    // 获得首篇未阅读文章的ID
    public String getEssay(String user);


    //获取文章翻译

    public String getTranslate(String content) throws IOException;

    //更新文章评论

    public void updateNote(String note,int userid,int essayid);

    //获取所有文章信息

    public List<Map<String,Object>> getAllEssay();
    //获取用户文章信息，获取了笔记内容

    public List<Map<String,Object>> getUserEssay(int userid);

    //获取文章信息，通过文章id
    public List<Map<String,Object>> getEssayDetail(int essayid);


    //查找文章

    public List<Map<String,Object>> queryEssay();

    //增加文章

    public boolean addEssay(Essay essay);
    //删除文章

    public boolean deleteEssay(Essay essay);
    //修改文章
    public boolean updataEssay(Essay essay);
    //查找单个文章
    public List<Map<String,Object>> queryEssayByTitle(Essay essay);
}
