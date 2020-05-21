package com.ouyang.ctrl.admin;

import com.ouyang.base.wrap.WrapParams;
import com.ouyang.base.wrap.Wrapper;
import com.ouyang.bean.UserToken;
import com.ouyang.bean.vo.Dormitories;
import com.ouyang.constant.Constant;
import com.ouyang.login.aspect.NeedLogin;
import com.ouyang.service.admin.DormitoryService;
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
 * @date 2020/3/27 19:25
 * @Description TODO
 * 管理员宿舍 Ctrl
 */
@RestController
@RequestMapping("admin/dormitory")
@AllArgsConstructor
public class DormitoryCtrl {

    private DormitoryService dormitoryService;

    // 获取所有宿舍
    @GetMapping("all")
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param(value = "pageNum", method = Method.NUMBER), @Param(value = "pageSize", method = Method.NUMBER)})
    public Wrapper<Dormitories> all(WrapParams wrapParams) {
        return dormitoryService.all(wrapParams.getIntValue("pageNum"), wrapParams.getIntValue("pageSize"));
    }

    // 查询某个宿舍
    @GetMapping("query")
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param("query"), @Param(value = "pageNum", method = Method.NUMBER), @Param(value = "pageSize", method = Method.NUMBER)})
    public Wrapper<Dormitories> query(WrapParams wrapParams) {
        return dormitoryService.query(wrapParams.getString("query"), wrapParams.getIntValue("pageNum"), wrapParams.getIntValue("pageSize"));
    }

    // 新建宿舍
    @PostMapping()
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param("adminPass"), @Param("dormitoryName")})
    public Wrapper<String> newDormitory(WrapParams wrapParams) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return dormitoryService.newDormitory(((UserToken) wrapParams.getTokenValue("user")).getAdminPass(),
                wrapParams.getString("adminPass"), wrapParams.getString("dormitoryName"));
    }

    // 删除宿舍
    @DeleteMapping()
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param("adminPass"), @Param("dormitoryId")})
    public Wrapper<String> deleteDormitory(WrapParams wrapParams) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return dormitoryService.deleteDormitory(((UserToken) wrapParams.getTokenValue("user")).getAdminPass(),
                wrapParams.getString("adminPass"), wrapParams.getLongValue("dormitoryId"));
    }
}
