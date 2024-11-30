package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Empleado;
import model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public boolean comprobarID(int id_producto) throws SQLException {//no sé como usar la fk
        connection = new DBConnection().getConnection();
        String query = String.format("SELECT * FROM %s WHERE id=?", SchemaDB.TAB_PRODUCT);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id_producto);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next(); //devuelve true si hay resultado
    }

    public void añadirPedido(Pedido pedido) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)",
                SchemaDB.TAB_ORDERS, SchemaDB.COL_ID_PROD,
                SchemaDB.COL_DESCRIPTION_ORD, SchemaDB.COL_TOTAL);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, String.valueOf(pedido.getId_producto()));
        preparedStatement.setString(2, pedido.getDescripcion());
        preparedStatement.setString(3, String.valueOf(pedido.getPrecio_total()));
        preparedStatement.execute();
    }
    public boolean mostrarPedido() throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format("SELECT * FROM %s", SchemaDB.TAB_ORDERS);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        System.out.println("Los pedidos son:");
        while (resultSet.next()) {
            String nombre = resultSet.getString(SchemaDB.COL_DESCRIPTION_ORD);
            System.out.print("\n" + nombre);
        }
        return resultSet.next();
    }
}
