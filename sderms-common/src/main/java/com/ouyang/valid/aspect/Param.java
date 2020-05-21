package com.ouyang.valid.aspect;


import com.ouyang.valid.bean.Method;

import java.lang.annotation.*;

/**
 * @copyright ：ouyang 版权所有 © 2019
 * @author 16计算机 ouyang
 * @version 1.0
 * @date 2019/11/15 11:15
 * @Description TODO
 *   Param 注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param{
    String value();
    Method[] method() default Method.NOTNULL;
}
