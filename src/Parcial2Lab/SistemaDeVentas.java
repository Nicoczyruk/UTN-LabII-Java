package Parcial2Lab;



import java.sql.*;
import java.util.ArrayList;

class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:33061/ventas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para ejecutar una consulta sin devolver resultados
    public static void ejecutarConsulta(String consulta) {
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Crear la declaración
            try (PreparedStatement statement = connection.prepareStatement(consulta)) {
                // Ejecutar la consulta
                statement.executeUpdate();
            }

            // Cerrar la conexión
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para ejecutar una consulta y devolver un conjunto de resultados
    public static ResultSet ejecutarConsultaConResultado(String consulta) {
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Crear la declaración
            PreparedStatement statement = connection.prepareStatement(consulta);

            // Ejecutar la consulta y devolver el conjunto de resultados
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class Producto {

    private int producto_id;
    private String nombre;
    private double precioPorUnidad;
    private int stock;

    public Producto() {
        this.producto_id = -1;
        this.nombre = "DESCONOCIDO";
        this.precioPorUnidad = -1;
        this.stock = -1;
    }

    public Producto(int id, String nombre, double precioPorUnidad, int stock) {
        this.producto_id = id;
        this.nombre = nombre;
        this.precioPorUnidad = precioPorUnidad;
        this.stock = stock;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    public void setPrecioPorUnidad(double precioPorUnidad) {
        this.precioPorUnidad = precioPorUnidad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "producto_id=" + producto_id +
                ", nombre='" + nombre + '\'' +
                ", precioPorUnidad=" + precioPorUnidad +
                ", stock=" + stock +
                '}';
    }
}

class Vendedor {

    private int vendedor_id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fecha_nacimiento;
    private Date fecha_contratacion;

    public Vendedor() {

    }

    public Vendedor(int vendedor_id, String nombre, String apellido, String dni,
                    Date fecha_nacimiento, Date fecha_contratacion) {
        this.vendedor_id = vendedor_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_contratacion = fecha_contratacion;
    }

    public Vendedor(String consultaBusqueda) {
        //Constructor especial que recibe una consulta de busqueda para crear
        //la instancia de vendedor.

        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consultaBusqueda);

        if (resultado != null) {

            try {

                while (resultado.next()) {
                    this.vendedor_id = resultado.getInt("vendedor_id");
                    this.nombre = resultado.getString("nombre");
                    this.apellido = resultado.getString("apellido");
                    this.dni = resultado.getString("dni");
                    this.fecha_nacimiento = resultado.getDate("fecha_nacimiento");
                    this.fecha_contratacion = resultado.getDate("fecha_contratacion");
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Error, no se recibieron datos");
        }

    }

    public int getVendedor_id() {
        return vendedor_id;
    }

    public void setVendedor_id(int vendedor_id) {
        this.vendedor_id = vendedor_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Date getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(Date fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "vendedor_id=" + vendedor_id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", fecha_contratacion=" + fecha_contratacion +
                '}';
    }
}

class Productos {

    public static void generarInforme() {
        //Metodo statico que imprime un informe con los datos de los productos
        //Calcula el total por producto (precio x unidad) y calcular el Total de totales

        String consulta = "SELECT * FROM productos";

        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        int totalTotal = 0;
        if(resultado != null) {

            try {

                System.out.println("Informe de Productos en Stock:");
                System.out.printf("%-30s %-5s %-5s %-8s\n","Producto","Stock","Precio","Total");
                System.out.println("----------------------------------------------------------");
                while(resultado.next()) {

                    String nombreProducto = resultado.getString("nombre");
                    int stock = resultado.getInt("stock");
                    double precioPorUnidad = resultado.getDouble("precio_por_unidad");
                    double calcularTotal = stock * precioPorUnidad;
                    totalTotal += calcularTotal;
                    System.out.printf("%-30s %-5d %-5.2f %-8.2f\n",nombreProducto,stock,precioPorUnidad,calcularTotal);

                }
                System.out.println("----------------------------------------------------------");
                System.out.println("\t\t\t\t\t\t\tTotal:\t\t   "+totalTotal);

            } catch(SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static Producto obtenerProductoPorID(int productoID) {
        //Metodo estatico que recibe una ID y busca en la tabla el producto asociado
        //a tal id.

        String consulta = "SELECT * FROM productos WHERE producto_id = '"+productoID+"'";

        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);


        if(resultado != null) {

            try {
                while(resultado.next()) {

                    int productoid = resultado.getInt("producto_id");
                    String nombreProducto = resultado.getString("nombre");
                    double porUnidad = resultado.getDouble("precio_por_unidad");
                    int stock = resultado.getInt("stock");

                    return new Producto(productoid,nombreProducto,porUnidad,stock);
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }


        } else {
            return null;
        }

        return null;
    }

    public static Producto obtenerProductoMasVendido() {
        //Metodo estatico que obtiene el producto mas vendido de una tabla de productos

        String consulta = "SELECT producto_id, SUM(cantidad_vendida) as total_vendido\n" +
                "FROM ventas\n" +
                "GROUP BY producto_id\n" +
                "ORDER BY total_vendido DESC\n" +
                "LIMIT 1";

        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        if(resultado != null) {

            try {
                while(resultado.next()) {

                    int productoid = resultado.getInt("producto_id");


                    return obtenerProductoPorID(productoid);
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }


        } else {
            return null;
        }

        return null;
    }

}

class Comerciales {

    public static Vendedor obtenerVendedorPorID(int vendedorID) {
        //Metodo estatico que obtiene los datos de un vendedor a traves de su ID

        String consulta = "SELECT * FROM vendedores WHERE vendedor_id = '"+vendedorID+"'";

        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        Vendedor vendedorObtenido = new Vendedor();

        if(resultado != null) {

            try {
                while(resultado.next()) {

                    vendedorObtenido.setVendedor_id(resultado.getInt("vendedor_id"));
                    vendedorObtenido.setNombre(resultado.getString("nombre"));
                    vendedorObtenido.setApellido(resultado.getString("apellido"));
                    vendedorObtenido.setDni(resultado.getString("dni"));
                    vendedorObtenido.setFecha_nacimiento(resultado.getDate("fecha_nacimiento"));
                    vendedorObtenido.setFecha_contratacion(resultado.getDate("fecha_contratacion"));

                    return vendedorObtenido;
                }


            } catch(SQLException e) {
                e.printStackTrace();
            }


        } else {
            return null;
        }

        return null;
    }

    public static ArrayList<Vendedor> listadoDeVendedores() {
        //Metodo estatico que devuelve un ArrayList de Vendedor, que contiene todos los vendedores
        //de la tabla de vendedores en la base de datos

        String consulta = "SELECT * FROM vendedores";

        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();

        if (resultado != null) {

            try {

                while(resultado.next()) {

                    Vendedor vendedorX = new Vendedor();
                    vendedorX.setVendedor_id(resultado.getInt("vendedor_id"));
                    vendedorX.setNombre(resultado.getString("nombre"));
                    vendedorX.setApellido(resultado.getString("apellido"));
                    vendedorX.setDni(resultado.getString("dni"));
                    vendedorX.setFecha_nacimiento(resultado.getDate("fecha_nacimiento"));
                    vendedorX.setFecha_contratacion(resultado.getDate("fecha_contratacion"));

                    vendedores.add(vendedorX);
                }


            } catch(SQLException e) {
                e.printStackTrace();
            }


        } else {
            return null;
        }

        return vendedores;


    }



}

public class SistemaDeVentas {

    public static void main(String[] args) {

        //Producto productoObtenido = Productos.obtenerProductoPorID(3);

        //System.out.println(productoObtenido.toString()); //para Probar que obtiene el producto buscado

        //Productos.generarInforme();

        //Vendedor vendedorBuscado = Comerciales.obtenerVendedorPorID(3); //Para probar que obtiene el vendedorBuscado

        //System.out.println(vendedorBuscado.toString());

        //Producto productoMasVendido = Productos.obtenerProductoMasVendido();

        //System.out.println(productoMasVendido.toString()); //Para probar que obtiene el producto mas vendido

        //ArrayList<Vendedor> vendedores = Comerciales.listadoDeVendedores();

        //System.out.println(vendedores.get(0).toString()); //Para probar que el array esta cargado
    }
}
