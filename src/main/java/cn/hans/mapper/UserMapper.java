package cn.hans.mapper;

import cn.hans.model.SysUser;

/**
 * @author : hans
 * @date : 2018/7/13 13:27
 */
public interface UserMapper {

    SysUser selectUserAndRoleById(Long id);

    SysUser selectUserAndRoleById2(Long id);
}
