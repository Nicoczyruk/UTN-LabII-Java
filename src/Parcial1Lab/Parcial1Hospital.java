package Parcial1Lab;

import java.io.*;
import java.util.*;

abstract class Persona implements Serializable {
    private String nombre;
    private int dni;
    private String fechaNacimiento;

    public Persona(String nombre, int dni, String fechaNacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}

interface Informacion {
    void verHistorialDeEventos();
}

class Doctor extends Persona  {

    private String especialidad;

    public Doctor(String nombre, int dni, String fechaNacimiento, String especialidad) {
        super(nombre,dni,fechaNacimiento);
        this.especialidad = especialidad;
    }

    public void mostrarDoctor() {
        System.out.println("Nombre: "+this.getNombre()+"\n"
        +"DNI: "+this.getDni()+"\n"
        +"Fecha de Nacimiento: "+this.getFechaNacimiento()+"\n"
        +"Especialidad: "+this.especialidad);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}

class Paciente extends Persona implements Informacion, Serializable {

    private int nroTelefono;
    private int tipoSangre;
    private ArrayList<HistorialMedico> historialMedico;

    public Paciente(String nombre, int dni, String fechaNacimiento, int nroTelefono,int tipoSangre) {
        super(nombre,dni,fechaNacimiento);
        this.nroTelefono = nroTelefono;
        this.tipoSangre = tipoSangre;
        this.historialMedico = new ArrayList<HistorialMedico>();

        }


    @Override
    public void verHistorialDeEventos() {
        System.out.println("HISTORIAL MEDICO PACIENTE: "+this.getNombre());
        for (HistorialMedico hm : this.historialMedico) {
            hm.mostrarHistorial();
        }
    }

