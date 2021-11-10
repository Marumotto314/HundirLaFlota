package paquetePrincipal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;

/**
 * JDialog que saltará al querer inciar una nueva partida para preguntar el nombre de los jugadores
 * @author mario
 */
public class Jugadores
{
	// Atributos
	private static JFrame ventanaPadre;
	private static JDialog ventana;
	private static JDialog ventanaError;
	private static JPanel panelJugador1, panelJugador2, enviar;
	private static JLabel mensaje, nombre1, nombre2;
	private static JTextField n1, n2;
	private static JButton mandar;
	
	
	// Constructores
	public Jugadores()
	{
		ventanaPadre = VentanaInicial.getVentana();
		ventana = new JDialog(ventanaPadre ,true);
		ventana.setBounds(ventanaPadre.getX() + 50, ventanaPadre.getY() + 50, 400, 400);
		ventanaError = new JDialog(ventana, true);
		
		
		ventana.setVisible(true);
	}
}
