package com.ouyang.bean.vo;

import com.ouyang.bean.po.Dormitory;
import lombok.Data;

import java.util.List;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 19:21
 * @Description TODO
 * 宿舍返回
 */
@Data
public class Dormitories {
    private long total;
    private List<Dormitory> dormitories;
}
