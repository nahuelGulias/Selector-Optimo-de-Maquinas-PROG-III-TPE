public class Maquina implements Comparable<Maquina>{

    private String nombre;
    private int piezasQueProduce;
    // private boolean enUso;


    public Maquina(String nombre, int piezas) {
        this.nombre = nombre;
        this.piezasQueProduce = piezas;

    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;

    }

    public int getPiezasQueProduce() {
        return piezasQueProduce;
    }

    public void setPiezas(int piezas) {
        this.piezasQueProduce = piezas;
    }

    public int compareTo(Maquina maquina) {
        return this.piezasQueProduce - maquina.getPiezasQueProduce();
    }

    @Override
    public String toString() {
        return "[" + nombre + ',' +  piezasQueProduce + "]";
    }
}
