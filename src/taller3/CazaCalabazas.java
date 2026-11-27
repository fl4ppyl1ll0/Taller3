package taller3;

public class CazaCalabazas implements Minijuego {
	@Override
	public String getNombre() {
		return "Caza de Calabazas";
	}

	@Override
	public String getDescripcion() {
		return "muerde la mayor cantidad de calabazas del huerto en 60 segundos.";
	}

	@Override
	public String getHabilidadPremiada() {
		return "Morder";
	}

	@Override
	public double visitar(Zombi z) {
		return z.getFuerzaMordida() * z.getVida() / 100.0 + z.getResistencia() / 2.0;
	}

	@Override
	public double visitar(Vampiro v) {
		return (v.getFuerza() + v.getVelocidad()) / 3.0;
	}

	@Override
	public double visitar(Momia m) {
		return m.getLongitudVendajes() * m.getFuerza() / 200.0;
	}

	@Override
	public double visitar(HombreLobo h) {
		return h.getFerocidad() * h.getFaseLunar() / 4.0 + h.getVelocidad() / 4.0;
	}
}
