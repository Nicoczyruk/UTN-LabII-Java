package PracticasParcial2;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

abstract class Persona {

    private int id;
    private String nombre;
    private int edad;

    public Persona() {
        this.id = 0;
        this.nombre = "DESCONOCIDO";
        this.edad = -1;
    }

    public Persona(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}

class Paciente extends Persona {

    private String historialMedico;
    private String fechaIngreso;
    private int doctorAsignado;

    public Paciente() {
        super();
        this.historialMedico = "DESCONOCIDO";
        this.fechaIngreso = "DESCONOCIDA";
        this.doctorAsignado = -1;
    }

    public Paciente(int id, String nombre, int edad, String historialMedico, String fechaIngreso) {
        super(id,nombre,edad);
        this.historialMedico = historialMedico;
        this.fechaIngreso = fechaIngreso;
        this.doctorAsignado = -1;
    }

    public String getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(String historialMedico) {
        this.historialMedico = historialMedico;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getDoctorAsignado() {
        return doctorAsignado;
    }

    public void setDoctorAsignado(int doctorAsignado) {
        this.doctorAsignado = doctorAsignado;
    }
}

class Doctor extends Persona {

    private String especialidad;

    public Doctor() {
        super();
        this.especialidad = "DESCONOCIDO";
    }

    public Doctor(int id, String nombre, int edad, String especialidad) {
        super(id,nombre,edad);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}

class Hospital {
    private List<Paciente> listaPacientes;
    private List<Doctor> listaDoctores;

    public Hospital() {
        this.listaPacientes = new ArrayList<>();
        this.listaDoctores = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) {

        listaPacientes.add(paciente);

        this.guardarEnBD();
    }

    public void mostrarPacientes(Connection conexion) {

        try {
            Statement statement = conexion.createStatement();

            String consulta = "SELECT * FROM pacientes";

            ResultSet result = statement.executeQuery(consulta);

            System.out.printf("%-5s %-10s %-7s %-15s %-12s %-15s\n", "id", "nombre", "edad", "historialMedico",
                    "fechaIngreso", "doctorAsignado");
            while(result.next()) {
                int id = result.getInt("id");
                String nombre = result.getString("nombre");
                int edad = result.getInt("edad");
                String historialMedico = result.getString("historial_medico");
                String fechaIngreso = result.getString("fecha_ingreso");
                int doctorAsignado = result.getInt("doctor");

                System.out.printf("%-5d %-10s %-7d %-15s %-12s %-15d\n", id, nombre, edad, historialMedico, fechaIngreso, doctorAsignado);
            }
            result.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void mostrarPacientesEntreFechas(String fechaInicio, String fechaFin, Connection conexion) {

        try {

            Statement statement = conexion.createStatement();

            String consulta = "SELECT * FROM pacientes WHERE fecha_ingreso BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"'";

            ResultSet result = statement.executeQuery(consulta);

            System.out.printf("%-5s %-10s %-7s %-15s %-12s %-15s\n", "id", "nombre", "edad", "historialMedico",
                    "fechaIngreso", "doctorAsignado");
            while(result.next()) {
                int id = result.getInt("id");
                String nombre = result.getString("nombre");
                int edad = result.getInt("edad");
                String historialMedico = result.getString("historial_medico");
                String fechaIngreso = result.getString("fecha_ingreso");
                int doctorAsignado = result.getInt("doctor");

                System.out.printf("%-5d %-10s %-7d %-15s %-12s %-15d\n", id, nombre, edad, historialMedico, fechaIngreso, doctorAsignado);
            }

            statement.close();
            result.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void eliminarPaciente(String nombrePaciente) {

        for (Paciente p : listaPacientes) {

            if (p.getNombre().equals(nombrePaciente)) {
                listaPacientes.remove(p);
                break;
            }
        }
        this.guardarEnBD();


    }

    public void guardarEnBD() {
        String url = "jdbc:mysql://localhost:3306/hospital";
        String usuario = "root";
        String pass = "";
        try {

            Connection conexion = DriverManager.getConnection(url,usuario,pass);
            PreparedStatement statement;

            // Insertar doctores
            for (Doctor doctor : listaDoctores) {
                String consultaDoctor = "INSERT INTO doctores (id, nombre, edad, especialidad) VALUES (DEFAULT, ?, ?, ?)";
                statement = conexion.prepareStatement(consultaDoctor);

                statement.setString(1, doctor.getNombre());
                statement.setInt(2, doctor.getEdad());
                statement.setString(3, doctor.getEspecialidad());
                statement.executeUpdate();

            }

            // Insertar pacientes
            for (Paciente paciente : listaPacientes) {
                String consultaPaciente = "INSERT INTO pacientes (id, nombre, edad, historial_medico, fecha_ingreso, doctor) VALUES (DEFAULT, ?, ?, ?, ?, ?)";
                statement = conexion.prepareStatement(consultaPaciente);

                statement.setString(1, paciente.getNombre());
                statement.setInt(2, paciente.getEdad());
                statement.setString(3, paciente.getHistorialMedico());
                statement.setString(4, paciente.getFechaIngreso());
                statement.setInt(5, paciente.getDoctorAsignado());
                statement.executeUpdate();

            }

            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public List<Doctor> getListaDoctores() {
        return listaDoctores;
    }

    public void setListaDoctores(List<Doctor> listaDoctores) {
        this.listaDoctores = listaDoctores;
    }


}


public class PracticaParcial2 {

        public static void main(String[] args) {

            String url = "jdbc:mysql://localhost:3306/hospital";
            String usuario = "root";
            String pass = "";

            Hospital hospital = new Hospital();

            Paciente p1 = new Paciente(1,"pedro",50,"Abandonado","2010-09-21");
            Doctor d1 = new Doctor(1,"Juan",30,"Cardiologo");

            Paciente p2 = new Paciente(2,"Selmo",50,"Abandonado","2010-09-21");
            Doctor d2 = new Doctor(2,"Juancito",30,"Cardiologo");

            hospital.agregarPaciente(p1);
            hospital.getListaDoctores().add(d1);

            String fechaInicio = "2023-01-20";
            String fechaFin = "2023-05-20";

            try {
                Connection conexion = DriverManager.getConnection(url,usuario,pass);

                hospital.guardarEnBD();



            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
}

