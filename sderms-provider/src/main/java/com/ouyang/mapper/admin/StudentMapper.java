package com.ouyang.mapper.admin;

import com.ouyang.bean.po.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 15:48
 * @Description TODO
 * 管理员学生 Mapper
 */
public interface StudentMapper {

    // 获取所有学生
    @Select("SELECT account, name, dormitory FROM student LIMIT #{pageNum}, #{pageSize}")
    @Results({@Result(property = "dormitory", column = "dormitory", one = @One(select = "com.ouyang.mapper.UserMapper.findDormitoryById"))})
    List<Student> findStudentAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 获取所有学生总条数
    @Select("SELECT COUNT(*) FROM student")
    long countStudentAll();

    // 获取查询的学生
    @Select("SELECT account, name, dormitory FROM student WHERE name LIKE #{query} LIMIT #{pageNum}, #{pageSize}")
    @Results({@Result(property = "dormitory", column = "dormitory", one = @One(select = "com.ouyang.mapper.UserMapper.findDormitoryById"))})
    List<Student> findStudentQuery(@Param("query") String query, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 获取查询的学生总条数
    @Select("SELECT COUNT(*) FROM student WHERE name LIKE #{query}")
    long countStudentQuery(@Param("query") String query);

    // 修改学生密码
    @Update("UPDATE student SET password = #{password} WHERE account = #{account}")
    int updateStudentPassword(@Param("account") String account, @Param("password") String password);

    // 删除学生
    @Delete("DELETE FROM student WHERE account = #{account}")
    int deleteStudent(@Param("account") String account);

    // 修改学生宿舍
    @Update("UPDATE student SET dormitory = #{dormitory} WHERE account = #{account}")
    int updateStudentDormitory(@Param("account") String account, @Param("dormitory") long dormitory);
}
