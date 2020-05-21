package com.ouyang.ctrl;

import com.ouyang.base.wrap.WrapParams;
import com.ouyang.base.wrap.Wrapper;
import com.ouyang.bean.UserToken;
import com.ouyang.bean.vo.Forms;
import com.ouyang.constant.Constant;
import com.ouyang.login.aspect.NeedLogin;
import com.ouyang.service.FormsService;
import com.ouyang.valid.aspect.Param;
import com.ouyang.valid.aspect.ParamValidation;
import com.ouyang.valid.bean.Method;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 11:47
 * @Description TODO
 * 报修 Ctrl
 */
@RestController
@RequestMapping("forms")
@AllArgsConstructor
public class FormsCtrl {

    private FormsService formsService;

    // 提出报修
    @PostMapping("form")
    @NeedLogin(value = "user", level = Constant.IDENTITY_STUDENT)
    @ParamValidation({@Param("title"), @Param("desc")})
    public Wrapper<String> newForm(WrapParams wrapParams) {
        return formsService.newForm(((UserToken) wrapParams.getTokenValue("user")).getAccount(),
                wrapParams.getString("title"), wrapParams.getString("desc"));
    }

    // 完善报修
    @PutMapping("form")
    @NeedLogin(value = "user", level = Constant.IDENTITY_REPAIR)
    @ParamValidation({@Param(value = "id", method = Method.NUMBER), @Param("desc")})
    public Wrapper<String> updateForm(WrapParams wrapParams) {
        return formsService.updateForm(((UserToken) wrapParams.getTokenValue("user")).getAccount(),
                wrapParams.getString("desc"), wrapParams.getLongValue("id"));
    }

    // 获取我的报修
    @GetMapping("my")
    @NeedLogin("user")
    @ParamValidation({@Param(value = "pageNum", method = Method.NUMBER), @Param(value = "pageSize", method = Method.NUMBER),
            @Param(value = "isHandle", method = Method.NUMBER)})
    public Wrapper<Forms> my(WrapParams wrapParams) {
        return formsService.one(((UserToken) wrapParams.getTokenValue("user")).getAccount(),
                ((UserToken) wrapParams.getTokenValue("user")).level, wrapParams.getIntValue("pageNum"),
                wrapParams.getIntValue("pageSize"), wrapParams.getIntValue("isHandle"));
    }

    // 获取所有报修
    @GetMapping("all")
    @NeedLogin("user")
    @ParamValidation({@Param(value = "pageNum", method = Method.NUMBER), @Param(value = "pageSize", method = Method.NUMBER),
            @Param(value = "isHandle", method = Method.NUMBER)})
    public Wrapper<Forms> all(WrapParams wrapParams) {
        return formsService.all(wrapParams.getIntValue("pageNum"), wrapParams.getIntValue("pageSize"),
                wrapParams.getIntValue("isHandle"));
    }

    // 获取某人报修
    @GetMapping("one")
    @NeedLogin("user")
    @ParamValidation({@Param("account"), @Param(value = "type", method = Method.NUMBER),
            @Param(value = "pageNum", method = Method.NUMBER), @Param(value = "pageSize", method = Method.NUMBER),
            @Param(value = "isHandle", method = Method.NUMBER)})
    public Wrapper<Forms> one(WrapParams wrapParams) {
        return formsService.one(wrapParams.getString("account"), wrapParams.getIntValue("type"),
                wrapParams.getIntValue("pageNum"), wrapParams.getIntValue("pageSize"), wrapParams.getIntValue("isHandle"));
    }

    // 查询某个报修
    @GetMapping("query")
    @NeedLogin("user")
    @ParamValidation({@Param("query"), @Param(value = "pageNum", method = Method.NUMBER),
            @Param(value = "pageSize", method = Method.NUMBER)})
    public Wrapper<Forms> query(WrapParams wrapParams) {
        return formsService.query(wrapParams.getString("query"), wrapParams.getIntValue("pageNum"),
                wrapParams.getIntValue("pageSize"));
    }

    // 报修反馈
    @PutMapping("feedback")
    @NeedLogin(value = "user", level = Constant.IDENTITY_STUDENT)
    @ParamValidation({@Param(value = "id", method = Method.NUMBER), @Param("feedback")})
    public Wrapper<String> feedback(WrapParams wrapParams) {
        return formsService.updateFormFeedback(((UserToken) wrapParams.getTokenValue("user")).getAccount(),
                wrapParams.getLongValue("id"), wrapParams.getString("feedback"));
    }
}
