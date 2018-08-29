package cn.hans.mapper;

import cn.hans.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);
}
