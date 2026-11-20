package taller3;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

//Florencia Lillo - 21950521-3 - ITI


public class App {
	private static Sistema sistema = new Sistema();
	public static void main(String[] args) {
		System.out.println("Cargando archivos...");
		cargarMonstruos();
	}

	public static void cargarMonstruos() {
		try (Scanner archivo = new Scanner(new File("Monstruos.txt"))) {
            int numeroLinea = 0;
            while (archivo.hasNextLine()) {
                String linea = archivo.nextLine().replace("\uFEFF", "");
                numeroLinea++;
                if (linea.trim().isEmpty()) continue;
                if (!sistema.agregarMonstruo(linea.split(";", -1))) {
                    System.out.println("Línea " + numeroLinea + " descartada en " + "Monstruos.txt" + ": " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo " + "Monstruos.txt" + ".");
        } catch (Exception e) {
            System.out.println("Error leyendo " + "Monstruos.txt" + ": " + e.getMessage());
        }
    }
		
	//saya calienteeee saya caliente, bailalo como tu quieras negraaaaaa :D
}
