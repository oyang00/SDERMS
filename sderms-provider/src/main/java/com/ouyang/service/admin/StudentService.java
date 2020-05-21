package com.ouyang.service.admin;

import com.ouyang.base.wrap.WrapMapper;
import com.ouyang.base.wrap.Wrapper;
import com.ouyang.bean.vo.Students;
import com.ouyang.mapper.admin.StudentMapper;
import com.ouyang.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 15:53
 * @Description TODO
 * 管理员学生 Service
 */
@Service
@AllArgsConstructor
public class StudentService {

    private StudentMapper studentMapper;

    // 获取所有学生
    public Wrapper<Students> all(int pageNum, int pageSize) {
        Students students = new Students();
        pageNum = (pageNum - 1) * pageSize;
        students.setStudents(studentMapper.findStudentAll(pageNum, pageSize));
        students.setTotal(studentMapper.countStudentAll());
        return WrapMapper.okObtain("获取成功", students);
    }

    // 查询某个学生
    public Wrapper<Students> query(String query, int pageNum, int pageSize) {
        Students students = new Students();
        query = "%" + query + "%";
        pageNum = (pageNum - 1) * pageSize;
        students.setStudents(studentMapper.findStudentQuery(query, pageNum, pageSize));
        students.setTotal(studentMapper.countStudentQuery(query));
        return WrapMapper.okObtain("获取成功", students);
    }

    // 修改学生密码
    public Wrapper<String> password(String adminStoragePass, String adminPass, String studentAccount, String studentNewPass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        adminPass = PasswordUtil.encrypt(adminPass);
        if (!adminStoragePass.equals(adminPass)) {
            return WrapMapper.errorExec("管理员密码错误");
        }
        studentNewPass = PasswordUtil.encrypt(studentNewPass);
        if (studentMapper.updateStudentPassword(studentAccount, studentNewPass) > 0) {
            return WrapMapper.okExec("修改成功");
        }
        return WrapMapper.errorExec("修改失败");
    }

    // 删除学生
    public Wrapper<String> delete(String adminStoragePass, String adminPass, String studentAccount) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        adminPass = PasswordUtil.encrypt(adminPass);
        if (!adminStoragePass.equals(adminPass)) {
            return WrapMapper.errorExec("管理员密码错误");
        }
        if (studentMapper.deleteStudent(studentAccount) > 0) {
            return WrapMapper.okExec("删除成功");
        }
        return WrapMapper.errorExec("删除失败");
    }

    // 修改学生宿舍
    public Wrapper<String> dormitory(String adminStoragePass, String adminPass, String studentAccount, long studentNewDormitory) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        adminPass = PasswordUtil.encrypt(adminPass);
        if (!adminStoragePass.equals(adminPass)) {
            return WrapMapper.errorExec("管理员密码错误");
        }
        if (studentMapper.updateStudentDormitory(studentAccount, studentNewDormitory) > 0) {
            return WrapMapper.okExec("修改成功");
        }
        return WrapMapper.errorExec("修改失败");
    }
}
