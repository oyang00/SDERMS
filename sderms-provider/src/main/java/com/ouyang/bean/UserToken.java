package com.ouyang.bean;

import com.ouyang.login.storage.Token;
import lombok.Data;

/**
 * @copyright ：ouyang 版权所有 © 2020
 * @author 16计算机 ouyang
 * @version 1.0
 * @date 2020/3/26 19:17
 * @Description TODO
 *  登录 Token
 */
@Data
public class UserToken extends Token {
    private String account;
    private String adminPass;
}
