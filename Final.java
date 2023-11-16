import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

class Nodo {
    int valor;
    Nodo izquierda, derecha;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierda = this.derecha = null;
    }
}

class ArbolBinario {
    Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public void insertarNodo(int valor) {
        if (this.raiz == null) {
            this.raiz = new Nodo(valor);
        } else {
            this.insertarNodoIterativo(valor);
        }
    }

    private void insertarNodoIterativo(int valor) {
        Nodo nuevoNodo = new Nodo(valor);
        Nodo nodoActual = this.raiz;
        Nodo padre = null;

        while (nodoActual != null) {
            padre = nodoActual;
            if (valor < nodoActual.valor) {
                nodoActual = nodoActual.izquierda;
            } else {
                nodoActual = nodoActual.derecha;
            }
        }

        if (valor < padre.valor) {
            padre.izquierda = nuevoNodo;
        } else {
            padre.derecha = nuevoNodo;
        }
    }

    public void inOrderTraversal() {
        MiStack<Nodo> stack = new MiStack<>();
        Nodo nodoActual = this.raiz;

        while (nodoActual != null || !stack.isEmpty()) {
            while (nodoActual != null) {
                stack.push(nodoActual);
                nodoActual = nodoActual.izquierda;
            }

            nodoActual = stack.pop();
            System.out.print(nodoActual.valor + " ");
            nodoActual = nodoActual.derecha;
        }
    }

    public static void imprimirArbolConProcedencia(Nodo raiz) {
        if (raiz == null) {
            System.out.println("Empty Tree");
            return;
        }

        MiQueue<NodoConPadre> cola = new MiQueue<>();
        cola.add(new NodoConPadre(raiz, null));

        while (!cola.isEmpty()) {
            NodoConPadre nodoActual = cola.poll();
            System.out.print(nodoActual.nodo.valor);

            if (nodoActual.padre != null) {
                System.out.print(" (Padre: " + nodoActual.padre.valor + ")");
            }

            System.out.print(" ");

            if (nodoActual.nodo.izquierda != null) {
                cola.add(new NodoConPadre(nodoActual.nodo.izquierda, nodoActual.nodo));
            }

            if (nodoActual.nodo.derecha != null) {
                cola.add(new NodoConPadre(nodoActual.nodo.derecha, nodoActual.nodo));
            }
        }
    }

    static class NodoConPadre {
        Nodo nodo;
        Nodo padre;

        public NodoConPadre(Nodo nodo, Nodo padre) {
            this.nodo = nodo;
            this.padre = padre;
        }
    }
}

class MiQueue<T> {
    private LinkedList<T> lista = new LinkedList<>();

    public void add(T elemento) {
        lista.add(elemento);
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        return lista.removeFirst();
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }
}

class MiStack<T> {
    private LinkedList<T> lista = new LinkedList<>();

    public void push(T elemento) {
        lista.addFirst(elemento);
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return lista.removeFirst();
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }
}

class MiScanner {
    private Scanner scanner;

    public MiScanner(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String nextLine() {
        return scanner.nextLine();
    }
}

public class Final {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        MiScanner scanner = new MiScanner(System.in);

        while (true) {
            System.out.print("Ingrese un nodo (o 'x' para terminar): ");
            String entrada = scanner.nextLine().toLowerCase();
            if (entrada.equals("x")) {
                break;
            }

            try {
                int valor = Integer.parseInt(entrada);
                arbol.insertarNodo(valor);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un valor entero valido.");
            }
        }

        System.out.println("\nArbol binario con procedencia:");
        ArbolBinario.imprimirArbolConProcedencia(arbol.raiz);

        System.out.println("\nRecorrido in-order:");
        arbol.inOrderTraversal();
    }
}
