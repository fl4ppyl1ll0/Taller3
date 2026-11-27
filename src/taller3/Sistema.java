package taller3;

import java.util.ArrayList;
import java.util.LinkedList;

public class Sistema {
	private ArrayList<Monstruo> monstruos = new ArrayList<>();
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private ArrayList<Minijuego> minijuegos = new ArrayList<>();
    private LinkedList<RegistroPartida> historial = new LinkedList<>();
    
    private String jugadorActual = null;
    private int puntajeSesion = 0;
    private int recordAnterior = 0;
    private int[] puntajePorMinijuego;      
    
    private Monstruo monstruoActual = null;
    private int minijuegoActual = -1;
    private double potencialActual = 0;

    public Sistema() {
        minijuegos.add(new CarreraCementerio());
        minijuegos.add(new CazaCalabazas());
        minijuegos.add(new LanzamientoLapidas());
        minijuegos.add(new SobrevivirAmanecer());
        puntajePorMinijuego = new int[minijuegos.size()];
        reiniciarMinijuegos();
    }
    
    private void reiniciarMinijuegos() {
    	for (int i = 0; i < puntajePorMinijuego.length; i++) puntajePorMinijuego[i] = -1;
    }

	public boolean agregarMonstruo(String[] campos) {
        if (campos == null || campos.length == 0) return false;
        String tipo = campos[0].trim();
        int esperados;
        if (tipo.equals("Zombi") || tipo.equals("Momia")) esperados = 6;
        else if (tipo.equals("Vampiro") || tipo.equals("HombreLobo")) esperados = 7;
        else return false;
        if (campos.length != esperados) return false;

        int[] v = new int[esperados - 1];
        try {
            for (int i = 0; i < v.length; i++) v[i] = Integer.parseInt(campos[i + 1].trim());
        } catch (NumberFormatException e) {
            return false;
        }
        if (!enRango(v[0], 1, 200)) return false;
        for (int i = 1; i <= 3; i++) if (!enRango(v[i], 1, 100)) return false;

        Monstruo nuevo;
        if (tipo.equals("Zombi")) {
            if (!enRango(v[4], 1, 100)) return false;
            nuevo = new Zombi(v[0], v[1], v[2], v[3], v[4]);
        } else if (tipo.equals("Vampiro")) {
            if (!enRango(v[4], 1, 100) || !enRango(v[5], 1, 100)) return false;
            nuevo = new Vampiro(v[0], v[1], v[2], v[3], v[4], v[5]);
        } else if (tipo.equals("Momia")) {
            if (!enRango(v[4], 1, 300)) return false;
            nuevo = new Momia(v[0], v[1], v[2], v[3], v[4]);
        } else {
            if (!enRango(v[4], 1, 100) || !enRango(v[5], 1, 4)) return false;
            nuevo = new HombreLobo(v[0], v[1], v[2], v[3], v[4], v[5]);
        }
        monstruos.add(nuevo);
        return true;
    }



	private boolean enRango(int valor, int min, int max) { return valor >= min && valor <= max; }



	public int cantidadMonstruos() {
		return monstruos.size();
	}



	public int cantidadJugadores() { 
		return jugadores.size(); 
	}



	public boolean existeJugador(String nombre) {
		return buscarJugador(nombre) != null;
	}



	public boolean iniciarSesion(String nombre) {
		 return buscarJugador(nombre) != null; 		
	}



	public int getRecord(String nombre) {
		Jugador j = buscarJugador(nombre);
        return j == null ? 0 : j.getMaxpuntaje();
	}



	private Jugador buscarJugador(String nombre) {
		 for (Jugador j : jugadores) if (j.getNombre().equals(nombre)) return j;
	        return null;
	}

	public ArrayList<String> monstruosDisponibles() {
        ArrayList<String> lista = new ArrayList<>();
        for (Monstruo m : disponibles()) lista.add(m.getDescripcion());
        return lista;
    }

    private ArrayList<Monstruo> disponibles() {
    	ArrayList<Monstruo> lista = new ArrayList<>();
        for (Monstruo m : monstruos) if (!m.isUsado()) lista.add(m);
        return lista;
	}

	
    public double prepararPartida(int indiceMinijuego, int indiceDisponible) {
        ArrayList<Monstruo> disp = disponibles();
        if (indiceMinijuego < 0 || indiceMinijuego >= minijuegos.size()) return -1;
        if (indiceDisponible < 0 || indiceDisponible >= disp.size()) return -1;
        if (minijuegoJugado(indiceMinijuego)) return -1;
        minijuegoActual = indiceMinijuego;
        monstruoActual = disp.get(indiceDisponible);
        potencialActual = minijuegos.get(minijuegoActual).calcularPotencial(monstruoActual);
        return potencialActual;
    }

