package com.ouyang.bean.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/26 20:22
 * @Description TODO
 * 报修表单表
 */
@Data
public class Form {
    private long id;
    private Student propose;
    private String proposeTitle;
    private String proposeDesc;
    private LocalDateTime proposeTime;
    private Repair repair;
    private String repairDesc;
    private LocalDateTime repairTime;
    private int isHandle;
    private String feedback;
}
