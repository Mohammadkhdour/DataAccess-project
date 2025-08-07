package com.khdour;
import org.flywaydb.core.Flyway;

public class FlywayMigration {
    public static void migrateDatabase(String url, String user, String password) {
        try {
            System.out.println("Starting Flyway migration...");
            System.out.println("Database URL: " + url);
            System.out.println("Migration location: classpath:db/migration");
            
            Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .load();

            
            // Run migration
            var result = flyway.migrate();
            
            System.out.println("✅ Flyway migration completed successfully.");
            System.out.println("📊 Migrations executed: " + result.migrationsExecuted);
            
        } catch (Exception e) {
            System.err.println("❌ Flyway migration failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Migration failed", e);
        }
    }
}