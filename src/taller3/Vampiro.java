package taller3;

public class Vampiro extends Monstruo{
	private int velocidadVuelo;
    private int resistenciaSol;
    
	public Vampiro(int vida, int velocidad, int fuerza, int resitencia, int velocidadVuelo, int resistenciaSol) {
		super("Vampiro", vida, velocidad, fuerza, resitencia);
		this.velocidadVuelo = velocidadVuelo;
		this.resistenciaSol = resistenciaSol;
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
