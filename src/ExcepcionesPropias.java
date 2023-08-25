import java.io.IOException;
import java.util.Scanner;

public class ExcepcionesPropias {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Ingrese un numero: ");
        try {
            evaluarRango(s.nextInt());
        } catch (rangoNumeros e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    static void evaluarRango(int n) throws rangoNumeros {

        if (n < 1 || n > 100) {

            rangoNumeros error = new rangoNumeros("Numero fuera de rango");
            throw error;
        }

    }
}
class rangoNumeros extends IOException {

    public rangoNumeros() {
    }

    public rangoNumeros(String mError) { super(mError); }

}
