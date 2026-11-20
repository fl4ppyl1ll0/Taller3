package taller3;

public class Jugador {
	private String nombre;
	private int maxpuntaje;
	public Jugador(String nombre, int maxpuntaje) {
		super();
		this.nombre = nombre;
		this.maxpuntaje = maxpuntaje;
	}
	public int getMaxpuntaje() {
		return maxpuntaje;
	}
	public void setMaxpuntaje(int maxpuntaje) {
		this.maxpuntaje = maxpuntaje;
	}
	public String getNombre() {
		return nombre;
	}
	
	

}
