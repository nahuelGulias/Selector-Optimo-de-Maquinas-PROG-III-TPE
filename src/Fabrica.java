import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Fabrica {
    private ArrayList<Maquina> solucion;
    private int cantPiezas;
    private int puestasEnFunc;
    private int piezasAProducir; //Cantidad de piezas a producir, leidas del txt
    private int cantEstados;

    public int getPuestasEnFunc() {
        return puestasEnFunc;
    }

    public int getCantEstados() {
        return cantEstados;
    }

    public Fabrica(int piezasAProducir) {
        this.solucion = new ArrayList<>();
        this.cantPiezas = 0;
        this.puestasEnFunc = 0;
        this.piezasAProducir = piezasAProducir;
        this.cantEstados = 0;
    }

    /*
       -------- Breve explicacion-------
       */
    public ArrayList<Maquina> greedy(ArrayList<Maquina> maquinasDadas) {
        privgGeedy(maquinasDadas, piezasAProducir);
        return solucion;
    }

    private void privgGeedy(ArrayList<Maquina> maquinas, int piezasAProducir) {
        Collections.sort(maquinas, Collections.reverseOrder());
        for (Maquina m : maquinas) {

            while (piezasAProducir >= m.getPiezasQueProduce()) {
                solucion.add(m);// Greedy: siempre toma la maquina con mayor pieza y la repite hasta que no sobrepase el monto
                piezasAProducir -= m.getPiezasQueProduce();
            }
        }

        puestasEnFunc = solucion.size();
        for (Maquina m : solucion) {
            cantPiezas += m.getPiezasQueProduce();
        }
    }

    /*
    -------- Breve explicacion-------
    Árbol de exploración: comenzaría tomando como opción la maquina m1 y cuando quiera volver a
    considerarla se topa con nuestra primera poda, que es que no se pase de las maquinas a producir.
     Una vez que termina de evaluar esa rama, sigue asimismo con m2. Nuestra segunda poda ya sería
     considerando nuestra solución y que el camino actual no sobrepase el tamaño de la misma, así que en el caso de que no
     haya alcanzado el numero de piezas a producir pero nuestro tamaño actual sea mayor que solucion, ya corta las consideraciones de esa rama.
    */
    public ArrayList<Maquina> backtracking(ArrayList<Maquina> maquinas) {
        ArrayList<Maquina> actual = new ArrayList<>();
        backtrackingRec(actual, maquinas,0);
        return solucion;
    }

    private void backtrackingRec(ArrayList<Maquina> actual, ArrayList<Maquina> maquinas, int piezasProducidas) {
        cantEstados++;
        //posible poda
        if (piezasProducidas > piezasAProducir) {
            return;
        }
        if (!solucion.isEmpty() && actual.size() >= solucion.size()) {
            return;
        }


        if (piezasProducidas == piezasAProducir) {
            if (solucion.isEmpty() || actual.size() < solucion.size()){
                this.solucion.clear();
                this.solucion.addAll(actual);
            }
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

