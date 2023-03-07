package ejercicio;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int intentos = FuncionesAhorcado.INTENTOS; // Para poder alterar los intentos del jugador
		char intentoLetra = ' '; // Guarda la letra que introduzca el usuario por teclado
		String intentoPalabra = ""; // Guarda la palabra que introduzca el usuario por teclado

		Scanner sc = new Scanner(System.in);

		FuncionesAhorcado.generaPalabra(); // Generamos la palabra aleatoria

		FuncionesAhorcado.pintaPista(); // Mostramos las letras usadas y la pista

		do { // El menú se mostrará al menos una vez

			switch (FuncionesAhorcado.menu()) { // En función de la opción introducida

			case 1: // Si se quiere introducir una letra
				System.out.println("Le quedan " + intentos + " intentos"); // Mostramos los intentos restantes
				System.out.println("Introduzca una letra a comprobar: ");
				intentoLetra = sc.next().toLowerCase().charAt(0); // Guardamos la letra introducida
				
				//Si la letra introducida no se ha usado antes
				if (!FuncionesAhorcado.letraErradaUsada(String.valueOf(intentoLetra))
						&& !FuncionesAhorcado.letraAcertadaUsada(String.valueOf(intentoLetra))) {
					intentos--; //Restamos un intento
				}
				else {
					System.err.println("Esa letra ya la has introducido");
				}

				// Comprobamos si la letra está o no en la palabra a adivinar
				FuncionesAhorcado.compruebaLetra(String.valueOf(intentoLetra));

				FuncionesAhorcado.pintaPista(); // Mostramos las pistas tras la letra intentada
				break;

			case 2: // Si se quiere introducir una palabra
				System.out.println("Le quedan " + intentos + " intentos"); // Mostramos los intentos restantes
				System.out.println("Introduzca la palabra secreta: ");
				intentoPalabra = sc.next().toLowerCase(); // Guardamos la letra introducida

				// Comprobamos si la palabra es o no la palabra a adivinar
				FuncionesAhorcado.compruebaPalabra(intentoPalabra);
				
				intentos--; //Restamos un intento

				FuncionesAhorcado.pintaPista(); // Mostramos las pistas tras la palabra intentada
				break;

			default:
				System.err.println("Esa opción no está contemplada o no existe"); // Mostramos mensaje de error
			}

			// El bucle se repetirá mientras queden intentos y la palabra no se acierte
		} while (intentos > 0 && !intentoPalabra.equalsIgnoreCase(FuncionesAhorcado.palabraSecreta));

		FuncionesAhorcado.finJuego(intentos); // Mostramos el resultado final

		sc.close(); // Cerramos el escáner
	}

}
