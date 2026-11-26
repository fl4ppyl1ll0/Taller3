package taller3;

public interface Minijuego extends VisitadorMinijuego{
	String getNombre();
    String getDescripcion();
    
    /** Nombre de la habilidad que multiplica el puntaje (x1.5). */
    String getHabilidadPremiada();
    
    /** Calcula el potencial de un monstruo (double dispatch, sin instanceof). */
    default double calcularPotencial(Monstruo monstruo) {
        return monstruo.aceptar(this);
    }
    
}
