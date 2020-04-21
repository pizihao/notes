package helloworld.liu.test;

import helloworld.liu.mapper.BolgMapper;
import helloworld.liu.pojo.Blog;
import helloworld.liu.utils.IdUtils;
import helloworld.liu.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author shidacaizi
 * @date 2020/4/14 18:56
 */
public class MyTest {
    @Test
    public void addBlog(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BolgMapper mapper = sqlSession.getMapper(BolgMapper.class);

        String title = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String author = "abcdefghijklmnopqrstuvwxyz";
        String views = "123456789";
        char[] title2 = title.toCharArray();
        char[] author2 = author.toCharArray();
        char[] views2 = views.toCharArray();
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            String title1 = "";
            String author1 = "";
            String views1 = "";
            for( int j = 0; j < 6; j ++) {
                char a = title2[random.nextInt(title2.length)];
                title1 = title1 + a;
            }
            for( int j = 0; j < 4; j ++) {
                char a = author2[random.nextInt(author2.length)];
                author1 = author1 + a;
            }
            for( int j = 0; j < 3; j ++) {
                char a = views2[random.nextInt(views2.length)];
                views1 = views1 + a;
            }
            Blog blog = new Blog(IdUtils.getId(), title1, author1, new Date(), Integer.parseInt(views1));
            mapper.addBlog(blog);
        }

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getListByPlan(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BolgMapper mapper = sqlSession.getMapper(BolgMapper.class);

        HashMap map = new HashMap();
        map.put("title","L");
        List<Blog> blogs = mapper.getListByPlan(map);

        blogs.forEach(blog -> {
            System.out.println(blog.toString());
        });

        sqlSession.commit();
        sqlSession.close();
    }
}
