package Practicas;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Persona implements Serializable { //Clase abstracta

    private String nombre;

    public Persona() {
        this.nombre = "DESCONOCIDO";
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    //GETTERS Y SETTER PERSONA
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

interface mostrarInformacion {
    void mostrarInformacion();
}

//-----------------------------------------------------------------------------------

class Huesped extends Persona implements mostrarInformacion, Serializable { //Clase huesped, hereda de persona implement
    //mostrarInformacion, Serializable

    private int nroHabitacion;

    public Huesped() {
        this.nroHabitacion = 0;
    }

    public Huesped(String nombre, int nroHabitacion) {
        super(nombre);
        this.nroHabitacion = nroHabitacion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre: "+super.getNombre()+"\n"
        +"Habitacion Asignada: "+ this.nroHabitacion);
    }

    //GETTERS Y SETTERS HUESPED
    public int getNroHabitacion() {
        return nroHabitacion;
    }

    public void setNroHabitacion(int nroHabitacion) {
        this.nroHabitacion = nroHabitacion;
    }
}

//------------------------------------------------------------------------------

class Habitacion implements mostrarInformacion, Serializable { //Clase habitacion, implementa Serializable y
    //mostratInformacion. Contiene un arraylist de Huespedes y el estado de la habitacion.

    private boolean estado;
    private ArrayList<Huesped> huespedes;
    private int cantidadCamas;
    private int nroHabitacion;

    public Habitacion() {
        this.estado = false;
        this.huespedes = null;
        this.cantidadCamas = 0;
        this.nroHabitacion = 0;
    }

    public Habitacion(int cantidadCamas, int nroHabitacion) {
        this.estado = false;
        this.huespedes = null;
        this.cantidadCamas = cantidadCamas;
        this.nroHabitacion = nroHabitacion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("***HABITACION "+this.nroHabitacion+"***");
        System.out.println("Estado: "+(this.estado ? "Ocupada" : "Disponible")+"\n"
                +"cantCamas: "+this.cantidadCamas+"\n"
                +"Numero de habitacion: "+this.nroHabitacion);
        System.out.println();
        if (this.huespedes == null) {
            System.out.println("Sin huespedes");
        } else {
            for(Huesped h : this.huespedes) {
                h.mostrarInformacion();
                System.out.println();
            }
        }
        System.out.println("-------------------------------------------");

    }

    //GETTERS Y SETTERS HABITACION
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ArrayList<Huesped> getHuespedes() {
        return huespedes;
    }

    public void setHuespedes(ArrayList<Huesped> huespedes) {
        this.huespedes = huespedes;
    }

    public int getCantidadCamas() {
        return cantidadCamas;
    }

    public void setCantidadCamas(int cantidadCamas) {
        this.cantidadCamas = cantidadCamas;
    }

    public int getNroHabitacion() {
        return nroHabitacion;
    }

    public void setNroHabitacion(int nroHabitacion) {
        this.nroHabitacion = nroHabitacion;
    }
}

//-----------------------------------------------------------------

class Hotel { //Clase Hotel que relaciona las clases Huespedes con habitaciones. Contiene un arraylist de habitaciones
    //Recupera el nombre del hotel de un archivo de texto (.txt) llamado nombreHotel ubicado en el root del proyecto
    //Posee los metodos agregarHabitacion, cancelarReserva y la capacidad de guardar / cargar la informacion de las
    //reservas usando la serializacion.

    private String nombreHotel;
    private ArrayList<Habitacion> habitaciones;

    public Hotel() {
        this.habitaciones = new ArrayList<Habitacion>();
        this.nombreHotel = recuperarNombre();
    }

    private String recuperarNombre() {
        String nombreHotel = "";
        try (Scanner entrada = new Scanner(new File("nombreHotel.txt"))) {
            nombreHotel = entrada.nextLine();
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el nombre del hotel desde el archivo: " + e.getMessage());
        }

        return nombreHotel;
    }

    public void agregarHabitacion(int cantidadCamas, int nroHabitacion) {
        Habitacion hab = new Habitacion(cantidadCamas, nroHabitacion);
        this.habitaciones.add(hab);
    }

    public void mostrarHabitaciones() {

        for (Habitacion habitacion : this.habitaciones) {
            habitacion.mostrarInformacion();
        }
    }

    public void cancelarReserva(int nroHabitacion) {
        this.habitaciones.get(nroHabitacion).setEstado(false);
        this.habitaciones.get(nroHabitacion).setHuespedes(null);
    }

    public void guardar(ArrayList<Habitacion> habitaciones) {
        try {
            // Crear un flujo de salida para escribir en un archivo llamado "concesionaria.txt"
            FileOutputStream archivoSalida = new FileOutputStream("Habitaciones.txt");
            // Crear un flujo de objeto para escribir objetos en el flujo de salida
            ObjectOutputStream flujoSalida = new ObjectOutputStream(archivoSalida);
            // Escribir el objeto "Vehiculos" en el flujo de salida
            flujoSalida.writeObject(habitaciones);
            // Importante: cerrar el flujo de salida para liberar recursos
            flujoSalida.close();
        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir información de la excepción
        }
    }

    public ArrayList<Habitacion> cargar() {
        ArrayList<Habitacion> habitaciones = null;
        try {
            // Abrir un flujo de entrada para leer desde el archivo "curso.txt"
            FileInputStream archivoEntrada = new FileInputStream("Habitaciones.txt");
            // Crear un flujo de objeto para leer objetos desde el flujo de entrada
            ObjectInputStream flujoEntrada = new ObjectInputStream(archivoEntrada);
            // Leer el objeto serializado desde el flujo de entrada y convertirlo a un objeto Curso
            Object objetoLeido = flujoEntrada.readObject();
            if(objetoLeido instanceof ArrayList) {
                habitaciones = (ArrayList<Habitacion>) objetoLeido;
                System.out.println("Datos cargados exitosamente!");
            } else {
                System.out.println("El objeto leido no pertenece a un ArrayList");
            }

            // Importante: cerrar el flujo de entrada para liberar recursos
            flujoEntrada.close();
        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir información de la excepción
        }
        return habitaciones; // Devolver el objeto habitaciones deserializado
    }


    //GETTERS Y SETTERS CLASE HOTEL
    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
}

//-----------------------------------------------------------------------

public class PracticaParcialHoteleria {

    public static void main(String[] args) { //Main del programa de gestion de Hotel, contiene un menu para realizar
        //las diferentes operaciones de acuerdo a la clase Hotel. NOTA: Al trabajar con numeros de habitaciones en el
        //input, se utiliza nrohabitaciones-1 para conseguir la posicion en el array. En el menu se puede encontrar como
        //variables opc, prestar atencion a lo que realizar cada parte del menu!
        Scanner scan = new Scanner(System.in);

        Hotel hotel = new Hotel();

        hotel.agregarHabitacion(2, 1);
        hotel.agregarHabitacion(3, 2);
        hotel.agregarHabitacion(3, 3);
        hotel.agregarHabitacion(1, 4);
        hotel.agregarHabitacion(5, 5);

        while (true) {

            System.out.println("-----------------HOTEL " + hotel.getNombreHotel() + "---------------");
            System.out.println("1.Ver lista de habitaciones" + "\n"
                    + "2.Reservar una habitacion" + "\n"
                    + "3.Cancelar una reserva" + "\n"
                    + "4.Guardar reservas en archivo" + "\n"
                    + "5.Cargar reservas desde un archivo" + "\n"
                    + "6.Salir");

            System.out.print("Opcion: ");
            int opc = scan.nextInt();

            switch (opc) {
                case 1:
                    hotel.mostrarHabitaciones();
                    break;
                case 2:
                    System.out.println("Reservar habitacion");
                    System.out.println("Habitaciones disponibles: ");
                    for (Habitacion h : hotel.getHabitaciones()) { //For each para mostrar el numero de habitaciones
                        if (!h.isEstado()) {                       //disponible
                            System.out.print(h.getNroHabitacion() + ", ");
                        }
                    }
                    System.out.println();
                    System.out.println("Ingrese habitacion que desea reservar: ");
                    int opc1 = scan.nextInt();

                    System.out.println("Ingrese cantidad de personas: ");
                    int opc2 = scan.nextInt();

                    if (opc2 == 2) {
                        System.out.println("Ingrese datos de la primera persona");

                        System.out.print("Nombre: ");
                        String nomb = scan.next();

                        System.out.println("Ingrese datos de la segunda persona");

                        System.out.print("Nombre: ");
                        String nomb1 = scan.next();


                        Huesped h1 = new Huesped(nomb, opc1);
                        Huesped h2 = new Huesped(nomb1, opc1);

                        ArrayList<Huesped> nuevosHuespedes = new ArrayList<Huesped>();
                        nuevosHuespedes.add(h1);
                        nuevosHuespedes.add(h2);

                        hotel.getHabitaciones().get(opc1 - 1).setHuespedes(nuevosHuespedes);
                        hotel.getHabitaciones().get(opc1 - 1).setEstado(true);
                    } else {
                        System.out.println("El minimo y maximo de personas es 2");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el numero de habitacion que desea cancelar");
                    int opc3 = scan.nextInt();
                    hotel.cancelarReserva(opc3 - 1);
                    break;
                case 4:
                    System.out.println("Guardar en archivo");
                    hotel.guardar(hotel.getHabitaciones());
                    break;
                case 5:
                    System.out.println("Cargar reservas desde archivo");
                    hotel.setHabitaciones(hotel.cargar());
                    break;
                case 6:
                    scan.close();
                    return;

                default:
                    System.out.println("Opcion ingresada incorrecta, por favor vuelva a intentarlo");
            }
        }
    }
}