    public int getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(int nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public int getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(int tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public ArrayList <HistorialMedico> getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(ArrayList <HistorialMedico> historialMedico) {
        this.historialMedico = historialMedico;
    }
}

class HistorialMedico implements Serializable {

    private String fecha;
    private String observaciones;

    public HistorialMedico(String fecha, String observaciones) {
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void mostrarHistorial() {
        System.out.println(this.fecha + "-" + this.observaciones);
    }
}

class Hospital {

    private ArrayList<Doctor> doctores;
    private ArrayList<Paciente> pacientes;

    public Hospital() {
        this.doctores = new ArrayList<Doctor>();
        this.pacientes = new ArrayList<Paciente>();
    }

    public void listaDoctores() {
        for (Doctor d : this.doctores) {
            d.mostrarDoctor();
            System.out.println("--------------------");
        }
    }

    public void actualizarInformacionPaciente (int dni, String nombre, String fechaNacimiento, int tipoSangre, int numeroTelefono) {

        int j = -1;

        for(int i = 0; i < this.pacientes.size(); i++) {
            if(this.pacientes.get(i).getDni() == dni) {
                j = i;
                break;
            }
        }

        if(j != -1) {
            this.pacientes.get(j).setNombre(nombre);
            this.pacientes.get(j).setFechaNacimiento(fechaNacimiento);
            this.pacientes.get(j).setNroTelefono(numeroTelefono);
            this.pacientes.get(j).setTipoSangre(tipoSangre);
        } else {
            System.out.println("Paciente no encontrado, cambios cancelados");
        }
    }

    public void mostrarHistorialPaciente(int dni) {

        int j = -1;

        for(int i = 0; i < this.pacientes.size(); i++) {
            if(this.pacientes.get(i).getDni() == dni) {
                j = i;
                break;
            }
        }

        if(j != -1) {
            this.pacientes.get(j).verHistorialDeEventos();
        } else {
            System.out.println("Paciente no encontrado");
        }
    }

    public void nuevoHistorial(int dni, Scanner scan) {

        int j = -1;

        for(int i = 0; i < this.pacientes.size(); i++) {
            if(this.pacientes.get(i).getDni() == dni) {
                j = i;
                break;
            }
        }

        if(j != -1) {
            ArrayList<HistorialMedico> h = new ArrayList<HistorialMedico>();
            while(true) {
                System.out.print("Ingrese fecha del evento (dd/mm/aaaa): ");
                String eventofecha = scan.next();

                System.out.println("Ingrese observacion: ");
                String observacion = scan.next();

                HistorialMedico h1 = new HistorialMedico(eventofecha, observacion);
                h.add(h1);

                System.out.println("Desea agregar otro evento? 1 SI / 2 NO");
                int check = scan.nextInt();

                if(check == 2) {
                    break;
                }
            }
            this.pacientes.get(j).setHistorialMedico(h);
        } else {
            System.out.println("Paciente no encontrado");
        }
    }

    public void guardarHistorial(ArrayList<Paciente> pacientes) {
        try {
            FileOutputStream archivoSalida = new FileOutputStream("Pacientes.txt");
            ObjectOutputStream flujoSalida = new ObjectOutputStream(archivoSalida);

            flujoSalida.writeObject(pacientes);
            // Importante: cerrar el flujo de salida para liberar recursos
            flujoSalida.close();
        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir información de la excepción
        }
    }

    public ArrayList<Paciente> cargarHistorial() {
        ArrayList<Paciente> pacientes = null;
        try {

            FileInputStream archivoEntrada = new FileInputStream("Pacientes.txt");

            ObjectInputStream flujoEntrada = new ObjectInputStream(archivoEntrada);
            Object objetoLeido = flujoEntrada.readObject();
            if(objetoLeido instanceof ArrayList) {
                pacientes = (ArrayList<Paciente>) objetoLeido;
                System.out.println("Datos cargados exitosamente!");
            } else {
                System.out.println("El objeto leido no pertenece a un ArrayList");
            }

            // Importante: cerrar el flujo de entrada para liberar recursos
            flujoEntrada.close();
        } catch (Exception e) {
            e.printStackTrace(); // En caso de error, imprimir información de la excepción
        }
        return pacientes; // Devolver el objeto habitaciones deserializado
    }

    public static String recuperarTexto(String nombreArchivo) {
        String texto = "";
        try (Scanner entrada = new Scanner(new File(nombreArchivo))) {
            if (entrada.hasNext()) {
                texto = entrada.nextLine();
            } else {
                System.err.println("El archivo está vacío.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el archivo " + nombreArchivo + ": " + e.getMessage());
        }

        return texto;
    }

    public void mostrarDatosDeContacto() {
        System.out.println(recuperarTexto("Contacto.txt"));
    }

    public ArrayList <Doctor> getDoctores() {
        return doctores;
    }

    public void setDoctores(ArrayList <Doctor> doctores) {
        this.doctores = doctores;
    }

    public ArrayList <Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList <Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}
public class Parcial1Hospital{

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Hospital hospital = new Hospital();

        Doctor d1 = new Doctor("Pedro",4263,"21/10/1999","Cirujano");
        Doctor d2 = new Doctor("Juan",6312,"12/5/1999","Cardiologo");
        Doctor d3 = new Doctor("Julieta",1234,"15/7/1999","Otorrino");

        hospital.getDoctores().add(d1);
        hospital.getDoctores().add(d2);
        hospital.getDoctores().add(d3);


        while (true) {
            hospital.mostrarDatosDeContacto();
            System.out.println("-----------------MENU HOSPITAL---------------");
            System.out.println("1.Listar doctores" + "\n"
                    + "2.Registrar nuevo paciente" + "\n"
                    + "3.Actualizar informacion personal de un paciente" + "\n"
                    + "4.Consultar el historial medico de un paciente" + "\n"
                    + "5.Nuevo historial para un paciente" + "\n"
                    + "6.Guardar historial de pacientes en archivo" + "\n"
                    + "7.Cargar historial de pacientes desde archivo"+ "\n"
                    + "8.Salir");

            System.out.print("Opcion: ");
            int opc = scan.nextInt();

            switch (opc) {
                case 1:
                    System.out.println("Listar Doctores");
                    hospital.listaDoctores();
                    break;
                case 2:
                    System.out.println("Por favor ingrese los datos del paciente:");
                    System.out.print("Nombre: ");
                    String nombre = scan.next();

                    System.out.print("DNI: ");
                    int dni = scan.nextInt();

                    System.out.print("Fecha de nacimiento (dd/mm/aaaa): ");
                    String fecha = scan.next();

                    System.out.print("Numero de telefono: ");
                    int numeroTelefono = scan.nextInt();

                    System.out.print("Tipo de sangre: ");
                    int tipoSangre = scan.nextInt();

                    System.out.println("Desea agregar historial medico? 1 = SI / 2 = NO");
                    int opc1 = scan.nextInt();

                    Paciente p1 = new Paciente(nombre, dni, fecha, numeroTelefono, tipoSangre);
                    if (opc1 == 1) {
                        while(true) {
                            System.out.print("Ingrese fecha del evento (dd/mm/aaaa): ");
                            String eventofecha = scan.next();

                            System.out.println("Ingrese observacion: ");
                            String observacion = scan.next();

                            HistorialMedico h = new HistorialMedico(eventofecha, observacion);
                            p1.getHistorialMedico().add(h);

                            System.out.println("Desea agregar otro evento? 1 SI / 2 NO");
                            int check = scan.nextInt();

                            if(check == 2) {
                                break;
                            }
                        }
                    }
                    hospital.getPacientes().add(p1);
                    break;
                case 3:
                    System.out.println("Ingrese DNI de la persona: ");
                    int dni1 = scan.nextInt();

                    System.out.print("Nombre: ");
                    String nuevoNombre = scan.next();

                    System.out.print("Fecha de nacimiento (dd/mm/aaaa): ");
                    String nuevaFecha = scan.next();

                    System.out.print("Numero de telefono: ");
                    int nuevoNumero = scan.nextInt();

                    System.out.print("Tipo de sangre: ");
                    int nuevoTipo = scan.nextInt();

                    hospital.actualizarInformacionPaciente(dni1,nuevoNombre,nuevaFecha,nuevoTipo,nuevoNumero);
                    break;
                case 4:
                    System.out.println("Ingrese DNI del paciente que desea consultar: ");
                    int consult = scan.nextInt();

                    hospital.mostrarHistorialPaciente(consult);
                    break;
                case 5:
                    System.out.println("Ingrese DNI de paciente: ");
                    int consult2 = scan.nextInt();

                    hospital.nuevoHistorial(consult2,scan);
                    break;
                case 6:
                    hospital.guardarHistorial(hospital.getPacientes());
                    break;
                case 7:
                    hospital.setPacientes(hospital.cargarHistorial());
                    break;
                case 8:
                    scan.close();
                    return;
                default:
                    System.out.println("Opcion ingresada incorrecta, por favor vuelva a intentarlo");
            }
        }
    }
}
