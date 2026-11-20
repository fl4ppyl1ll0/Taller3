package taller3;

import java.util.ArrayList;

public class Sistema {
	private ArrayList<Monstruo> monstruos = new ArrayList<>();
    
    
    
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

  
}
