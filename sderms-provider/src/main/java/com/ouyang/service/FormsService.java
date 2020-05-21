package com.ouyang.service;

import com.ouyang.base.constant.CustomConstant;
import com.ouyang.base.utils.ValueUtils;
import com.ouyang.base.wrap.WrapMapper;
import com.ouyang.base.wrap.Wrapper;
import com.ouyang.bean.vo.Forms;
import com.ouyang.constant.Constant;
import com.ouyang.mapper.FormsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 11:37
 * @Description TODO
 * 报修 Service
 */
@Service
@AllArgsConstructor
public class FormsService {

    private FormsMapper formsMapper;

    // 提出报修
    public Wrapper<String> newForm(String propose, String title, String desc) {
        if (formsMapper.insertForm(propose, title, desc, LocalDateTime.now(), Constant.HANDLE_NO) > 0) {
            return WrapMapper.okExec("提交成功");
        }
        return WrapMapper.errorExec("提交失败");
    }

    // 完善报修
    public Wrapper<String> updateForm(String repair, String desc, long id) {
        if (formsMapper.updateFormRepair(id, repair, desc, LocalDateTime.now(), Constant.HANDLE_YES) > 0) {
            return WrapMapper.okExec("修改成功");
        }
        return WrapMapper.errorExec("修改失败");
    }

    // 获取某人报修
    public Wrapper<Forms> one(String account, int type, int pageNum, int pageSize, int isHandle) {
        Forms forms = new Forms();
        pageNum = (pageNum - 1) * pageSize;
        switch (type) {
            case Constant.IDENTITY_STUDENT:
                if (isHandle == -1) {
                    forms.setForms(formsMapper.findFormByPropose(account, pageNum, pageSize));
                    forms.setTotal(formsMapper.countFormByPropose(account));
                } else {
                    forms.setForms(formsMapper.findFormByProposeAndHandle(account, pageNum, pageSize, isHandle));
                    forms.setTotal(formsMapper.countFormByProposeAndHandle(account, isHandle));
                }
                break;
            case Constant.IDENTITY_REPAIR:
                if (isHandle == -1) {
                    forms.setForms(formsMapper.findFormByRepair(account, pageNum, pageSize));
                    forms.setTotal(formsMapper.countFormByRepair(account));
                } else {
                    forms.setForms(formsMapper.findFormByRepairAndHandle(account, pageNum, pageSize, isHandle));
                    forms.setTotal(formsMapper.countFormByRepairAndHandle(account, isHandle));
                }
                break;
            default:
                return WrapMapper.errorObtain("类型错误");
        }
        return WrapMapper.okObtain("获取成功", forms);
    }

    // 获取所有报修
    public Wrapper<Forms> all(int pageNum, int pageSize, int isHandle) {
        Forms forms = new Forms();
        pageNum = (pageNum - 1) * pageSize;
        if (isHandle == -1) {
            forms.setForms(formsMapper.findFormAll(pageNum, pageSize));
            forms.setTotal(formsMapper.countFormAll());
        } else {
            forms.setForms(formsMapper.findFormAllByHandle(pageNum, pageSize, isHandle));
            forms.setTotal(formsMapper.countFormAllByHandle(isHandle));
        }
        return WrapMapper.okObtain("获取成功", forms);
    }

    // 查询某个报修
    public Wrapper<Forms> query(String query, int pageNum, int pageSize) {
        Forms forms = new Forms();
        query = "%" + query + "%";
        pageNum = (pageNum - 1) * pageSize;
        forms.setForms(formsMapper.findFormQuery(query, pageNum, pageSize));
        forms.setTotal(formsMapper.countFormQuery(query));
        return WrapMapper.okObtain("获取成功", forms);
    }

    // 报修反馈
    public Wrapper<String> updateFormFeedback(String propose, long id, String feedback) {
        if (formsMapper.updateFormFeedback(id, feedback, propose) > 0) {
            return WrapMapper.okExec("报修成功");
        }
        return WrapMapper.errorExec("报修失败");
    }
}
