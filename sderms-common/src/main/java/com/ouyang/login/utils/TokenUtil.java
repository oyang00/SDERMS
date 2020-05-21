package com.ouyang.login.utils;

import com.ouyang.base.utils.ValueUtils;
import com.ouyang.login.storage.Storage;
import com.ouyang.login.storage.Token;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2019
 * @date 2020/3/12 22:25
 * @Description TODO
 * Token 工具类
 */
public class TokenUtil {

    // 获取 request 的 Token
    public static Token checkToken(String tokenCode) {
        Token token = Storage.get(tokenCode);
        if (ValueUtils.valEmpty(token)) {
            return null;
        }
        if(token.effectiveTime == -1){
            // 永久存储
            return token;
        }
        if (LocalDateTime.now().isAfter(token.generationTime.plusHours(token.effectiveTime))) {
            // Token 过期
            Storage.remove(tokenCode);
            return null;
        }
        return token;
    }

    // 存入 Token
    public static String putTokenStorage(Token token) {
        token.generationTime = LocalDateTime.now();
        if (token.effectiveTime == 0) {
            token.effectiveTime = 5;
        }
        String tokenCode = UUID.randomUUID().toString().replaceAll("-", "");
        token.code = tokenCode;
        Storage.put(tokenCode, token);
        return tokenCode;
    }

    // 移除 Token
    public static void removeToken(String tokenCode) {
        Storage.remove(tokenCode);
    }
}
