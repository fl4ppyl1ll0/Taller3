package taller3;

public class Vampiro extends Monstruo{
	public Vampiro(String tipo, int vida, int velocidad, int fuerza, int resitencia, boolean usado, int velocidadVuelo,
			int resistenciaSol) {
		super("Vampiro", vida, velocidad, fuerza, resitencia, usado);
		this.velocidadVuelo = velocidadVuelo;
		this.resistenciaSol = resistenciaSol;
	}
	private int velocidadVuelo;
    private int resistenciaSol;
}
