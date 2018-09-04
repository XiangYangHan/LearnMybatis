package cn.hans.mapper;

import cn.hans.model.SysRole;
import org.apache.ibatis.annotations.*;
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

    /**
     *没有配置回写自增id
     */
    @Insert({
            "insert into sys_role(id, role_name, enabled, create_by, create_time) "
            , " values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"
    })
    int insert(SysRole sysRole);

    /**
     * 回写自增主键
     * @param sysRole
     * @return
     */
    @Insert({
            "insert into sys_role(id, role_name, enabled, create_by, create_time) "
            , " values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertGenerateKey(SysRole sysRole);

    /**
     * 通过查询的方式获取自增主键
     * @param sysRole
     * @return
     */
    @Insert({
            "insert into sys_role(id, role_name, enabled, create_by, create_time) "
            , " values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"
    })
    @SelectKey(
            statement = "SELECT LAST_INSERT_ID()",
            before = false,
            resultType = Long.class,
            keyProperty = "id")
    int insertGenerateKey2(SysRole sysRole);

    @Update({
            "update sys_role set "
            ,"role_name = #{roleName}"
            ,", enabled = #{enabled}"
            ,", create_by = #{createBy}"
            ,", create_time = #{createTime, jdbcType=TIMESTAMP}"
            ," where id = #{id}"
    })
    int updateById(SysRole sysRole);

    @Delete({"delete from sys_role where id = #{id}"})
    int deleteById(Long id);

}