package utils;

import importer.Args;

public class TestUtils {

    public static Args buildMysqlArgs() {
        Args args = new Args();
        args.setDatabaseType("mysql");
        args.setHost(getDockerIp());
        args.setDatabase("test");
        args.setPort("3306");
        args.setUser("root");
        args.setPassword("root");
        return args;
    }

    public static String getDockerIp() {
        String dockerIP = System.getProperty("io-gateway-java.docker.ip");
        if(dockerIP == null || dockerIP.isEmpty()){
            return "localhost";
        }
        return dockerIP;
    }

    public static Args buildOracleArgs() {
        Args args = new Args();
        args.setDatabaseType("oracle");
        args.setHost(getDockerIp());
        args.setPort("1521");
        args.setUser("system");
        args.setPassword("oracle");
        args.setSid("xe");
        return args;
    }

    public static Args buildSQLServerArgs() {
        Args args = new Args();
        args.setDatabaseType("sqlserver");
        args.setHost(getDockerIp());
        args.setDatabase("test");
        args.setPort("1433");
        args.setUser("sa");
        args.setPassword("StrongPassword1");
        return args;
    }

    public static Args buildPostgreSQLArgs() {
        Args args = new Args();
        args.setDatabaseType("postgresql");
        args.setHost(getDockerIp());
        args.setDatabase("test");
        args.setPort("5432");
        args.setUser("postgres");
        args.setPassword("postgres");
        return args;
    }
}
