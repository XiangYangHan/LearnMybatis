package cn.hans.mapper;

import cn.hans.model.SysUser;
import cn.hans.util.TestBase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserMapperTest extends TestBase {

    @Test
    public void testSelectByUser() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        SysUser queryUser = new SysUser();
        //只查用户名
        queryUser.setUserName("st");
        //select id , user_name , user_password , user_email , user_info , head_img , create_time from sys_user where 1 = 1 and user_name like concat('%', ?, '%')
        List<SysUser> userList = userMapper.selectByUser(queryUser);
        assertTrue(userList.size() > 0);
        queryUser = new SysUser();
        //只查邮箱
        queryUser.setUserEmail("admin@qq.com");
        //select id , user_name , user_password , user_email , user_info , head_img , create_time from sys_user where 1 = 1 and user_email = ?
        userList = userMapper.selectByUser(queryUser);
        assertTrue(userList.size() > 0);
        queryUser = new SysUser();
        //同时查用户名和邮箱
        queryUser.setUserName("st");
        queryUser.setUserEmail("admin@qq.com");
        //select id , user_name , user_password , user_email , user_info , head_img , create_time from sys_user where 1 = 1 and user_name like concat('%', ?, '%') and user_email = ?
        userList = userMapper.selectByUser(queryUser);
        assertTrue(userList.size() == 0);
    }

    @Test
    public void testUpdateByIdSelective() {
        SysUser user = new SysUser();
        //更新id为1的用户记录
        user.setId(1L);
        user.setUserEmail("test0@qq.com");
        int result = sqlSession.update("cn.hans.mapper.UserMapper.updateByIdSelective", user);
        assertEquals(1, result);
        SysUser user1 = sqlSession.selectOne("cn.hans.mapper.UserMapper.selectById", user);
        assertEquals(user.getUserEmail(), user1.getUserEmail());
        assertEquals("admin", user1.getUserName());
    }

    @Test
    public void testUpdateNone() {
        SysUser user = new SysUser();
        user.setId(1L);
        int result = sqlSession.update("cn.hans.mapper.UserMapper.updateNone", user);
        assertEquals(0, result);
    }

    @Test
    public void testInsertSelectiveEmail() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("test-selective");
        sysUser.setUserPassword("123456");
        assertEquals(1L, sqlSession.insert("cn.hans.mapper.UserMapper.insertSelectiveEmail", sysUser));
        assertNotNull(sysUser.getId());
        System.out.println(sysUser.getId());
        sysUser = sqlSession.selectOne("cn.hans.mapper.UserMapper.selectById", sysUser);
//        assertNull(sysUser.getUserEmail()); 
        sysUser.setUserEmail("test-selective@qq.com");
        assertEquals(1L, sqlSession.insert("cn.hans.mapper.UserMapper.insertSelectiveEmail", sysUser));
        assertNotNull(sysUser.getId());
        System.out.println(sysUser.getId());
        sysUser = sqlSession.selectOne("cn.hans.mapper.UserMapper.selectById", sysUser);
        assertNotNull(sysUser.getUserEmail());
    }
}
