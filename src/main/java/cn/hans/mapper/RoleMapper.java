package cn.hans.mapper;

import cn.hans.model.SysRole;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


public interface RoleMapper {

    @Results(id = "roleMap", value = {
                    @Result(property = "id", column = "id")
                    ,@Result(property = "roleName", column = "role_name")
                    ,@Result(property = "enabled", column = "enabled")
                    ,@Result(property = "createBy", column = "create_by")
                    ,@Result(property = "createTime", column = "create_time", jdbcType = JdbcType.TIMESTAMP)
    })


    @Select({
            "select id, role_name, enabled, create_by, create_time ",
            "from sys_role ",
            "where id = #{id}"
    })
    SysRole selectById(Long id);

    @ResultMap("roleMap")
    @Select({
            "select id, role_name, enabled, create_by, create_time "
            ," from sys_role "
            ," where id = #{id}"
    })
    SysRole selectById2(Long id);

    @ResultMap("roleMap")
    @Select("select * from sys_role")
    List<SysRole> selectAll();

}