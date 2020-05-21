package com.ouyang.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ouyang.base.wrap.WrapParams;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import java.io.IOException;

import java.util.Collection;
import java.util.Enumeration;


/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2019
 * @date 2019/11/15 11:15
 * @Description TODO
 * 封装处理工具类
 */
public class WrapUtils {

    private WrapUtils() {

    }

    /**
     * request 参数写入 WrapParams
     *
     * @param request
     * @return WrapParams
     * @throws IOException
     */
    public static WrapParams paramToWrapParams(HttpServletRequest request) throws IOException, ServletException {

        JSONObject jsonObject = new JSONObject();
        ServletInputStream is = request.getInputStream();
        String paramString = ValueUtils.convertStreamToString(is);

        if (ValueUtils.valNotEmpty(paramString)) {
            jsonObject = JSON.parseObject(paramString);
        } else {

            if (request.getHeader("content-type") != null &&
                    (request.getHeader("content-type").contains("multipart/form-data")
                            || request.getHeader("content-type").contains("multipart/mixed"))) {
                Collection<Part> parts = request.getParts();
                for (Part part : parts) {
                    if (ValueUtils.valNotEmpty(part.getSubmittedFileName())) {
                        jsonObject.put(part.getName(), part);
                    }
                }
            }

            Enumeration<String> enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String tempEnu = enu.nextElement();
                jsonObject.put(tempEnu, request.getParameter(tempEnu));
            }
        }

        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attrName = attributeNames.nextElement();
            if (attrName.contains("_loginToken")) {
                jsonObject.put(attrName.split("_")[0], request.getAttribute(attrName));
            }
        }

        return new WrapParams(jsonObject);
    }


}
