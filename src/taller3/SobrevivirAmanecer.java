package taller3;

public class SobrevivirAmanecer implements Minijuego {
	@Override
	public String getNombre() {
		return "Sobrevivir al Amanecer";
	}

	@Override
	public String getDescripcion() {
		return "aguanta en pie cuando salgan los primeros rayos de sol.";
	}

	@Override
	public String getHabilidadPremiada() {
		return "Regenerarse";
	}

	@Override
	public double visitar(Zombi z) {
		return z.getResistencia() * z.getVida() / 100.0;
	}

	@Override
	public double visitar(Vampiro v) {
		return v.getResistencia() * v.getResistenciaSol() / 100.0;
	}

	@Override
	public double visitar(Momia m) {
		return (m.getResistencia() + m.getVida()) / 2.0 * (1.0 + m.getLongitudVendajes() / 300.0);
	}

	@Override
	public double visitar(HombreLobo h) {
		return (h.getResistencia() + h.getVida()) / 2.0 * (5.0 - h.getFaseLunar()) / 4.0;
	}
}
