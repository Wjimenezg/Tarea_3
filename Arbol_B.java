package arbol_b;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arbol_B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el grado del arbol:");
        int grado = scanner.nextInt();

        Interac_Arbol arbolInteractivo = new Interac_Arbol(grado);

        while (true) {
            System.out.println("\nSeleccione una opcion:");
            System.out.println("1. Insertar claves");
            System.out.println("2. Mostrar clave(s)");
            System.out.println("3. Buscar clave");
            System.out.println("4. Eliminar clave");
            System.out.println("5. Salir");
            System.out.println("\n");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la cantidad de claves a insertar:");
                    int cantidad = scanner.nextInt();
                    List<Integer> claves = new ArrayList<>();
                    for (int i = 0; i < cantidad; i++) {
                        System.out.println("Ingrese la clave " + (i + 1) + ":");
                        int clave = scanner.nextInt();
                        claves.add(clave);
                    }
                    arbolInteractivo.insertarClaves(claves);
                    break;
                case 2:
                    System.out.println("Claves del arbol B:");
                    arbolInteractivo.mostrarArbol();
                    break;
                case 3:
                    System.out.println("Ingrese la clave a buscar:");
                    int claveBuscar = scanner.nextInt();
                    arbolInteractivo.buscarClave(claveBuscar);
                    break;
                case 4:
                     System.out.println("Ingrese la clave a eliminar:");
                    int claveEliminar = scanner.nextInt();
                    System.out.println("\n");
                    arbolInteractivo.eliminarClave(claveEliminar);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor, seleccione una opcion valida");
            }
        }
    }
}
