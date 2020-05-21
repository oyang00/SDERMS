package com.ouyang.ctrl;

import com.ouyang.base.wrap.WrapParams;
import com.ouyang.base.wrap.Wrapper;
import com.ouyang.bean.UserToken;
import com.ouyang.bean.po.Dormitory;
import com.ouyang.bean.vo.Login;
import com.ouyang.constant.Constant;
import com.ouyang.login.aspect.NeedLogin;
import com.ouyang.service.UserService;
import com.ouyang.valid.aspect.Param;
import com.ouyang.valid.aspect.ParamValidation;
import com.ouyang.valid.bean.Method;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/26 21:10
 * @Description TODO
 * 用户 Ctrl
 */
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserCtrl {

    private UserService userService;

    // 登录
    @PostMapping("login")
    @ParamValidation({@Param("account"), @Param("password"), @Param(value = "type", method = Method.NUMBER)})
    public Wrapper<Login> login(WrapParams wrapParams) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return userService.login(wrapParams.getString("account"), wrapParams.getString("password"), wrapParams.getIntValue("type"));
    }

    // 注销
    @PutMapping("logout")
    @NeedLogin("user")
    @ParamValidation()
    public Wrapper<String> logout(WrapParams wrapParams) {
        return userService.logout((UserToken) wrapParams.getTokenValue("user"));
    }

    // 检测学生是否存在
    @GetMapping("check")
    @ParamValidation(@Param("account"))
    public Wrapper<String> check(WrapParams wrapParams) {
        return userService.check(wrapParams.getString("account"));
    }

    // 学生注册
    @PostMapping("register")
    @ParamValidation({@Param("account"), @Param("password"), @Param("name"), @Param(value = "dormitory", method = Method.NUMBER)})
    public Wrapper<String> register(WrapParams wrapParams) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(wrapParams);
        return userService.register(wrapParams.getString("account"), wrapParams.getString("password"),
                wrapParams.getString("name"), wrapParams.getLongValue("dormitory"));
    }

    // 修改密码
    @PutMapping("password")
    @NeedLogin("user")
    @ParamValidation({@Param("oldPass"), @Param("newPass")})
    public Wrapper<String> password(WrapParams wrapParams) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return userService.password(((UserToken) wrapParams.getTokenValue("user")).getAccount(),
                wrapParams.getString("oldPass"), wrapParams.getString("newPass"),
                ((UserToken) wrapParams.getTokenValue("user")).level);
    }

    // 学生修改姓名
    @PutMapping("name")
    @NeedLogin(value = "user", level = Constant.IDENTITY_STUDENT)
    @ParamValidation(@Param("newName"))
    public Wrapper<String> name(WrapParams wrapParams) {
        return userService.name(((UserToken) wrapParams.getTokenValue("user")).getAccount(), wrapParams.getString("newName"));
    }

    // 获取所有宿舍
    @GetMapping("dormitory")
    @ParamValidation()
    public Wrapper<List<Dormitory>> dormitory(WrapParams wrapParams) {
        return userService.dormitory();
    }
}
