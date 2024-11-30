package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    //todas las operaciones de acceso al objeto
    //todas las acciones contra BD -> Create, Read, Update, Delete (CRUD)
    private Connection connection;
    private PreparedStatement preparedStatement; //para hacer inserciones
    private ResultSet resultSet; //para poder trabajar con selecciones

    public boolean registrarUsuario(Usuario usuario) throws SQLException {
        connection = new DBConnection().getConnection();
        //siempre hay intentar utilizar un preparedStatment
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUE (?,?,?,?,?)",
                SchemaDB.DB_NAME,
                SchemaDB.COL_NAME, SchemaDB.COL_MAIL, SchemaDB.COL_PASS, SchemaDB.COL_PH, SchemaDB.COL_ID_PER);
        //statement.execute(query);
        //statement.executeUpdate()
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, usuario.getNombre());
        preparedStatement.setString(2, usuario.getCorreo());
        preparedStatement.setString(3, usuario.getPassword());
        preparedStatement.setInt(4, usuario.getTelefono());
        preparedStatement.setInt(5, usuario.getIdPerfil());
        // connection.close();
        return preparedStatement.execute();
    }
    public boolean realizarLogin(String correo, String pass) throws SQLException {
        connection = new DBConnection().getConnection();
        preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s WHERE %s=? AND %s=?", SchemaDB.TAB_USER,
                SchemaDB.COL_MAIL, SchemaDB.COL_PASS));
        preparedStatement.setString(1,correo);
        preparedStatement.setString(2,pass);
        resultSet = preparedStatement.executeQuery();
        // si hay un resultado -> login ok -> true
        // si no hay un resultado -> login no ok -> false
        return resultSet.next();
    }
}

