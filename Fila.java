import java.util.Comparator;

public class Fila<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public Fila() {
        inicio = null;
        fim = null;
        tamanho = 0;
    }

    public void enqueue(T item) {
        No<T> novo = new No<>(item);
        if (isEmpty()) {
            inicio = novo;
        } else {
            fim.next = novo;
        }
        fim = novo;
        tamanho++;
    }

    public T dequeue() {
        if (!isEmpty()) {
            No<T> item = inicio;
            inicio = inicio.next;
            item.next = null;
            tamanho--;
            if (isEmpty()) {
                fim = null;
            }
            return item.value;
        }
        return null;
    }

    public T peek() {
        if (!isEmpty()) {
            return inicio.value;
        }
        return null;
    }

    public void exibir() {
        No<T> atual = inicio;
        while (atual != null) {
            System.out.print(atual.value.toString() + " ");
            atual = atual.next;
        }
        System.out.println();
    }

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public Fila<T> clone() {
        Fila<T> clone = new Fila<>();
        No<T> atual = inicio;
        while (atual != null) {
            clone.enqueue(atual.value);
            atual = atual.next;
        }
        return clone;
    }

    public boolean removeFirstOccurrence(T elemento) {
        No<T> anterior = null;
        No<T> atual = inicio;
        while (atual != null) {
            if (atual.value.equals(elemento)) {
                if (anterior != null) {
                    anterior.next = atual.next;
                    if (atual == fim) {
                        fim = anterior;
                    }
                } else {
                    inicio = atual.next;
                    if (inicio == null) {
                        fim = null;
                    }
                }
                tamanho--;
                return true;
            }
            anterior = atual;
            atual = atual.next;
        }
        return false;
    }

    public void inverterUsandoPilha() {
        Pilha<T> auxiliar = new Pilha<>();
        while (!isEmpty()) {
            auxiliar.push(dequeue());
        }
        while (!auxiliar.isEmpty()) {
            enqueue(auxiliar.pop());
        }
    }

    public void armazenarNaPilha() {
        Pilha<T> pilhaAuxiliar = new Pilha<>();
        No<T> atual = inicio;
        while (atual != null) {
            pilhaAuxiliar.push(atual.value);
            atual = atual.next;
        }
        // pilhaAuxiliar.exibir();
        // return pilhaAuxiliar;
    }

    public T maiorNumero(Comparator<T> comparator) {
        if (isEmpty()) {
            return null;
        }
        T maior = inicio.value;
        No<T> atual = inicio.next;
        while (atual != null) {
            if (comparator.compare(atual.value, maior) > 0) {
                maior = atual.value;
            }
            atual = atual.next;
        }
        return maior;
    }

    public T menorNumero(Comparator<T> comparator) {
        if (isEmpty()) {
            return null;
        }
        T menor = inicio.value;
        No<T> atual = inicio.next;
        while (atual != null) {
            if (comparator.compare(atual.value, menor) < 0) {
                menor = atual.value;
            }
            atual = atual.next;
        }
        return menor;
    }

    private int comparar(T a, T b) {
        if (a instanceof Comparable && b instanceof Comparable) {
            Comparable<T> comparableA = (Comparable<T>) a;
            return comparableA.compareTo(b);
        } else {
            throw new IllegalArgumentException("Os elementos não são comparáveis.");
        }
    }

    public void ordenarDecrescenteUsandoPilha() {
        if (isEmpty() || size() == 1) {
            return;
        }
        Pilha<T> pilhaAuxiliar = new Pilha<>();
        while (!isEmpty()) {
            pilhaAuxiliar.push(dequeue());
        }
        while (!pilhaAuxiliar.isEmpty()) {
            T elemento = pilhaAuxiliar.pop();
            while (!isEmpty() && comparar(elemento, peek()) < 0) {
                pilhaAuxiliar.push(dequeue());
            }
            enqueue(elemento);
        }
    }

    public void organizarAlfabeticamente() {
        if (isEmpty() || size() == 1) {
            return;
        }
        Pilha<T> pilhaAuxiliar = new Pilha<>();
        while (!isEmpty()) {
            T elemento = dequeue();
            while (!pilhaAuxiliar.isEmpty() && comparar(elemento, pilhaAuxiliar.peek()) > 0) {
                enqueue(pilhaAuxiliar.pop());
            }
            pilhaAuxiliar.push(elemento);
        }
        while (!pilhaAuxiliar.isEmpty()) {
            enqueue(pilhaAuxiliar.pop());
        }
    }

    public class No<T> {
        T value;
        No<T> next;

        public No(T value) {
            this.value = value;
            this.next = null;
        }
    }
}
