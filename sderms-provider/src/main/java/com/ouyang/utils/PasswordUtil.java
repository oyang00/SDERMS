package com.ouyang.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/26 19:42
 * @Description TODO
 * 密码工具类
 */
public class PasswordUtil {

    /**
     *  md5 加密
     * @param password
     * @return String
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String encrypt(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes("UTF8"));
        byte s[] = md.digest();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < s.length; i++) {
            result.append(Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6));
        }
        return result.toString();
    }
}
