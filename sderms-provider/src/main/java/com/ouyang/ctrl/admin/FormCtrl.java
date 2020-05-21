package com.ouyang.ctrl.admin;

import com.ouyang.base.wrap.WrapParams;
import com.ouyang.base.wrap.Wrapper;
import com.ouyang.bean.UserToken;
import com.ouyang.constant.Constant;
import com.ouyang.login.aspect.NeedLogin;
import com.ouyang.service.admin.FormService;
import com.ouyang.valid.aspect.Param;
import com.ouyang.valid.aspect.ParamValidation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 20:11
 * @Description TODO
 * 管理员报修 Ctrl
 */
@RestController
@RequestMapping("admin/form")
@AllArgsConstructor
public class FormCtrl {

    private FormService formService;

    // 删除报修
    @DeleteMapping()
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param("adminPass"), @Param("formId")})
    public Wrapper<String> deleteForm(WrapParams wrapParams) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return formService.deleteForm(((UserToken) wrapParams.getTokenValue("user")).getAdminPass(),
                wrapParams.getString("adminPass"), wrapParams.getLongValue("formId"));
    }
}
