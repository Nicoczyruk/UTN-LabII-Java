package GestionCuentas;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        GestorCuentas gestorCuentas = new GestorCuentas();

        while(true) {
            System.out.println("\tSISTEMA DE GESTION DE CUENTAS"); //Menu
            System.out.println("""
                    1.Crear cuenta persona
                    2.Crear cuenta empresa
                    3.Eliminar cuenta persona
                    4.Eliminar cuenta empresa
                    5.Editar saldo persona
                    6.Editar saldo cuenta
                    7.Mostrar cuentas almacenadas
                    8.Salir""");
            System.out.print("Opcion: ");
            int opc = s.nextInt();

            switch(opc) {
                case 1:
                    System.out.print("Ingrese numero de cuenta: ");
                    int numC = s.nextInt();

                    System.out.print("Ingrese saldo de cuenta: ");
                    double saldo = s.nextDouble();

                    System.out.print("Ingrese nombre: ");
                    String nombre = s.next();

                    System.out.print("Ingrese apellido: ");
                    String apellido = s.next();

                    CuentaPersona p = new CuentaPersona(numC,saldo,nombre,apellido);
                    gestorCuentas.agregarCuentaPersona(p);

                    break;
                case 2:
                    System.out.print("Ingrese numero de cuenta: ");
                    int numE = s.nextInt();

                    System.out.print("Ingrese saldo de cuenta: ");
                    double saldoE = s.nextDouble();

                    System.out.print("Ingrese nombre de la empresa: ");
                    String nombreE = s.next();

                    System.out.print("Ingrese tipo de cuenta: ");
                    String tipoCuenta = s.next();

                    CuentaSociedad e = new CuentaSociedad(numE,saldoE,nombreE,tipoCuenta);
                    gestorCuentas.agregarCuentaSociedad(e);

                    break;

                case 3:
                    System.out.print("Ingrese el numero de cuenta persona que desea eliminar: ");
                    int num = s.nextInt();
                    gestorCuentas.eliminarCuentaPersona(num);

                    break;

                case 4:
                    System.out.print("Ingrese el numero de cuenta sociedad que desea eliminar: ");
                    int num1 = s.nextInt();
                    gestorCuentas.eliminarCuentaSociedad(num1);

                    break;
                case 5:
                    System.out.print("Ingrese numero de cuenta persona: ");
                    int num2 = s.nextInt();

                    System.out.print("Ingrese el nuevo saldo de la cuenta: ");
                    double sal = s.nextDouble();

                    gestorCuentas.editarCuentaPersona(num2,sal);
                    break;

                case 6:
                    System.out.print("Ingrese numero de cuenta sociedad: ");
                    int num3 = s.nextInt();

                    System.out.print("Ingrese el nuevo saldo de la cuenta: ");
                    double salE = s.nextDouble();

                    gestorCuentas.editarCuentaSociedad(num3,salE);
                    break;
                case 7:
                    gestorCuentas.mostrarTodasLasCuentas();
                    break;
                case 8:
                    s.close();
                    return;
                default:
                    System.out.println("Numero incorrecto, por favor vuelva a ingresar una opcion");

            }
        }

    }

}
