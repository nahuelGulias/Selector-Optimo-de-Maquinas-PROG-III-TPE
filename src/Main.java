import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear las máquinas
        ArrayList<Maquina> maquinas = new ArrayList<>();
        maquinas.add(new Maquina("M1", 7));
        maquinas.add(new Maquina("M2", 3));
        maquinas.add(new Maquina("M3", 4));
        maquinas.add(new Maquina("M4", 1));

        // Cantidad de piezas a producir
        int piezasRequeridas = 12;

        // Crear la fábrica y ejecutar el algoritmo greedy
        Fabrica fabrica = new Fabrica();
        ArrayList<Maquina> resultado = fabrica.greedy(maquinas, piezasRequeridas);

        // Mostrar resultados
        System.out.println("Máquinas usadas:");
        for (Maquina m : resultado) {
            System.out.println("- " + m.getNombre() + " (" + m.getPiezasQueProduce() + " piezas)");
        }

        // Mostrar resumen
        System.out.println(fabrica);
    }
}
