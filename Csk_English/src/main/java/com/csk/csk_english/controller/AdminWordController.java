package com.csk.csk_english.controller;

import com.csk.csk_english.domain.Word;
import com.csk.csk_english.domain.WordHttp;
import com.csk.csk_english.service.WordService;
import com.csk.csk_english.utils.TransferUtil;
import com.csk.csk_english.utils.jsonToData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class AdminWordController {
    @Autowired
    private WordService wordService;

    @PostMapping("/admin/word/add")
    public boolean addWord(@RequestBody Word word) throws IOException {
        List<String> list = wordService.getAllContent();
        String content=word.getComponet();
        if(list.contains(content)){
            return false;
        }
        String json= TransferUtil.getWordDetail(word.getComponet());
        Word newword= jsonToData.toData(json);
        wordService.insertWord(newword);
        return true;
    }

    @PostMapping("/admin/word/deleta")
    public boolean deleteWord(@RequestBody Word word){

        return wordService.deleteWord(word );
    }

    @GetMapping("/admin/word/query")
    public Object queryWord(@RequestParam(value = "pageindex" ,defaultValue ="1"  ) Integer pageIndex,
                            @RequestParam(value = "pagesize" ,defaultValue ="1"  ) Integer pageSize){
        System.out.println(pageIndex);
        System.out.println(pageSize);
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> list=wordService.queryWord();
        PageInfo pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @PostMapping("/admin/word/update")
    public boolean updateWord(@RequestBody WordHttp word){
        return wordService.updatetWord(word);
    }

    @PostMapping("/admin/word/queryByComponet")
    public List<Map<String,Object>> queryByComponet(@RequestBody Word word){
        return wordService.getWordByComponet(word.getComponet());
    }
}
