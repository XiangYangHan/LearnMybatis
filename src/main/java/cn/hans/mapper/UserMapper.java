package cn.hans.mapper;

import cn.hans.model.SysUser;

import java.util.List;

/**
 * @author : hans
 * @date : 2018/7/13 13:27
 */
public interface UserMapper {

    List<SysUser> selectByUser(SysUser sysUser);

    int updateByIdSelective(SysUser sysUser);

    int updateNone(SysUser sysUser);

    SysUser selectById(SysUser sysUser);

    int insertSelectiveEmail(SysUser sysUser);

    SysUser selectByIdOrUserName(SysUser sysUser);
}
