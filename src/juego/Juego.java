package juego;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** 
 * Clase que instanciará la ventana del juego de un jugador
 * @author mario
 */
public class Juego
{
	// Atributos
	private String jugador;
	private JFrame ventana;
	private JLabel mensajeTurno;// Mensaje que aparecerá para saber en que turno nos encontramos
	
	// Atributos para juego 
	private boolean turnoTerminado = false;
	private int x, y;
	private final int TAMAÑO_X = 5, TAMAÑO_Y = 5;
	
	private boolean barcos[][] = new boolean[TAMAÑO_X][TAMAÑO_Y];// ¿Donde están los barcos?
	
	/* ATAQUE y DEFENSA
	 * En vez de tener dos ventanas una para Ataque y otra para Defensa vamos a tener dos paneles en la misma
	 * con los que iremos cambiando su visibilidad dependiendo el turno en el que nos encontremos.
	 */
	// Ataque
	private JPanel ataque;
	private JPanel tablaAtaque;// Tabla GridLayout que contendrá los botones
	private JButton botonesBarcos[][] = new JButton[TAMAÑO_X][TAMAÑO_Y];
	private boolean casillaDisparada[][] = new boolean[TAMAÑO_X][TAMAÑO_Y];// ¿Ha sido ya disparada esa casilla?

	// Defensa
	private JPanel defensa;
	
	// Constructores
	public Juego(String jugador)
	{
		// Ventana General
		this.jugador = jugador;
		ventana = new JFrame("Capitán " + jugador);
		ventana.setBounds(100, 100, 400, 400);
		
		// Ataque
		ataque = new JPanel();
		ataque.setLayout(new GridLayout(TAMAÑO_X, TAMAÑO_Y));
		for(int i = 0; i < TAMAÑO_X; i++)
		{
			for(int j = 0; j < TAMAÑO_X; j++)
			{
				int coordenadaX = i, coordenadaY = j;
				botonesBarcos[i][j] = new JButton("(" + i + "," + j + ")");
				botonesBarcos[i][j].addActionListener(new ActionListener() {
					public void actionPerformed (ActionEvent e)
					{
						x = coordenadaX;
						y = coordenadaY;
						turnoTerminado = true;
					}
				});
				ataque.add(botonesBarcos[i][j]);
			}
		}
		
		// Añadimos los componentes a nuestra ventana
		ventana.add(mensajeTurno);
		ventana.add(ataque);

		ventana.setVisible(true);
	}
	
	// Métodos
	public boolean miTurno()
	{
		ataque.setVisible(true);
		mensajeTurno.setText("ATAQUE");
		
		while(!turnoTerminado){ }// Esperamos a que el usuario elija una celda para disparar
		GestorJugadores.disparar(x, y);
		
		try{ Thread.sleep(1000); } catch (InterruptedException e) { /* No hacemos nada dado que es solo código visual */ }
		ataque.setVisible(false);
		mensajeTurno.setText("DEFENSA");
		defensa.setVisible(true);
		
		return true;
	}
	/**
	 * Recibe el disparo del otro jugador
	 * @param Coordenada X
	 * @param Coordenada Y
	 * @return true si es tocado, false si es agua
	 */
	public boolean recibirDisparo(int x, int y)
	{
		if(barcos[x][y])
			return true;
		return false;
	}
	public void desvelarCasilla(boolean fueHit, int x, int y)
	{
		if(fueHit)
		{
			botonesBarcos[x][y].setText("Tocado");
			casillaDisparada[x][y] = false;
		}
		else
			botonesBarcos[x][y].setText("Agua");
	}
}
