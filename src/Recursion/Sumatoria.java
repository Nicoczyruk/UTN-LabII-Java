package Recursion;

public class Sumatoria {


    public static int sumR(int n1, int n2, int n3) {  //Funcion sumatoria recursiva, n1 = desde / n2 = hasta / n3 = suma


        n3 = n3 + n2;

        if (n2 == n1) {
            return n3;
        }


        return sumR(n1,n2-1,n3);
    }

    public static void main(String[] args) {

        int sum = sumR(1,10,0);

        System.out.println(sum);

    }

}
