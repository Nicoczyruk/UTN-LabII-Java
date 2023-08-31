package SistemaGestionEmpleados;
import java.util.Scanner;


import java.util.ArrayList;

public class GestorEmpleados {

    Scanner s = new Scanner(System.in);

    ArrayList<Empleado> Gestor; //Atributo ArrayList

    public GestorEmpleados() { //Constructor que inicializa el Arraylist
        Gestor = new ArrayList<Empleado>();
    }


    public void agregarEmpleado(Empleado empleado) { //Metodo que agrega un empleado al array.
        Gestor.add(empleado);
    }

    public void eliminarEmpleado(int id) { //Metodo que elimina un empleado del array, usa un for loop para encontrar
                                            // la ID a remover, y se rompe para salir del loop.

        for (int i = 0; i < Gestor.size(); i++) {
            if (Gestor.get(i).getId() == id) {
                Gestor.remove(i);
                break;
            }
        }
    }

    public void editarEmpleado(String Nnombre, int id, double NsueldoBase) {
        for (Empleado empleado : Gestor) { //Recorrer la lista de empleados hasta matchear id y modificar
            if(empleado.getId() == id) {
                empleado.setNombre(Nnombre);
                empleado.setSueldoBase(NsueldoBase);
                if (empleado instanceof EmpleadoComision) { //Pregunta si el empleado es instancia de EmpleadoPorHoras o de EmpleadoComision
                                                            //En caso de encontrar una instancia, solicita la modificacion del atributo propio de dicha clase.
                    System.out.println("Empleado que cobra Comision");
                    System.out.print("Ingrese nuevo valor de ventas o ingrese el mismo: ");
                    double ventas = s.nextDouble();
                    ((EmpleadoComision) empleado).setVentasRealizadas(ventas);
                } else if (empleado instanceof  EmpleadoPorHoras) {
                    System.out.println("Empleado que trabaja por horas");
                    System.out.print("Ingrese nuevo valor de horas trabajadas o ingrese el mismo valor para mantener: ");
                    int horas = s.nextInt();
                    ((EmpleadoPorHoras) empleado).setHorasTrabajadas(horas);
                }
            }
        }
    }

    public String toString() { //Metodo ToString sobrescrito para mostrar todos los empleados en sucesion.
        String detalles = "EMPLEADOS DE LA EMPRESA" + "\n";
        for (Empleado empleado : Gestor) {
            detalles += empleado +"\n";
        }

        return detalles;
    }



}
