import java.util.ArrayList;
import java.util.Collections;

public class Fabrica {
    private ArrayList<Maquina> solucion;
    private int cantPiezas=0;
    private int puestasEnFunc =0;


    public  ArrayList<Maquina> greedy (ArrayList<Maquina>maquinasDadas, int piezas){
        solucion = new ArrayList<>();
        privgGeedy(maquinasDadas,piezas);
        return solucion;
    }
    private void privgGeedy (ArrayList<Maquina> maquinas, int piezas){
        Collections.sort(maquinas,Collections.reverseOrder());
        for (Maquina m : maquinas) {

            while (piezas >= m.getPiezasQueProduce()) {
                solucion.add(m);// Greedy: siempre toma la maquina con mayor pieza y la repite hasta que no sobrepase el monto
                piezas -= m.getPiezasQueProduce();
            }
        }

        puestasEnFunc = solucion.size();
        for(Maquina m : solucion){
            cantPiezas += m.getPiezasQueProduce();;
        }
    }

    @Override
    public String toString() {
        return "Fabrica{" +
                "solucion=" + solucion.toString() + "\n" +
                ", cantPiezas=" + cantPiezas +
                ", puestasEnFunc=" + puestasEnFunc +
                '}';
    }
}
