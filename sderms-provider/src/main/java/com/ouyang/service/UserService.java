package com.ouyang.service;

import com.ouyang.base.utils.ValueUtils;
import com.ouyang.base.wrap.WrapMapper;
import com.ouyang.base.wrap.Wrapper;
import com.ouyang.bean.UserToken;
import com.ouyang.bean.po.Admin;
import com.ouyang.bean.po.Dormitory;
import com.ouyang.bean.po.Repair;
import com.ouyang.bean.po.Student;
import com.ouyang.bean.vo.Login;
import com.ouyang.bean.vo.LoginStudent;
import com.ouyang.constant.Constant;
import com.ouyang.login.utils.TokenUtil;
import com.ouyang.mapper.UserMapper;
import com.ouyang.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/26 20:43
 * @Description TODO
 * 用户 Service
 */
@Service
@AllArgsConstructor
public class UserService {

    private UserMapper userMapper;

    // 账号密码登录
    public Wrapper<Login> login(String account, String password, int type) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Login login;
        password = PasswordUtil.encrypt(password);
        switch (type) {
            case Constant
                    .IDENTITY_STUDENT:
                // 学生
                Student student = userMapper.findStudentByAccountAndPassword(account, password);
                if (ValueUtils.valEmpty(student)) {
                    return WrapMapper.errorExec("账号或密码错误");
                }
                login = new LoginStudent();
                Login loginTmp = loginAfter(student.getAccount(), null, Constant.IDENTITY_STUDENT);
                BeanUtils.copyProperties(student, login);
                login.setToken(loginTmp.getToken());
                break;
            case Constant.IDENTITY_REPAIR:
                // 维修人员
                Repair repair = userMapper.findRepairByAccountAndPassword(account, password);
                if (ValueUtils.valEmpty(repair)) {
                    return WrapMapper.errorExec("账号或密码错误");
                }
                login = loginAfter(repair.getAccount(), null, Constant.IDENTITY_REPAIR);
                break;
            case Constant.IDENTITY_ADMIN:
                // 管理员
                Admin admin = userMapper.findAdminByAccountAndPassword(account, password);
                if (ValueUtils.valEmpty(admin)) {
                    return WrapMapper.errorExec("账号或密码错误");
                }
                login = loginAfter(admin.getAccount(), admin.getPassword(), Constant.IDENTITY_ADMIN);
                break;
            default:
                // type 类型错误
                return WrapMapper.errorExec("未知的用户类型");
        }
        return WrapMapper.okExec("登录成功", login);
    }

    // 登录验证之后处理
    private Login loginAfter(String account, String adminPass, int type) {
        UserToken userToken = new UserToken();
        userToken.setAccount(account);
        userToken.setAdminPass(adminPass);
        userToken.level = type;
        userToken.effectiveTime = -1;
        String tokenCode = TokenUtil.putTokenStorage(userToken);
        Login login = new Login();
        login.setToken(tokenCode);
        login.setAccount(account);
        return login;
    }

    // 注销
    public Wrapper<String> logout(UserToken token) {
        TokenUtil.removeToken(token.code);
        return WrapMapper.okExec("注销成功");
    }

    // 检查学生是否存在
    public Wrapper<String> check(String account) {
        Student student = userMapper.findStudentByAccount(account);
        if (ValueUtils.valNotEmpty(student)) {
            return WrapMapper.errorExec("账号已存在");
        }
        return WrapMapper.okExec("账号不存在");
    }

    // 新增学生
    public Wrapper<String> register(String account, String password, String name, long dormitory) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        password = PasswordUtil.encrypt(password);
        if (userMapper.insertStudent(account, password, name, dormitory) > 0) {
            return WrapMapper.okExec("注册成功");
        }
        return WrapMapper.errorExec("注册失败");
    }

    // 修改密码
    public Wrapper<String> password(String account, String oldPass, String newPass, int type) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        oldPass = PasswordUtil.encrypt(oldPass);
        newPass = PasswordUtil.encrypt(newPass);
        int flag;
        switch (type) {
            case Constant.IDENTITY_STUDENT:
                flag = userMapper.updateStudentPassword(account, oldPass, newPass);
                break;
            case Constant.IDENTITY_REPAIR:
                flag = userMapper.updateRepairPassword(account, oldPass, newPass);
                break;
            case Constant.IDENTITY_ADMIN:
                flag = userMapper.updateAdminPassword(account, oldPass, newPass);
                break;
            default:
                flag = -1;
        }
        if (flag == -1) {
            return WrapMapper.errorExec("类型错误");
        }
        if (flag > 0) {
            return WrapMapper.okExec("修改成功");
        }
        return WrapMapper.errorExec("修改失败");
    }

    // 修改学生姓名
    public Wrapper<String> name(String account, String name) {
        if (userMapper.updateStudentName(account, name) > 0) {
            return WrapMapper.okExec("修改成功");
        }
        return WrapMapper.errorExec("修改失败");
    }

    // 获取所有宿舍
    public Wrapper<List<Dormitory>> dormitory() {
        return WrapMapper.okObtain("获取成功", userMapper.findDormitoryAll());
    }
}
