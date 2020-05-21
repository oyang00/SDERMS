package com.ouyang.mapper;

import com.ouyang.bean.po.Admin;
import com.ouyang.bean.po.Dormitory;
import com.ouyang.bean.po.Repair;
import com.ouyang.bean.po.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/26 20:30
 * @Description TODO
 * 用户 Mapper
 */
public interface UserMapper {
    // 根据账号密码获取学生
    @Select("SELECT account, password, name, dormitory FROM student WHERE account = #{account} AND password = #{password}")
    @Results({@Result(property = "dormitory", column = "dormitory", one = @One(select = "com.ouyang.mapper.UserMapper.findDormitoryById"))})
    Student findStudentByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    // 根据账号密码获取维修人员
    @Select("SELECT account, password FROM repair WHERE account = #{account} AND password = #{password}")
    Repair findRepairByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    // 根据账号密码获取管理员
    @Select("SELECT account, password FROM admin WHERE account = #{account} AND password = #{password}")
    Admin findAdminByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    // 根据 id 获取宿舍
    @Select("SELECT id, name FROM dormitory WHERE id = #{id}")
    Dormitory findDormitoryById(@Param("id") long id);

    // 根据账号获取学生
    @Select("SELECT account, name, dormitory FROM student WHERE account = #{account}")
    @Results({@Result(property = "dormitory", column = "dormitory", one = @One(select = "com.ouyang.mapper.UserMapper.findDormitoryById"))})
    Student findStudentByAccount(@Param("account") String account);

    // 根据账号获取维修人员
    @Select("SELECT account FROM repair WHERE account = #{account}")
    Repair findRepairByAccount(@Param("account") String account);

    // 新增学生
    @Insert("INSERT INTO student(account, password, name, dormitory) VALUES(#{account}, #{password}, #{name}, #{dormitory})")
    int insertStudent(@Param("account") String account, @Param("password") String password,
                      @Param("name") String name, @Param("dormitory") long dormitory);

    // 修改学生密码
    @Update("UPDATE student SET password = #{newPass} WHERE account = #{account} AND password = #{oldPass}")
    int updateStudentPassword(@Param("account") String account, @Param("oldPass") String oldPass, @Param("newPass") String newPass);

    // 修改维修人员密码
    @Update("UPDATE repair SET password = #{newPass} WHERE account = #{account} AND password = #{oldPass}")
    int updateRepairPassword(@Param("account") String account, @Param("oldPass") String oldPass, @Param("newPass") String newPass);

    // 修改维修人员密码
    @Update("UPDATE admin SET password = #{newPass} WHERE account = #{account} AND password = #{oldPass}")
    int updateAdminPassword(@Param("account") String account, @Param("oldPass") String oldPass, @Param("newPass") String newPass);

    // 学生修改姓名
    @Update("UPDATE student SET name = #{newName} WHERE account = #{account}")
    int updateStudentName(@Param("account") String account, @Param("newName") String newName);

    // 获取所有宿舍
    @Select("SELECT id, name FROM dormitory")
    List<Dormitory> findDormitoryAll();
}
