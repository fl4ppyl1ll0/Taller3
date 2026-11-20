package taller3;

public class Zombi extends Monstruo{
	private int fuerzaMordida;

	public Zombi(String tipo, int vida, int velocidad, int fuerza, int resitencia, boolean usado, int fuerzaMordida) {
		super("Zombi", vida, velocidad, fuerza, resitencia, usado);
		this.fuerzaMordida = fuerzaMordida;
	}

	public int getFuerzaMordida() {
		return fuerzaMordida;
	}
	
	

}
