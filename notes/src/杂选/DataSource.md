### DataSource

在没有数据库连接池的情况下，DataSource只有提供一个获取数据库连接的功能。

需要用的数据有：

- driver
- url
- username
- password

在生成数据库连接池之前需要注册数据库连接驱动：driver

这个驱动生成连接的具体实现在对应的驱动包中，如mysql的驱动实在mysql-connector-java包中，代码如下:

~~~java
public java.sql.Connection connect(String url, Properties info) throws SQLException {
    if (url == null) {
        throw SQLError.createSQLException(Messages.getString("NonRegisteringDriver.1"), SQLError.SQL_STATE_UNABLE_TO_CONNECT_TO_DATASOURCE, null);
    }

    if (StringUtils.startsWithIgnoreCase(url, LOADBALANCE_URL_PREFIX)) {
        return connectLoadBalanced(url, info);
    } else if (StringUtils.startsWithIgnoreCase(url, REPLICATION_URL_PREFIX)) {
        return connectReplicationConnection(url, info);
    }

    Properties props = null;

    if ((props = parseURL(url, info)) == null) {
        return null;
    }

    if (!"1".equals(props.getProperty(NUM_HOSTS_PROPERTY_KEY))) {
        return connectFailover(url, info);
    }

    try {
        Connection newConn = com.mysql.jdbc.ConnectionImpl.getInstance(host(props), port(props), props, database(props), url);

        return newConn;
    } catch (SQLException sqlEx) {
        // Don't wrap SQLExceptions, throw
        // them un-changed.
        throw sqlEx;
    } catch (Exception ex) {
        SQLException sqlEx = SQLError.createSQLException(
            Messages.getString("NonRegisteringDriver.17") + ex.toString() + Messages.getString("NonRegisteringDriver.18"),
            SQLError.SQL_STATE_UNABLE_TO_CONNECT_TO_DATASOURCE, null);

        sqlEx.initCause(ex);

        throw sqlEx;
    }
}
~~~

注册是将当前的驱动对象放入一个集合中，注册的方法代码如下：

~~~java
//第二个参数da 为 null
public static synchronized void registerDriver(java.sql.Driver driver,
                                               DriverAction da)
    throws SQLException {

    /* Register the driver if it has not already been added to our list */
    if(driver != null) {
        //实际上是放入了一个集合中
        //DriverInfo中把DriverAction和Driver整合到了一起
        //实际上这里有用的还是Driver
        registeredDrivers.addIfAbsent(new DriverInfo(driver, da));
    } else {
        // This is for compatibility with the original DriverManager
        throw new NullPointerException();
    }

    println("registerDriver: " + driver);

}
~~~

Driver是一个静态的类，但是对于不同的项目来说Driver可能是不同的。所以提供一个Driver类的静态代理：

~~~java
public class TestDriver implements Driver {
    private final Driver driver;

    TestDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return driver.connect(url,info);
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return driver.acceptsURL(url);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return driver.getPropertyInfo(url, info);
    }

    @Override
    public int getMajorVersion() {
        return driver.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return driver.getMinorVersion();
    }

    @Override
    public boolean jdbcCompliant() {
        return driver.jdbcCompliant();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return driver.getParentLogger();
    }
}
~~~

在DriverManager类创建连接时：

~~~java
for(DriverInfo aDriver : registeredDrivers) {
    // 验证权限
    if(isDriverAllowed(aDriver.driver, callerCL)) {
        try {
            println("    trying " + aDriver.driver.getClass().getName());
            //根据用户提供的驱动类来建立连接
            Connection con = aDriver.driver.connect(url, info);
            if (con != null) {
                println("getConnection returning " + aDriver.driver.getClass().getName());
                return (con);
            }
        } catch (SQLException ex) {
            if (reason == null) {
                reason = ex;
            }
        }
    } else {
        println("    skipping: " + aDriver.getClass().getName());
    }

}
~~~

这样就创建出了数据库连接。







