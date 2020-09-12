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
        return System.getProperty("io-sdk-java.docker.ip", "localhost");
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

}
