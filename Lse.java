import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Lse<T> {
    private No<T> inicio;
    private No<T> ultimo;
    private int tamanho = 0;
    private final int NAO_ENCONTRADO = -1;
    private final String NAO_EXISTE = "Posição não existe.";
    private final String LISTA_VAZIA = "Lista está vazia.";
    private static final int MENOR = -1;
    private static final int IGUAL_MAIOR = 0;
    
    
   
    public void adiciona(T elemento) {
        No<T> celula = new No<T>(elemento);
        if (this.tamanho == 0) {
            this.inicio = celula;
        } else {
            this.ultimo.setProximo(celula);
        }
        this.ultimo = celula;
        this.tamanho++;
    }

    public void adicionaInicio(T elemento) {
        if (this.tamanho == 0) {
            No<T> novoNo = new No<>(elemento);
            this.inicio = novoNo;
            this.ultimo = novoNo;
        } else {
            No<T> novoNo = new No<>(elemento, this.inicio);
            this.inicio = novoNo;
        }
        this.tamanho++;
    }
    
    public void adiciona(int posicao, T elemento) {
        if (this.posicaoNaoExiste(posicao)) {
            throw new IllegalArgumentException(NAO_EXISTE);
        }
        if (posicao == 0) {                                 
            this.adicionaInicio(elemento);
        } 
        else if (posicao == this.tamanho) {               
            this.adiciona(elemento);
        } 
        else {                                            
            No<T> noAnterior = this.buscaNo(posicao);
            No<T> proximoNo = noAnterior.getProximo();
            No<T> novoNo = new No<>(elemento, proximoNo);
            noAnterior.setProximo(novoNo);
            this.tamanho++;
        }
    }
    
    public void limpa() {
        for (No<T> atual = this.inicio; atual != null;) {
            No<T> proximo = atual.getProximo();
            atual.setElemento(null);
            atual.setProximo(null);
            atual = proximo;
        }
        this.inicio = null;
        this.ultimo = null;
        this.tamanho = 0;
    }
    

    public T removeInicio() {
        if (this.tamanho == 0) {
            throw new RuntimeException(LISTA_VAZIA);
        }
        T removido = this.inicio.getElemento();
        this.inicio = this.inicio.getProximo();
        this.tamanho--;
        if (this.tamanho == 0) {
            this.ultimo = null;
        }
        return removido;
    }
    
    public T removeFinal() {
        if (this.tamanho == 0) {
            throw new RuntimeException(LISTA_VAZIA);
        }
        if (this.tamanho == 1) {
            return this.removeInicio();
        }
        No<T> penultimoNo = this.buscaNo(this.tamanho - 2);
        T removido = penultimoNo.getProximo().getElemento();
        penultimoNo.setProximo(null);
        this.ultimo = penultimoNo;
        this.tamanho--;

        return removido;
    }

    private boolean posicaoNaoExiste(int posicao) {
        return !(posicao >= 0 && posicao <= this.tamanho);
    }

    public T remove(int posicao) {
        if (this.posicaoNaoExiste(posicao)) {
            throw new IllegalArgumentException(NAO_EXISTE);
        }

        if (posicao == 0) {
            return this.removeInicio();
        }
        if (posicao == this.tamanho - 1) {
            return this.removeFinal();
        }
        No<T> noAnterior = this.buscaNo(posicao - 1);
        No<T> atual = noAnterior.getProximo();
        No<T> proximo = atual.getProximo();
        noAnterior.setProximo(proximo);
        atual.setProximo(null);
        this.tamanho--;
        return atual.getElemento();
    }

    public int getTamanho() {
        return this.tamanho;
    }
  
    private No<T> buscaNo(int posicao) {
        if (this.posicaoNaoExiste(posicao)) {
            throw new IllegalArgumentException(NAO_EXISTE);
        }
        No<T> noAtual = this.inicio;
        for (int i = 0; i < posicao; i++) {
            noAtual = noAtual.getProximo();
        }
        return noAtual;
    }
    
    public T buscaPorPosicao(int posicao) {
        return this.buscaNo(posicao).getElemento();
    }
    public int busca(T elemento) {
        No<T> noAtual = this.inicio;
        int pos = 0;
        while (noAtual != null) {
            if (noAtual.getElemento().equals(elemento)) {
                return pos;
            }
            pos++;
            noAtual = noAtual.getProximo();
        }
        return NAO_ENCONTRADO;
    }

       
    public void adicionaOrdenado(T elemento, Comparator<T> comparator) {
        if (this.tamanho == 0) { 
            this.adicionaInicio(elemento);
        } else if (comparator.compare(this.inicio.getElemento(), elemento) >= IGUAL_MAIOR) {
            this.adicionaInicio(elemento);
        } else {
            No<T> atual = this.inicio;
            while (atual.getProximo() != null
                    && comparator.compare(atual.getProximo().getElemento(), elemento) == MENOR) {
                atual = atual.getProximo();
            }
            No<T> celula = new No<>(elemento, atual.getProximo());
            atual.setProximo(celula);
            this.tamanho++;
        }
    }

    @SuppressWarnings("unchecked")
    public T[] transformaArray() {
        if (this.tamanho == 0) {
            return (T[]) new Object[0];
        }
        Object[] vetor = new Object[this.tamanho];
        No<T> atual = this.inicio;
        for (int i = 0; i < this.tamanho; i++) {
            vetor[i] = atual.getElemento();
            atual = atual.getProximo();
        }
        return (T[]) vetor;
    }

    public void inverte() { 
        No<T> atual = this.inicio;
        No<T> proximo = null;
        No<T> anterior = null;
        while (atual != null) {
            proximo = atual.getProximo();
            atual.setProximo(anterior);
            anterior = atual;
            atual = proximo;
        }
        this.inicio = anterior;
    }
    
    @Override
    public String toString() {
        if (this.tamanho == 0) {
            return "[]"; 
        }

        StringBuilder builder = new StringBuilder("[");
        No<T> atual = this.inicio;
        while (atual != null) {
            builder.append(atual.getElemento()).append(",");
            atual = atual.getProximo();
        }
        builder.deleteCharAt(builder.length() - 1).append("]");
        return builder.toString();
    }

  /*  @Override
    public String toString() {
        if (this.tamanho == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        // builder.append("ListaEncadeada [inicio=").append(inicio).append("]");

        No<T> atual = this.inicio;
        for (int i = 0; i < this.tamanho - 1; i++) {
            builder.append(atual.getElemento()).append(",");
            atual = atual.getProximo();
        }
        builder.append(atual.getElemento()).append("]");
        /*
         * builder.append(atual.getElemento()).append(",");
         * while (atual.getProximo() != null) {
         * atual = atual.getProximo();
         * builder.append(atual.getElemento()).append(",");
         * }
         */
    //    return builder.toString();
   // }
    

    public int indiceMenorElemento() {
        if (tamanho == 0) {
            throw new IllegalStateException("A lista está vazia.");
        }

        int menorValor = Integer.MAX_VALUE;
        int posicaoMenorValor = -1;

        for (int i = 0; i < tamanho; i++) {
            T valor = buscaPorPosicao(i);
            if (valor instanceof Integer) {
                int intValue = (Integer) valor;
                if (intValue < menorValor) {
                    menorValor = intValue;
                    posicaoMenorValor = i;
                }
            } else {
                throw new IllegalStateException("A lista contém elementos que não são inteiros.");
            }
        }

        return posicaoMenorValor;
    }

    public T menorElemento() {
        if (tamanho == 0) {
            throw new IllegalStateException("A lista está vazia.");
        }

        T menorValor = null;

        for (int i = 0; i < tamanho; i++) {
            T valor = buscaPorPosicao(i);
            if (menorValor == null || compare(valor, menorValor) < 0) {
                menorValor = valor;
            }
        }

        return menorValor;
    }

    private int compare(T a, T b) {
        if (a instanceof Comparable && b instanceof Comparable) {
            return ((Comparable<T>) a).compareTo(b);
        } else {
            throw new IllegalStateException("Os elementos da lista não são comparáveis.");
        }
    }
    
    public boolean contem(T elemento) {
        return busca(elemento) != -1;
    }
    public void removeElemento(T elemento) {
        while (contem(elemento)) {
            remove(busca(elemento));
        }
    }
    public void substituiElemento(T elementoAntigo, T elementoNovo) {
        for (int i = 0; i < tamanho; i++) {
            if (buscaPorPosicao(i).equals(elementoAntigo)) {
                remove(i);
                adiciona(i, elementoNovo);
            }
        }
    }
    public int contaOcorrencias(T elemento) {
        int contador = 0;
        for (int i = 0; i < tamanho; i++) {
            if (buscaPorPosicao(i).equals(elemento)) {
                contador++;
            }
        }
        return contador;
    }
    public T get(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IllegalArgumentException("Posição inválida.");
        }

        No<T> no = buscaNo(posicao);
        return no.getElemento();
    }
      
    public int tamanho() {
        return tamanho;
    }
    public boolean contemRepetidos() {
        if (tamanho < 2) {
            return false;
        }

        HashSet<T> set = new HashSet<>();
        No<T> atual = inicio;
        while (atual != null) {
            if (!set.add(atual.getElemento())) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }
    public int contarRepetidos() {
        HashMap<T, Integer> map = new HashMap<>();
        No<T> atual = inicio;
        while (atual != null) {
            map.put(atual.getElemento(), map.getOrDefault(atual.getElemento(), 0) + 1);
            atual = atual.getProximo();
        }

        int contador = 0;
        for (int count : map.values()) {
            if (count > 1) {
                contador++;
            }
        }
        return contador;
    }
    public void removerRepetidos() {
        HashMap<T, Integer> map = new HashMap<>();
        No<T> anterior = null;
        No<T> atual = inicio;

        while (atual != null) {
            if (map.containsKey(atual.getElemento())) {
                anterior.setProximo(atual.getProximo());
                tamanho--;
            } else {
                map.put(atual.getElemento(), 1);
                anterior = atual;
            }
            atual = atual.getProximo();
        }

        ultimo = anterior;
    }
    public void swap(int posicao1, int posicao2) {
        if (posicao1 < 0 || posicao1 >= tamanho || posicao2 < 0 || posicao2 >= tamanho) {
            throw new IllegalArgumentException("Posições inválidas.");
        }
        if (posicao1 == posicao2) {
            return; 
        }
        No<T> no1 = buscaNo(posicao1);
        No<T> no2 = buscaNo(posicao2);
        T temp = no1.getElemento();
        no1.setElemento(no2.getElemento());
        no2.setElemento(temp);
    }
    
    
    public Lse<T> fork(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IllegalArgumentException("Posição inválida.");
        }
        Lse<T> novaLista = new Lse<>();
        No<T> atual = buscaNo(posicao); 
        novaLista.inicio = atual.getProximo();
        novaLista.ultimo = ultimo;
        novaLista.tamanho = tamanho - posicao;
        ultimo = atual;
        ultimo.setProximo(null);
        tamanho = posicao;
        return novaLista;
    }
        
	class No<T> {
		private T elemento;
		private No<T> proximo;
		
		public No(T elemento) {
			this.elemento=elemento;
			this.proximo=null;
		}
		
		public No(T elemento,No<T>proximo) {
			this.elemento=elemento;
			this.proximo=null;
		}
			
		
		public No<T> getProximo() {
			return proximo;
		}
		public void setProximo(No<T> proximo) {
			this.proximo = proximo;
		}
		public T getElemento() {
			return elemento;
		}
		public void setElemento(T elemento) {
			this.elemento = elemento;
		}
		
		@Override
		public String toString() {
			return "No [elemento=" + elemento + ", proximo=" + proximo + "]";
		}
		}	

}