package com.liu.mapper;

import com.liu.pojo.Blog;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author shidacaizi
 * @date 2020/4/14 18:58
 */
public interface BolgMapper {
    // 插入数据
    int addBlog(Blog blog);

    // 查询博客 根据传入的不同属性，返回不同的数据
    List<Blog> getListByPlan(Map map);
}
