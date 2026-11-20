package taller3;

public interface VisitadorMinijuego {
	double visitar(Zombi zombi);
    double visitar(Vampiro vampiro);
    double visitar(Momia momia);
    double visitar(HombreLobo hombreLobo);

}
