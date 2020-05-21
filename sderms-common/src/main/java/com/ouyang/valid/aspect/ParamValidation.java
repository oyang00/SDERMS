package com.ouyang.valid.aspect;

import java.lang.annotation.*;

/**
 * @copyright ：ouyang 版权所有 © 2019
 * @author 16计算机 ouyang
 * @version 1.0
 * @date 2019/11/15 11:16
 * @Description TODO
 *   ParamValidation 注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamValidation {
    Param[] value() default {};
}



