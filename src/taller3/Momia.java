package taller3;

public class Momia extends Monstruo implements Regenerable, Furioso{
	private int longitudVendajes;

	public Momia(int vida, int velocidad, int fuerza, int resitencia, int longitudVendajes) {
		super("Momia", vida, velocidad, fuerza, resitencia);
		this.longitudVendajes = longitudVendajes;
	}

	public int getLongitudVendajes() {
		return longitudVendajes;
	}

	@Override
	public double aceptar(VisitadorMinijuego visitor) {
		return visitor.visitar(this);
	}

	@Override
    public String getDescripcion() { 
		return descripcionBase() + " | Vendajes " + longitudVendajes; }

    @Override
    public String regenerarse() { 
    	return "Los vendajes de la Momia se reacomodan solos y sanan su cuerpo."; }

    @Override
    public String enfurecerse() { 
    	return "La Momia ruge y una maldición antigua la envuelve."; }
}
