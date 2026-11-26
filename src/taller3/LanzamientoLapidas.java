package taller3;

public class LanzamientoLapidas implements Minijuego {
	@Override
	public String getNombre() {
		return "Lanzamiento de Lápidas";
	}

	@Override
	public String getDescripcion() {
		return "lanza una lápida lo más lejos posible.";
	}

	@Override
	public String getHabilidadPremiada() {
		return "Enfurecerse";
	}

	@Override
	public double visitar(Zombi z) {
		return z.getFuerza() * z.getResistencia() / 100.0;
	}

	@Override
	public double visitar(Vampiro v) {
		return v.getFuerza() + v.getVelocidadVuelo() / 4.0;
	}

	@Override
	public double visitar(Momia m) {
		return m.getFuerza() + m.getLongitudVendajes() / 10.0;
	}

	@Override
	public double visitar(HombreLobo h) {
		return h.getFuerza() + h.getFerocidad() * h.getFaseLunar() / 8.0;
	}

}
