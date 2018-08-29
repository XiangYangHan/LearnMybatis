package cn.hans.mapper;

import cn.hans.model.SysRole;
import cn.hans.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


/**
 * @author : hans
 * @date : 2018/8/13 19:22
 */
public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        //获取SqlSession
        SqlSession sqlSession = getSqlSession();

        try {
            //获取UserMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用接口方法，查询id = 1的用户
            SysUser sysUser = userMapper.selectById(1L);
            //sysUser不为空
            assertNotNull(sysUser);
            assertEquals("admin", sysUser.getUserName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            List<SysUser> userList = sqlSession.selectList("cn.hans.mapper.UserMapper.selectAll");
            printList(userList);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("userId", 1L);
            List<SysRole> roleList = sqlSession.selectList("cn.hans.mapper.UserMapper.selectRolesByUserId", param);
            assertNotNull(roleList);
            printList(roleList);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesWithUserInfoByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("userId", 1L);
            List<SysRole> roleList = sqlSession.selectList("cn.hans.mapper.UserMapper.selectRolesWithUserInfoByUserId", param);
            assertNotNull(roleList);
            printList(roleList);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUser sysUser = new SysUser();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("1@test.com");
            sysUser.setUserInfo("test1 info");
            //可以存放二进制数据
            //java.io.ByteArrayInputStream@44a3ec6b(ByteArrayInputStream)
            sysUser.setHeadImg(new byte[]{1,2,3});
            sysUser.setCreateTime(new Date());
            //插入一条数据，返回的是写入影响的行数
            int result = sqlSession.insert("cn.hans.mapper.UserMapper.insertUser", sysUser);
            assertEquals(1, result);
            //id为null，因为没有设置回写id值
            assertNull(sysUser.getId());
        } finally {
            //为了避免影响其他测试,这里rollback本次insert
            sqlSession.rollback();
            //sqlSessionFactory.openSession()返回的SqlSession默认是不自动提交的，所以上面的rollback()语句即使注释掉也不会保存数据到数据库
            sqlSession.close();
        }
    }

    @Test
    public void testInsertUserGenerateId() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUser sysUser = new SysUser();
            sysUser.setUserName("test2");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("2@test.com");
            sysUser.setUserInfo("test2 info");
            sysUser.setHeadImg(new byte[]{1,2,3});
            sysUser.setCreateTime(new Date());
            int result = sqlSession.insert("cn.hans.mapper.UserMapper.insertUserGenerateId", sysUser);
            assertEquals(1, result);
            assertNotNull(sysUser.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsertUserGenerateId2() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUser sysUser = new SysUser();
            sysUser.setUserName("test3");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("3@test.com");
            sysUser.setUserInfo("test3 info");
            sysUser.setHeadImg(new byte[]{1,2,3});
            sysUser.setCreateTime(new Date());
            int result = sqlSession.insert("cn.hans.mapper.UserMapper.insertUserGenerateId2", sysUser);
            assertEquals(1, result);
            assertNotNull(sysUser.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = userMapper.selectById(1L);
            assertEquals("admin", sysUser.getUserName());
            sysUser.setUserName("admin_testUpdateById");
            int result = userMapper.updateById(sysUser);
            assertEquals(1, result);
            sysUser = userMapper.selectById(1L);
            assertEquals("admin_testUpdateById", sysUser.getUserName());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }



}
