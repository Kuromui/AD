package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //permite llegar a la conexión
    private static Connection connection;

    private void createConnection() throws SQLException {//tratamos la excepción en la firma del método para que cuando alguien lo llame, le pueda dar una respuesta
        String url = String.format("jdbc:mysql://%s:%s/%s", SchemaDB.HOST, SchemaDB.PORT, SchemaDB.DB_NAME); //donde me conecto
        connection = DriverManager.getConnection(url, "root", ""); //DriveMananger permite analizar cuales son los drivers instalados
    }
    public Connection getConnection()throws SQLException{
        if(connection == null) {
            createConnection();
        }
        return null;
    }
    public static void setConnection(Connection connection) {
        DBConnection.connection = connection;

    }
}
