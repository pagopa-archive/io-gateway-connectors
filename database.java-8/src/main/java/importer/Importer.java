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
        return false;
    }

    public List<Message> loadMessages() {
        List<Message> messages = new ArrayList<>();
        Statement stmt;
        JdbcConfiguration jdbcConfiguration = new JdbcConfiguration(args.getDatabaseType());
        try (Connection connection = jdbcConfiguration.getConnection(args)) {
            stmt = connection.createStatement();
            String selectSql = "SELECT 0 AS amount,\n" +
                    "scadenza AS due_date,\n" +
                    "destinatario AS fiscal_code,\n" +
                    "0 AS invalid_after_due_date,\n" +
                    "testo AS markdown,\n" +
                    "1 AS notice_number,\n" +
                    "titolo AS subject FROM messages";
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()) {
                Message msg = new Message(
                    resultSet.getInt("amount"),
                    resultSet.getDate("due_date").getTime(),
                    resultSet.getString("fiscal_code"),
                    resultSet.getBoolean("invalid_after_due_date"),
                    resultSet.getString("markdown"),
                    resultSet.getInt("notice_number"),
                    resultSet.getString("subject"));
                messages.add(msg);
            }

        } catch (SQLException ex) {
            throw new ImportException("Error reading messages: "+ex.getMessage(), ex);
        }
        return messages;
    }

}
