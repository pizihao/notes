package helloworld.liu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author shidacaizi
 * @date 2020/4/14 18:59
 */
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {

        try {
            // 获取mybatis第一获取sqlSessionFactory对象
            String resource = "mybatis-config.xml" ;
            InputStream inputstream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //        既然办了SqlSessionFactory. 顾名思义。我们就可以从中获得SqlSession的实例了。
//        SqlSession完全包含了面向数据库执行sQL命令所需的所有方法。
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession(true);
    }
}
