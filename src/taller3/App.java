package taller3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

//Florencia Lillo - 21950521-3 - ITI

public class App {
	private static final String archMonstruo = "Monstruos.txt";
	private static final String archRegistro = "Registros.txt";
	
	private static Scanner sc = new Scanner(System.in);
    private static boolean sesionActiva = false;
	private static Sistema sistema = new Sistema();

	public static void main(String[] args) {
		System.out.println("Cargando archivos...");
		cargarMonstruos();
		if (sistema.cantidadMonstruos() == 0) {
			System.out.println("No hay monstruos válidos en " + archMonstruo);
			return;
		}
		System.out.println(" > " + sistema.cantidadMonstruos() + " monstruos cargados desde " + archMonstruo);
		cargarRegistros();
		System.out.println(" > " + sistema.cantidadJugadores() + " jugadores cargados desde " + archRegistro);
		boolean salir = false;
		while (!salir) {
			String nombre = pedirNombre();
			boolean existe = sistema.existeJugador(nombre);
			sistema.iniciarSesion(nombre);
			boolean sesionActiva = true;
			if (existe) {
				System.out.println("\n¡Bienvenido de vuelta, " + nombre + "! Tu récord actual es de "
						+ sistema.getRecord(nombre) + " puntos.");
			} else {
				System.out.println("\n¡Bienvenido, " + nombre + "! Es tu primera vez jugando.");
			}
			salir = menuPrincipal(nombre);
		}
		System.out.println("¡Hasta pronto!");
	}

	private static boolean menuPrincipal(String nombre) {
		while (true) {
			System.out.println("\n------ MENÚ PRINCIPAL (" + nombre + ") ------");
			System.out.println("1) Jugar un minijuego");
			System.out.println("2) Ver catálogo de monstruos");
			System.out.println("3) Ver historial de la sesión");
			System.out.println("4) Ver ranking de jugadores");
			System.out.println("5) Terminar sesión");
			System.out.println("6) Salir");
			int opcion = leerOpcion(1, 6);
			switch (opcion) {
			case 1:
				jugarMinijuego();
				break;
			case 2:
				verCatalogo();
				break;
			case 3:
				verHistorial();
				break;
			case 4:
				verRanking();
				break;
			case 5:
				terminarSesion();
				return false;
			default:
				terminarSesion();
				return true;
			}
		}
	}

	private static void terminarSesion() {
		// TODO Auto-generated method stub

	}

	private static void verRanking() {
		// TODO Auto-generated method stub

	}

	private static void verHistorial() {
		// TODO Auto-generated method stub

	}

	private static void verCatalogo() {
		// TODO Auto-generated method stub

	}

	private static void jugarMinijuego() {
		while (true) {
            System.out.println("\n------ MINIJUEGOS ------");
            ArrayList<String> estados = sistema.estadoMinijuegos();
            for (int i = 0; i < estados.size(); i++) System.out.println((i + 1) + ") " + estados.get(i));
            System.out.println("0) Volver");
            int opcion = leerOpcion(0, estados.size());
            if (opcion == 0) return;
            int minijuego = opcion - 1;
            if (sistema.minijuegoJugado(minijuego)) {
                System.out.println("[!] Ya jugaste " + sistema.nombreMinijuego(minijuego) + " en esta sesión.");
                continue;
            }

            ArrayList<String> disponibles = sistema.monstruosDisponibles();
            if (disponibles.isEmpty()) {
                System.out.println("[!] No quedan monstruos disponibles en esta sesión.");
                return;
            }
            System.out.println("\n" + sistema.descripcionMinijuego(minijuego));
            System.out.println("\nElige el monstruo en el que te transformarás:");
            for (int i = 0; i < disponibles.size(); i++) System.out.println((i + 1) + ") " + disponibles.get(i));
            System.out.println("0) Volver");
            int eleccion = leerOpcion(0, disponibles.size());
            if (eleccion == 0) continue;

            double potencial = sistema.prepararPartida(minijuego, eleccion - 1);
            if (potencial < 0) {
                System.out.println("[!] No se pudo iniciar la partida.");
                continue;
            }
            System.out.println("\nTe comes un dulce de dudosa procedencia...");
            System.out.println("Potencial para " + sistema.nombreMinijuegoActual() + ": " + sistema.formatear(potencial));
            
            ArrayList<String> habilidades = sistema.habilidadesMonstruoActual();
            System.out.println("\n¿Quieres usar una habilidad?");
            for (int i = 0; i < habilidades.size(); i++) System.out.println((i + 1) + ") " + habilidades.get(i));
            System.out.println("0) No usar habilidad");
            int hab = leerOpcion(0, habilidades.size()) - 1;   // -1 = ninguna

            if (hab >= 0) {
                System.out.println(sistema.mensajeHabilidad(hab));
                if (sistema.habilidadEfectiva(hab)) System.out.println("¡La habilidad fue efectiva! Puntaje x1.5");
                else System.out.println("La habilidad no tuvo efecto en este minijuego.");
            }
            int puntaje = sistema.completarPartida(hab);
            System.out.println("\n>> Puntaje obtenido: " + puntaje + " puntos");
            if (sistema.todosLosMinijuegosJugados()) System.out.println("¡Completaste todos los minijuegos!");
            return;
        }
    }


	private static int leerOpcion(int min, int max) {
        while (true) {
            System.out.print("> ");
            String texto = leerLinea().trim();
            try {
                int opcion = Integer.parseInt(texto);
                if (opcion >= min && opcion <= max) return opcion;
                System.out.println("[!] Opción fuera de rango. Intente nuevamente.");
            } catch (NumberFormatException e) {
                System.out.println("[!] Debe ingresar un número. Intente nuevamente.");
            }
        }
	}

	private static String leerLinea() {
		if (!sc.hasNextLine()) {
            System.out.println("\n[!] Entrada cerrada. Finalizando...");
            if (sesionActiva) terminarSesion();
            System.exit(0);
        }
        return sc.nextLine();
    }

	private static String pedirNombre() {
        System.out.println("\n=========================================");
        System.out.println("             SPOOKY SEASON");
        System.out.println("=========================================");
        while (true) {
            System.out.print("Ingrese su nombre de usuario: ");
            String nombre = leerLinea().trim();
            String error = sistema.validarNombre(nombre);
            if (error == null) return nombre;
            System.out.println("[!] " + error);
        }
	}

	private static void cargarRegistros() {
		File f = new File(archRegistro);
    if (!f.exists()) {
        System.out.println("[i] " + archRegistro + " no existe; se creará al guardar.");
        return;
    }
    try (Scanner archivo = new Scanner(f)) {
        int numeroLinea = 0;
        while (archivo.hasNextLine()) {
            String linea = archivo.nextLine().replace("\uFEFF", "");
            numeroLinea++;
            if (linea.trim().isEmpty()) continue;
            if (!sistema.agregarJugador(linea.split(";", -1))) {
                System.out.println("Línea " + numeroLinea + " descartada en " + archRegistro + ": " + linea);
            }
        }
    } catch (Exception e) {
        System.out.println("Error leyendo " + archRegistro + ": " + e.getMessage());
    }

	}

	public static void cargarMonstruos() {
		try (Scanner archivo = new Scanner(new File("Monstruos.txt"))) {
			int numeroLinea = 0;
			while (archivo.hasNextLine()) {
				String linea = archivo.nextLine().replace("\uFEFF", "");
				numeroLinea++;
				if (linea.trim().isEmpty())
					continue;
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

	// saya calienteeee saya caliente, bailalo como tu quieras negraaaaaa :D
}
