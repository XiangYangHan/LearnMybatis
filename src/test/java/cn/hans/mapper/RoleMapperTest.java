package cn.hans.mapper;

import cn.hans.model.SysRole;
import cn.hans.util.TestBase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RoleMapperTest extends TestBase {

    @Test
    public void testSelectById() {
        SysRole sysRole = sqlSession.selectOne("cn.hans.mapper.RoleMapper.selectById", 1L);
        assertNotNull(sysRole);
        assertEquals("管理员", sysRole.getRoleName());
    }

    @Test
    public void testSelectById2() {
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        SysRole sysRole = roleMapper.selectById2(1L);
        assertNotNull(sysRole);
        System.out.println(sysRole);
    }

    @Test
    public void testSelectAll() {
        List<SysRole> roles = sqlSession.selectList("cn.hans.mapper.RoleMapper.selectAll");
        assertNotNull(roles);
        assertTrue(roles.size() > 0);
        printList(roles);
    }
}
