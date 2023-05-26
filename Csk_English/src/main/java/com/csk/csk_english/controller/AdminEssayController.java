package com.csk.csk_english.controller;

import com.csk.csk_english.domain.Essay;
import com.csk.csk_english.service.EssayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AdminEssayController {
    @Autowired
    private EssayService essayService;

    //查询文章
    @GetMapping("/admin/essay/queryAll")
    public Object queryAll(@RequestParam(value = "pageindex" ,defaultValue ="1"  ) Integer pageIndex,
                                             @RequestParam(value = "pagesize" ,defaultValue ="1"  ) Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> list=essayService.queryEssay();
        PageInfo pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
    //查询文章通过题目
    @PostMapping("/admin/essay/queryByID")
    public List<Map<String,Object>> queryByTitle(@RequestBody Essay essay){
        return essayService.queryEssayByTitle(essay);
    }
    //删除文章
    @PostMapping("/admin/essay/deleteEssay")
    public boolean deleteEssay(@RequestBody Essay essay){
        return essayService.deleteEssay(essay);
    }
    //添加文章
    @PostMapping("/admin/essay/addEssay")
    public boolean addEssay(@RequestBody Essay essay){

        return essayService.addEssay(essay);
    }
    //更新文章
    @PostMapping("/admin/essay/updataEssay")
    public boolean updateEssay(@RequestBody Essay essay){
        return essayService.updataEssay(essay);
    }
}
