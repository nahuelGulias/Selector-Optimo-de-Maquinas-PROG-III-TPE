import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear las máquinas
        ArrayList<Maquina> maquinas = new ArrayList<>();

        // Cantidad de piezas a producir
        int piezasRequeridas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("src/datos.txt"))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    piezasRequeridas = Integer.parseInt(linea);
                    primeraLinea = false;
                } else {
                    String[] partes = linea.split(",");
                    if (partes.length == 2) {
                        String nombre = partes[0].trim();
                        int cantidad = Integer.parseInt(partes[1].trim());
                        maquinas.add(new Maquina(nombre, cantidad));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo" + e.getMessage());
        }

        System.out.println(maquinas.toString());

        // Crear la fábrica y ejecutar el algoritmo greedy
        Fabrica fabrica = new Fabrica(piezasRequeridas, maquinas);
        ArrayList<Maquina> resultadoBacktracking = fabrica.backtracking(maquinas);

        // Mostrar resultados
//        System.out.println("Máquinas usadas:");
//        System.out.println(fabrica.getCantEstados());
//        for (Maquina m : resultadoBacktracking) {
//            System.out.println("- " + m.getNombre() + " (" + m.getPiezasQueProduce() + " piezas)");
//        }

        ArrayList<Maquina> resultadoGreedy = fabrica.greedy(maquinas);

        System.out.println(fabrica.toString());
        // Mostrar resultados
//        System.out.println("Greedy");
//        System.out.println("Máquinas usadas:");
//        for (Maquina m : resultadoGreedy) {
//            System.out.println("- " + m.getNombre() + " (" + m.getPiezasQueProduce() + " piezas)");
//        }


        // Mostrar resumen
        //    System.out.println(fabrica);
    }
}