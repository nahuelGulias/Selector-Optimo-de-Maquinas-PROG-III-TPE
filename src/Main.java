import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Donde tendré las maquinas
        ArrayList<Maquina> maquinas = new ArrayList<>();

        // Cantidad de piezas a producir
        int piezasRequeridas = 0;
        // Se intenta leer el archivo txt
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

        // Se crea la fábrica y se ejecutan los algoritmos
        Fabrica fabrica = new Fabrica(piezasRequeridas, maquinas);
        // Backtracking
        ArrayList<Maquina> resultadoBacktracking = fabrica.backtracking(maquinas);
        //Greedy
        ArrayList<Maquina> resultadoGreedy = fabrica.greedy(maquinas);

        // Mostrar resultados
        System.out.println(fabrica.toString());

    }
}