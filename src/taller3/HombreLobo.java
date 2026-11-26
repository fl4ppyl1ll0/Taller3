package taller3;

public class HombreLobo extends Monstruo implements Mordedor, Furioso{
	private int ferocidad;
    private int faseLunar;
    
	public HombreLobo(int vida, int velocidad, int fuerza, int resitencia,	int velocidadVuelo, int resistenciaSol) {
		super("HombreLobo", vida, velocidad, fuerza, resitencia);
		this.ferocidad = ferocidad;
        this.faseLunar = faseLunar;
	}

	public int getFerocidad() { return ferocidad; }
    public int getFaseLunar() { return faseLunar; }

    @Override
    public double aceptar(VisitadorMinijuego visitor) { return visitor.visitar(this); }

    @Override
    public String getDescripcion() {
        return descripcionBase() + " | Ferocidad " + ferocidad + " | Luna " + faseLunar;
    }

    @Override
    public String morder() { return "El Hombre Lobo muerde con unas fauces enormes."; }

    @Override
    public String enfurecerse() { return "El Hombre Lobo aúlla a la luna y se llena de furia."; }
}
