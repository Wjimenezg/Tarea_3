package arbol_b;

import java.util.List;

public class Interac_Arbol {
    private Implemen_Arbol arbolB;

    public Interac_Arbol(int grado) {
        arbolB = new Implemen_Arbol(grado); // inicia el arbol b con el grado indicado
    }

    public void insertarClaves(List<Integer> claves) {
        for (int clave : claves) {
            arbolB.insert(clave); // inserta la clave en el arbol b
        }
        System.out.println("Las claves han sido insertadas."); //mensaje al insertar las claves
    }

    public void eliminarClave(int clave) {
        boolean eliminada = arbolB.eliminar(clave); //eliminara la clave del arbol
        if (eliminada) { // en caso que se elimine correctamente
            System.out.println("La clave " + clave + " fue eliminada"); //mostrara el siguiente mensaje
        } else { // sino existe la clave
            System.out.println("La clave " + clave + " no existe, ingrese una clave valida");
        }
    }

    public boolean buscarClave(int clave) {
        boolean encontrada = arbolB.buscar(clave); //buscara la clave en el arbol
        if (encontrada) { // si es encontrada
            System.out.println("La clave " + clave + " ha sido encontrada en el arbol."); // mostrara el siguiente mensaje
        } else { // sino es encontrada
            System.out.println("La clave " + clave + " no se encuentra en el arbol."); // mostrara el siguiente mensaje
        }
        return encontrada;
    }
    
    
    public void mostrarArbol() { // metodo para mostrar el arbol 
        arbolB.Mostrar();
    }

    //obtener y establecer el arbol 
    public Implemen_Arbol getArbolB() {
        return arbolB;
    }

    public void setArbolB(Implemen_Arbol arbolB) {
        this.arbolB = arbolB;
    }
}

