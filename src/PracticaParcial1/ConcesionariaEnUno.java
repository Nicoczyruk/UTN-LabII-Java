package PracticaParcial1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

abstract class Vehiculo implements Serializable {

    protected String marca;
    protected String modelo;
    protected double precio;

    public Vehiculo() {
        this.marca = "Desconocido";
        this.modelo = "Desconocido";
        this.precio = 0.00;
    }

    public Vehiculo(String marca, String modelo, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    public abstract double calcularImpuesto();
    public abstract String mostrarInformacion();

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

class Coche extends Vehiculo {

    private int cantRuedas;
    private int capacidadTransporte;

    public Coche(int cantRuedas, int capacidadTransporte) {
        this.cantRuedas = cantRuedas;
        this.capacidadTransporte = capacidadTransporte;
    }

    public Coche(String marca, String modelo, double precio, int cantRuedas, int capacidadTransporte) {
        super(marca,modelo,precio);
        this.cantRuedas = cantRuedas;
        this.capacidadTransporte = capacidadTransporte;
    }

    @Override
    public double calcularImpuesto() {
        return this.precio * 0.30;
    }

    @Override
    public String mostrarInformacion() {
        return "Informacion del Vehiculo"+"\n"
                +"Marca: "+this.marca+"\n"
                +"Modelo: "+this.modelo+"\n"
                +"Precio: "+this.precio+"\n"
                +"Cantidad de ruedas: "+this.cantRuedas+"\n"
                +"Capacidad de transporte hasta "+this.capacidadTransporte+" personas"+"\n";
    }

    public int getCantRuedas() {
        return cantRuedas;
    }

    public void setCantRuedas(int cantRuedas) {
        this.cantRuedas = cantRuedas;
    }

    public int getCapacidadTransporte() {
        return capacidadTransporte;
    }

    public void setCapacidadTransporte(int capacidadTransporte) {
        this.capacidadTransporte = capacidadTransporte;
    }
}

class Moto extends Vehiculo {

    private int capacidadTransporte;
    private String color;

    public Moto(int capacidadTransporte, String color) {
        this.capacidadTransporte = capacidadTransporte;
        this.color = color;
    }

    public Moto(String marca, String modelo, double precio, int capacidadTransporte, String color) {
        super(marca,modelo,precio);
        this.capacidadTransporte = capacidadTransporte;
        this.color = color;
    }

    @Override
    public double calcularImpuesto() {
        return this.precio * 0.16;
    }

    @Override
    public String mostrarInformacion() {
        return "Informacion del Vehiculo"+"\n"
                +"Marca: "+this.marca+"\n"
                +"Modelo: "+this.modelo+"\n"
                +"Precio: "+this.precio+"\n"
                +"Color: "+this.color+"\n"
                +"Capacidad de transporte hasta "+this.capacidadTransporte+" personas"+"\n";
    }

    public int getCapacidadTransporte() {
        return capacidadTransporte;
    }

    public void setCapacidadTransporte(int capacidadTransporte) {
        this.capacidadTransporte = capacidadTransporte;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

interface Serializar {

    void guardar(ArrayList<Vehiculo> vehiculos);
    ArrayList<Vehiculo> cargar();

}

class Concesionaria implements Serializar {

    private ArrayList<Vehiculo> Vehiculos;

    public Concesionaria() {
        this.Vehiculos = new ArrayList<Vehiculo>();
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        this.Vehiculos.add(vehiculo);
    }

    public void eliminarVehiculo(String marca, String modelo) {

        int j = -1;

        for (int i = 0; i < this.Vehiculos.size(); i++) {
            Vehiculo v = this.Vehiculos.get(i);
            if (v.getMarca().equals(marca) && v.getModelo().equals(modelo)) {
                j = i;
                break;
            }
        }

        if (j != -1) {
            this.Vehiculos.remove(j);
            System.out.println("Vehiculo eliminado con exito");
        } else {
            System.out.println("No se encontro el vehiculo que desea eliminar");
        }
    }

    public void editarPrecio(String marca, String modelo, double nuevoPrecio) {
        int j = -1;

        for (int i = 0; i < this.Vehiculos.size(); i++) {
            Vehiculo v = this.Vehiculos.get(i);
            if (v.getMarca().equals(marca) && v.getModelo().equals(modelo)) {
                j = i;
                break;
            }
        }

        if (j != -1) {
            this.Vehiculos.get(j).setPrecio(nuevoPrecio);
            System.out.println("Precio editado correctamente!");
        } else {
            System.out.println("Vehiculo no encontrado en la base de datos");
        }

    }

    public void guardar(ArrayList<Vehiculo> Vehiculos) {
        try {
            // Crear un flujo de salida para escribir en un archivo llamado "concesionaria.txt"
            FileOutputStream archivoSalida = new FileOutputStream("Concesionaria.txt");
            // Crear un flujo de objeto para escribir objetos en el flujo de salida
            ObjectOutputStream flujoSalida = new ObjectOutputStream(archivoSalida);
            // Escribir el objeto "Vehiculos" en el flujo de salida
            flujoSalida.writeObject(Vehiculos);
            // Importante: cerrar el flujo de salida para liberar recursos
            flujoSalida.close();
        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir información de la excepción
        }
    }

    public ArrayList<Vehiculo> cargar() {
        ArrayList<Vehiculo> vehiculos = null;
        try {
            // Abrir un flujo de entrada para leer desde el archivo "curso.txt"
            FileInputStream archivoEntrada = new FileInputStream("Concesionaria.txt");
            // Crear un flujo de objeto para leer objetos desde el flujo de entrada
            ObjectInputStream flujoEntrada = new ObjectInputStream(archivoEntrada);
            // Leer el objeto serializado desde el flujo de entrada y convertirlo a un objeto Curso
            Object objetoLeido = flujoEntrada.readObject();
            if(objetoLeido instanceof ArrayList) {
                vehiculos = (ArrayList<Vehiculo>) objetoLeido;
                System.out.println("Datos cargados exitosamente!");
            } else {
                System.out.println("El objeto leido no pertenece a un ArrayList");
            }

            // Importante: cerrar el flujo de entrada para liberar recursos
            flujoEntrada.close();
        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir información de la excepción
        }
        return vehiculos; // Devolver el objeto vehiculos deserializado
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return Vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        Vehiculos = vehiculos;
    }

    public void mostrarVehiculos() {
        System.out.println("------------------------VEHICULOS------------------------");
        for (int i = 0; i < this.Vehiculos.size(); i++) {
            System.out.println(this.Vehiculos.get(i).mostrarInformacion());
            System.out.println();
        }
    }
}


public class ConcesionariaEnUno {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Concesionaria concesionaria = new Concesionaria();

        while(true) {

            System.out.println("-----------------CONCESIONARIA---------------");
            System.out.println("""
                    1.Agregar Vehiculo
                    2.Eliminar Vehiculo
                    3.Editar Precio Vehiculo
                    4.Mostrar Vehiculos
                    5.Guardar Datos
                    6.Cargar ultima sesion
                    7.Salir""");
            System.out.print("Opcion: ");
            int opc = scan.nextInt();

            switch(opc) {
                case 1:
                    System.out.println("""
                            1.Moto
                            2.Auto
                            """);
                    System.out.print("Opcion: ");
                    int opc1 = scan.nextInt();

                    if(opc1 == 1) {
                        System.out.print("Marca: ");
                        String marca = scan.next();

                        System.out.print("Modelo: ");
                        String modelo = scan.next();

                        System.out.print("Precio: ");
                        double precio = scan.nextDouble();

                        System.out.print("Capacidad de transporte: ");
                        int capacidad = scan.nextInt();

                        System.out.print("Color: ");
                        String color = scan.next();

                        Moto moto = new Moto(marca,modelo,precio,capacidad,color);
                        concesionaria.agregarVehiculo(moto);
                    } else if (opc1 == 2) {
                        System.out.print("Marca: ");
                        String marca = scan.next();

                        System.out.print("Modelo: ");
                        String modelo = scan.next();

                        System.out.print("Precio: ");
                        double precio = scan.nextDouble();

                        System.out.print("Capacidad de transporte: ");
                        int capacidad = scan.nextInt();

                        System.out.print("Cantidad de ruedas: ");
                        int cantRuedas = scan.nextInt();

                        Coche auto = new Coche(marca,modelo,precio,cantRuedas,capacidad);
                        concesionaria.agregarVehiculo(auto);
                    } else{
                        System.out.println("Numero ingresado incorrecto, intentelo nuevamente");
                    }
                    break;

                case 2:

                    System.out.print("Ingrese la marca del vehiculo: ");
                    String marca = scan.next();

                    System.out.print("Ingrese el modelo del vehiculo: ");
                    String modelo = scan.next();

                    concesionaria.eliminarVehiculo(marca,modelo);
                    break;

                case 3:
                    System.out.print("Ingrese la marca del vehiculo: ");
                    String marca1 = scan.next();

                    System.out.print("Ingrese el modelo del vehiculo: ");
                    String modelo1 = scan.next();

                    System.out.print("Ingrese el nuevo precio del vehiculo: ");
                    double nPrecio = scan.nextDouble();

                    concesionaria.editarPrecio(marca1,modelo1,nPrecio);
                    break;

                case 4:
                    concesionaria.mostrarVehiculos();
                    break;

                case 5:
                    concesionaria.guardar(concesionaria.getVehiculos());
                    break;

                case 6:
                    ArrayList<Vehiculo> lista = concesionaria.cargar();
                    concesionaria.setVehiculos(lista);

                    break;

                case 7:
                    scan.close();
                    return;

                default:
                    System.out.println("Opcion ingresada incorrecta, por favor vuelva a intentarlo");
            }
        }


    }
}
