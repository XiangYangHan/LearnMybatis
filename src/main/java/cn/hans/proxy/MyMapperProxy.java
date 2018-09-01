package cn.hans.proxy;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MyMapperProxy<T> implements InvocationHandler {

    private SqlSession sqlSession;

    private Class<T> mapperInterface;

    public MyMapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    /**
     * 调用一个接口方法时，会先通过接口的全限定名称和当前调用的方法名的组合得到一个方法id
     * 这个方法id就是Mapper映射文件中namespace和具体SQL标签id的组合，通过这种方式可以将接口方法和XML文件中的sql语句关联起来。
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method invoke in MyMapperProxy.invoke");
        //针对不同的sql类型，需要调用sqlSession不同的方法
        //todo sql类型如何通知给代理类呢？2018年8月30日 08点01分
        //sqlSession中方法有很多，同一个方法根据参数不同有相应的重载版本
        //这里只考虑没有参数的查询列表情况
        List list = sqlSession.selectList(mapperInterface.getCanonicalName() + '.' + method.getName());
        //返回值也有很多情况
        return list;
    }
}
