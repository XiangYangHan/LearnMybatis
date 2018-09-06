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
}
