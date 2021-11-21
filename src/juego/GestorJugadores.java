package juego;

import paquetePrincipal.VentanaInicial;

/**
 * Esta clase es el puente por el cual se comunicar�n los dos jugadores.
 * @author mario
 */
public abstract class GestorJugadores 
{
	// Atributos
	private static boolean juegoTerminado = false;
	private static Juego j1;
	private static Juego j2;
	private static final int PUNTUACION_MAXIMA = 1000;
	protected static int puntuacionJ1 = 0;
	protected static int puntuacionJ2 = 0;
	
	// M�todos
	public static void empezar()
	{
		// Mientras que el juego no este terminado iremos cambiando de un jugador a otro
		while(!juegoTerminado)
		{
			j1.miTurno();
			if(puntuacionJ1 == PUNTUACION_MAXIMA)
				break;
			
			j2.miTurno();
			if(puntuacionJ2 == PUNTUACION_MAXIMA)
				juegoTerminado = true;
		}
		VentanaInicial.juegoTerminado(puntuacionJ1, puntuacionJ2);
	}
	
	/**
	 * Disparo que ser� mandado al campo del otro jugador
	 * @param jugador
	 * @param posX
	 * @param posY
	 */
	public static void disparar(int posX, int posY)
	{
		// TODO M�todo para disparar que ser� compartido por los dos objetos
		j1.desvelarCasilla(j2.recibirDisparo(posX, posY), posX, posY);
	}
	public static void recibirJugadores(Juego jugador1, Juego jugador2)
	{
		j1 = jugador1;
		j2 = jugador2;
	}
}