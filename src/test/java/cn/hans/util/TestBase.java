package cn.hans.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;


/**
 * @author : hans
 * @date : 2018/8/13 19:14
 */
public class TestBase {

    static {
        init();
    }

    private static SqlSessionFactory sqlSessionFactory;

    public SqlSession sqlSession;

//    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    protected <B> void printList(Collection<B> collections) {
        for (B b : collections) {
            System.out.println(b);
        }
    }

    @Before
    public void initSqlSession() {
        System.out.println("method invoke in TestBase.initSqlSession");
        sqlSession = getSqlSession();
    }

    @After
    public void closeSqlSession() {
        System.out.println("method invoke in TestBase.closeSqlSession");
        sqlSession.close();
    }

    public static void printParam(Object object) {
        System.out.println(object);
    }
}
