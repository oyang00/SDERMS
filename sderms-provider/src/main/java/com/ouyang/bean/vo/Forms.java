package com.ouyang.bean.vo;

import com.ouyang.bean.po.Form;
import lombok.Data;

import java.util.List;

/**
 * @copyright ：ouyang 版权所有 © 2020
 * @author 16计算机 ouyang
 * @version 1.0
 * @date 2020/3/27 13:53
 * @Description TODO
 *   报修表单返回
 */
@Data
public class Forms {
    private long total;
    private List<Form> forms;
}
