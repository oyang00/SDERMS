package com.ouyang.login.storage;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2019
 * @date 2020/3/12 22:39
 * @Description TODO
 * Token bean
 */
public class Token {
    // token code
    public String code;
    // 有效时间，单位:时，-1 为永久存储
    public long effectiveTime;
    // 生成时间
    public LocalDateTime generationTime;
    // 权限等级
    public int level;

}
