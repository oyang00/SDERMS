package com.ouyang.mapper;

import com.ouyang.bean.po.Form;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 11:29
 * @Description TODO
 * 报修 Mapper
 */
public interface FormsMapper {

    // 提出报修
    @Insert("INSERT INTO form(propose, propose_title, propose_desc, propose_time, is_handle) " +
            "VALUES(#{propose}, #{title}, #{desc}, #{time}, #{handle})")
    int insertForm(@Param("propose") String propose, @Param("title") String title, @Param("desc") String desc,
                   @Param("time") LocalDateTime time, @Param("handle") int handle);

    // 完善报修
    @Update("UPDATE form SET repair = #{repair}, repair_desc = #{desc}, repair_time = #{time}, is_handle = #{handle} " +
            "WHERE id = #{id}")
    int updateFormRepair(@Param("id") long id, @Param("repair") String repair, @Param("desc") String desc,
                         @Param("time") LocalDateTime time, @Param("handle") int handle);

    // 获取学生报修
    @Select("SELECT id, propose, propose_title, propose_desc, propose_time, repair, repair_desc, repair_time, is_handle, feedback " +
            "FROM form WHERE propose = #{propose} LIMIT #{pageNum}, #{pageSize}")
    @Results({
            @Result(property = "propose", column = "propose", one = @One(select = "com.ouyang.mapper.UserMapper.findStudentByAccount")),
            @Result(property = "repair", column = "repair", one = @One(select = "com.ouyang.mapper.UserMapper.findRepairByAccount"))
    })
    List<Form> findFormByPropose(@Param("propose") String propose, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 获取学生报修总条数
    @Select("SELECT COUNT(*) FROM form WHERE propose = #{propose}")
    long countFormByPropose(@Param("propose") String propose);

    // 获取学生是否处理报修
    @Select("SELECT id, propose, propose_title, propose_desc, propose_time, repair, repair_desc, repair_time, is_handle, feedback " +
            "FROM form WHERE propose = #{propose} AND is_handle = #{isHandle} LIMIT #{pageNum}, #{pageSize}")
    @Results({
            @Result(property = "propose", column = "propose", one = @One(select = "com.ouyang.mapper.UserMapper.findStudentByAccount")),
            @Result(property = "repair", column = "repair", one = @One(select = "com.ouyang.mapper.UserMapper.findRepairByAccount"))
    })
    List<Form> findFormByProposeAndHandle(@Param("propose") String propose, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("isHandle") int isHandle);

    // 获取学生是否处理报修总条数
    @Select("SELECT COUNT(*) FROM form WHERE propose = #{propose} AND is_handle = #{isHandle}")
    long countFormByProposeAndHandle(@Param("propose") String propose, @Param("isHandle") int isHandle);

    // 获取维修人员报修
    @Select("SELECT id, propose, propose_title, propose_desc, propose_time, repair, repair_desc, repair_time, is_handle, feedback " +
            "FROM form WHERE repair = #{repair} LIMIT #{pageNum}, #{pageSize}")
    @Results({
            @Result(property = "propose", column = "propose", one = @One(select = "com.ouyang.mapper.UserMapper.findStudentByAccount")),
            @Result(property = "repair", column = "repair", one = @One(select = "com.ouyang.mapper.UserMapper.findRepairByAccount"))
    })
    List<Form> findFormByRepair(@Param("repair") String repair, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 获取维修人员报修总条数
    @Select("SELECT COUNT(*) FROM form WHERE repair = #{repair}")
    long countFormByRepair(@Param("repair") String repair);

    // 获取维修人员是否处理报修
    @Select("SELECT id, propose, propose_title, propose_desc, propose_time, repair, repair_desc, repair_time, is_handle, feedback " +
            "FROM form WHERE repair = #{repair} AND is_handle = #{isHandle} LIMIT #{pageNum}, #{pageSize}")
    @Results({
            @Result(property = "propose", column = "propose", one = @One(select = "com.ouyang.mapper.UserMapper.findStudentByAccount")),
            @Result(property = "repair", column = "repair", one = @One(select = "com.ouyang.mapper.UserMapper.findRepairByAccount"))
    })
    List<Form> findFormByRepairAndHandle(@Param("repair") String repair, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("isHandle") int isHandle);

    // 获取维修人员是否处理报修总条数
    @Select("SELECT COUNT(*) FROM form WHERE repair = #{repair} AND is_handle = #{isHandle}")
    long countFormByRepairAndHandle(@Param("repair") String repair, @Param("isHandle") int isHandle);

    // 获取所有报修
    @Select("SELECT id, propose, propose_title, propose_desc, propose_time, repair, repair_desc, repair_time, is_handle, feedback " +
            "FROM form LIMIT #{pageNum}, #{pageSize}")
    @Results({
            @Result(property = "propose", column = "propose", one = @One(select = "com.ouyang.mapper.UserMapper.findStudentByAccount")),
            @Result(property = "repair", column = "repair", one = @One(select = "com.ouyang.mapper.UserMapper.findRepairByAccount"))
    })
    List<Form> findFormAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 获取所有报修总条数
    @Select("SELECT COUNT(*) FROM form")
    long countFormAll();

    // 获取所有是否处理报修
    @Select("SELECT id, propose, propose_title, propose_desc, propose_time, repair, repair_desc, repair_time, is_handle, feedback " +
            "FROM form WHERE is_handle = #{isHandle} LIMIT #{pageNum}, #{pageSize}")
    @Results({
            @Result(property = "propose", column = "propose", one = @One(select = "com.ouyang.mapper.UserMapper.findStudentByAccount")),
            @Result(property = "repair", column = "repair", one = @One(select = "com.ouyang.mapper.UserMapper.findRepairByAccount"))
    })
    List<Form> findFormAllByHandle(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize, @Param("isHandle") int isHandle);

    // 获取所有是否处理报修总条数
    @Select("SELECT COUNT(*) FROM form WHERE is_handle = #{isHandle}")
    long countFormAllByHandle(@Param("isHandle") int isHandle);

    // 获取查询的报修
    @Select("SELECT id, propose, propose_title, propose_desc, propose_time, repair, repair_desc, repair_time, is_handle, feedback " +
            "FROM form WHERE propose_title LIKE #{query} LIMIT #{pageNum}, #{pageSize}")
    @Results({
            @Result(property = "propose", column = "propose", one = @One(select = "com.ouyang.mapper.UserMapper.findStudentByAccount")),
            @Result(property = "repair", column = "repair", one = @One(select = "com.ouyang.mapper.UserMapper.findRepairByAccount"))
    })
    List<Form> findFormQuery(@Param("query") String query, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    // 获取查询的报修总条数
    @Select("SELECT COUNT(*) FROM form WHERE propose_title LIKE #{query}")
    long countFormQuery(@Param("query") String query);

    // 报修反馈
    @Update("UPDATE form SET feedback = #{feedback} WHERE id = #{id} AND propose = #{propose}")
    int updateFormFeedback(@Param("id") long id, @Param("feedback") String feedback, @Param("propose") String propose);
}
