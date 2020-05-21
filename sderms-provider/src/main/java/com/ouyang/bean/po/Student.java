package com.ouyang.bean.po;

import lombok.Data;

/**
 * @copyright ：ouyang 版权所有 © 2020
 * @author 16计算机 ouyang
 * @version 1.0
 * @date 2020/3/26 20:19
 * @Description TODO
 *   学生表
 */
@Data
public class Student {
    private String account;
    private String password;
    private String name;
    private Dormitory dormitory;
}
