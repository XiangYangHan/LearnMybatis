package cn.hans.test;

import cn.hans.model.SysUser;

public class LazyLoadingTest {

    public static void main(String[] args) {
        UserMapperTest userMapperTest = new UserMapperTest();
        SysUser sysUser = userMapperTest.testLazyLoad();
        System.out.println(sysUser);
        System.out.println(sysUser.getRole());
    }
}
