package taller3;

public class Momia extends Monstruo{
	private int longitudVendajes;

	public Momia(int vida, int velocidad, int fuerza, int resitencia, int longitudVendajes) {
		super("Momia", vida, velocidad, fuerza, resitencia);
		this.longitudVendajes = longitudVendajes;
	}

	@Override
	public double aceptar(VisitadorMinijuego visitor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDescripcion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
