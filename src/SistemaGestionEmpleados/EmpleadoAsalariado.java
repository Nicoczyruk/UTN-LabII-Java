package SistemaGestionEmpleados;

public class EmpleadoAsalariado extends Empleado implements Impuesto { //Hereda de Empleado, implementa de Impuesto

    public EmpleadoAsalariado(String nombre, int id, double sueldoBase) { //No posee atributos propios por lo que se construye
                                                                         //llamando al constructor superior
        super(nombre,id,sueldoBase);

    }

    //No requiere de getter y setter ya que no posee atributo propio y hereda los getter y setter de la clase superior.

    public double calcularSueldo() { //Implementacion simple del metodo CalcularSueldo(). El empleado cobra un sueldo fijo.
        return sueldoBase;
    }

    @Override
    public double calcularImpuesto() { //Implementacion propia del metodo CalcularImpuesto();

        return calcularSueldo() * 0.15;
    }

    @Override
    public String toString() { //Metodo ToString() ligeramente modificado para mayor legibilidad
        return "EmpleadoAsalariado {\n" +
                "Nombre= " + nombre + "\n" +
                "Id= " + id + "\n" +
                "SueldoBase= " + sueldoBase +
                '}';
    }
}
