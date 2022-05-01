package tp3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.Integer;

public class GeneradorAleatorio {
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		int cantNums = 0;
		String nombreArchivo = "";
		File archivoCsv;
		boolean numeroValido = false;
		boolean nombreValido = false;
		
		System.out.println("Ingrese la cantidad de numeros a generar para el archivo");
		while (!numeroValido) {
			cantNums = entrada.nextInt();
			if (cantNums > 0) {
				numeroValido = true;
			}
			else {
				System.out.println("Ingrese un numero mayor a 0");
			}
		}
		
		System.out.println("Ingrese el nombre del archivo a generar");
		while (!nombreValido) {
			nombreArchivo = entrada.nextLine();
			if (nombreArchivo == "") {
				nombreValido = true;
			}
			else {
				System.out.println("Texto vacio no es valido como nombre para el archivo. Por favor ingrese un nombre valido");
			}
		}
		
		archivoCsv = generarArchivo(cantNums, nombreArchivo, entrada);
	}
	
	public static File generarArchivo(int cantNums, String nombreArchivo, Scanner entrada) {
		long tiempoInicial, tiempoFinal;
		tiempoInicial = System.currentTimeMillis();
		int metodo = 0;
		int aleatorio = 0;
		File archivoCsv = new File (nombreArchivo + ".ascii");
		
		while (metodo != 1 && metodo != 2) {
			System.out.println("¿Mediante que metodo desea generar los numeros? Ingrese el numero correspondiente\n 1- Von Neumann \n 2- Generador congruencial");
			metodo = entrada.nextInt();
			if (metodo != 1 && metodo != 2) {
				System.out.println("Ingrese una opcion valida");
			}
		}
		
		try {
			PrintWriter printWriter = new PrintWriter(archivoCsv);
			StringBuilder stringBuilder = new StringBuilder();
			
			for (int i = 0; i < cantNums; i++) {
				if (metodo == 1) {
					aleatorio = aleatorioVonNeumann(metodo);
				}
//				else {
//					aleatorio = aleatorioCongruencial();
//				}
				System.out.println(aleatorio);
				stringBuilder.append(aleatorio);
				stringBuilder.append(",");
			}
			
			printWriter.write(stringBuilder.toString());
			printWriter.close();
			System.out.println("Archivo generado\r\n");
		}
		catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
		}
		
		tiempoFinal = System.currentTimeMillis() - tiempoInicial;
		System.out.println("La generacion del archivo tardo "+tiempoFinal+" milisegundos\r\n");
		
		return archivoCsv;
	}
	
	public static int aleatorioVonNeumann(int semilla) {
		int aleatorioGenerado;
		String cuadrado;
		int longitudNumero, inicioNum, finNum;
		
		cuadrado = String.valueOf(semilla^2);
		longitudNumero = cuadrado.length();
		inicioNum = (int)Math.floor((longitudNumero - 10) / 2);
		finNum = (int)Math.ceil((longitudNumero - 10) / 2);
		
		aleatorioGenerado = Integrer.parseInt(cuadrado.substring(inicioNum, finNum));
		
		return aleatorioGenerado;
	}
	
//	public static int aleatorioCongruencial() {
//		int aleatorioGenerado;
//		return aleatorioGenerado;
//	}

}
