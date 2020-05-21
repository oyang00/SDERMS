package com.ouyang.ctrl.admin;

import com.ouyang.base.wrap.WrapParams;
import com.ouyang.base.wrap.Wrapper;
import com.ouyang.bean.UserToken;
import com.ouyang.bean.vo.Repairs;
import com.ouyang.constant.Constant;
import com.ouyang.login.aspect.NeedLogin;
import com.ouyang.service.admin.RepairService;
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
 * @date 2020/3/27 17:57
 * @Description TODO
 * 管理员维修人员 Ctrl
 */
@RestController
@RequestMapping("admin/repair")
@AllArgsConstructor
public class RepairCtrl {

    private RepairService repairService;

    // 获取所有维修人员
    @GetMapping("all")
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param(value = "pageNum", method = Method.NUMBER), @Param(value = "pageSize", method = Method.NUMBER)})
    public Wrapper<Repairs> all(WrapParams wrapParams) {
        return repairService.all(wrapParams.getIntValue("pageNum"), wrapParams.getIntValue("pageSize"));
    }

    // 查询某个维修人员
    @GetMapping("query")
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param("query"), @Param(value = "pageNum", method = Method.NUMBER), @Param(value = "pageSize", method = Method.NUMBER)})
    public Wrapper<Repairs> query(WrapParams wrapParams) {
        return repairService.query(wrapParams.getString("query"), wrapParams.getIntValue("pageNum"), wrapParams.getIntValue("pageSize"));
    }

    // 修改维修人员密码
    @PutMapping("password")
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param("adminPass"), @Param("repairAccount"), @Param("repairNewPass")})
    public Wrapper<String> password(WrapParams wrapParams) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return repairService.password(((UserToken) wrapParams.getTokenValue("user")).getAdminPass(),
                wrapParams.getString("adminPass"), wrapParams.getString("repairAccount"), wrapParams.getString("repairNewPass"));
    }

    // 新建维修人员
    @PostMapping()
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param("adminPass"), @Param("repairAccount"), @Param("repairPass")})
    public Wrapper<String> newRepair(WrapParams wrapParams) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return repairService.newRepair(((UserToken) wrapParams.getTokenValue("user")).getAdminPass(),
                wrapParams.getString("adminPass"), wrapParams.getString("repairAccount"), wrapParams.getString("repairPass"));
    }

    // 删除维修人员
    @DeleteMapping()
    @NeedLogin(value = "user", level = Constant.IDENTITY_ADMIN)
    @ParamValidation({@Param("adminPass"), @Param("repairAccount")})
    public Wrapper<String> deleteRepair(WrapParams wrapParams) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return repairService.deleteRepair(((UserToken) wrapParams.getTokenValue("user")).getAdminPass(),
                wrapParams.getString("adminPass"), wrapParams.getString("repairAccount"));
    }
}
