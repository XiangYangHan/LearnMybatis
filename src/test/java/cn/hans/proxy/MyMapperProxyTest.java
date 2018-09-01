package cn.hans.proxy;

import cn.hans.mapper.UserMapper;
import cn.hans.model.SysUser;
import cn.hans.util.TestBase;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.List;

import static org.junit.Assert.*;

public class MyMapperProxyTest extends TestBase {


    @Test
    public void testProxy() {
        //创建代理，以后对于被代理对象的方法调用都将调用此代理的invoke方法
        MyMapperProxy<UserMapper> userMapperProxy = new MyMapperProxy<>(sqlSession, UserMapper.class);
        //创建被代理对象
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserMapper.class}, userMapperProxy);
        List<SysUser> users = userMapper.selectAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
//        printList(users);
    }

}
