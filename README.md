# Task Description: Simple Data Access Project

This task is designed to teach and demonstrate modern data access patterns in Java. The focus is on using **JDBI**, **HikariCP**, and **Flyway** to build a robust data access layer with MySQL and make simple CRUD operation.

## Objectives

1. **Database Configuration**
   - Use **HikariCP** for efficient connection pooling.

2. **Database Migrations**
   - Manage schema changes using **Flyway**.
   - Understand version-controlled migrations with SQL scripts.

3. **Data Access Layer**
   - Implement a DAO (Data Access Object) using **JDBI**.
   - Perform CRUD operations (Create, Read, Update, Delete).

4. **Testing and Validation**
   - Write unit tests using **JUnit 5**.
   - Validate database operations and schema changes.

## Tools and Technologies

- **Java 21**: Programming language.
- **Maven**: Build and dependency management.
- **MySQL 8.0**: Database.
- **HikariCP**: Connection pooling.
- **Flyway**: Database migration management.
- **JDBI**: Modern database access library.
- **JUnit 5**: Testing framework.

## Task Breakdown

### Step 1: Database Configuration
- define the connection details as constant variable in app class.
- Implement `DataSourceConfig.java` to use HikariCP for connection pooling.

### Step 2: Database Migrations
- Create migration scripts `FlywayMigration.java` for schema changes.
- Use Flyway to apply migrations programmatically.

### Step 3: Data Access Layer
- Build a `Book.java` entity class.
- Implement a `BookDao.java` class using JDBI.
- Add methods for CRUD operations.

### Step 4: Testing
- Write unit tests for all CRUD operations.
- Test Flyway migrations and data integrity.

## Expected Outcomes

- Understand how to configure and manage database connections in Java.
- Learn to use Flyway for schema versioning and migrations.
- Gain hands-on experience with JDBI for building a data access layer.
- Write effective unit tests to validate database operations.

## Sample Data

The task includes sample data for testing CRUD operations:

| ISBN    | Title                     | Author                | Language | Description                              |
|---------|---------------------------|-----------------------|----------|------------------------------------------|
| 1001  | The Alchemist             | Paulo Coelho          | English  | -                                        |
| 1212  | the men                   | mohammad khdour       | Arabic   | A book about men's experiences...        |


## Project Structure

```
src/
├── main/
│   ├── java/com/khdour/
│   │   ├── App.java                 # Main application
│   │   ├── Book.java               # Entity class
│   │   ├── BookDao.java            # Data Access Object
│   │   ├── DataSource.java         # Database configuration
│   │   └── FlywayMigration.java    # Migration management
│   └── resources/
│       └── db/migration/
│           ├── R__updateDatabase.sql      # repeatable flyway migration script
│           ├── V1__database.sql          # Initial schema
│           └── V2__add_column_description.sql  # Schema evolution
└── test/
    └── java/com/khdour/
        └── AppTest.java                   # Application tests
         
```

This task is part of a university training program to teach modern data access patterns in Java.
