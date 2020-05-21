package com.ouyang.bean.vo;

import com.ouyang.bean.po.Dormitory;
import lombok.Data;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/26 20:49
 * @Description TODO
 * 登录学生返回
 */
@Data
public class LoginStudent extends Login {
    private String name;
    private Dormitory dormitory;
}
