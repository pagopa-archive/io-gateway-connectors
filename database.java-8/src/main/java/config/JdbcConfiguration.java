package config;

import error.ImportException;
import importer.Args;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConfiguration {

    private String databaseType;
    private String jdbcUrl;

    public JdbcConfiguration(String databaseType) {
        this.databaseType = databaseType;
        init();
    }

    private void init() {

        try (InputStream input = this.getClass().getResourceAsStream("/" + databaseType + "/jdbc.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            jdbcUrl = prop.getProperty("jdbc.url");

            Class.forName(prop.getProperty("jdbc.driver"));

        } catch (ClassNotFoundException ex) {
            throw new ImportException("JDBC driver class not found", ex);
        } catch (IOException ex) {
            throw new ImportException("Error loading jdbc.properties: "+ex.getMessage(), ex);
        }
    }

    public Connection getConnection(Args args) {
        try {
            return DriverManager.getConnection(buildJdbcUrl(args), args.getUser(), args.getPassword());
        } catch (SQLException ex) {
            throw new ImportException("Error open jdbc connection: "+ex.getMessage(), ex);
        }
    }

    public String buildJdbcUrl(Args args) {
        String url = jdbcUrl.replace("${host}", args.getHost())
                .replace("${port}", args.getPort());
        if(args.getDatabase() != null){
            url = url.replace("${database}", args.getDatabase());
        }
        if(args.getSid() != null){
            url = url.replace("${sid}", args.getSid());
        }
        return url;
    }
}
