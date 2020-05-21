package com.ouyang.service.admin;

import com.ouyang.base.utils.ValueUtils;
import com.ouyang.base.wrap.WrapMapper;
import com.ouyang.base.wrap.Wrapper;
import com.ouyang.bean.po.Repair;
import com.ouyang.bean.vo.Repairs;
import com.ouyang.bean.vo.Students;
import com.ouyang.mapper.UserMapper;
import com.ouyang.mapper.admin.RepairMapper;
import com.ouyang.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 17:56
 * @Description TODO
 * 管理员维修人员 Service
 */
@Service
@AllArgsConstructor
public class RepairService {

    private RepairMapper repairMapper;
    private UserMapper userMapper;

    // 获取所有维修人员
    public Wrapper<Repairs> all(int pageNum, int pageSize) {
        Repairs repairs = new Repairs();
        pageNum = (pageNum - 1) * pageSize;
        repairs.setRepairs(repairMapper.findRepairAll(pageNum, pageSize));
        repairs.setTotal(repairMapper.countRepairAll());
        return WrapMapper.okObtain("获取成功", repairs);
    }

    // 查询某个维修人员
    public Wrapper<Repairs> query(String query, int pageNum, int pageSize) {
        Repairs repairs = new Repairs();
        pageNum = (pageNum - 1) * pageSize;
        query = "%" + query + "%";
        repairs.setRepairs(repairMapper.findRepairQuery(query, pageNum, pageSize));
        repairs.setTotal(repairMapper.countRepairQuery(query));
        return WrapMapper.okObtain("获取成功", repairs);
    }

    // 修改维修人员密码
    public Wrapper<String> password(String adminStoragePass, String adminPass, String repairAccount, String repairNewPass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        adminPass = PasswordUtil.encrypt(adminPass);
        if (!adminStoragePass.equals(adminPass)) {
            return WrapMapper.errorExec("管理员密码错误");
        }
        repairNewPass = PasswordUtil.encrypt(repairNewPass);
        if (repairMapper.updateRepairPassword(repairAccount, repairNewPass) > 0) {
            return WrapMapper.okExec("修改成功");
        }
        return WrapMapper.errorExec("修改失败");
    }

    // 新建维修人员
    public Wrapper<String> newRepair(String adminStoragePass, String adminPass, String repairAccount, String repairPass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        adminPass = PasswordUtil.encrypt(adminPass);
        if (!adminStoragePass.equals(adminPass)) {
            return WrapMapper.errorExec("管理员密码错误");
        }
        if (ValueUtils.valNotEmpty(userMapper.findRepairByAccount(repairAccount))) {
            return WrapMapper.errorExec("账号已存在");
        }
        repairPass = PasswordUtil.encrypt(repairPass);
        if (repairMapper.insertRepair(repairAccount, repairPass) > 0) {
            return WrapMapper.okExec("新建成功");
        }
        return WrapMapper.errorExec("新建失败");
    }

    // 删除维修人员
    public Wrapper<String> deleteRepair(String adminStoragePass, String adminPass, String repairAccount) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        adminPass = PasswordUtil.encrypt(adminPass);
        if (!adminStoragePass.equals(adminPass)) {
            return WrapMapper.errorExec("管理员密码错误");
        }
        if (repairMapper.deleteRepair(repairAccount) > 0) {
            return WrapMapper.okExec("删除成功");
        }
        return WrapMapper.errorExec("删除失败");
    }
}
