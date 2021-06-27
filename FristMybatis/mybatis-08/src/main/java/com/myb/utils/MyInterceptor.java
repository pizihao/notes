package com.myb.utils;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @program: JVMDome
 * @description: 自定义拦截器
 * @author: liuwenhao
 * @create: 2021-06-07 16:18
 **/
@Intercepts({@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
public class MyInterceptor implements Interceptor {
    private static final int MAPPED_STATEMENT_INDEX = 0;
    private static final int PARAM_OBJ_INDEX = 1;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();

//        Map<String, Object> objectMap = new HashMap<>(6);
//        objectMap.put("name", "呵呵");
//        objectMap.put("id", 1);
//        objectMap.put("age", 12);
//        args[1] = objectMap;
//        System.out.println(args[1]);
        
        MappedStatement statement = (MappedStatement) args[0];
        String sql = statement.getBoundSql(args[1]).getSql() + " limit 5";
        setCurrentSql(invocation, sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof DefaultParameterHandler) {
            try {
                DefaultParameterHandler handler = (DefaultParameterHandler) target;
                Field declaredField = handler.getClass().getDeclaredField("boundSql");
                declaredField.setAccessible(true);
                BoundSql boundSql = (BoundSql) declaredField.get(handler);
                Field sql = boundSql.getClass().getDeclaredField("sql");
                sql.setAccessible(true);
                sql.set(boundSql, "select * from person limit 10");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private void setCurrentSql(Invocation invo, String sql) throws NoSuchFieldException, IllegalAccessException {
        MappedStatement mappedStatement = getMappedStatement(invo);
        Object[] args = invo.getArgs();
        // index = 1 参数的下标
        Object paramObj = args[PARAM_OBJ_INDEX];
        //boundSql 并不是一个存储好的量 而是一个在使用时才进行创建的 对象
        BoundSql boundSql = mappedStatement.getBoundSql(paramObj);
        //反射修改MappedStatement的数据
        MappedStatement newMappedStatement = copyFromMappedStatement(mappedStatement
                , new StaticSqlSource(mappedStatement.getConfiguration(), sql, mappedStatement.getParameterMap().getParameterMappings()));
        //反射的方式设置 新的sql
        Field declaredField = boundSql.getClass().getDeclaredField("sql");
        declaredField.setAccessible(true);
        declaredField.set(boundSql, sql);
        //更换参数
        args[MAPPED_STATEMENT_INDEX] = newMappedStatement;
    }

    private MappedStatement getMappedStatement(Invocation invo) {
        Object[] args = invo.getArgs();
        Object mappedStatement = args[MAPPED_STATEMENT_INDEX];
        return (MappedStatement) mappedStatement;
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) throws NoSuchFieldException, IllegalAccessException {
        Field sqlSource = ms.getClass().getDeclaredField("sqlSource");
        sqlSource.setAccessible(true);
        sqlSource.set(ms, newSqlSource);
        return ms;
    }
}