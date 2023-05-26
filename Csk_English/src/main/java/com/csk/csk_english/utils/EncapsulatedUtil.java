package com.csk.csk_english.utils;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yang
 */
@Service
public class EncapsulatedUtil {
    public Object encapsData(List<Map<String,Object>> list){
        //封装数据发给前端。
        JSONObject jsonObject = new JSONObject();
        JSONArray message = new JSONArray();
        message.addAll(list);
        //meta
        JSONObject meta = new JSONObject();
        meta.put("msg","获取成功");
        meta.put("status",200);
        jsonObject.put("message",message);
        jsonObject.put("meta",meta);
        return jsonObject;
    }

    public Object encapsObject(Object obj){
        //封装数据发给前端。
        JSONObject jsonObject = new JSONObject();
        //message
        JSONObject message = new JSONObject();
        message.put("data",obj);
        //meta
        JSONObject meta = new JSONObject();
        meta.put("msg","获取成功");
        meta.put("status",200);

        jsonObject.put("message",message);
        jsonObject.put("meta",meta);
        return jsonObject;
    }


}
