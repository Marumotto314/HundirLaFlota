package paquetePrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Dialogo de error que se hará visible si ha habido algún problema al introducir los jugadores
 * @author mario
 */
public class JugadoresError
{
	// Atributos
	private static JDialog dialogoPadre;
	private static JDialog ventana;
	private static JPanel panel, boton;
	private static JLabel mensajeError;
	private static JButton repetir;
	
	// Constructor
	/**
	 * Se creará ventana pero no se hará visible, solo se hara visible cuando ocurra un error
	 */
	public JugadoresError()
	{
		// Ventana
		dialogoPadre = CreacionJugadores.getDialog();
		ventana = new JDialog(dialogoPadre, true);
		ventana.setBounds(dialogoPadre.getX() + 20, dialogoPadre.getY() + 30, 260, 120);
		ventana.getContentPane().setLayout(new BoxLayout(ventana.getContentPane(), BoxLayout.Y_AXIS));
		
		// Mensaje de error
		mensajeError = new JLabel("");
		panel = new JPanel();
		panel.add(mensajeError);
		
		// Botón
		repetir = new JButton("Volver a intentar");
		boton = new JPanel();
		boton.add(repetir);
		
		repetir.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)
	        {
				ventana.setVisible(false);
	        }
		});
		
		ventana.add(panel);
		ventana.add(boton);
	}
	
	// Métodos
	public static void hacerVisible(ErroresJugadores error)
	{
		if(error.equals(ErroresJugadores.NINGUNTEXTO))
			mensajeError.setText("¿Nadie para jugar?");
		
		if(error.equals(ErroresJugadores.MUCHOTEXTO))
			mensajeError.setText("¿Que tal una abreviatura? 8 letras max");
		
		if(error.equals(ErroresJugadores.MISMOJUGADOR))
			mensajeError.setText("Yo vs Yo");
		
		ventana.setVisible(true);
	}
}
