package com.example.cup02;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

/**
 * @author Create by liuwenhao on 2021/6/19 16:04
 */
public class TestDataSource implements DataSource {


    private final String driver;
    private final String url;
    private final String username;
    private final String password;

    private Connection connection;

    public TestDataSource(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Connection getConnection() throws SQLException {
        return connection == null ? openConnection(this.username, this.password) : connection;
    }

    /**
     * 用于准备和数据进行连接的数据
     *
     * @param username 数据库用户名
     * @param password 数据库密码
     * @return 数据库连接诶
     * @author Created by liuwenhao on 2021/6/19 17:52
     */
    private Connection openConnection(String username, String password) {
        //注册 Driver
        registerDriver(this.driver);
        //获取连接
        if (username == null) {
            username = this.username;
        }
        if (password == null) {
            password = this.password;
        }
        try {
            connection = DriverManager.getConnection(this.url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 向 {@link DriverManager} 中注册驱动
     *
     * @param driverClass 驱动
     * @author Created by liuwenhao on 2021/6/19 18:35
     */
    public void registerDriver(String driverClass) {
        try {
            Class<?> clazz = Class.forName(driverClass, true, null);
            //生成 driver 对象
            Driver driver = (Driver) clazz.newInstance();
            //通过 DriverManager 注册这个驱动
            DriverManager.registerDriver(new TestDriver(driver));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return connection == null ? openConnection(username, password) : connection;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}