package com.ouyang.base.utils;


import com.ouyang.base.constant.CustomConstant;

import java.io.*;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2019
 * @date 2019/11/15 11:13
 * @Description TODO
 * 值处理工具类
 */
public class ValueUtils {
    private ValueUtils() {

    }

    /**
     * 输入流转化为 String
     *
     * @param is
     * @return String
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    /**
     * 判断值是否为不为空
     *
     * @param obj
     * @return boolean
     */
    public static boolean valNotEmpty(Object obj) {
        boolean b = true;
        if (obj == null) {
            b = false;
        } else if (obj instanceof CharSequence) {
            if (((CharSequence) obj).length() == 0) {
                b = false;
            }
        } else if (obj instanceof Map) {
            if (((Map<?, ?>) obj).isEmpty()) {
                b = false;
            }
        } else if (obj instanceof String) {
            if ("".equals(obj) || "null".equals(obj)) {
                b = false;
            }
        } else if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            if (objs.length == 0) {
                b = false;
            } else {
                boolean empty = false;
                for (Object o : objs) {
                    if (valNotEmpty(o)) {
                        empty = true;
                        break;
                    }
                }
                b = empty;
            }
        }
        return b;
    }

    /**
     * 判断值是否为空
     *
     * @param obj
     * @return boolean
     */
    public static boolean valEmpty(Object obj) {
        return !valNotEmpty(obj);
    }

    /**
     * 判断字符串是否为整数
     *
     * @param str
     * @return boolean
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile(CustomConstant.Regular.INTEGER);
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否为浮点数
     *
     * @param str
     * @return boolean
     */
    public static boolean isDigital(String str) {
        Pattern pattern = Pattern.compile(CustomConstant.Regular.FLOAT);
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否为目录
     *
     * @param path
     * @return boolean
     */
    public static boolean isValidDirectory(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            return true;
        }
        return false;
    }


}
