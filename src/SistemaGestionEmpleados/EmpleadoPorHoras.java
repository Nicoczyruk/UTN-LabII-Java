package SistemaGestionEmpleados;

public class EmpleadoPorHoras extends Empleado implements Impuesto { //Hereda de Empleado, implementa de Impuesto

    private int horasTrabajadas; //atributo propio

    public EmpleadoPorHoras(String nombre, int id, double sueldoBase, int horasTrabajadas) { //Constructor clase superior + atributo de la subclase.
        super(nombre,id,sueldoBase);
        this.horasTrabajadas = horasTrabajadas;

    }

    public int getHorasTrabajadas() { //Getter
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) { //Setter
        this.horasTrabajadas = horasTrabajadas;
    }

    public double calcularSueldo() { //Implementacion del metodo heredado calcularSueldo()

        return sueldoBase * horasTrabajadas;

    }

    public double calcularImpuesto() { //Implementacion del metodo heredado CalcularImpuesto()

        return calcularSueldo() * 0.15;
    }

    @Override
    public String toString() { //Metodo ToString() ligeramente modificado para mayor legibilidad
        return "EmpleadoPorHoras {\n" +
                "HorasTrabajadas=" + horasTrabajadas +"\n"+
                "Nombre='" + nombre + "\n" +
                "Id=" + id + "\n" +
                "SueldoBase= " + sueldoBase +
                '}';
    }


}
