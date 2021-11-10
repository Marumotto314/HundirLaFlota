package ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;

/**
 * Trabajar� con el fichero de las puntuaciones tanto para escribir puntuaciones tanto como para 
 * leerlas
 * @author mario
 */
public abstract class Puntuaciones
{
	// Atributos
	private static File ficheroPuntuaciones = new File(".\\Puntuaciones.txt");
	private static FileWriter escribir;
	private static BufferedReader lector;

	// M�todos
	/**
	 * A�ade al fichero las nuevas puntuaciones recibe los jugadores y sus respectivas puntuaciones
	 * @param1 El d�a y la hora a la que acabo la partida
	 * @param2 Nombre del Jugador1
	 * @param3 Puntuaci�n del Jugador1
	 * @param4 Nombre del Jugador2
	 * @param5 Puntuaci�n del Jugador2
	 * @throws IOException 
	 */
	public static void escribirPuntuacion(String fecha, String Jugador1, int puntuacion1, String Jugador2, int puntuacion2) throws IOException
	{
		escribir = new FileWriter(ficheroPuntuaciones, true);
		escribir.write(String.format("%s : %s -> %s  %s -> %s\n", fecha, Jugador1, puntuacion1, Jugador2, puntuacion2));
		escribir.close();
	}
	
	/**
	 * Para facilitar trabajar con 'n' partidas vamos a trabajar con un JList para poder a�adir los elementos 
	 * de forma m�s sencilla.
	 * <br>
	 * Leer� la informaci�n que contiene el fichero de puntuaciones para devolverla como un modelo que ser�
	 * a�adido a un JList.
	 * @return El modelo que contendr� todas las partidas
	 * @throws FileNotFoundException 
	 */
	public static DefaultListModel leer() throws Exception
	{
		lector = new BufferedReader(new FileReader(ficheroPuntuaciones));	
		String linea;
		DefaultListModel modelo = new DefaultListModel();// Modelo que devolveremos
		
		// Bucle para leer las lineas
		while((linea = lector.readLine()) != null)
		{
			modelo.addElement(linea);
		}
	
		lector.close();
	
		return modelo;
	}
}