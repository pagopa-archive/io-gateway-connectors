package importer;

import config.Configuration;
import config.JdbcConfiguration;
import domain.Message;
import error.ImportException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Importer {

    private Args args;

    public Importer(Args args){
        this.args = args;
    }

    public boolean showForm() {
        if("mysql".equals(args.getDatabaseType())) {
            return args.getHost() == null
                    || args.getPort() == null
                    || args.getDatabase() == null
                    || args.getUser() == null
                    || args.getPassword() == null;
        }
        if("oracle".equals(args.getDatabaseType())) {
            return args.getHost() == null
                    || args.getPort() == null
                    || args.getSid() == null
                    || args.getUser() == null
                    || args.getPassword() == null;
        }
        if("sqlserver".equals(args.getDatabaseType())) {
            return args.getHost() == null
                    || args.getDatabase() == null
                    || args.getPort() == null
                    || args.getUser() == null
                    || args.getPassword() == null;
        }
        if("postgresql".equals(args.getDatabaseType())) {
            return args.getHost() == null
                    || args.getPort() == null
                    || args.getDatabase() == null
                    || args.getUser() == null
                    || args.getPassword() == null;
        }
        return false;
    }

    public List<Message> loadMessages() {
        List<Message> messages = new ArrayList<>();
        Statement stmt;
        JdbcConfiguration jdbcConfiguration = new JdbcConfiguration(args.getDatabaseType());
        try (Connection connection = jdbcConfiguration.getConnection(args)) {
            stmt = connection.createStatement();
            String selectSql = "SELECT * FROM messages";
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()) {
                Message msg = new Message(
                    resultSet.getInt("amount"),
                    resultSet.getDate("due_date"),
                    resultSet.getString("fiscal_code"),
                    resultSet.getBoolean("invalid_after_due_date"),
                    resultSet.getString("markdown"),
                    resultSet.getString("notice_number"),
                    resultSet.getString("subject"));
                messages.add(msg);
            }

        } catch (SQLException ex) {
            throw new ImportException("Error reading messages: "+ex.getMessage(), ex);
        }
        return messages;
    }

}
