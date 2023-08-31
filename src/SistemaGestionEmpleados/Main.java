package SistemaGestionEmpleados;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws HorasTrabajadasExcepcion, VentasExcedidasExcepcion {

        Scanner s = new Scanner(System.in);
        GestorEmpleados gestor = new GestorEmpleados();

        while(true) {
            System.out.println("\tSISTEMA DE GESTION DE EMPLEADOS"); //Menu
            System.out.println("1.Agregar empleado\n2.Eliminar empleado\n3.Editar empleado\n4.Calcular sueldo de empleado\n5.Calcular impuesto de empleado\n6.Mostrar empleado\n7.Mostrar todos los empleados\n8.Salir");
            int opc = s.nextInt();

            switch (opc) { //Switch que evalua opc y ejecuta segun numero asignado.
                case 1:
                    System.out.println("Que tipo de empleado desea agregar?");
                    System.out.println("1.Empleado por horas\n2.Empleado Asalariado\n3.Empleado por comision");
                    int op = s.nextInt();

                    //Agrega diferentes tipos de empleado, evalua que requerimientos necesita cada tipo.
                    if (op == 1) {
                        System.out.print("Ingrese nombre del empleado: ");
                        String nombre = s.next();
                        System.out.print("Ingrese identificacion: ");
                        int id = s.nextInt();
                        System.out.print("Ingrese sueldo base: ");
                        double sueldoB = s.nextDouble();
                        System.out.print("Ingrese las horas trabajadas: ");
                        int horas = s.nextInt();

                        if (horas > 160 || horas < 0) { //Excepcion en caso de que las horas superen lo permitido por mes
                            throw  new HorasTrabajadasExcepcion("Las horas trabajadas no pueden exceder 160 o ser menor a 0");
                        }

                        Empleado Nempleado = new EmpleadoPorHoras(nombre, id, sueldoB, horas); //Creo una instancia y la agrego al array
                        gestor.Gestor.add(Nempleado);
                        System.out.println("Empleado añadido exitosamente"); //Confirmacion de que se cargo con exito el empleado

                    } else if (op == 2) {
                        System.out.print("Ingrese nombre del empleado: ");
                        String nombre = s.next();
                        System.out.print("Ingrese identificacion: ");
                        int id = s.nextInt();
                        System.out.print("Ingrese sueldo base: ");
                        double sueldoB = s.nextDouble();

                        Empleado Nempleado = new EmpleadoAsalariado(nombre, id, sueldoB);//Creo una instancia y la agrego al array
                        gestor.Gestor.add(Nempleado);
                        System.out.println("Empleado añadido exitosamente"); //Confirmacion de que se cargo con exito el empleado

                    } else if (op == 3) {
                        System.out.print("Ingrese nombre del empleado: ");
                        String nombre = s.next();
                        System.out.print("Ingrese identificacion: ");
                        int id = s.nextInt();
                        System.out.print("Ingrese sueldo base: ");
                        double sueldoB = s.nextDouble();
                        System.out.print("Ingrese el valor de las ventas realizadas: ");
                        double ventas = s.nextDouble();

                        if(ventas > 1000000 || ventas < 0) { //El monto generado por las ventas no puede superar el $1.000.00, lanza excepcion
                            throw  new VentasExcedidasExcepcion("Las ventas no pueden ser superiores a $1.000.000 o menores a $0");
                        }

                        Empleado Nempleado = new EmpleadoComision(nombre, id, sueldoB, ventas); //Crea instancia y la agrega al array
                        gestor.Gestor.add(Nempleado);
                        System.out.println("Empleado añadido exitosamente"); //Confirmacion de que se cargo con exito el empleado
                    } else {
                        System.out.println("Error, numero ingresado incorrecto, vuelva a intentarlo");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese la id del empleado a eliminar: "); //Busca y elimina un empleado, la busca se hace en base a la ID.
                    int eliminar = s.nextInt();
                    gestor.eliminarEmpleado(eliminar);
                    break;
                case 3:
                    System.out.print("Ingrese la id del empleado a editar"); //Se edita un empleado de acuerdo a sus atributos, se busca dicho empleado por ID
                    int id = s.nextInt();
                    System.out.print("Ingrese el nuevo nombre: ");
                    String Nnombre = s.next();
                    System.out.print("Ingrese el nuevo sueldo base: ");
                    double sueldoB = s.nextDouble();


                    gestor.editarEmpleado(Nnombre, id, sueldoB);
                    System.out.println("Edicion exitosa!");
                    break;
                case 4:
                    System.out.print("Ingrese la id del empleado: "); //Se muestra el sueldo de un empleado, se busca a traves de la ID
                    int ide = s.nextInt();
                    for (Empleado empleado : gestor.Gestor) {
                        if (empleado.getId() == ide) {
                            System.out.println("El sueldo de " + empleado.getNombre() + " es de: " + empleado.calcularSueldo());
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.print("Ingrese la id del empleado: "); //Se muestran los impuestos que debera pagar un empleado.
                    int iden = s.nextInt();

                    for (Empleado empleado : gestor.Gestor) {
                        if (empleado.getId() == iden) {
                            Impuesto calculo = (Impuesto) empleado;
                            ((Impuesto) empleado).calcularImpuesto();

                            System.out.println("Los impuestos de " + empleado.getNombre() + " son: " + ((Impuesto) empleado).calcularImpuesto());
                        }
                    }
                    break;
                case 6:
                    System.out.print("Ingrese el ID del empleado que desea ver: "); //Se muestra un empleado, proporcionandole una ID para la busqueda
                    int di = s.nextInt();                                           //Se utiliza el ToString dependiendo del tipo de empleado.
                    for (Empleado empleado : gestor.Gestor) {
                        if (empleado.getId() == di) {
                            System.out.println(empleado);
                            break;
                        }
                    }
                    break;
                case 7:
                    System.out.println(gestor); //Se muestran todos los empleados utilizando el toString de la clase GestorEmpleados
                    break;
                case 8: //Salir del programa.
                    s.close();
                    return;
                default: //Opcion por defecto en caso de que se ingrese un numero invalido.
                    System.out.println("Numero de opcion incorrecto, por favor ingrese un numero valido.");
            }
        }
    }

}

