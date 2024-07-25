import java.util.Comparator;

public class Dequee<T> {
    private No<T> frente;
    private No<T> fim;


    public Dequee() {
        frente = null;
        fim = null;
    }

    public boolean isEmpty() {
        return frente == null;
    }

    public void addFirst(T elemento) {
        No<T> novoNo = new No<T>(elemento);
        if (isEmpty()) {
            frente = fim = novoNo;
        } else {
            novoNo.next = frente;
            frente.prev = novoNo;
            frente = novoNo;
        }
    }

    public void addLast(T elemento) {
        No<T> novoNo = new No<T>(elemento);
        if (isEmpty()) {
            frente = fim = novoNo;
        } else {
            fim.next = novoNo;
            novoNo.prev = fim;
            fim = novoNo;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            System.out.println("O deque está vazio.");
            return null;
        } else {
            T elemento = frente.value;
            frente = frente.next;
            if (frente == null) {
                fim = null;
            } else {
                frente.prev = null;
            }
            return elemento;
        }
    }

    public void removeFirstOccurrence(T elemento) {
        No<T> atual = frente;
        while (atual != null) {
            if (atual.value.equals(elemento)) {
                if (atual == frente) {
                    removeFirst();
                    return;
                } else if (atual == fim) {
                    removeLast();
                    return;
                } else {
                    atual.prev.next = atual.next;
                    atual.next.prev = atual.prev;
                    return;
                }
            }
            atual = atual.next;
        }
    }
    
    
    

    public T removeLast() {
        if (isEmpty()) {
            System.out.println("O deque está vazio.");
            return null;
        } else {
            T elemento = fim.value;
            fim = fim.prev;
            if (fim == null) {
                frente = null;
            } else {
                fim.next = null;
            }
            return elemento;
        }
    }

    public T first() {
        if (isEmpty()) {
            System.out.println("O deque está vazio.");
            return null;
        } else {
            return frente.value;
        }
    }

    public T last() {
        if (isEmpty()) {
            System.out.println("O deque está vazio.");
            return null;
        } else {
            return fim.value;
        }
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.println("O deque está vazio.");
        } else {
            No<T> atual = frente;
            while (atual != null) {
                System.out.print(atual.value + " ");
                atual = atual.next;
            }
            System.out.println();
        }
    }

    public void inverter() {
        if (isEmpty() || frente == fim) {
            return;
        }
        No<T> atual = frente;
        No<T> temp = null;
        while (atual != null) {
            temp = atual.next;
            atual.next = atual.prev;
            atual.prev = temp;
            atual = temp;
        }
        temp = frente;
        frente = fim;
        fim = temp;
    }

    public void moverPrimeiroParaFinal() {
        if (isEmpty() || frente == fim) {
            return;
        }
        T primeiro = removeFirst();
        addLast(primeiro);
    }

    public void sort(Comparator<? super T> comparator) {
        if (isEmpty() || frente == fim) {
            return;
        }
        No<T> atual = frente;
        while (atual != null) {
            No<T> temp = atual.next;
            while (temp != null) {
                if (comparator.compare(atual.value, temp.value) > 0) {
                    T tempValue = atual.value;
                    atual.value = temp.value;
                    temp.value = tempValue;
                }
                temp = temp.next;
            }
            atual = atual.next;
        }
    }
    
    public void armazenarNaPilha(Pilha<T> pilha) {
        No<T> atual = frente;
        while (atual != null) {
            pilha.push(atual.value);
            atual = atual.next;
        }
    }
    
    public boolean contains(T elemento) {
        No<T> atual = frente;
        while (atual != null) {
            if (atual.value.equals(elemento)) {
                return true;
            }
            atual = atual.next;
        }
        return false;
    }
    
    public Dequee<T> cloneDeque() {
        Dequee<T> clone = new Dequee<>();
        No<T> atual = frente;
        while (atual != null) {
            clone.addLast(atual.value);
            atual = atual.next;
        }
        return clone;
    }
    
    public void ordenarPorTamanho() {
        Dequee<T> ordenado = new Dequee<>();
        while (!isEmpty()) {
            T atual = removeFirst();
            if (ordenado.isEmpty() || atual.toString().length() <= ordenado.last().toString().length()) {
                ordenado.addLast(atual);
            } else {
                while (!ordenado.isEmpty() && atual.toString().length() > ordenado.last().toString().length()) {
                    addLast(ordenado.removeLast());
                }
                ordenado.addLast(atual);             }
        }  
        while (!ordenado.isEmpty()) {
            addLast(ordenado.removeFirst());
        }
    }
    
    public int size() {
        int size = 0;
        No<T> atual = frente;
        while (atual != null) {
            size++;
            atual = atual.next;
        }
        return size;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index fora dos limites do deque.");
        }
        No<T> atual = frente;
        for (int i = 0; i < index; i++) {
            atual = atual.next;
        }
        return atual.value;
    }
    


    public class No<T> {
        T value;
        No<T> next;
        No<T> prev;

        public No(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
}