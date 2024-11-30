package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmpleadoDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void añadirEmpleado(Empleado empleado) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)",
                SchemaDB.TAB_EMPLOYEE, SchemaDB.COL_NAME_EMP,
                SchemaDB.COL_SURNAME, SchemaDB.COL_MAIL);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, empleado.getNombre());
        preparedStatement.setString(2, empleado.getApellidos());
        preparedStatement.setString(3, empleado.getCorreo());
        preparedStatement.execute();
    }

    public boolean mostrarEmpleado(String nombre, String apellidos) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format("SELECT %s, %s FROM %s",
                 SchemaDB.COL_NAME_EMP, SchemaDB.COL_SURNAME, SchemaDB.TAB_EMPLOYEE);
        System.out.println("Los empleados son:");
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) { //recorrer los registros de la DB
            nombre = resultSet.getString(SchemaDB.COL_NAME_EMP);
            apellidos = resultSet.getString(SchemaDB.COL_SURNAME);
            System.out.printf("%s %s \n", nombre, apellidos);
        }
        return resultSet.next();
    }
}
