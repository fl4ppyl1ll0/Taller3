package taller3;

public abstract class Monstruo {
	private String tipo;
	private int vida;
	private int velocidad;
	private int fuerza;
	private int resitencia;
	private boolean usado;
	public Monstruo(String tipo, int vida, int velocidad, int fuerza, int resitencia, boolean usado) {
		super();
		this.tipo = tipo;
		this.vida = vida;
		this.velocidad = velocidad;
		this.fuerza = fuerza;
		this.resitencia = resitencia;
		this.usado = false;
	}
	
	

}
