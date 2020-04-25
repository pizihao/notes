package helloworld.liu.mapper;

import helloworld.liu.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/14 16:53
 */
public interface TeacherMapper {
    List<Teacher> getTeacherList();

    Teacher getTeacherById(@Param("id") int id);

    Teacher getTeacherToStudent(@Param("id") int id);
}
