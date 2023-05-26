package com.csk.csk_english.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.csk.csk_english.domain.Word;
import com.csk.csk_english.service.WordServiceImpl;

public class jsonToData {

    //解析json数据并写入数据库
    public static Word toData(String json){
        JSONObject jsonObject;

        jsonObject = JSON.parseObject(json);
        String compent=jsonObject.getString("query");
        JSONArray jsonArray = jsonObject.getJSONArray("web");
        String meaning=jsonObject.getString("translation").replace("[","").replace("]","");
        String wordGroup=jsonArray.getJSONObject(1).getString("key");
        String wordGroupMeaning=jsonArray.getJSONObject(1).getString("value").replace("[","").replace("]","");
        String wordGroup1=jsonArray.getJSONObject(2).getString("key");
        String wordGroupMeaning1=jsonArray.getJSONObject(2).getString("value").replace("[","").replace("]","");
        JSONObject jsonObject1 = jsonObject.getJSONObject("basic");
        String usPhonetic=jsonObject1.getString("us-phonetic");
        String usSpeech=jsonObject1.getString("us-speech");
        String ukPhonetic=jsonObject1.getString("us-phonetic");
        String ukSpeech=jsonObject1.getString("us-speech");
        String explain=jsonObject1.getString("explains").replace("[","").replace("]","");
        //System.out.println(compent+ meaning+wordGroup+wordGroup1+wordGroupMeaning+wordGroupMeaning1+ukSpeech+ukPhonetic+usSpeech+usPhonetic+explain);
        Word word=new Word(compent,meaning,wordGroup,wordGroupMeaning,wordGroup1,wordGroupMeaning1,usSpeech,usPhonetic,ukSpeech,ukPhonetic,explain);
        System.out.println(word.toString());   return  word;

    }


}
