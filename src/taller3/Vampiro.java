package taller3;

public class Vampiro extends Monstruo implements Mordedor, Volador{
	private int velocidadVuelo;
    private int resistenciaSol;
    
	public Vampiro(int vida, int velocidad, int fuerza, int resitencia, int velocidadVuelo, int resistenciaSol) {
		super("Vampiro", vida, velocidad, fuerza, resitencia);
		this.velocidadVuelo = velocidadVuelo;
		this.resistenciaSol = resistenciaSol;
	}

	public int getVelocidadVuelo() {
		return velocidadVuelo;
	}

	public int getResistenciaSol() {
		return resistenciaSol;
	}

	@Override
	public double aceptar(VisitadorMinijuego visitor) {
		return visitor.visitar(this);
	}

	@Override
	public String getDescripcion() {
		return descripcionBase() + " | Vuelo " + velocidadVuelo + " | Sol " + resistenciaSol;

	}

	@Override
    public String morder() { 
		return "El Vampiro clava sus colmillos con elegancia siniestra."; }

    @Override
    public String volar() { 
    	return "El Vampiro se convierte en murciélago y surca el cielo."; }
	
}
