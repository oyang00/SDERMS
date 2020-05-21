package com.ouyang.service.admin;

import com.ouyang.base.wrap.WrapMapper;
import com.ouyang.base.wrap.Wrapper;
import com.ouyang.mapper.admin.FormMapper;
import com.ouyang.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 20:09
 * @Description TODO
 * 管理员报修 Service
 */
@Service
@AllArgsConstructor
public class FormService {

    private FormMapper formMapper;

    // 删除报修
    public Wrapper<String> deleteForm(String adminStoragePass, String adminPass, long formId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        adminPass = PasswordUtil.encrypt(adminPass);
        if (!adminStoragePass.equals(adminPass)) {
            return WrapMapper.errorExec("管理员密码错误");
        }
        if (formMapper.deleteForm(formId) > 0) {
            return WrapMapper.okExec("删除成功");
        }
        return WrapMapper.errorExec("删除失败");
    }
}
