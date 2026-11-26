package taller3;

public interface Minijuego extends VisitadorMinijuego{
	String getNombre();
    String getDescripcion();
    
    String getHabilidadPremiada();
    
  
    default double calcularPotencial(Monstruo monstruo) {
        return monstruo.aceptar(this);
    }
    
}
