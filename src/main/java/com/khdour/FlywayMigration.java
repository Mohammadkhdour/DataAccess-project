package com.khdour;
import org.flywaydb.core.Flyway;

public class FlywayMigration {
    public static void migrateDatabase(String url, String user, String password) {

            System.out.println("Starting Flyway migration...");
            System.out.println("Database URL: " + url);
            System.out.println("Migration location: classpath:db/migration");
            
            Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .cleanDisabled(false)
                .load();

            flyway.clean();
            // Run migration
            flyway.migrate();
            
            System.out.println("Flyway migration completed successfully.");
    }
}