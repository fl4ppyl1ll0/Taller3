package taller3;

public class Zombi extends Monstruo implements Mordedor, Regenerable{
	private int fuerzaMordida;

	public Zombi(int vida, int velocidad, int fuerza, int resitencia, int fuerzaMordida) {
		super("Zombi", vida, velocidad, fuerza, resitencia);
		this.fuerzaMordida = fuerzaMordida;
	}

	public int getFuerzaMordida() {
		return fuerzaMordida;
	}

	@Override
	public double aceptar(VisitadorMinijuego visitor) {
		return visitor.visitar(this); 
	}

	@Override
	public String getDescripcion() {
		return descripcionBase() + " | Mordida " + fuerzaMordida;
	}

	@Override
    public String morder() { 
		return "El Zombi hunde sus dientes podridos con una mordida brutal."; }

    @Override
    public String regenerarse() { 
    	return "Las heridas del Zombi se cierran lentamente."; }
	}
	
	


