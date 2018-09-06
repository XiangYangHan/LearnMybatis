package cn.hans.mapper;

import cn.hans.model.SysUser;

import java.util.List;

/**
 * @author : hans
 * @date : 2018/7/13 13:27
 */
public interface UserMapper {

    List<SysUser> selectByUser(SysUser sysUser);
}
