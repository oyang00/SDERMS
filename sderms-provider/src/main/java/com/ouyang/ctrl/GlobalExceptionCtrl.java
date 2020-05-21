package com.ouyang.ctrl;


import com.ouyang.base.constant.CustomConstant;
import com.ouyang.base.wrap.WrapMapper;
import com.ouyang.base.wrap.Wrapper;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/26 21:38
 * @Description TODO
 * 全局异常处理 Ctrl
 */
@ControllerAdvice
public class GlobalExceptionCtrl {

    // 请求方法异常处理
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Wrapper<String> exceptionHandler() {
        return WrapMapper.error(CustomConstant.Wrap.CODE_METHOD_FAILURE, "请求方法错误");
    }

    // 全局异常处理
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Wrapper<String> globalExceptionHandler(Exception e) {
        e.printStackTrace();
        return WrapMapper.error(CustomConstant.Wrap.CODE_SERVER_EXCEPTION, "服务器异常");
    }
}
