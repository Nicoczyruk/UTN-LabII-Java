package SistemaGestionEmpleados;

public abstract class Empleado { //Clase abstracta para un Sistema de Gestion de Empleados

    //posee 3 atributos
    protected String nombre;
    protected int id;
    protected double sueldoBase;

    public Empleado(String nombre, int id, double sueldoBase) { //Constructor por defecto
        this.nombre = nombre;
        this.id = id;
        this.sueldoBase = sueldoBase;

    }

    //Metodos Getter y Setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public abstract double calcularSueldo(); //Metodo Abstracto que heredan las subclases.


}
