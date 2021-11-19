package paquetePrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ficheros.Puntuaciones;

/**
 * Esta clase creará una estancia estática del lanzador del juego que contendrá un botón
 * para iniciar la partida y un campo con el registro de las últimas partidas y su respectiva
 * puntuación. La puntuación se guardará después de cada partida en un fichero binario.
 * @author mario
 */
public class VentanaInicial
{
	// Atributos
	private static JFrame ventana;
	
	private static final String RUTA = ".\\Recursos";
	
	private static JLabel mensaje;
	private static JList<String> puntuaciones;
	private static JScrollPane scrollLista;
	private static JPanel iniciarPartida, panelIzq, panelDch, panelSuperior;
	private static JButton botonIniciarPartida, botonSalir; 
	private static Jugadores dialogoJugadores;
	private static JLabel fotoDerecha, fotoIzquierda;
	private static String jugador1, jugador2;// Los nombres de los jugadores 
	private static int puntuacion1, puntuacion2;// Las puntuaciones de cada jugador
	
	// Juego
	private static Juego juego1;
	private static Juego juego2;
	
	// Fuente de letra 
	private static Font fuente;
	// Color 
	private static Color color1 = new Color(96, 205, 241);// Panel Superior e Inferior
	private static Color color2 = new Color(169, 169, 169);// Panel Izq y Dch
	
	// Constructores
	public VentanaInicial()
	{
		// Ventana
		ventana = new JFrame("Hundir La Flota");
		ventana.setBounds(100, 100, 500, 550);
		ventana.setIconImage(new ImageIcon(RUTA + "\\Icono.png").getImage());
		ventana.setResizable(false);
		ventana.getContentPane().setLayout(new BorderLayout());
		
		// Mensaje
		panelSuperior = new JPanel();
		fuente = new Font("Arial", Font.BOLD, 25);
		mensaje = new JLabel("TABLA DE PUNTUACIONES", SwingConstants.CENTER);
		mensaje.setForeground(Color.DARK_GRAY);
		mensaje.setBackground(Color.CYAN);
		mensaje.setFont(fuente);
		
		panelSuperior.setBackground(color1);
		panelSuperior.setLayout(new FlowLayout());
		panelSuperior.add(mensaje);
		
		// Panel Izquierdo
		panelIzq = new JPanel();
		fotoIzquierda = new JLabel();
		fotoIzquierda.setIcon(new ImageIcon(RUTA + "\\barco.png"));
		panelIzq.setBackground(color2);
		panelIzq.setLayout(new BoxLayout(panelIzq, BoxLayout.X_AXIS));// Layout para que la foto quede alineada en el centro
		panelIzq.add(fotoIzquierda);
		
		// Panel Derecho
		panelDch = new JPanel();
		fotoDerecha = new JLabel();
		fotoDerecha.setIcon(new ImageIcon(RUTA + "\\submarino.png"));
		panelDch.setBackground(color2);
		panelDch.setLayout(new BoxLayout(panelDch, BoxLayout.X_AXIS));// Layout para que la foto quede alineada en el centro
		panelDch.add(fotoDerecha);
		
		// Puntuaciones
		fuente = new Font("Arial", Font.ITALIC, 17);
		puntuaciones = new JList<String>();
		puntuaciones.setFont(fuente);
		try { 
			puntuaciones.setModel(Puntuaciones.leer()); 
		} catch (Exception e) {
			System.out.println(e);
		}
		// Instanciamos el Scroll que tendra la lista
		scrollLista = new JScrollPane(puntuaciones);
		
		
		// Empezar partida, luego añadiremos imagenes al JLabel que funciona como contenedor
		iniciarPartida = new JPanel();
		iniciarPartida.setBackground(color1);
		botonIniciarPartida = new JButton("Nueva Partida");
		botonSalir = new JButton("Salir");
		iniciarPartida.setLayout(new FlowLayout());
		botonIniciarPartida.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)
	        {
				dialogoJugadores = new Jugadores();
	        }
		});
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)
			{
				ventana.setVisible(false);
			}
		});
		iniciarPartida.add(botonIniciarPartida);
		iniciarPartida.add(botonSalir);
		
		// Añadimos los componentes a la ventana
		ventana.getContentPane().add(panelSuperior, BorderLayout.NORTH);
		ventana.getContentPane().add(panelIzq, BorderLayout.WEST);
		ventana.getContentPane().add(scrollLista, BorderLayout.CENTER);
		ventana.getContentPane().add(panelDch, BorderLayout.EAST);
		ventana.getContentPane().add(iniciarPartida, BorderLayout.SOUTH);
		
		ventana.setVisible(true);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	// Métodos
	/**
	 * @return JFrame VentanaInicial
	 */
	public static JFrame getVentana()
	{
		return ventana;
	}
	public static void recibirJugadores(String j1, String j2)
	{
		jugador1 = j1;
		jugador2 = j2;
	}
	public static void iniciarJuego() 
	{
		ventana.setVisible(false);
		juego1 = new Juego(jugador1);
		juego2 = new Juego(jugador2);
	}
}
