
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Persona implements Serializable {
    protected String nombre;
    protected String apellido;
    protected int dni;

    public Persona() {
        this.nombre = "DESCONOCIDO";
        this.apellido = "DESCONOCIDO";
        this.dni = 0;
    }

    public Persona(String nombre, String apellido, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }


    //GETTERS Y SETTERS
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}

class Huesped extends Persona implements MostrarInformacion, Serializable {
    private int nroHabitacion;
    private int cantDias;

    public Huesped() {
        this.nroHabitacion = 0;
        this.cantDias = 0;
    }

    public Huesped(String nombre, String apellido, int dni, int nroHabitacion, int cantDias) {
        super(nombre,apellido,dni);
        this.nroHabitacion = nroHabitacion;
        this.cantDias = cantDias;
    }

    @Override
    public void mostrarInformacionCompleta() {
        System.out.println("Nombre: "+this.nombre+"\n"
        +"Apellido: "+this.apellido+"\n"
        +"DNI: "+this.dni+"\n"
        +"Numero de habitacion: "+this.nroHabitacion+"\n"
        +"Dias pagados: "+this.cantDias);
    }

    @Override
    public void mostrarInformacionParcial() {
        System.out.println("Nombre: "+this.nombre+"\n"
        +"Apellido: "+this.apellido+"\n"
        +"Numero de habitacion: "+this.nroHabitacion);
    }

    //GETTERS Y SETTERS
    public int getNroHabitacion() {
        return nroHabitacion;
    }

    public void setNroHabitacion(int nroHabitacion) {
        this.nroHabitacion = nroHabitacion;
    }

    public int getCantDias() {
        return cantDias;
    }

    public void setCantDias(int cantDias) {
        this.cantDias = cantDias;
    }
}

class Habitacion implements MostrarInformacion, Serializable {
    private boolean estado;
    private ArrayList<Huesped> huespedes;
    private int cantCamas;
    private int nroHabitacion;

    public Habitacion() {
        this.estado = false;
        this.huespedes = null;
        this.cantCamas = 0;
        this.nroHabitacion = 0;
    }

    public Habitacion(boolean estado, int cantCamas, int nroHabitacion) {
        this.estado = estado;
        this.huespedes = null;
        this.cantCamas = cantCamas;
        this.nroHabitacion = nroHabitacion;

    }

    public Habitacion(boolean estado, ArrayList<Huesped> listaHuespedes, int cantCamas, int nroHabitacion) {
        this.estado = estado;
        this.huespedes = listaHuespedes;
        this.cantCamas = cantCamas;
        this.nroHabitacion = nroHabitacion;
    }

    @Override
    public void mostrarInformacionCompleta() {
        System.out.println("---------HABITACION "+this.nroHabitacion+"-----------------");
        System.out.println("Estado: "+this.estado+"\n"
        +"cantCamas: "+this.cantCamas+"\n"
        +"Numero de habitacion: "+nroHabitacion);

        if (this.huespedes == null) {
            System.out.println("Sin huespedes");
        } else {
            for(Huesped h : this.huespedes) {
                h.mostrarInformacionCompleta();
            }
        }
        System.out.println("--------------------------------------------------");
    }

    @Override
    public void mostrarInformacionParcial() {
        System.out.println("Estado: "+this.estado+"\n"
                +"Capacidad: "+nroHabitacion);
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ArrayList <Huesped> getHuespedes() {
        return huespedes;
    }

    public void setHuespedes(ArrayList <Huesped> huespedes) {
        this.huespedes = huespedes;
    }

    public int getCantCamas() {
        return cantCamas;
    }

    public void setCantCamas(int cantCamas) {
        this.cantCamas = cantCamas;
    }

    public int getNroHabitacion() {
        return nroHabitacion;
    }

    public void setNroHabitacion(int nroHabitacion) {
        this.nroHabitacion = nroHabitacion;
    }
}

class Hotel {

