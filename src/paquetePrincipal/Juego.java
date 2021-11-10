package paquetePrincipal;

import javax.swing.JFrame;

/** 
 * Clase que instanciará la ventana del juego de un jugador
 * @author mario
 */
public class Juego
{
	// Atributos
	private String jugador;
	private JFrame ventana;
	
	// Constructores
	public Juego(String jugador)
	{
		this.jugador = jugador;
		ventana = new JFrame("Capitán " + jugador);
		ventana.setBounds(100, 100, 400, 400);
		
		ventana.setVisible(true);
	}
}
