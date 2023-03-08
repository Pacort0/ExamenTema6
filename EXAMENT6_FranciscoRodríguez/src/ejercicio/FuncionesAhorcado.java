package ejercicio;

import java.util.Scanner;

/**
 * Clase con las funciones del juego del ahorcado. Supone el funcionamiento
 * interno del juego
 * 
 * @author frodriguez
 *
 */

public class FuncionesAhorcado {
	// Palabras posibles
	static String[] palabras = { "humanidad", "persona", "hombre", "mujer", "individuo", "cuerpo", "pierna", "cabeza",
			"brazo", "familia" };
	static String palabraSecreta = ""; // Guarda la palabra elegida cada ronda
	static String palabraPista = ""; // Almacena las letras acertadas, así como el tamaño de la palabra
	static String noAcertadas = ""; // Almacena las letras que se han usado y errado
	final static int INTENTOS = 7; // Número máximo de intentos

	/**
	 * Función que escoge una palabra de la lista de posibles palabras
	 */
	public static void generaPalabra() {
		int elegida = 0; // Guarda el índice de la palabra elegida

		elegida = (int) (Math.random() * palabras.length);
		palabraSecreta = palabras[elegida];

		// Bucle for que rellena la pista de '_'
		for (int i = 0; i < palabraSecreta.length(); i++) {
			palabraPista += '_';
		}
	}

	/**
	 * Función que imprime el menú inicial por pantalla
	 * 
	 * @return Recoge y devuelve la opción elegida por el usuario
	 */
	public static int menu() {
		int opcion; // Guarda la opción elegida

		Scanner sc = new Scanner(System.in);

		System.out.println("Seleccione una de las siguientes opciones: " + "\n\t 1. Introducir letra."
				+ "\n\t 2. Introducir palabra.");
		opcion = sc.nextInt();

		return opcion;
	}

	/**
	 * Función que comprueba si la letra introducida por teclado se encuentra en la
	 * palabra En caso afirmativo, la introduce en la pista En caso negativom añade
	 * la letra a la lista de letras erradas
	 * 
	 * @param letra Recibe por parámetro la letra introducida
	 */
	public static void compruebaLetra(String letra) {
		Boolean presente = false; // Indicará si la letra se encuentra o no en la palabra
		// Para poder alterar caracteres separados de la pista, la pasamos a array de
		// chars
		char[] pista = palabraPista.toCharArray();

		for (int i = 0; i < palabraSecreta.length(); i++) {
			// Comparamos la letra con el caracter en el indice 'i' de la palabraSecreta
			if (letra.equalsIgnoreCase(String.valueOf(palabraSecreta.charAt(i)))) {
				presente = true; // Si hay un caracter presente
				pista[i] = letra.charAt(0); // Colocamos la letra en la posición 'i' de la pista
			}
		}

		palabraPista = String.valueOf(pista); // Pasamos la pista de nuevo a String

		if (!presente && !letraErradaUsada(letra)) {
			noAcertadas += letra + " "; // Si no está presente la letra, se añade a la lista de letras erradas
		}
	}

	/**
	 * Función que comprueba si se repite la letra introducida con alguna letra ya
	 * fallada
	 * 
	 * @param letra Recibe la letra introducida por parámetros
	 * @return Devuelve un booleano que indica si la letra se repite o no
	 */
	public static Boolean letraErradaUsada(String letra) {
		Boolean usada = false;
		int cont = 0;

		// Mientras no se repita la letra y se siga recorriendo la palabra
		while (cont < noAcertadas.length() && usada == false) {
			// Si la letra es igual a la letra en el indice 'cont' de la lista de letras no
			// acertadas
			if (letra.equalsIgnoreCase(String.valueOf(noAcertadas.charAt(cont)))) {
				usada = true;
			}
			cont++;
		}
		return usada;
	}

	/**
	 * Función que comprueba si se repite la letra introducida con alguna letra ya
	 * acertada
	 * 
	 * @param letra Recibe la letra introducida por parámetros
	 * @return Devuelve un booleano que indica si la letra se repite o no
	 */
	public static Boolean letraAcertadaUsada(String letra) {
		Boolean usada = false;
		int cont = 0;

		// Mientras no se repita la letra y se siga recorriendo la palabra
		while (cont < palabraPista.length() && usada == false) {
			// Si la letra es igual a la letra en el indice 'cont' de la pista
			if (letra.equalsIgnoreCase(String.valueOf(palabraPista.charAt(cont)))) {
				usada = true;
			}
			cont++;
		}
		return usada;
	}

	/**
	 * Función que comprueba si la palabra introducida es o no la palabra a adivinar
	 * Si se acierta, se guarda en la pista Si se falla, no se hace nada
	 * 
	 * @param palabra Recibe por parámetro la palabra introducida
	 */
	public static void compruebaPalabra(String palabra) {
		if (palabra.equalsIgnoreCase(palabraSecreta)) {
			palabraPista = palabra;
		}
	}

	/**
	 * Función que muestra las palabras usadas y las pistas tras cada intento
	 */
	public static void pintaPista() {
		System.out.println("Letras erradas: " + noAcertadas);
		System.out.println("Pista: " + palabraPista);
	}

	/**
	 * Función que muestra el resultado de la partida según los intentos que queden
	 * y cuál sea la palabra pista una vez acabado el bucle while
	 * 
	 * @param intentos Número de intentos restantes
	 */
	public static void finJuego(int intentos) {
		System.out.println(
				(intentos > 0 && palabraPista.equalsIgnoreCase(palabraSecreta)) ? "¡¡ENHORABUENA!! HAS ACERTADO"
						: ("GAME OVER. La palabra correcta era " + palabraSecreta));
	}
}
