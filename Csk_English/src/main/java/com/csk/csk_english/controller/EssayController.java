package com.csk.csk_english.controller;

import com.csk.csk_english.domain.EssayHttpget;
import com.csk.csk_english.domain.User;
import com.csk.csk_english.domain.UserEssay;
import com.csk.csk_english.service.EssayService;
import com.csk.csk_english.utils.EncapsulatedUtil;
import com.csk.csk_english.utils.TransferUtil;
import com.csk.csk_english.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class EssayController {
    @Autowired
    private EssayService essayService;
    @Autowired
    private EncapsulatedUtil encapsulatedUtil;
    @Autowired
    private UploadUtils uploadUtils;
    @GetMapping("/find")
    public Object find(){
        List<Map<String,Object>> list= essayService.find();
        return encapsulatedUtil.encapsObject(list);
    }
    @PostMapping("/getEssay")
    public Object login(@RequestBody User user){

        String essayid=essayService.getEssay(user.getUsername());
        int id=Integer.parseInt(essayid);
        return essayService.getEssayDetail(id);
    }

    @PostMapping("/test")
    public Object test( MultipartFile file){

        String a= uploadUtils.uploadTxt(file);
        return encapsulatedUtil.encapsObject(a);
    }
    //获取文章翻译
    @PostMapping("/essay/getTranslate")
    public Object getTrans(@RequestBody EssayHttpget essayHttpget) throws IOException {

        return TransferUtil.getWordDetail(essayHttpget.essay);
    }
    //更新文章评论
    @PostMapping("/essay/updateNote")
    public void updateNote(@RequestBody UserEssay userEssay) {
        essayService.updateNote(userEssay.getNote(), userEssay.getUserid(), userEssay.getEssayid());

    }
    //获取所有文章信息
    @GetMapping("/essay/getAllEssay")
    public List<Map<String,Object>> getAllEssay(){
        return essayService.getAllEssay();
    }
    //获取用户文章信息，获取了笔记内容
    @PostMapping("/essay/getUserEssay")
    public List<Map<String,Object>> getUserEssay(@RequestBody UserEssay userEssay){
        return essayService.getUserEssay(userEssay.getUserid());
    }
    //获取文章信息，通过文章id
    @PostMapping("/essay/getEssayDetail")
    public List<Map<String,Object>> getEssayDetail(@RequestBody UserEssay userEssay ) {
        return essayService.getEssayDetail(userEssay.getEssayid());
    }
}
