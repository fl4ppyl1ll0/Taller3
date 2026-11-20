package taller3;

public class Momia extends Monstruo{
	private int longitudVendajes;

	public Momia(String tipo, int vida, int velocidad, int fuerza, int resitencia, boolean usado,
			int longitudVendajes) {
		super("Momia", vida, velocidad, fuerza, resitencia, usado);
		this.longitudVendajes = longitudVendajes;
	}
	
}
