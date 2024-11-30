package database;

import javax.sound.sampled.Port;

public interface SchemaDB {
    String HOST = "localhost";
    String PORT = "3306";
    String DB_NAME="almacen";

    //TABLA PRODUCTOS
    String TAB_PRODUCT="productos";
    String COL_NAME="nombre";
    String COL_DESCRIPTION="descripcion";
    String COL_QUANTITY="cantidad";
    String COL_PRICE="precio";

    //TABLA EMPLEADOS
    String TAB_EMPLOYEE="empleados";
    String COL_NAME_EMP="nombre";
    String COL_SURNAME="apellidos";
    String COL_MAIL="correo";

    //TABLA PEDIDOS
    String TAB_ORDERS="pedidos";
    String COL_ID_PROD="id_producto";
    String COL_DESCRIPTION_ORD="descripcionPedido";
    String COL_TOTAL="precio_total";

    //TABLA PRODUCTOS_FAV
    String TAB_FAV="productos_fav";
    String COL_ID_FAV="id";


}
