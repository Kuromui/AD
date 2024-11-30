import dao.EmpleadoDao;
import dao.PedidoDao;
import dao.ProductoDao;
import dao.Producto_FavDao;
import model.Empleado;
import model.Pedido;
import model.Producto;
import model.Producto_Fav;

import java.sql.SQLException;
import java.util.Scanner;

public class Entrada {
   public static void main(String[] args) throws SQLException {

       ProductoDao productoDao = new ProductoDao();
       EmpleadoDao empleadoDao = new EmpleadoDao();
       PedidoDao pedidoDao = new PedidoDao();
       Producto_FavDao productoFavDao = new Producto_FavDao();
       Producto producto = new Producto();
       Empleado empleado = new Empleado();
       Pedido pedido = new Pedido();
       Producto_Fav productoFav = new Producto_Fav();
       int opcion;
       Scanner scanner = new Scanner(System.in);


       do{
            menu();
           opcion= scanner.nextInt();
           switch (opcion){
               case 1 ://Añadir productos con Json
                   productoDao.obtenerProductos();
                   System.out.println("TERMINADO");
                   break;
               case 2 ://Mostrar productos <600
                   productoDao.mostrarProducto(600.00);
                   break;
               case 3 ://CREACION EMPLEADOS
                        System.out.println("Dime nombre a introducir");
                        String nombre = scanner.next();
                        System.out.println("Dime apellidos a introducir");
                        String apellidos = scanner.next();
                        System.out.println("Dime correo a introducir");
                        String correo = scanner.next();
                        try {
                            empleadoDao.añadirEmpleado(new Empleado(nombre, apellidos,correo));
                        } catch (SQLException e) {
                            System.out.println("Hay un error en la ejecucion");
                            System.out.println(e.getMessage());
                        }
                   break;
               case 4 : //CONSULTA EMPLEADOS
                       try {
                           empleadoDao.mostrarEmpleado(empleado.getNombre(), empleado.getApellidos());
                       } catch (SQLException e) {
                           System.out.println("Fallo en la impresión");
                           System.out.println(e.getMessage());
                       }
                   break;
               case 5 ://MOSTRAR PEDIDO
                   pedidoDao.mostrarPedido();
                   break;
               case 6 :
                   System.out.println("SALIR");
                   break;
           }

       }while (opcion!=6);
   }
   protected static void menu(){
       System.out.println("\n");
       System.out.println("Seleccione una opción:");
       System.out.println("1.Añadir productos mediante Json.");
       System.out.println("2.Mostrar productos <600€.");
       System.out.println("3.Crear empleado");
       System.out.println("4.Mostrar empleados");
       System.out.println("5.Mostrar pedidos.");
       System.out.println("6.SALIR");
   }
}
