package com.csk.csk_english.service;

import com.csk.csk_english.domain.Essay;
import com.csk.csk_english.mapper.EssayMapper;
import com.csk.csk_english.mapper.UserMapper;
import com.csk.csk_english.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@Service
public class EssayServiceImpl implements EssayService {
    @Autowired
    private EssayMapper essayMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Map<String, Object>> find() {
        return essayMapper.find();
    }

    @Override
    public String getEssay(String user) {
        return essayMapper.getEssaynotReadID(user);
    }



    //获取文章翻译
    @Override
    public String getTranslate(String content) throws IOException {
        return TransferUtil.getWordDetail(content);
    }
    //更新文章评论
    @Override
    public void updateNote(String note, int userid, int essayid) {
        essayMapper.updateNote(note,userid,essayid);

    }

    //获取所有文章信息
    @Override
    public List<Map<String,Object>> getAllEssay(){
        return essayMapper.getAllEssay();
    }
    //获取用户文章信息，获取了笔记内容
    @Override
    public List<Map<String,Object>> getUserEssay(int userid){
        return essayMapper.getUserEssay(userid);
    }

    //获取文章信息，通过文章id
    @Override
    public List<Map<String,Object>> getEssayDetail(int essayid) {
        return essayMapper.getEssayDetail(essayid);
    }


    //查找所有文章

    @Override
    public List<Map<String,Object>> queryEssay(){
        return essayMapper.find();
    }

    //查找单个文章
    @Override
    public List<Map<String,Object>> queryEssayByTitle(Essay essay){
        return essayMapper.queryBytitle(essay.getEssayid());
    }

    //增加文章

    @Override
    public boolean addEssay(Essay essay){
        return essayMapper.addEssay(essay.getTitle(),essay.getClassify(),essay.getEssayPath());
    }
    //删除文章
    @Override
    public boolean deleteEssay(Essay essay){
        userMapper.deleteEssayUserByEssay(essay.getEssayid());
        return essayMapper.deleteEssay(essay.getEssayid());
    }
    //修改文章
    @Override
    public boolean updataEssay(Essay essay){
        return essayMapper.updataEssay(essay.getTitle(),essay.getClassify(),essay.getEssayPath(),essay.getEssayid());
    }

}
