package cn.hans.mapper;

import cn.hans.model.SysRole;
import cn.hans.model.SysUser;

import java.util.List;

/**
 * @author : hans
 * @date : 2018/7/13 13:27
 */
public interface UserMapper {

    SysUser selectById(Long id);

    List<SysUser> selectAll();

    List<SysRole> selectRolesByUserId(Long userId);

    List<SysRole> selectRolesWithUserInfoByUserId(Long userId);

    int insertUser(SysUser sysUser);

    int insertUserGenerateId(SysUser sysUser);

    int insertUserGenerateId2(SysUser sysUser);

    int updateById(SysUser sysUser);

    int deleteById(Long id);

    int deleteById(SysUser sysUser);
}
