import java.util.Scanner;

public class MiprimerRepo {

    public static void main(String args[]) {

        Scanner s = new Scanner(System.in);

        System.out.println("Ingrese primer numero: ");
        int n1 = s.nextInt();

        System.out.println("Ingrese segundo numero: ");
        int n2 = s.nextInt();

        System.out.println("Ingrese tercer numero: ");
        int n3 = s.nextInt();

        if (n1 > n2 && n1 > n3) {
            if (n2 > n3) {
                System.out.println("Orden: "+n1+" "+n2+" "+n3);
            } else {
                System.out.println("Orden: "+n1+" "+n3+" "+n2);
            }
        } else if (n2 > n1 && n2 > n3) {
            if (n1 > n3) {
                System.out.println("Orden: "+n2+" "+n1+" "+n3);
            } else {
                System.out.println("Orden: " + n2 + " " + n3 + " " + n1);
            }

        } else if (n3 > n1 && n3 > n2) {
            if (n1 > n2) {
                System.out.println("Orden: "+n3+" "+n1+" "+n2);
            } else {
                System.out.println("Orden: " + n3 + " " + n2 + " " + n1);
            }
        }
    }
}