    private ArrayList<Habitacion> habitaciones;

    public Hotel() {
        this.habitaciones = new ArrayList<Habitacion>(5);
    }

    public ArrayList <Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList <Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void agregarHabitacion(boolean estado, int cantCamas,int nroHabitacion) {
        Habitacion hab = new Habitacion(estado, cantCamas, nroHabitacion);
        this.habitaciones.add(hab);
    }

    public void mostrarHabitaciones() {

        for (Habitacion habitacion : this.habitaciones) {
            habitacion.mostrarInformacionCompleta();
        }
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

    public void cancelarReserva(int i) {
        this.habitaciones.get(i).setEstado(false);
        this.habitaciones.get(i).setHuespedes(null);
    }

}

interface MostrarInformacion {
    void mostrarInformacionParcial();
    void mostrarInformacionCompleta();

}

public class PracticaParcialHotel {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Hotel hotel = new Hotel();

        hotel.agregarHabitacion(false,2,1);
        hotel.agregarHabitacion(false,3,2);
        hotel.agregarHabitacion(false,3,3);
        hotel.agregarHabitacion(false,1,4);
        hotel.agregarHabitacion(false,5,5);

        String nombreHotel = "";
        try (Scanner entrada = new Scanner(new File("nombreHotel.txt"))) {
            nombreHotel = entrada.nextLine();
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el nombre del hotel desde el archivo: " + e.getMessage());
        }


        while(true) {

            System.out.println("-----------------HOTEL "+nombreHotel+"---------------");
            System.out.println("1.Ver lista de habitaciones"+"\n"
                    +"2.Reservar una habitacion"+"\n"
                    +"3.Cancelar una reserva"+"\n"
                    +"4.Guardar reservas en archivo"+"\n"
                    +"5.Cargar reservas desde un archivo"+"\n"
                    +"6.Salir");

            System.out.print("Opcion: ");
            int opc = scan.nextInt();

            switch (opc) {
                case 1:
                    hotel.mostrarHabitaciones();
                    break;
                case 2:
                    System.out.println("Reservar habitacion");
                    System.out.println("Habitaciones disponibles: ");
                    for (Habitacion h : hotel.getHabitaciones()) {
                        if(!h.isEstado()) {
                            System.out.print(h.getNroHabitacion()+", ");
                        }
                    }
                    System.out.println();
                    System.out.println("Ingrese habitacion que desea reservar: ");
                    int opc1 = scan.nextInt();

                    System.out.println("Ingrese cantidad de personas: ");
                    int opc2 = scan.nextInt();

                    if(opc2 == 2) {
                        System.out.println("Ingrese datos de la primera persona");

                        System.out.print("Nombre: ");
                        String nomb = scan.next();

                        System.out.print("Apellido: ");
                        String apellido = scan.next();

                        System.out.print("DNI: ");
                        int dni = scan.nextInt();

                        System.out.println("Ingrese datos de la segunda persona");

                        System.out.print("Nombre: ");
                        String nomb1 = scan.next();

                        System.out.print("Apellido: ");
                        String apellido1 = scan.next();

                        System.out.print("DNI: ");
                        int dni1 = scan.nextInt();

                        Huesped h1 = new Huesped(nomb,apellido,dni,opc1,15);
                        Huesped h2 = new Huesped(nomb1,apellido1,dni1,opc1,15);

                        ArrayList<Huesped> nuevosHuespedes = new ArrayList<Huesped>();
                        nuevosHuespedes.add(h1);
                        nuevosHuespedes.add(h2);

                        hotel.getHabitaciones().get(opc1-1).setHuespedes(nuevosHuespedes);
                        hotel.getHabitaciones().get(opc1-1).setEstado(true);
                    } else {
                        System.out.println("El minimo y maximo de personas es 2");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el numero de habitacion que desea cancelar");
                    int opc3 = scan.nextInt();
                    hotel.cancelarReserva(opc3-1);
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
