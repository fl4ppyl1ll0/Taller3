package taller3;

public class Momia extends Monstruo{
	private int longitudVendajes;

	public Momia(int vida, int velocidad, int fuerza, int resitencia, int longitudVendajes) {
		super("Momia", vida, velocidad, fuerza, resitencia);
		this.longitudVendajes = longitudVendajes;
	}
	
}
