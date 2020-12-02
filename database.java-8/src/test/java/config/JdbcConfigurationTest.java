package config;

import error.ImportException;
import importer.Args;
import importer.Importer;
import org.junit.Test;
import utils.TestUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JdbcConfigurationTest {

    @Test
    public void testJdbcUrlMysql(){
        Args args = TestUtils.buildMysqlArgs();

        String url = new JdbcConfiguration("mysql").buildJdbcUrl(args);

        assertEquals("jdbc:mysql://" + TestUtils.getDockerIp() + ":3306/test", url);
    }

    @Test
    public void testConnectionMysql() throws SQLException {
        Args args = TestUtils.buildMysqlArgs();

        Connection connection = new JdbcConfiguration("mysql").getConnection(args);

        assertTrue(connection.isValid(1));
    }

    @Test(expected =ImportException.class)
    public void testConnectionErrorMysql() {
        Args args = TestUtils.buildMysqlArgs();
        args.setPort("1234");

        new Importer(args).loadMessages();
    }

    @Test
    public void testJdbcUrlOracle(){
        Args args = TestUtils.buildOracleArgs();

        String url = new JdbcConfiguration("oracle").buildJdbcUrl(args);

        assertEquals("jdbc:oracle:thin:@" + TestUtils.getDockerIp() + ":1521:xe", url);
    }

    @Test
    public void testConnectionOracle() throws SQLException {
        Args args = TestUtils.buildOracleArgs();

        Connection connection = new JdbcConfiguration("oracle").getConnection(args);

        assertTrue(connection.isValid(1));
    }

    @Test(expected =ImportException.class)
    public void testConnectionErrorOracle() {
        Args args = TestUtils.buildOracleArgs();
        args.setPort("1234");

        new Importer(args).loadMessages();
    }

    @Test
    public void testJdbcUrlSQLServer(){
        Args args = TestUtils.buildSQLServerArgs();

        String url = new JdbcConfiguration("sqlserver").buildJdbcUrl(args);

        assertEquals("jdbc:sqlserver://" + TestUtils.getDockerIp() + ":1433;databaseName=test", url);
    }

    @Test
    public void testConnectionSQLServer() throws SQLException {
        Args args = TestUtils.buildSQLServerArgs();

        Connection connection = new JdbcConfiguration("sqlserver").getConnection(args);

        assertTrue(connection.isValid(1));
    }

    @Test(expected =ImportException.class)
    public void testConnectionErrorSQLServer() {
        Args args = TestUtils.buildSQLServerArgs();
        args.setPort("1234");

        new Importer(args).loadMessages();
    }

    @Test
    public void testJdbcUrlPostgreSQL(){
        Args args = TestUtils.buildPostgreSQLArgs();

        String url = new JdbcConfiguration("postgresql").buildJdbcUrl(args);

        assertEquals("jdbc:postgresql://" + TestUtils.getDockerIp() + ":5432/test", url);
    }

    @Test
    public void testConnectionPostgreSQL() throws SQLException {
        Args args = TestUtils.buildPostgreSQLArgs();

        Connection connection = new JdbcConfiguration("postgresql").getConnection(args);

        assertTrue(connection.isValid(1));
    }

    @Test(expected =ImportException.class)
    public void testConnectionErrorPostgreSQL() {
        Args args = TestUtils.buildPostgreSQLArgs();
        args.setPort("1234");

        new Importer(args).loadMessages();
    }
}
