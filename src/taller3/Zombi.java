package taller3;

public class Zombi extends Monstruo{
	private int fuerzaMordida;

	public Zombi(int vida, int velocidad, int fuerza, int resitencia, int fuerzaMordida) {
		super("Zombi", vida, velocidad, fuerza, resitencia);
		this.fuerzaMordida = fuerzaMordida;
	}

	public int getFuerzaMordida() {
		return fuerzaMordida;
	}
	
	

}
