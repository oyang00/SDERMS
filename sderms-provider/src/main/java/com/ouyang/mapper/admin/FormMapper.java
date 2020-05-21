package com.ouyang.mapper.admin;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2020
 * @date 2020/3/27 20:08
 * @Description TODO
 * 管理员报修 Mapper
 */
public interface FormMapper {

    // 删除报修
    @Delete("DELETE FROM form WHERE id = #{id}")
    int deleteForm(@Param("id") long id);
}
