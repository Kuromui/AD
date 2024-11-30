package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //permite llegar a la conexión
    private static Connection connection;
    private Object url;

    private void createConnection() throws SQLException {
        String url = String.format("jdbc:mysql://localhost:3306/almacen");
    }
    public Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://localhost:3306/almacen");
        try {
            return DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
            throw e;
        }
    }
    public static void setConnection(Connection connection) {
        DBConnection.connection = connection;

    }
}

