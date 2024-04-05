
package arbol_b;

import java.util.ArrayList;
import java.util.List;

class NodoArbol {
    List<Integer> claves; //lista que almacena las claves
    List<NodoArbol> hijos; //lista que almacena los hijos
    boolean hoja_o_No; //indicara si el nodo es una hoja o no

    NodoArbol(boolean hoja) {
        this.claves = new ArrayList<>(); //inicializa la lista de claves
        this.hijos = new ArrayList<>(); //inicializa la lista de hijos
        this.hoja_o_No = hoja; // inicializa el valor del boolean
    }
}

public class Implemen_Arbol {
    private NodoArbol raiz; //raiz del arbol
    private int grado; // El grado mínimo del árbol, es decir el numero minimo de hijos

    public Implemen_Arbol(int t) { 
        this.raiz = null; //inicializara la raiz como nula (0)
        this.grado = t;  //asigna el grado minimo, (t) porque es la letra que del grado en programacion 
    }

    //---------------------------- INSERTAR UNA CLAVE---------------------------------------------------------------------------
    public void insert(int clave) {
        if (raiz == null) { // si el arbol esta vacio
            raiz = new NodoArbol(true); // creara una nueva raiz que es una hoja
            raiz.claves.add(clave); // agregara la clave a la lista de claves
        } else { // sino
            if (raiz.claves.size() == (2 * grado - 1)) { //si la raiz esta llena
                NodoArbol nuevaRaiz = new NodoArbol(false); //creara una nueva raiz
                nuevaRaiz.hijos.add(raiz); //agregara la raiz anterior como hijo de la nueva raiz
                dividirHijo(nuevaRaiz, 0, raiz); // dividira el hijo de la nueva raiz
                raiz = nuevaRaiz; // asignara la nueva raiz del arbol
            }
            insertarClave(raiz, clave); // inserta la clave en el arbol
        }
    }

    private void insertarClave(NodoArbol nodo, int clave) { //metodo para insertar otra nueva clave
        int indice = nodo.claves.size() - 1; // obtendra el indice donde se insertara la nueva clave
        if (nodo.hoja_o_No) { // si el nodo es una hoja
            nodo.claves.add(null); // agregara un espacio para la nueva clave
            while (indice >= 0 && clave < nodo.claves.get(indice)) { //mientras... desplazara las claves mayores a la derecha
                nodo.claves.set(indice + 1, nodo.claves.get(indice));
                indice--;
            }
            nodo.claves.set(indice + 1, clave); // insertara la nueva clave en la posicion adecuada
        } else { //sino, es decir si no es una hoja
            while (indice >= 0 && clave < nodo.claves.get(indice)) { //mientras... busca el hijo adecuado para insertar la clave
                indice--;
            }
            indice++;
            if (nodo.hijos.get(indice).claves.size() == (2 * grado - 1)) { //si el hijo esta lleno 
                dividirHijo(nodo, indice, nodo.hijos.get(indice)); //dividira el hijo
                if (clave > nodo.claves.get(indice)) { //decidira en que hijo se insertara la clave
                    indice++;
                }
            }
            insertarClave(nodo.hijos.get(indice), clave); // inserta la clave donde corresponde
        }
    }

    private void dividirHijo(NodoArbol padre, int indice, NodoArbol hijo) {
        NodoArbol nuevoHijo = new NodoArbol(hijo.hoja_o_No); //crea un nuevo hijo
        for (int contador = 0; contador < grado - 1; contador++) { //traslada los datos mayores al nuevo hijo
            nuevoHijo.claves.add(hijo.claves.remove(grado));
        }
        if (!hijo.hoja_o_No) { //si el hijo no es una hoja
            for (int contador = 0; contador < grado; contador++) { //agrega los hijos del hijo al nuevo hijo
                nuevoHijo.hijos.add(hijo.hijos.remove(grado));
            }
        }
        padre.claves.add(indice, hijo.claves.remove(grado - 1)); // agrega el dato mediano al padre
        padre.hijos.add(indice + 1, nuevoHijo); //agrega el nuevo hijo al padre
    }
    
