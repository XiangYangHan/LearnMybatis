package cn.hans.mapper;

import cn.hans.model.SysRole;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roles = roleMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
            assertNotNull(roles);
            assertTrue(roles.size() > 0);
        } finally {
            sqlSession.close();
        }
    }
}
