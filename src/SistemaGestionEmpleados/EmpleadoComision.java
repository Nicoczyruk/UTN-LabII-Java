package SistemaGestionEmpleados;

public class EmpleadoComision extends Empleado implements Impuesto { //Hereda de Empleado e implementa de Impuesto

    private double ventasRealizadas; //Atributo propio de la clase

    public EmpleadoComision(String nombre, int id, double sueldoBase, double ventasRealizadas) { //Constructor de la clase + clase superior
        super(nombre,id,sueldoBase);
        this.ventasRealizadas = ventasRealizadas;

    }

    public double getVentasRealizadas() { //Metodo get
        return ventasRealizadas;
    }

    public void setVentasRealizadas(double ventasRealizadas) { //Metodo set
        this.ventasRealizadas = ventasRealizadas;
    }

    @Override
    public double calcularSueldo() { //Implementacion propia del metodo CalcularSueldo() heredado de Empleado

        double c = ventasRealizadas * 0.2;

        return sueldoBase+c;
    }

    public double calcularImpuesto() { //Implementacion del metodo CalcularImpuesto() de la interfaz Impuesto.

        return calcularSueldo() * 0.15;
    }

    @Override
    public String toString() { //Metodo ToString ligeramente modificado para presentar la informacion de forma mas legible.
        return "EmpleadoComision {\n" +
                "VentasRealizadas= " + ventasRealizadas+"\n" +
                "Nombre= " + nombre + "\n" +
                "Id= " + id+ "\n" +
                "SueldoBase=" + sueldoBase +
                '}';
    }
}
