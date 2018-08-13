package cn.hans.mapper;

import cn.hans.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

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
}