    //--------------------------------------------BUSCAR UNA CLAVE---------------------------------------------
    public boolean buscar(int clave) {
    return buscar(raiz, clave); // inicia la busqueda desde la raiz
}

private boolean buscar(NodoArbol nodo, int clave) {
    if (nodo == null) {// Si el nodo es nulo, la clave no está presente
        return false; 
    }
    int indice = 0;
    while (indice < nodo.claves.size() && clave > nodo.claves.get(indice)) { //mientras... busca el dato en el nodo
        indice++;
    }
    if (indice < nodo.claves.size() && clave == nodo.claves.get(indice)) { // si encuentra el dato en el nodo
        return true; // La clave fue encontrada en este nodo
    }
    if (nodo.hoja_o_No) { // Si el nodo es una hoja y no contiene la clave, la clave no está presente
        return false; 
    }
    
    return buscar(nodo.hijos.get(indice), clave); // La clave puede estar en alguno de los hijos del nodo actual
}

    //-------------------------------------Eliminar una clave---------------------------------------------------------------
    public boolean eliminar(int clave) {
    if (raiz == null) { // si la raiz es nula, es decir el arbol sin ningun dato
        return false; // No se puede eliminar de un árbol vacío
    }
    boolean eliminada = eliminarClave(raiz, clave); 
    if (raiz.claves.isEmpty() && !raiz.hoja_o_No) { //si la raiz queda vacia y no es una hoja
        raiz = raiz.hijos.get(0); // Si la raíz queda vacía, asignamos su hijo como nueva raíz
    }
    return eliminada; // indica si la clave fue eliminada
}

private boolean eliminarClave(NodoArbol nodo, int clave) {
    int indice = nodo.claves.indexOf(clave); //busca la posicion de la clave
    if (indice != -1) { // si la clave existe
        if (nodo.hoja_o_No) { // si el nodo es una hoja
            nodo.claves.remove(indice); //eliminara la clave del nodo
            return true;
        } else { // si el nodo no es una hoja
            NodoArbol hijoAnterior = nodo.hijos.get(indice); //buscara el anterior o posterior y lo reemplazara
            if (hijoAnterior.claves.size() >= grado) {//si el hijo izquierdo tiene suficientes claves
                while (!hijoAnterior.hoja_o_No) {
                    hijoAnterior = hijoAnterior.hijos.get(hijoAnterior.claves.size()); // obtendra el posterior y lo reemplazara por la clave eliminada
                }
                int anterior = hijoAnterior.claves.get(hijoAnterior.claves.size() - 1);
                eliminarClave(nodo.hijos.get(indice), anterior);
                nodo.claves.set(indice, anterior);
                return true;
            } else {
                NodoArbol hijoPosterior = nodo.hijos.get(indice + 1); // Si el hijo izquierdo no tiene suficientes claves, obtenemos el sucesor
                while (!hijoPosterior.hoja_o_No) {
                    hijoPosterior = hijoPosterior.hijos.get(0);
                }
                int posterior = hijoPosterior.claves.get(0);
                eliminarClave(nodo.hijos.get(indice + 1), posterior);
                nodo.claves.set(indice, posterior);
                return true;
            }
        }
        } else { // si la clave no está en este nodo
        if (nodo.hoja_o_No) { // si el nodo es una hoja y no contiene la clave
            return false;
        } else { // si el nodo no es una hoja
            if (nodo.hijos.size() > indice) { // Verificamos que el índice esté dentro de los límites de la lista de hijos
                boolean eliminada = eliminarClave(nodo.hijos.get(indice), clave);
                if (eliminada && nodo.hijos.get(indice).claves.isEmpty()) { // si el hijo ya no tiene datos
                    nodo.hijos.remove(indice); // elimina al hijo
                }
                return eliminada;
            } else {
                return false;
            }
        }
    }
}

//---------------------------------------------BUSCAR UNA CLAVE------------------------------------------------------------------
    public void Mostrar() {
        mostrar(raiz); // muestra desde la raíz
    }

    private void mostrar(NodoArbol nodo) {
        if (nodo != null) {
            for (int posicion = 0; posicion < nodo.claves.size(); posicion++) {
                System.out.print(nodo.claves.get(posicion) + " "); // imprimira las clavos o datos del nodo
            }
            System.out.println();

            if (!nodo.hoja_o_No) { //si el nodo no es una hoja, mostrara a sus hijos
                for (int posicion = 0; posicion < nodo.hijos.size(); posicion++) {
                    mostrar(nodo.hijos.get(posicion)); // mostrara cada hijo
                }
            }
        }
    }
}

