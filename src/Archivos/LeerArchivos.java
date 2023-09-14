package Archivos;

import java.io.File;
import java.io.FileReader;

public class LeerArchivos {

    public static void leerDatos() {

        try {
            FileReader entrada = new FileReader("Prueba.txt");

            int caracter = entrada.read();

            char letra = (char) caracter;

            while (caracter != -1) {

                System.out.print(letra);
                caracter = entrada.read();
                letra = (char) caracter;

            }
            entrada.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        leerDatos();
    }
}
