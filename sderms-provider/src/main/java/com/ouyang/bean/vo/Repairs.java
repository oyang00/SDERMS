package com.ouyang.bean.vo;

import com.ouyang.bean.po.Repair;
import lombok.Data;

import java.util.List;

/**
 * @copyright ：ouyang 版权所有 © 2020
 * @author 16计算机 ouyang
 * @version 1.0
 * @date 2020/3/27 18:00
 * @Description TODO
 *   维修人员返回
 */
@Data
public class Repairs {
    private long total;
    private List<Repair> repairs;
}
