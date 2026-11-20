package taller3;

public class HombreLobo extends Monstruo{
	public HombreLobo(String tipo, int vida, int velocidad, int fuerza, int resitencia, boolean usado,
			int velocidadVuelo, int resistenciaSol) {
		super("HombreLobo", vida, velocidad, fuerza, resitencia, usado);
		this.velocidadVuelo = velocidadVuelo;
		this.resistenciaSol = resistenciaSol;
	}
	private int velocidadVuelo;
    private int resistenciaSol;
}
