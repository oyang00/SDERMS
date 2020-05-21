package com.ouyang.mapper.admin;

import com.ouyang.bean.po.Dormitory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 19:23
 * @Description TODO
 * 管理员宿舍 Mapper
 */
public interface DormitoryMapper {

    // 获取所有宿舍
    @Select("SELECT id, name FROM dormitory LIMIT #{pageNum}, #{pageSize}")
    List<Dormitory> findDormitoryAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 获取所有宿舍总条数
    @Select("SELECT COUNT(*) FROM dormitory")
    long countDormitoryAll();

    // 获取查询的宿舍
    @Select("SELECT id, name FROM dormitory WHERE name LIKE #{query} LIMIT #{pageNum}, #{pageSize}")
    List<Dormitory> findDormitoryQuery(@Param("query") String query, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 获取查询的宿舍总条数
    @Select("SELECT COUNT(*) FROM dormitory WHERE name LIKE #{query}")
    long countDormitoryQuery(@Param("query") String query);

    // 新建宿舍
    @Insert("INSERT INTO dormitory(name) VALUES(#{name})")
    int insertDormitory(@Param("name") String name);

    // 删除宿舍
    @Delete("DELETE FROM dormitory WHERE id = #{id}")
    int deleteDormitory(@Param("id") long id);
}
