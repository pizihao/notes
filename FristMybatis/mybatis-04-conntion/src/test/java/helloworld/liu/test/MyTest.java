package helloworld.liu.test;

import helloworld.liu.mapper.StudentMapper;
import helloworld.liu.mapper.TeacherMapper;
import helloworld.liu.pojo.Student;
import helloworld.liu.pojo.Teacher;
import helloworld.liu.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/14 16:54
 */
public class MyTest {
    @Test
    public void getTeacherList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        List<Teacher> users = mapper.getTeacherList();

        users.forEach(users1 -> {
            System.out.println(users1.toString());
        });

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getStudentAndTeacherList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> users = mapper.getStudentAndTeacherList();

        users.forEach(users1 -> {
            System.out.println(users1.toString());
        });

        sqlSession.commit();
        sqlSession.close();
    }
}
