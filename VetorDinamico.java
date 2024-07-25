
public class VetorDinamico {
    private String[] elementos;
    private int tamanhoAtual = 0;

    public VetorDinamico(int capacidade) {
        elementos = new String[capacidade];
    }
    // ADD
    public void adicionar(String elemento) {
        if (tamanhoAtual < elementos.length) {
            elementos[tamanhoAtual] = elemento;
            tamanhoAtual++;
        } else {
            System.out.println("Lista cheia, não é possível adicionar mais elementos.");
        }
    }
    // Exercio 1 Simular Comportamento de Vetor
    public String obter(int indice) {
        if (indice >= 0 && indice < tamanhoAtual) {
            return elementos[indice];
        } else {
            return null;
        }
    }
    // Size
    public int tamanho() {
        return tamanhoAtual;   
    }
    
    // Remove
    public void removerPorIndice(int indice) {
        if (indice >= 0 && indice < tamanhoAtual) {
            for (int i = indice; i < tamanhoAtual - 1; i++) {
                elementos[i] = elementos[i + 1];
            }
            elementos[tamanhoAtual - 1] = null;
            tamanhoAtual--;
        }
    }
    
    public void removerPorElemento(String elemento) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (elementos[i] != null && elementos[i].equals(elemento)) {
                this.removerPorIndice(i);
                return;
            }
        }
    }

    public boolean estaVazia() {
        return tamanhoAtual == 0;
    }
    
    public boolean contem(String elemento) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (elementos[i] != null && elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    public void limpar() {
        elementos = new String[elementos.length];
        tamanhoAtual = 0;
    }
    // Exercio 1 = Buscar elemento
    public int indiceDe(String elemento) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (elementos[i] != null && elementos[i].equals(elemento)) {
                return i;
            }
        }
        return -1;
    }
    
    public int ultimoIndiceDe(String elemento) {
        for (int i = tamanhoAtual - 1; i >= 0; i--) {
            if (elementos[i] != null && elementos[i].equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    public void substituir(int indice, String novoElemento) {
        if (indice >= 0 && indice < tamanhoAtual) {
            elementos[indice] = novoElemento;
        }
    }
    
    public VetorDinamico copiar() {
        VetorDinamico novaLista = new VetorDinamico(elementos.length);
        for (int i = 0; i < tamanhoAtual; i++) {
            novaLista.adicionar(this.obter(i));
        }
        return novaLista;
    }

    public void concatenar(VetorDinamico outraLista) {
        for (int i = 0; i < outraLista.tamanho(); i++) {
            this.adicionar(outraLista.obter(i));
        }
    }
    
    public void inverter() {
        for (int i = 0; i < tamanhoAtual / 2; i++) {
            String temp = elementos[i];
            elementos[i] = elementos[tamanhoAtual - 1 - i];
            elementos[tamanhoAtual - 1 - i] = temp;
        }
    }
    
    public VetorDinamico subLista(int inicio, int fim) {
        VetorDinamico subLista = new VetorDinamico(fim - inicio);
        for (int i = inicio; i < fim; i++) {
            if (i >= 0 && i < tamanhoAtual) {
                subLista.adicionar(this.obter(i));
            }
        }
        return subLista;
    }

    public int contarOcorrencias(String elemento) {
        int contador = 0;
        for (int i = 0; i < tamanhoAtual; i++) {
            if (elementos[i] != null && elementos[i].equals(elemento)) {
                contador++;
            }
        }
        return contador;
    }

    public boolean equals(VetorDinamico outraLista) {
        if (tamanhoAtual != outraLista.tamanho()) {
            return false;
        }
        for (int i = 0; i < tamanhoAtual; i++) {
            if (!this.obter(i).equals(outraLista.obter(i))) {
                return false;
            }
        }
        return true;
    }
    
    public void adicionarEmPosicao(String elemento, int posicao) {
        if (posicao >= 0 && posicao <= tamanhoAtual) {
            if (tamanhoAtual == elementos.length) {
                expandirCapacidade();
            }
            for (int i = tamanhoAtual; i > posicao; i--) {
                elementos[i] = elementos[i - 1];
            }
            elementos[posicao] = elemento;
            tamanhoAtual++;
        }
    }
    
    private void expandirCapacidade() {
        int novaCapacidade = elementos.length * 2;
        String[] novaLista = new String[novaCapacidade];
        for (int i = 0; i < elementos.length; i++) {
            novaLista[i] = elementos[i];
        }
        elementos = novaLista;
    }
    
    public void inserirTodosEmPosicao(VetorDinamico outraLista, int posicao) {
        for (int i = 0; i < outraLista.tamanho(); i++) {
            this.adicionarEmPosicao(outraLista.obter(i), posicao + i);
        }
    }

    public String[] toArray() {
        String[] array = new String[tamanhoAtual];
        for (int i = 0; i < tamanhoAtual; i++) {
            array[i] = this.obter(i);
        }
        return array;
    }
  
    public boolean contemTodos(VetorDinamico outraLista) {
        for (int i = 0; i < outraLista.tamanho(); i++) {
            if (!this.contem(outraLista.obter(i))) {
                return false;
            }
        }
        return true;
    }
    
    public void removerTodos(VetorDinamico outraLista) {
        for (int i = 0; i < outraLista.tamanho(); i++) {
            this.removerPorElemento(outraLista.obter(i));
        }
    }
    
    public VetorDinamico elementosRepetidos() {
        VetorDinamico repetidos = new VetorDinamico(tamanhoAtual);
        for (int i = 0; i < tamanhoAtual - 1; i++) {
            String elementoAtual = this.obter(i);
            for (int j = i + 1; j < tamanhoAtual; j++) {
                if (elementoAtual.equals(this.obter(j)) && !repetidos.contem(elementoAtual)) {
                    repetidos.adicionar(elementoAtual);
                }
            }
        }
        return repetidos;
    }
    // Exercio 1 Verificar Numeros Repetidos
    public boolean haItensRepetidos() {
        return contarItensRepetidos() > 0;
    }

    public int contarItensRepetidos() {
        int contadorItensRepetidos = 0; 
        VetorDinamico itensRepetidos = this.elementosRepetidos();
        for (int i = 0; i < itensRepetidos.tamanho(); i++) {
            contadorItensRepetidos += this.contarOcorrencias(itensRepetidos.obter(i));
        }
        return contadorItensRepetidos;
    }
    
    // Exercio 1 Eliminar Numeros Repetidos
    public void removerElementosRepetidos() {
        VetorDinamico elementosUnicos = new VetorDinamico(tamanhoAtual);
        for (int i = 0; i < tamanhoAtual; i++) {
            String elemento = this.obter(i);
            if (!elementosUnicos.contem(elemento)) {
                elementosUnicos.adicionar(elemento);
            }
        }
        this.elementos = elementosUnicos.toArray();
        this.tamanhoAtual = elementosUnicos.tamanho();
    }
    
    //Exercio 1 Verificar se a Lista esta ordenada
    public boolean estaOrdenada() {
        for (int i = 0; i < tamanhoAtual - 1; i++) {
            if (elementos[i].compareTo(elementos[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
    public static boolean ehPar(int numero) {
        return numero % 2 == 0;
    }
    
    public void ordenarCrescente() {
        for (int i = 0; i < tamanhoAtual - 1; i++) {
            for (int j = i + 1; j < tamanhoAtual; j++) {
                if (Integer.parseInt(elementos[i]) > Integer.parseInt(elementos[j])) {
                    String temp = elementos[i];
                    elementos[i] = elementos[j];
                    elementos[j] = temp;
                }
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}