package com.ouyang.base.wrap;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @copyright ：ouyang 版权所有 © 2019
 * @author 16计算机 ouyang
 * @version 1.0
 * @date 2019/11/15 10:15
 * @Description TODO
 *   返回封装
 */
@Data
@AllArgsConstructor
public class Wrapper<T> {
    private int code;
    private String msg;
    private T data;

}
