package dao;

import com.google.gson.Gson;
import database.DBConnection;
import database.SchemaDB;
import model.Producto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDao {

    private Connection connection;
    private PreparedStatement preparedStatement; //para hacer inserciones
    private ResultSet resultSet; //resultSet obtiene y actualiza columnas por nombre, trabajar con selecciones

    //PETICION JSON

    public void obtenerProductos() {
        String urlString = "https://dummyjson.com/products";
        URL url;
        {
            try {
                url = new URL(String.format(urlString));
                HttpURLConnection connection1 = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection1.getInputStream())));
                String linea = bufferedReader.readLine();
                JSONObject response = new JSONObject(linea);
                JSONArray products = response.getJSONArray("products");
                for (Object product : products) {
                    if (product instanceof JSONObject) {
                        String nombre = ((JSONObject) product).getString("title");
                        String descripcion = ((JSONObject) product).getString("description");
                        int cantidad = ((JSONObject) product).getInt("stock");
                        double precio = ((JSONObject) product).getDouble("price");

                        //Conversión a PRODUCTO
                        Gson gson = new Gson();
                        Producto producto = gson.fromJson(product.toString(), Producto.class);
                        añadirProducto(producto);
                    }
                }
            } catch (MalformedURLException e) {
                System.out.println("Fallo en la url");
            } catch (IOException e) {
                System.out.println("Fallo en la conexión productos");
            } catch (SQLException e) {
                System.out.println("Fallo en la creación del objeto");
                System.out.println(e.getMessage());
                ;
            }
        }
    }

    private void añadirProducto(Producto producto) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?)",
                SchemaDB.TAB_PRODUCT, SchemaDB.COL_NAME,
                SchemaDB.COL_DESCRIPTION, SchemaDB.COL_QUANTITY, SchemaDB.COL_PRICE);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, producto.getTitle());
        preparedStatement.setString(2, producto.getDescription());
        preparedStatement.setString(3, String.valueOf(producto.getStock()));
        preparedStatement.setString(4, String.valueOf(producto.getPrice()));
        preparedStatement.execute();
        System.out.println("añadido");
    }

    public boolean mostrarProducto(double price) throws SQLException {
        connection = new DBConnection().getConnection();
        String query = String.format("SELECT * FROM %s WHERE %s < ?",
                 SchemaDB.TAB_PRODUCT, SchemaDB.COL_PRICE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1, price);
        resultSet = preparedStatement.executeQuery();
        System.out.print("Los productos con un precio < 600€ son: ");
        while (resultSet.next()) {
            String nombre = resultSet.getString(SchemaDB.COL_NAME);
            double precio = resultSet.getDouble(SchemaDB.COL_PRICE);
            System.out.print("\n" + nombre);
        }
        return resultSet.next();
    }
}
