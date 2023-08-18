package Recursion;

public class Factorial {

    public static long factorial(long n) { //Sobrecarga del metodo factorial recursivo. Utilizando tipo long como parametro
        // Se utiliza el modo iterativo para el calculo del factorial

        long f = 1;
        while (n > 0) {

            f = f * n;
            n = n - 1;
        }
        return f;
    }

    public static int factorial(int n) { //Funcion que calcula el factorial de un numero n usando recursividad.
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }


    public static void main(String[] args) {

        System.out.println("Hola mundo");
        int fact = factorial(5);

        System.out.println(fact);

    }


}
