package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Producto_Fav;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Producto_FavDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void añadirProductoFav(Producto_Fav productoFav) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format("INSERT INTO %s (%s) SELECT %s FROM %s WHERE %s > ?", SchemaDB.TAB_FAV,SchemaDB.COL_ID_FAV,SchemaDB.COL_ID_PROD,SchemaDB.TAB_PRODUCT,SchemaDB.COL_PRICE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1, 1000);
        preparedStatement.execute();
    }
}