    public boolean minijuegoJugado(int indice) {
		return puntajePorMinijuego[indice] >= 0; }

	public void cancelarPartida() {
        monstruoActual = null;
        minijuegoActual = -1;
        potencialActual = 0;
    }

	public String formatear(double valor) {long centesimas = Math.round(valor * 100);
    long entero = centesimas / 100;
    long decimales = centesimas % 100;
    return entero + "." + (decimales < 10 ? "0" : "") + decimales;
	}

	public String nombreMinijuegoActual() {
		return minijuegos.get(minijuegoActual).getNombre(); }

    public ArrayList<String> habilidadesMonstruoActual() {
        return habilidadesDe(monstruoActual);
	}

	private ArrayList<String> habilidadesDe(Monstruo m) {
        ArrayList<String> habs = new ArrayList<>();
        if (m instanceof Mordedor) habs.add("Morder");
        if (m instanceof Volador) habs.add("Volar");
        if (m instanceof Regenerable) habs.add("Regenerarse");
        if (m instanceof Furioso) habs.add("Enfurecerse");
        return habs;
	}

	public String mensajeHabilidad(int indiceHabilidad) {
        ArrayList<String> habs = habilidadesMonstruoActual();
        if (indiceHabilidad < 0 || indiceHabilidad >= habs.size()) return "";
        String h = habs.get(indiceHabilidad);
        if (h.equals("Morder")) return ((Mordedor) monstruoActual).morder();
        if (h.equals("Volar")) return ((Volador) monstruoActual).volar();
        if (h.equals("Regenerarse")) return ((Regenerable) monstruoActual).regenerarse();
        return ((Furioso) monstruoActual).enfurecerse();
	}

	public int completarPartida(int indiceHabilidad) {
        if (monstruoActual == null || minijuegoActual < 0) return -1;
        double multiplicador = habilidadEfectiva(indiceHabilidad) ? 1.5 : 1.0;
        int puntaje = (int) Math.round(potencialActual * 10 * multiplicador);
        ArrayList<String> habs = habilidadesMonstruoActual();
        String usada = (indiceHabilidad >= 0 && indiceHabilidad < habs.size()) ? habs.get(indiceHabilidad) : "Ninguna";

        historial.add(new RegistroPartida(minijuegos.get(minijuegoActual).getNombre(),
                monstruoActual.getTipo(), usada, puntaje));
        puntajePorMinijuego[minijuegoActual] = puntaje;
        monstruoActual.setUsado(true);
        puntajeSesion += puntaje;
        cancelarPartida();
        return puntaje;
    }

	public boolean habilidadEfectiva(int indiceHabilidad) {
        ArrayList<String> habs = habilidadesMonstruoActual();
        if (indiceHabilidad < 0 || indiceHabilidad >= habs.size()) return false;
        return habs.get(indiceHabilidad).equals(minijuegos.get(minijuegoActual).getHabilidadPremiada());
	}

	public boolean todosLosMinijuegosJugados() {
		for (int p : puntajePorMinijuego) if (p < 0) return false;
    return true;
	}

	public ArrayList<String> estadoMinijuegos() {
		ArrayList<String> lista = new ArrayList<>();
        for (int i = 0; i < minijuegos.size(); i++) {
            String estado = minijuegoJugado(i) ? "[JUGADO - " + puntajePorMinijuego[i] + " pts]" : "[PENDIENTE]";
            lista.add(rellenar(minijuegos.get(i).getNombre(), 24) + estado);
        }
        return lista;

	}

	 private String rellenar(String texto, int ancho) {
	        StringBuilder sb = new StringBuilder(texto);
	        while (sb.length() < ancho) sb.append(' ');
	        return sb.toString();
	}

	 public String nombreMinijuego(int indice) { 
		 return minijuegos.get(indice).getNombre(); }

	 public String descripcionMinijuego(int indice) {
	        Minijuego m = minijuegos.get(indice);
	        return m.getNombre() + ": " + m.getDescripcion();
	 }

	 public String validarNombre(String nombre) {
		 if (nombre == null || nombre.trim().isEmpty()) return "El nombre no puede estar vacío.";
	        if (nombre.contains(";")) return "El nombre no puede contener el carácter ';'.";
	        return null;
	 }

	 public boolean agregarJugador(String[] campos) {
		 if (campos == null || campos.length != 2) return false;
	        String nombre = campos[0].trim();
	        if (validarNombre(nombre) != null || buscarJugador(nombre) != null) return false;
	        try {
	            int puntaje = Integer.parseInt(campos[1].trim());
	            if (puntaje < 0) return false;
	            jugadores.add(new Jugador(nombre, puntaje));
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	 }


  
}
