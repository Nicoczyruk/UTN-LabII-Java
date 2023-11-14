package PracticasParcial2;

import java.sql.*;

class Persona {
    private String nombre;
    private int edad;

    public Persona() {
        this.nombre = "DESCONOCIDO";
        this.edad = -1;
    }

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
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
    private int doctorCabecera;
    private String fechaIngreso;

    public Paciente() {
        super();
        this.historialMedico = "DESCONOCIDO";
        this.doctorCabecera = 0;
        this.fechaIngreso = "DESCONOCIDA";
    }

    public Paciente(String nombre, int edad, String historialMedico, int doctorCabecera, String fechaIngreso) {
        super(nombre,edad);
        this.historialMedico = historialMedico;
        this.doctorCabecera = doctorCabecera;
        this.fechaIngreso = fechaIngreso;
    }

    public String getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(String historialMedico) {
        this.historialMedico = historialMedico;
    }

    public int getDoctorCabecera() {
        return doctorCabecera;
    }

    public void setDoctorCabecera(int doctorCabecera) {
        this.doctorCabecera = doctorCabecera;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}

class Doctor extends Persona {
    private String especialidad;

    public Doctor() {
        super();
        this.especialidad = "DESCONOCIDA";
    }

    public Doctor(String nombre, int edad, String especialidad) {
        super(nombre, edad);
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

    public void agregarPaciente(Paciente paciente) {
        String consulta = "INSERT INTO `pacientes` (`id`, `nombre`, `edad`, `historial_medico`, `fecha_ingreso`, `doctor`) " +
                "VALUES (NULL,'"+paciente.getNombre()+"','"+paciente.getEdad()+"','"+paciente.getHistorialMedico()+"','" + paciente.getFechaIngreso() + "','" + paciente.getDoctorCabecera() +"')";

        DBHelper.ejecutarConsulta(consulta);
        System.out.println("Paciente ingresado correctamente!");
    }

    public void listarPacientes() {
        String consulta = "SELECT * FROM pacientes";

        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        listarPacientesGeneral(resultado);
    }

    public void listarPacientesGeneral(ResultSet resultado) {

        if (resultado != null) {

            try {
                System.out.println("Lista de pacientes");
                System.out.printf("%-10s %-15s %-5s %-20s %-12s %-13s\n", "ID", "Nombre", "Edad", "Historial Médico", "Fecha Ingreso", "Doctor");

                while(resultado.next()) {
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    int edad = resultado.getInt("edad");
                    String historialMedico = resultado.getString("historial_medico");
                    String fechaIngreso = resultado.getString("fecha_ingreso");
                    int idDoctor = resultado.getInt("doctor");

                    System.out.printf("%-10d %-15s %-5d %-20s %-12s %-13d\n", id, nombre, edad, historialMedico, fechaIngreso, idDoctor);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Error, no se recibieron resultados");
        }
    }

    public void listarPacientesEntreFechas(String fecha1, String fecha2) {

        String consulta = "SELECT * FROM pacientes WHERE fecha_ingreso BETWEEN '"+fecha1+"' AND '"+fecha2+"';";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        listarPacientesGeneral(resultado);

    }

    public void asignarDoctorCabecera(String nombreDoctor, String nombrePaciente) {

        String consulta = "UPDATE pacientes SET doctor = (SELECT id FROM doctores WHERE nombre = '"+nombreDoctor+"') WHERE nombre = '"+nombrePaciente+"'";

        DBHelper.ejecutarConsulta(consulta);
        System.out.println("Doctor asignado correctamente!");
    }

    public void eliminarPaciente(String nombre) {

        String consulta = "DELETE FROM `pacientes` WHERE `pacientes`.`nombre` = '"+nombre+"'";
        DBHelper.ejecutarConsulta(consulta);

        System.out.println("Eliminación exitosa!");
    }

    public void buscarSegunIDyEspecialidad(int id, String especialidad) {

        String consulta = "SELECT * FROM `doctores` WHERE `id` >= '"+id+"' AND `especialidad` LIKE '%"+especialidad+"%'";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        if (resultado != null) {

            try {
                System.out.println("Lista de doctores");
                System.out.printf("%-10s %-15s %-5s %-20s\n", "ID", "Nombre", "Edad", "Especialidad");

                while(resultado.next()) {
                    int ide = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    int edad = resultado.getInt("edad");
                    String especialidad1 = resultado.getString("especialidad");


                    System.out.printf("%-10d %-15s %-5d %-20s \n", ide, nombre, edad, especialidad1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Error, no se recibieron resultados");
        }
    }

}

class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/hospitalBD";
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


public class PracticaParcial2 {

    public static void main(String[] args) {


        Hospital hospital = new Hospital();

        Paciente p1 = new Paciente("Pedro",25,"Cancer",-1,"1999-05-10");

        //hospital.agregarPaciente(p1);

        //hospital.listarPacientes();

        //hospital.asignarDoctorCabecera("Doctor2","Juan");

        //String fechaDesde = "2010-01-01";
        //String fechaHasta = "2010-12-01";

        //hospital.listarPacientesEntreFechas(fechaDesde,fechaHasta);

        //hospital.eliminarPaciente("Paciente1");

        hospital.buscarSegunIDyEspecialidad(1,"edi");



    }
}
