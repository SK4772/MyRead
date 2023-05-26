package com.csk.csk_english.controller;

import com.csk.csk_english.domain.Node;
import com.csk.csk_english.domain.UserEssay;
import com.csk.csk_english.mapper.NoteMapper;
import com.csk.csk_english.service.NoteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AdminNoteController {

    @Autowired
    private  NoteService noteService;
    @Autowired
    private NoteMapper noteMapper;


    //查询笔记
    @GetMapping("admin/note/queryAll")
    public Object queryAll(@RequestParam(value = "pageindex" ,defaultValue ="1"  ) Integer pageIndex,
                           @RequestParam(value = "pagesize" ,defaultValue ="1"  ) Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> list=noteService.queryAll();
        PageInfo pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
    //查询笔记通过用户ID
    @PostMapping("/admin/note/queryByID")
    public List<Map<String,Object>> queryByID(@RequestBody UserEssay userEssay){
        return  noteService.queryByID(userEssay.getUserid());

    }
    //查询笔记通过文章ID
    @PostMapping("/admin/note/queryByEssayID")
    public List<Map<String,Object>> queryByEssayID(@RequestBody UserEssay userEssay) {
        return noteService.queryByEssayID(userEssay.getEssayid());
    }
    //查询笔记通过用户ID和文章ID
    @PostMapping("/admin/note/queryByIDs")
    public List<Map<String,Object>> queryByIDs(@RequestBody UserEssay userEssay){
        return  noteService.queryByIDs(userEssay.getUserid(),userEssay.getEssayid());
    }
    //增加笔记
    @PostMapping("/admin/note/addNote")
    public boolean addNote(@RequestBody Node userEssay){
        return noteService.addNote(userEssay.getUserid(),userEssay.getEssayid(),userEssay.getNode());
    }
    //修改笔记
    @PostMapping("/admin/note/updateNote")
    public boolean updateNote(@RequestBody Node userEssay){
        return noteService.updateNote(userEssay.getNode(),userEssay.getUserid(),userEssay.getEssayid());
    }
    //修改笔记
    @PostMapping("/admin/note/updateNoteUser")

    public boolean updateNoteUser(@RequestBody Node userEssay){
        boolean update=noteService.updateNote(userEssay.getNode(),userEssay.getUserid(),userEssay.getEssayid());

        if (!update) {
            noteMapper.addNote(userEssay.getUserid(), userEssay.getEssayid(), userEssay.getNode());
        }
        System.out.println(update);
        return true;
        //        return noteService.updateNote(userEssay.getNode(),userEssay.getUserid(),userEssay.getEssayid());
    }
    //删除笔记
    @PostMapping("/admin/note/deleteNote")
    public boolean deleteNote(@RequestBody UserEssay userEssay){
        return noteService.deleteNote(userEssay.getUserid(),userEssay.getEssayid());
    }
}
