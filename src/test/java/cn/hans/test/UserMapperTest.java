package cn.hans.test;

import cn.hans.model.SysUser;
import cn.hans.util.TestBase;
import org.apache.ibatis.session.SqlSession;
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
        System.out.println("调用sysUser.getRole()");
        assertNotNull(sysUser.getRole());
    }

    @Test
    public void selectUserByIdAndLazySelectRole() {
        SqlSession session = getSqlSession();
        SysUser sysUser = session.selectOne("cn.hans.mapper.UserMapper.selectUserByIdAndLazySelectRole", 1L); //DEBUG模式下延迟加载过程无法体现
        assertNotNull(sysUser);
        System.out.println(sysUser.getRole());
        System.out.println("测试lazyLoadTriggerMethods");
        System.out.println(sysUser.toString());
        System.out.println(sysUser.getId());
        sysUser.equals(null);
        System.out.println("调用sysUser.getRole()");
        assertNotNull(sysUser.getRole());
        System.out.println(sysUser);
    }

    public SysUser testLazyLoad() {
        SqlSession session = getSqlSession();
        SysUser sysUser = session.selectOne("cn.hans.mapper.UserMapper.selectUserByIdAndLazySelectRole", 1L); //DEBUG模式下延迟加载过程无法体现
        System.out.println("关闭session");
        session.close(); //todo 为什么session已经关闭了，但是还是能够延迟加载属性
        return sysUser;
    }

    @Test
    public void selectById() {
        cn.hans.generator.model.SysUser sysUser = sqlSession.selectOne("cn.hans.generator.dao.SysUserMapper.selectByPrimaryKey", 1L);
        assertNotNull(sysUser);
        System.out.println(sysUser);
    }
}
