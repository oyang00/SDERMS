package com.ouyang.ctrl.admin;

import com.ouyang.base.wrap.WrapParams;
import com.ouyang.base.wrap.Wrapper;
import com.ouyang.bean.UserToken;
import com.ouyang.bean.vo.Students;
import com.ouyang.constant.Constant;
import com.ouyang.login.aspect.NeedLogin;
import com.ouyang.service.admin.StudentService;
import com.ouyang.valid.aspect.Param;
import com.ouyang.valid.aspect.ParamValidation;
import com.ouyang.valid.bean.Method;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 16:00
 * @Description TODO
 * 管理员学生 Ctrl
 */
@RestController
@RequestMapping("admin/student")
@AllArgsConstructor
public class StudentCtrl {

    private StudentService studentService;

    // 获取所有学生
    @GetMapping("all")
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param(value = "pageNum", method = Method.NUMBER), @Param(value = "pageSize", method = Method.NUMBER)})
    public Wrapper<Students> all(WrapParams wrapParams) {
        return studentService.all(wrapParams.getIntValue("pageNum"), wrapParams.getIntValue("pageSize"));
    }

    // 查询某个学生
    @GetMapping("query")
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param("query"), @Param(value = "pageNum", method = Method.NUMBER), @Param(value = "pageSize", method = Method.NUMBER)})
    public Wrapper<Students> query(WrapParams wrapParams) {
        return studentService.query(wrapParams.getString("query"), wrapParams.getIntValue("pageNum"), wrapParams.getIntValue("pageSize"));
    }

    // 修改学生密码
    @PutMapping("password")
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param("adminPass"), @Param("studentAccount"), @Param("studentNewPass")})
    public Wrapper<String> password(WrapParams wrapParams) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return studentService.password(((UserToken) wrapParams.getTokenValue("user")).getAdminPass(),
                wrapParams.getString("adminPass"), wrapParams.getString("studentAccount"), wrapParams.getString("studentNewPass"));
    }

    // 删除学生
    @DeleteMapping()
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param("adminPass"), @Param("studentAccount")})
    public Wrapper<String> delete(WrapParams wrapParams) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return studentService.delete(((UserToken) wrapParams.getTokenValue("user")).getAdminPass(),
                wrapParams.getString("adminPass"), wrapParams.getString("studentAccount"));
    }

    // 修改学生宿舍
    @PutMapping("dormitory")
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param("adminPass"), @Param("studentAccount"), @Param(value = "studentNewDormitory", method = Method.NUMBER)})
    public Wrapper<String> dormitory(WrapParams wrapParams) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return studentService.dormitory(((UserToken) wrapParams.getTokenValue("user")).getAdminPass(),
                wrapParams.getString("adminPass"), wrapParams.getString("studentAccount"),
                wrapParams.getLongValue("studentNewDormitory"));
    }
}
