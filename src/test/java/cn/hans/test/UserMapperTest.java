package cn.hans.test;

import cn.hans.model.SysUser;
import cn.hans.util.TestBase;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserMapperTest extends TestBase {

    @Test
    public void testSelectUserAndRoleById() {
        SysUser sysUser = sqlSession.selectOne("cn.hans.mapper.UserMapper.selectUserAndRoleById", 1L);
        assertNotNull(sysUser);
        assertNotNull(sysUser.getRole());
        System.out.println(sysUser);
    }

    @Test
    public void selectUserAndRoleById2() {
        SysUser sysUser = sqlSession.selectOne("cn.hans.mapper.UserMapper.selectUserAndRoleById2", 1L);
        assertNotNull(sysUser);
        assertNotNull(sysUser.getRole());
        System.out.println(sysUser);
    }

    @Test
    public void selectUserByIdAndSelectRole() {
        SysUser sysUser = sqlSession.selectOne("cn.hans.mapper.UserMapper.selectUserByIdAndSelectRole", 1L);
        assertNotNull(sysUser);
        assertNotNull(sysUser.getRole());
    }

    @Test
    public void selectById() {
        cn.hans.generator.model.SysUser sysUser = sqlSession.selectOne("cn.hans.generator.dao.SysUserMapper.selectByPrimaryKey", 1L);
        assertNotNull(sysUser);
        System.out.println(sysUser);
    }
}
