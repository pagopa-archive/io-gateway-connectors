import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Args {

    private String host;

    private Integer port;

    private String sid;

    private String database;

    private String user;

    private String password;

//    public Args(JsonObject jsonArgs) {
//        if(jsonArgs != null) {
//            host = parseString(jsonArgs, "host");
//            port = parseInt(jsonArgs, "port");
//            sid = parseString(jsonArgs, "sid");
//            database = parseString(jsonArgs, "database");
//            user = parseString(jsonArgs, "user");
//            password = parseString(jsonArgs, "password");
//        }
//    }
//
//    private String parseString(JsonObject jsonArgs, String property) {
//        JsonPrimitive jsonPrimitive = jsonArgs.getAsJsonPrimitive(property);
//        return jsonPrimitive == null ? null : jsonPrimitive.getAsString();
//    }
//
//    private Integer parseInt(JsonObject jsonArgs, String property) {
//        JsonPrimitive jsonPrimitive = jsonArgs.getAsJsonPrimitive(property);
//        return jsonPrimitive == null ? null : jsonPrimitive.getAsInt();
//    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
