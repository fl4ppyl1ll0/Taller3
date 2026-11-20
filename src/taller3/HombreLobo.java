package taller3;

public class HombreLobo extends Monstruo{
	private int velocidadVuelo;
    private int resistenciaSol;
    
	public HombreLobo(int vida, int velocidad, int fuerza, int resitencia,	int velocidadVuelo, int resistenciaSol) {
		super("HombreLobo", vida, velocidad, fuerza, resitencia);
		this.velocidadVuelo = velocidadVuelo;
		this.resistenciaSol = resistenciaSol;
	}
	
}
