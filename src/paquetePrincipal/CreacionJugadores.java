package paquetePrincipal;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JDialog que saltará al querer inciar una nueva partida para preguntar el nombre de los jugadores
 * @author mario
 */
public class CreacionJugadores
{
	// Atributos
	private static JFrame ventanaPadre;
	private static JDialog ventana;
	private static JugadoresError ventanaError;
	private static JPanel panelMensaje, panelJugador1, panelJugador2, panelBotones;
	private static JLabel mensaje, nombre1, nombre2;
	private static JTextField n1, n2;
	private static JButton terminar, volver;
	
	
	// Constructores
	public CreacionJugadores()
	{
		ventanaPadre = VentanaInicial.getVentana();
		ventana = new JDialog(ventanaPadre ,true);
		ventana.setBounds(ventanaPadre.getX() + 100, ventanaPadre.getY() + 150, 300, 200);
		ventana.getContentPane().setLayout(new BoxLayout(ventana.getContentPane(), BoxLayout.Y_AXIS));
		ventana.setResizable(false);
		ventanaError = new JugadoresError();
		
		// Mensaje
		mensaje = new JLabel("JUGADORES");
		panelMensaje = new JPanel();
		mensaje.setFont(new Font("Arial", Font.PLAIN, 23));
		panelMensaje.add(mensaje);
		
		// Jugador 1
		nombre1 = new JLabel("Jugador 1:");
		n1 = new JTextField(10);
		panelJugador1 = new JPanel();
		panelJugador1.setLayout(new FlowLayout());
		panelJugador1.add(nombre1);
		panelJugador1.add(n1);
		
		// Jugador 2
		nombre2 = new JLabel("Jugador 2:");
		n2 = new JTextField(10);
		panelJugador2 = new JPanel();
		panelJugador2.setLayout(new FlowLayout());
		panelJugador2.add(nombre2);
		panelJugador2.add(n2);
		
		// Botones
		terminar = new JButton("¡JUGAR!");
		volver = new JButton("Volver");
		panelBotones = new JPanel();
		panelBotones.add(terminar);
		panelBotones.add(volver);
		
		terminar.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)
	        {
				comprobar();
	        }
		});
		volver.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)
	        {
				ventana.setVisible(false);
	        }
		});
		
		// Añadimos los componentes a la ventana
		ventana.add(panelMensaje);
		ventana.add(panelJugador1);
		ventana.add(panelJugador2);
		ventana.add(panelBotones);
		
		ventana.setVisible(true);
	}
	
	// Métodos
	/**
	 * Método privado que comprobará los nombres de los jugadores antes de mandarlos
	 */
	private static void comprobar()
	{
		String j1 = n1.getText();
		String j2 = n2.getText();

		// Vamos a comprobar los campos 
		if(j1.equals("") || j2.equals(""))// ¿Están vacios?
		{
			JugadoresError.hacerVisible(ErroresJugadores.NINGUNTEXTO);
			return;
		}
		if(j1.length()>8 || j2.length()>8)// ¿Nombres muy largos?
		{
			JugadoresError.hacerVisible(ErroresJugadores.MUCHOTEXTO);
			return;
		}	
		if(j1.equals(j2))
		{
			JugadoresError.hacerVisible(ErroresJugadores.MISMOJUGADOR);
			return;
		}
		
		ventana.setVisible(false);
		VentanaInicial.recibirJugadores(j1, j2);
		VentanaInicial.iniciarJuego();
	}
	public static JDialog getDialog()
	{
		return ventana;
	}
}
