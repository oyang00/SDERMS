package com.ouyang.base.wrap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ouyang.login.storage.Token;

import javax.servlet.http.Part;
import java.util.Set;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2019
 * @date 2019/11/14 19:18
 * @Description TODO
 * 参数封装
 */
public class WrapParams {
    /**
     * params 对象
     */
    private JSONObject jsonObject;

    public WrapParams(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String toString() {
        return jsonObject.toString();
    }

    /**
     * 存入字段
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        jsonObject.put(key, value);
    }

    /**
     * 获取参数数量
     *
     * @return int
     */
    public int size() {
        return jsonObject.size();
    }

    /**
     * 判断参数是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return jsonObject.isEmpty();
    }

    /**
     * 判断参数是否包含 key
     *
     * @param key
     * @return boolean
     */
    public boolean containsKey(Object key) {
        return jsonObject.containsKey(key);
    }

    /**
     * 判断参数是否包含 value
     *
     * @param value
     * @return boolean
     */
    public boolean containsValue(Object value) {
        return jsonObject.containsValue(value);
    }

    /**
     * 获取 key 获取对应 value
     *
     * @param key
     * @return Object
     */
    public Object get(Object key) {
        return jsonObject.get(key);
    }

    /**
     * 根据 key 获取对应 value，转化为 JSONObject
     *
     * @param key
     * @return JSONObject
     */
    public JSONObject getJsonObject(String key) {
        return jsonObject.getJSONObject(key);
    }

    /**
     * 根据 key 获取对应 value，转化为 JSONArray
     *
     * @param key
     * @return JSONArray
     */
    public JSONArray getJsonArray(String key) {
        return jsonObject.getJSONArray(key);
    }

    /**
     * 根据 key 获取对应 value，转化为泛型
     *
     * @param key
     * @param clazz
     * @return T
     */
    public <T> T getObject(String key, Class<T> clazz) {
        return jsonObject.getObject(key, clazz);
    }

    /**
     * 根据 key 获取对应 value，转化为 boolean
     *
     * @param key
     * @return boolean
     */
    public boolean getBooleanValue(String key) {
        return jsonObject.getBooleanValue(key);
    }

    /**
     * 根据 key 获取对应 value，转化为 int
     *
     * @param key
     * @return int
     */
    public int getIntValue(String key) {
        return jsonObject.getIntValue(key);
    }

    /**
     * 根据 key 获取对应 value，转化为 long
     * @param key
     * @return
     */
    public long getLongValue(String key) {
        return jsonObject.getLongValue(key);
    }

    /**
     * 根据 key 获取对应 value，转化为 String
     *
     * @param key
     * @return String
     */
    public String getString(String key) {
        return jsonObject.getString(key);
    }

    /**
     * 根据 key 获取对应 value，转化为 Part
     *
     * @param key
     * @return Part
     */
    public Part getPartValue(String key) {
        return jsonObject.getObject(key, Part.class);
    }

    /**
     * 根据 key 获取对应 value，转化为 Token
     *
     * @param key
     * @return Token
     */
    public Token getTokenValue(String key) {
        return jsonObject.getObject(key, Token.class);
    }

    /**
     * 清除所有参数
     */
    public void clear() {
        jsonObject.clear();
    }

    /**
     * 删除 key 对应的参数
     *
     * @param key
     * @return Object
     */
    public Object remove(String key) {
        return jsonObject.remove(key);
    }

    /**
     * 获取参数 key 集合
     *
     * @return Set<String>
     */
    public Set<String> keySet() {
        return jsonObject.keySet();
    }


}
