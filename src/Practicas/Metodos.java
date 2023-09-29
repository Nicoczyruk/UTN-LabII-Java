package Practicas;
import java.io.*;
import java.util.Scanner;

public class Metodos {

    public static int recuperarNumero(String nombreArchivo) {
        int numero = 0;
        try (Scanner entrada = new Scanner(new File(nombreArchivo))) {
            if (entrada.hasNextInt()) {
                numero = entrada.nextInt();
            } else {
                System.err.println("El archivo no contiene un número válido.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el archivo " + nombreArchivo + ": " + e.getMessage());
        }

        return numero;
    }

    public static String recuperarTexto(String nombreArchivo) {
        String texto = "";
        try (Scanner entrada = new Scanner(new File(nombreArchivo))) {
            if (entrada.hasNext()) {
                texto = entrada.nextLine();
            } else {
                System.err.println("El archivo está vacío.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el archivo " + nombreArchivo + ": " + e.getMessage());
        }

        return texto;
    }


    public static void escribirArchivo(String nombreArchivo, String contenido) {
        try (BufferedWriter salida = new BufferedWriter(new FileWriter(nombreArchivo))) {
            salida.write(contenido);
            System.out.println("Archivo " + nombreArchivo + " escrito exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }

    public static void escribirArchivo(String nombreArchivo, int valorNumerico) {
        try (BufferedWriter salida = new BufferedWriter(new FileWriter(nombreArchivo))) {
            salida.write(String.valueOf(valorNumerico)); // Convertimos el valor numérico a String antes de escribirlo
            System.out.println("Archivo " + nombreArchivo + " escrito exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }

    public static void escribirArchivo(String nombreArchivo, double valorNumerico) {
        try (BufferedWriter salida = new BufferedWriter(new FileWriter(nombreArchivo))) {
            salida.write(String.valueOf(valorNumerico)); // Convertimos el valor numérico a String antes de escribirlo
            System.out.println("Archivo " + nombreArchivo + " escrito exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }

    public static void escribirArchivoSimple(String nombreArchivo, String contenido) {
        try (FileWriter salida = new FileWriter(nombreArchivo)) {
            salida.write(contenido);
            System.out.println("Archivo " + nombreArchivo + " escrito exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }


}



