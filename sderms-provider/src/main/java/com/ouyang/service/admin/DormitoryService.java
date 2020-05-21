package com.ouyang.service.admin;

import com.ouyang.base.wrap.WrapMapper;
import com.ouyang.base.wrap.Wrapper;
import com.ouyang.bean.vo.Dormitories;
import com.ouyang.mapper.admin.DormitoryMapper;
import com.ouyang.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 19:24
 * @Description TODO
 * 管理员宿舍 Service
 */
@Service
@AllArgsConstructor
public class DormitoryService {

    private DormitoryMapper dormitoryMapper;

    // 获取所有宿舍
    public Wrapper<Dormitories> all(int pageNum, int pageSize) {
        Dormitories dormitories = new Dormitories();
        pageNum = (pageNum - 1) * pageSize;
        dormitories.setDormitories(dormitoryMapper.findDormitoryAll(pageNum, pageSize));
        dormitories.setTotal(dormitoryMapper.countDormitoryAll());
        return WrapMapper.okObtain("获取成功", dormitories);
    }

    // 查询某个宿舍
    public Wrapper<Dormitories> query(String query, int pageNum, int pageSize) {
        Dormitories dormitories = new Dormitories();
        pageNum = (pageNum - 1) * pageSize;
        query = "%" + query + "%";
        dormitories.setDormitories(dormitoryMapper.findDormitoryQuery(query, pageNum, pageSize));
        dormitories.setTotal(dormitoryMapper.countDormitoryQuery(query));
        return WrapMapper.okObtain("获取成功", dormitories);
    }

    // 新建宿舍
    public Wrapper<String> newDormitory(String adminStoragePass, String adminPass, String dormitoryName) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        adminPass = PasswordUtil.encrypt(adminPass);
        if (!adminStoragePass.equals(adminPass)) {
            return WrapMapper.errorExec("管理员密码错误");
        }
        if (dormitoryMapper.insertDormitory(dormitoryName) > 0) {
            return WrapMapper.okExec("新建成功");
        }
        return WrapMapper.errorExec("新建失败");
    }

    // 删除宿舍
    public Wrapper<String> deleteDormitory(String adminStoragePass, String adminPass, long dormitoryId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        adminPass = PasswordUtil.encrypt(adminPass);
        if (!adminStoragePass.equals(adminPass)) {
            return WrapMapper.errorExec("管理员密码错误");
        }
        if (dormitoryMapper.deleteDormitory(dormitoryId) > 0) {
            return WrapMapper.okExec("删除成功");
        }
        return WrapMapper.errorExec("删除失败");
    }

}
