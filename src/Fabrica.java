import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Fabrica {
    private ArrayList<Maquina> solucionGreedy;
    private ArrayList<Maquina> solucionBacktracking;
    private ArrayList<Maquina> maquinas;
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

    public Fabrica(int piezasAProducir, ArrayList<Maquina> maquinas) {
        this.solucionGreedy = new ArrayList<>();
        this.solucionBacktracking = new ArrayList<>();
        this.cantPiezas = 0;
        this.puestasEnFunc = 0;
        this.piezasAProducir = piezasAProducir;
        this.cantEstados = 0;
        this.maquinas = new ArrayList<>(maquinas);
    }

    /*
       -------- Breve explicacion-------
       Nuestros candidatos van a ser las maquinas que me pasan por parámetro. Y la estrategia greedy para
       nuestra seleccion de candidatos es ordenarlas de mayor a menor produccion de piezas, entonces al inicial elijo
       tengo la de mayor cantidad, mientras la cantidas de piezas que produce mi maquina seleccionada entre en las piezas que
       faltan producir,continuo agtegando la misma, cuando se pase, prosigo con la siguiente maquina.
       En caso de haber encpntrado una solucion, envío un mensaje con la cantidad de piezas faltantes.
       */
    public ArrayList<Maquina> greedy(ArrayList<Maquina> maquinasDadas) {
        privgGeedy(maquinasDadas, piezasAProducir);
        return solucionGreedy;
    }

    private void privgGeedy(ArrayList<Maquina> maquinas, int piezasAProducir) {
        Collections.sort(maquinas, Collections.reverseOrder());
        for (Maquina m : maquinas) {

            while (piezasAProducir >= m.getPiezasQueProduce()) {
                solucionGreedy.add(m);// Greedy: siempre toma la maquina con mayor pieza y la repite hasta que no sobrepase el monto
                piezasAProducir -= m.getPiezasQueProduce();
            }
        }

        puestasEnFunc = solucionGreedy.size();
        for (Maquina m : solucionGreedy) {
            cantPiezas += m.getPiezasQueProduce();
        }
        if (piezasAProducir == 0) {
            System.out.println("Greedy encontró una solución exacta.");
        } else {
            System.out.println("Greedy no pudo producir exactamente la cantidad requerida. Faltan " + piezasAProducir + " piezas.");
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
        return solucionBacktracking;
    }

    private void backtrackingRec(ArrayList<Maquina> actual, ArrayList<Maquina> maquinas, int piezasProducidas) {
        cantEstados++;
        //posible poda
        if (piezasProducidas > piezasAProducir) {
            return;
        }
        if (!solucionBacktracking.isEmpty() && actual.size() >= solucionBacktracking.size()) {
            return;
        }


        if (piezasProducidas == piezasAProducir) {
            if (solucionBacktracking.isEmpty() || actual.size() < solucionBacktracking.size()){
                this.solucionBacktracking.clear();
                this.solucionBacktracking.addAll(actual);
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
            return "Fabrica{" + "\n" +
                    "- Solucion Greedy= " + solucionGreedy.toString() + "\n" +
                    "- Cantidad de piezas producidas = " + cantPiezas + "\n" +
                    "- Cantidad de puestas en funcionamiento = " + solucionGreedy.size() + "\n" +
                    "- Nodos explorados = " + maquinas.size() + "\n" +
                     "\n" +
                    "- Solucion Backtracking = " + solucionBacktracking.toString() + "\n" +
                    "- Cantidad de piezas producidas = " + cantPiezas + "\n" +
                    "- Cantidad de puestas en funcionamiento = " + solucionBacktracking.size() + "\n" +
                    "- Estados generados  = " + cantEstados + "\n" +
            '}';
        }
}

