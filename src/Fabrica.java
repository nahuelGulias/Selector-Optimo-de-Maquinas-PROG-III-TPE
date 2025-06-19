import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Fabrica {
    private ArrayList<Maquina> solucionGreedy;
    private ArrayList<Maquina> solucionBacktracking;
    private ArrayList<Maquina> maquinas;
    private int cantPiezas;
    private int puestasEnFunc;
    private int piezasAProducir; //Cantidad de piezas a producir, leídas del txt
    private int cantEstados;

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
       Nuestros candidatos serán las máquinas obtenidas por parámetro. La estrategia greedy utilizada para
       nuestra selección de candidatos será, una vez ordenadas las máquinas de mayor a menor producción de piezas,
       seleccionar la máquina que produzca la mayor cantidad de piezas posibles. Siempre y cuando, dicha cantidad
       que produce mi máquina seleccionada, sea menor que la cantidad de piezas que restan producir, con la posibilidad de
       poder reutilizar esa máquina. En caso de no poder reutilizar una máquina, dado que las piezas que produce excede la cantidad
       especificada, se prosigue con la siguiente máquina.
       En caso de no encontrarse solución, se imprimirá un mensaje por consola.
   */

    public ArrayList<Maquina> greedy(ArrayList<Maquina> maquinasDadas) {
        privateGreedy(maquinasDadas, piezasAProducir);
        return solucionGreedy;
    }

    private void privateGreedy(ArrayList<Maquina> maquinas, int piezasAProducir) {
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
     - Árbol de exploración: Se genera tomando como opción la máquina m1. Esta misma, cuando se quiera volver a
       considerar, se toparía con nuestra primera poda, la cual verifica que las piezas producidas hasta el momento
       no excedan la cantidad indicada. Una vez termina de evaluar esa rama, sigue explorando cada rama de los nodos(máquinas).
       Nuestra segunda poda sería considerando nuestra solución, evaluando que nuestra solución actual no sobrepase la mejor solución,
       por lo que, en el caso de que no haber alcanzado la cantidad necesaria de piezas a producir, si nuestro tamaño actual supera la solución,
       no se considerará esa rama.

     - Se considerará solución, siempre que se obtenga la secuencia que sume la cantidad necesaria de piezas, en menor cantidad de puestas en funcionamiento.
     - Los estados estarán dados por la cantidad de llamadas recursivas, en las que se evalúa la posibilidad de agregar una máquina determinada a la solución
    */

    public ArrayList<Maquina> backtracking(ArrayList<Maquina> maquinas) {
        ArrayList<Maquina> actual = new ArrayList<>();
        backtrackingRec(actual, maquinas,0);
        return solucionBacktracking;
    }

    private void backtrackingRec(ArrayList<Maquina> actual, ArrayList<Maquina> maquinas, int piezasProducidas) {
        cantEstados++;
        // Primera poda
        if (piezasProducidas > piezasAProducir) {
            return;
        }
        // Segunda poda
        if (!solucionBacktracking.isEmpty() && actual.size() >= solucionBacktracking.size()) {
            return;
        }
        if (piezasProducidas == piezasAProducir) {
            if (solucionBacktracking.isEmpty() || actual.size() < solucionBacktracking.size()){
                this.solucionBacktracking.clear();
                this.solucionBacktracking.addAll(actual);
            }
        } else {
            for (Maquina m : maquinas) {
                actual.add(m);
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

