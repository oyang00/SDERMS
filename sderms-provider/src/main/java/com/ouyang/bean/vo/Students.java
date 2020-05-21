package com.ouyang.bean.vo;

import com.ouyang.bean.po.Student;
import lombok.Data;

import java.util.List;

/**
 * @copyright ：ouyang 版权所有 © 2020
 * @author 16计算机 ouyang
 * @version 1.0
 * @date 2020/3/27 15:55
 * @Description TODO
 *   学生返回
 */
@Data
public class Students {
    private long total;
    private List<Student> students;
}
