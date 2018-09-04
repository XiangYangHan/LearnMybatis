package cn.hans.mapper;

import cn.hans.model.SysPrivilege;
import cn.hans.util.TestBase;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrivilegeTestMapper extends TestBase {

    @Test
    public void testSelectById() {
        SysPrivilege sysPrivilege = sqlSession.selectOne("cn.hans.mapper.PrivilegeMapper.selectById", 1L);
        assertNotNull(sysPrivilege);
        assertEquals(1L, sysPrivilege.getId().longValue());
        System.out.println(sysPrivilege.getPrivilegeName());
        System.out.println(sysPrivilege.getPrivilegeUrl());
    }
}
