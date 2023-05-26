package com.csk.csk_english.controller;

import com.csk.csk_english.domain.User;
import com.csk.csk_english.domain.UserWord;
import com.csk.csk_english.domain.Word;
import com.csk.csk_english.domain.WordListUser;
import com.csk.csk_english.mapper.WordMapper;
import com.csk.csk_english.service.WordServiceImpl;
import com.csk.csk_english.utils.EncapsulatedUtil;
import com.csk.csk_english.utils.TransferUtil;
import com.csk.csk_english.utils.jsonToData;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class WordController {
    @Autowired
    private WordServiceImpl wordService;

    @Autowired
    private WordMapper wordMapper;
    @Autowired
    private EncapsulatedUtil encapsulatedUtil;
    @GetMapping("/word/getAll")
    public Object getAll(@RequestParam(value = "pageindex" ,defaultValue ="1"  ) Integer pageIndex,
                         @RequestParam(value = "pagesize" ,defaultValue ="1"  ) Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,Object>> list=wordService.getAllword();
        return encapsulatedUtil.encapsObject(list);
    }




    @GetMapping("/word/insert")
    public void insertWord() throws IOException {
        List <String>list = new ArrayList();//list.add()：向列表尾部添加元素
        list.add("my");
        list.add("word");
        list.add("go");
        list.add("come");

        for(int i=2;i<list.size();i++){
            String json= TransferUtil.getWordDetail(list.get(i));
            Word word=jsonToData.toData(json);
            System.out.println(word.getComponet());
            wordService.insertWord(word);
        }

    }

    //获取单词详情
    @PostMapping("word/queryword")
    public Word getquery(@RequestBody Word word) throws IOException {
        String json= TransferUtil.getWordDetail(word.getComponet());
        Word queryword=jsonToData.toData(json);
        return  queryword;
    }
    //获取未学习的单词id
    @PostMapping("/word/getNoWord")
    public List getNoWord(@RequestBody User user){
        int userid=(int)user.getUserid();
        List list= wordService.getNoWord(userid);
        if(list.size()==0){
            wordService.wordIsExist(userid);
            List<Integer> s=wordMapper.getAllID();
            for(int id :s){
                wordService.buildUserWord(userid,id);
            }
            list = wordService.getNoWord(userid);
        }
        return list;
    }

    //获取用户词库的单词id
    @PostMapping("/word/getWordAll")
    public List<Map<String,Object>> getWordAll(@RequestBody User user){
        return wordService.getWordAll(user.getUserid());

    }

    //获取用户自定义词库的单词
    @PostMapping("/word/getUserWord")
    public List<Map<String,Object>> getWordUserAll(@RequestBody User user){
        List<Map<String,Object>> list =wordService.getWordAll(user.getUserid());
        System.out.println(user.getUserid());
        System.out.println(list);
        int id=0;
        Map<String,Object> word=null;
        List<Map<String,Object>>  userWordList =new ArrayList<>();
        for (int i=0;i<list.size();i++){
             id= (int) list.get(i).get("wordID");
            System.out.println(id);
             word=wordService.getWordDetail(id);
             System.out.println(word);
             userWordList.add(word);
        }
        return userWordList;
    }

    //获取基础词库的所有单词内容
    @GetMapping("/word/getAllWord")
    public List<Map<String,Object>> getWordAll(){
        System.out.println(123);
        System.out.println( wordService.getAllWord());
        return  wordService.getAllWord();
    }

    //获取某个单词的单词详情
    @PostMapping("/word/getWordDetail")
    public Map<String,Object> getWordDetail(@RequestBody Word word){
        return wordService.getWordDetail(word.getId());
    }

    //插入某个单词
    @PostMapping("/word/insertword")
    public  void insert(@RequestBody Word word) throws Exception{
        String json= TransferUtil.getWordDetail(word.getComponet());
        Word newword=jsonToData.toData(json);
        wordService.insertWord(newword);
    }

    //删除用户单词映射记录
    @PostMapping("/word/wordIsExit")
    public void wordIsExist(@RequestBody User user){
        wordService.wordIsExist(user.getUserid());
    }

    //建立用户单词映射
    @PostMapping("/word/buildUserWord")
    public void buildUserWord(@RequestBody UserWord userWord){
        wordService.buildUserWord(userWord.getUserid(),userWord.getWordid());
    }

    //向用户词书内添加单词
    @PostMapping("/word/addUserWord")
    public boolean addUserWord(@RequestBody UserWord userWord){
        List<Map<String,Object>> list= wordService.getWordAll(userWord.getUserid());
        for(int i=0;i<list.size();i++){
            if(list.get(i).get("wordID").equals(userWord.getWordid())){
                return false;
            }
        }

        wordService.buildUserWord(userWord.getUserid(),userWord.getWordid());
        return true;
    }

    //向用户词书内删除单词
    @PostMapping("/word/deleteUserWord")
    public boolean deleteUserWord(@RequestBody UserWord userWord){
        List<Map<String,Object>> list= wordService.getWordAll(userWord.getUserid());
        for(int i=0;i<list.size();i++){
            if(list.get(i).get("wordID").equals(userWord.getWordid())){
                wordService.deleteUserWord(userWord.getUserid(),userWord.getWordid());
                return true;
            }
        }
        return false;
    }
    //获取单词id
    @PostMapping("/word/getWordId")
    public int getWordId(@RequestBody Word word){

        return wordService.getWordId(word.getComponet());
    }

    //用户自定义词书
    @PostMapping("/word/setWords")
    public void setWords(@RequestBody WordListUser wordListUser) throws IOException, InterruptedException {
        List<String>list=wordListUser.getList();
        int userid=wordListUser.getUserid();
        System.out.println(userid);
        List<String> s=wordService.getAllContent();
        for(String  word :list){
            if(!s.contains(word)){
                String json= TransferUtil.getWordDetail(word);
                Word newword=jsonToData.toData(json);
                wordService.insertWord(newword);
                Thread.sleep(1 * 1000);
            }
        }
        wordService.wordIsExist(userid);
        for(String  word :list){
           int wordid=wordService.getWordId(word);
           wordService.buildUserWord(userid,wordid);
        }


    }
    @PostMapping("/word/learnWord")
    public void learnWord(@RequestBody UserWord userWord) {
        DateFormat learnDate = new SimpleDateFormat("yyyy-MM-dd");//注意月和小时的格式为两个大写字母
        Date date = new Date();//获得当前时间
        String da = learnDate.format(date);//将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中
        System.out.println(da);
        wordService.learnWord(userWord.getWordid(), userWord.getUserid(),da);
    }
    @PostMapping("/word/resetWord")
    public void resetWord(@RequestBody UserWord userWord) {
        wordService.lastWord(userWord.getWordid(), userWord.getUserid());
    }
    @PostMapping("/word/getSelectMeaning")
    public List<String> getSelectMeaning(@RequestBody UserWord userWord){
        return wordService.getMeaning(userWord.getWordid());
    }
    @PostMapping("/word/getdatelist")
    public List<Map<String,Object>> datelist(@RequestBody UserWord userWord){
        return wordService.datelist(userWord.getUserid());
    }
    //获取学习进度
    @PostMapping("/word/getProcess")
    public Object getProcess(@RequestBody UserWord userWord){

        return wordService.getProcess(userWord.getUserid());

    }


}
