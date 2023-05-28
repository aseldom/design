package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        String driver = properties.getProperty("driver_class");
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        Class.forName(driver);
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s);",
            tableName, "id SERIAL PRIMARY KEY");
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("DROP TABLE IF EXISTS %s;",
                    tableName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s;",
                    tableName, columnName, type);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s DROP COLUMN %s;",
                    tableName, columnName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;",
                    tableName, columnName, newColumnName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TableEditor tableEditor = new TableEditor(config);
        String table = "Cars";
        /* Drop table*/
        tableEditor.dropTable(table);
        /* Create table*/
        tableEditor.createTable(table);
        System.out.println("After SQL command \"CREATE TABLE\":");
        System.out.println(tableEditor.getTableScheme(table));
        /*ADD COLUMN*/
        tableEditor.addColumn(table, "name", "text");
        System.out.println("The table after SQL command \"ADD COLUMN\":");
        System.out.println(tableEditor.getTableScheme(table));
        /*RENAME COLUMN*/
        tableEditor.renameColumn(table, "name", "cars_name");
        System.out.println("The table after SQL command \"ADD COLUMN\":");
        System.out.println(tableEditor.getTableScheme(table));
        /*DROP COLUMN*/
        tableEditor.dropColumn(table, "id");
        System.out.println("The table after SQL command \"DROP COLUMN\":");
        System.out.println(tableEditor.getTableScheme(table));
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}