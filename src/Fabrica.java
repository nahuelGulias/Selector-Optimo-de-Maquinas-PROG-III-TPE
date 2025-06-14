import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Fabrica {
    private ArrayList<Maquina> solucion;
    private int cantPiezas;
    private int puestasEnFunc;
    private int piezasAProducir; //Cantidad de piezas a producir, leidas del txt
    private int cantEstados;

    public Fabrica() {
        this.solucion = new ArrayList<>();
        this.cantPiezas = 0;
        this.puestasEnFunc = 0;
        this.piezasAProducir = 12;
        this.cantEstados = 0;
    }

    /*
       -------- Breve explicacion-------
       */
    public ArrayList<Maquina> greedy(ArrayList<Maquina> maquinasDadas, int piezas) {
        privgGeedy(maquinasDadas, piezas);
        return solucion;
    }

    private void privgGeedy(ArrayList<Maquina> maquinas, int piezas) {
        Collections.sort(maquinas, Collections.reverseOrder());
        for (Maquina m : maquinas) {

            while (piezas >= m.getPiezasQueProduce()) {
                solucion.add(m);// Greedy: siempre toma la maquina con mayor pieza y la repite hasta que no sobrepase el monto
                piezas -= m.getPiezasQueProduce();
            }
        }

        puestasEnFunc = solucion.size();
        for (Maquina m : solucion) {
            cantPiezas += m.getPiezasQueProduce();
        }
    }

    /*
    -------- Breve explicacion-------
    */
    public ArrayList<Maquina> backtracking(ArrayList<Maquina> maquinas, int piezas) {
        solucion = new ArrayList<>();

        int piezasProducidas = 0;
        backtrackingRec(solucion, maquinas, piezasProducidas);
        return solucion;
    }

    private void backtrackingRec(ArrayList<Maquina> actual, ArrayList<Maquina> maquinas, int piezasProducidas) {
        cantEstados++;
        //posible poda
        if (piezasProducidas > piezasAProducir) {
            return;
        }

        if (piezasProducidas == piezasAProducir) {
            if (actual.size() < solucion.size())
                this.solucion.clear();
            this.solucion.addAll(actual);
        } else {
            for (Maquina m : maquinas) { //Agarra primer maquina
                actual.add(m); // agrega maquina
                backtrackingRec(actual, maquinas, piezasProducidas + m.getPiezasQueProduce()); // llamo
                actual.removeLast();
            }
        }
    }


        @Override
        public String toString () {
            return "Fabrica{" +
                    "solucion=" + solucion.toString() + "\n" +
                    ", cantPiezas=" + cantPiezas +
                    ", puestasEnFunc=" + puestasEnFunc +
                    '}';
        }
}

