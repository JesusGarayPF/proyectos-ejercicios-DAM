package EjerciciosAsh;

import java.lang.reflect.Array;
import java.util.*;

public class ExplicacionArrayList {

    public static void main(String[] args) {
        //ARRAYLIST

        //*Es diferente del Array normal en que su tamaño es variable y que solo puede almacenar objetos
        //*Al igual que Array permite obtener los elementos en funcion de su posicion
        //*Puede guardar valores duplicados y valores null
        //*Mantiene el orden de insercion
        //*Si no se especifica nada puede almacenar hasta 10 elementos

        //ArrayList tiene 3 constructores

        List<Integer> numeros = new ArrayList<>(); //Constructor vacío
        //Uso esto para añadirle 10 numeros
        int contador = 0;
        while (contador <= 20) {
            numeros.add(contador);
            contador++;
        }
        List<Integer> ej2 = new ArrayList<>(numeros); //Constructor que recibe una colección
        List<Integer> ej3 = new ArrayList<>(20); //Constructor con capacidad inicial de 10


        //MÉTODOS DE ARRAYLIST

        //Métodos para añadir o quitar
        int quintoNumero = numeros.get(4); //Devuelve el numero en la quinta posicion del Array, porque empieza por 0
        ej3.getFirst(); //Devuelve el primer elemento de la coleccion
        ej3.getLast(); //Devuelve el ultimo elemento de la coleccion
        ej3.add(1123123); //Añade este elemento al Array
        ej3.addAll(numeros); //Añade toda una coleccion
        ej3.addAll(0, numeros); //Añade toda una coleccion en la posicion indicada
        ej3.remove(0); //ej3 solo tienen el numero añadido arriba, por tanto si elimino lo que haya en la posicion 0 lo borro
        ej3.remove(1123123); //.remove tiene dos sobrecargas, una recibe la posicion y otra recibe un objeto. Si encuentra el objeto devuelve true
        ej3.removeFirst(); //Eliminar el primero de la lista;
        ej3.removeLast(); //Elimina el último de la lista;
        ej3.removeAll(ej2); //Elimina de ej3 todos los objetos que estén en ej2;
        ej3.retainAll(numeros); //Elimina todos los elementos que no estén en numeros
        ej3.set(4, 25); // Introduce en la posicion 4 el numero 25;
        int tamaño = ej3.size(); //Devuelve el tamaño de la lista

        //Metodos para buscar o comprobar
        if (ej3.contains(4)) { //.contains devuelve true si se encuentra el objeto que recibe como parametro
            System.out.println("Contiene el numero 4");
        } else {
            System.out.println("No contiene el numero 4");
        }
        if (ej3.isEmpty()) { //.isEmpty devuelve true si la lista está vacia
            System.out.println("La lista esta vacia");
        } else {
            System.out.println("la lista no esta vacia");
        }
        int primeraAparicion = ej3.indexOf(4); //devuelve la posicion en la que se encuentra el elemento buscando desde el principio. Si no está devuelve -1;
        int ultimaAparicion = ej3.lastIndexOf(4); //devuelve la posicion en la que se encuentra el elemento buscando desde el final . Si no está devuelve -1;


        //ORDENAR ARRAYLIST

        Collections.sort(ej3);  //Por orden natural
        ej3.sort(Comparator.comparing(null)); //Tambien por orden natural
        ej3.reversed(); //Por orden inverso
        //Por orden alternativo (uso la clase Pais que te pase antes para crear una lista de paises)
        Pais p1 = new Pais("España", 3, 3, 4);
        Pais p2 = new Pais("Jovenlandia", 4, 4, 1);
        Pais p3 = new Pais("Francia", 5, 4, 4);
        Pais p4 = new Pais("Suecia", 1, 2, 5);
        List<Pais> paises = new ArrayList<>();
        paises.add(p1);
        paises.add(p2);
        paises.add(p3);
        paises.add(p4);
        //Ordeno por nombre de pais de forma ascendente
        paises.sort(Comparator.comparing(Pais::getNombre));
        //Ordeno por poblacion de forma decreciente
        paises.sort(Comparator.comparing(Pais::getPoblacion).reversed());
        //Ordeno por pib y si son iguales por poblacion y de forma decreciente
        paises.sort(Comparator.comparing(Pais::getPib).thenComparing(Pais::getPoblacion).reversed());


        //COMO RECORRER UN ARRAYLIST

        //Distingamos tres operaciones que hacer con la lista, añadir, modificar y eliminar
        //Para añadir no hace falta un iterador, pero para eliminar si
        //Si quieres poder hacer las tres operaciones usa ListIterator
        //Ej de añadir
        int c = 0;
        while (c <= 20) {
            numeros.add(c);
            c++;
        }
        //Ej de eliminacion
        List<Pais> copia = new ArrayList<>(paises);
        Iterator<Pais> iterCopia = copia.iterator();
        while (iterCopia.hasNext()) {
            Pais pais = iterCopia.next();
            if (pais.equals("Jovenlandia")) {
                iterCopia.remove();
            }
        }
        //Ej de las tres opciones con ListIterator
        List<Pais> copia2 = new ArrayList<>(paises);
        ListIterator<Pais> iterCopia2 = copia2.listIterator();
        while (iterCopia2.hasNext()) {
            Pais pais = iterCopia2.next();
            if (pais.getNombre().equals("Jovenlandia")) {
                iterCopia2.remove();
            } else if (pais.getNombre().equals("España")) {
                iterCopia2.add(new Pais("Peru", 1, 1, 1));
            } else if (pais.getNombre().equals("Francia")) {
                iterCopia2.set(new Pais("Argelia", 1, 1, 1));
            }
        }
    }
}
