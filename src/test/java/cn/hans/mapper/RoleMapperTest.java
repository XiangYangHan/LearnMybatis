package cn.hans.mapper;

import cn.hans.model.SysRole;
import cn.hans.util.TestBase;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void testInsert() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("超级管理员");
        sysRole.setEnabled(1);
        sysRole.setCreateBy(1L);
        sysRole.setCreateTime(new Date());
        sqlSession.insert("cn.hans.mapper.RoleMapper.insert", sysRole);
        assertNull(sysRole.getId());
        System.out.println(sysRole.getId());
    }


    @Test
    public void testInsertGenerateKey() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("超管");
        sysRole.setEnabled(1);
        sysRole.setCreateBy(1L);
        sysRole.setCreateTime(new Date());
        int result = sqlSession.insert("cn.hans.mapper.RoleMapper.insertGenerateKey", sysRole);
        assertTrue(result > 0);
        assertNotNull(sysRole.getId());
        System.out.println(sysRole.getId());
    }

    @Test
    public void testInsertGenerateKey2() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("@SelectKey");
        sysRole.setEnabled(1);
        sysRole.setCreateBy(1L);
        sysRole.setCreateTime(new Date());
        int result = sqlSession.insert("cn.hans.mapper.RoleMapper.insertGenerateKey2", sysRole);
        assertTrue(result > 0);
        assertNotNull(sysRole.getId());
        System.out.println(sysRole.getId());
    }

    @Test
    public void testUpdate() {
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        SysRole sysRole1 = roleMapper.selectById(1L);
        assertNotNull(sysRole1);
        sysRole1.setEnabled(0);
        sysRole1.setRoleName("超管");
        sysRole1.setCreateTime(new Date());//创建的时间对象带有毫秒值，而数据库中字段类型为datetime是不保存毫秒值的
        assertTrue(roleMapper.updateById(sysRole1) > 0);
        SysRole sysRole2 = roleMapper.selectById2(1L);
        assertNotNull(sysRole2);
        assertTrue(0 == sysRole2.getEnabled());
        assertEquals("超管", sysRole2.getRoleName());
        System.out.println(new Date().getTime() - sysRole2.getCreateTime().getTime());//此处得出的值可能为负数，因为上面更新时间到数据库中时只能保存到秒的精度
    }

}
