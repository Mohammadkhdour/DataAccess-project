package com.khdour;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;


//    static {
//        config = new HikariConfig("/db/migration/datasource.properties");
//
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//
//        ds = new HikariDataSource(config);
//    }
    public static DataSource getDataSource(String url, String user, String password) {
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        ds = new HikariDataSource(config);
        return ds;
    }

    private DataSourceConfig() {
    }

}
