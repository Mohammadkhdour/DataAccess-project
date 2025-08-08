package com.khdour;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config = new HikariConfig("/db/migration/datasource.properties");
        
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        
        ds = new HikariDataSource(config);
    }
    public static DataSource getDataSource() {
        return ds;
    }

    private DataSourceConfig() {}

}
