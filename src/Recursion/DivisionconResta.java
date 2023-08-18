package Recursion;

public class DivisionconResta {

    public static int divConResta(long n, long div) { //Division con resta iterativa.

        int result = 0;
        int resto = 0; //Contiene el resto en caso de que exista uno

        while (n >= div) {

            n = n - div;
            result += 1;
        }

        resto = (int) n;
        if (resto != 0) { //Si hay resto, imprime cual es su cantidad
            System.out.println("La division ha terminado con resto: "+resto);
        }

        return result;

    }

    public static int divConResta(int n, int div, int result) { //Division con resta recursiva, condicion de salida
                                                                //Si n < div devuelve el resultado. No se toma en cuenta
                                                                //el resto.

        if (n < div) {

            return result;
        }
        n = n - div;

        return divConResta(n,div,result+1);


    }

    public static void main(String[] args) {

        int numero = divConResta(20,4);

        System.out.println("Resultado de la division: "+numero);

    }

}
