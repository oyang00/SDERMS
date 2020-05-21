package com.ouyang.mapper.admin;

import com.ouyang.bean.po.Repair;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 17:56
 * @Description TODO
 * 管理员维修人员 Mapper
 */
public interface RepairMapper {

    // 获取所有维修人员
    @Select("SELECT account FROM repair LIMIT #{pageNum}, #{pageSize}")
    List<Repair> findRepairAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 获取所有维修人员总条数
    @Select("SELECT COUNT(*) FROM repair")
    long countRepairAll();

    // 获取查询的维修人员
    @Select("SELECT account FROM repair WHERE account LIKE #{query} LIMIT #{pageNum}, #{pageSize}")
    List<Repair> findRepairQuery(@Param("query") String query, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 获取查询的维修人员总条数
    @Select("SELECT COUNT(*) FROM repair WHERE account LIKE #{query}")
    long countRepairQuery(@Param("query") String query);

    // 修改维修人员密码
    @Update("UPDATE repair SET password = #{password} WHERE account = #{account}")
    long updateRepairPassword(@Param("account") String account, @Param("password") String password);

    // 新建维修人员
    @Insert("INSERT INTO repair(account, password) VALUES(#{account}, #{password})")
    int insertRepair(@Param("account") String account, @Param("password") String password);

    // 删除维修人员
    @Delete("DELETE FROM repair WHERE account = #{account}")
    int deleteRepair(@Param("account") String account);
}
