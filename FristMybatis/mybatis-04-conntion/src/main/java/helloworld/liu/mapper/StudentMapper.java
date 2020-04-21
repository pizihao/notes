package helloworld.liu.mapper;

import helloworld.liu.pojo.Student;

import java.util.List;


/**
 * @author shidacaizi
 * @date 2020/4/14 16:52
 */
public interface StudentMapper {

    List<Student> getStudentAndTeacherList();

}
