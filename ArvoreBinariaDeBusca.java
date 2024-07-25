
public class ArvoreBinariaDeBusca {
    Node raiz;

    ArvoreBinariaDeBusca() {
        raiz = null;
    }

    void inserir(int chave) {
        raiz = inserirRecursivamente(raiz, chave);
    }

    Node inserirRecursivamente(Node raiz, int chave) {
        if (raiz == null) {
            raiz = new Node(chave);
            return raiz;
        }
        if (chave < raiz.chave)
            raiz.esquerda = inserirRecursivamente(raiz.esquerda, chave);
        else if (chave > raiz.chave)
            raiz.direita = inserirRecursivamente(raiz.direita, chave);

        return raiz;
    }

    void preOrdem(Node node, StringBuilder sb) {
        if (node == null)
            return;
        if (sb.length() > 0)
            sb.append(" ");
        sb.append(node.chave);
        preOrdem(node.esquerda, sb);
        preOrdem(node.direita, sb);
    }

    void emOrdem(Node node, StringBuilder sb) {
        if (node == null)
            return;
        emOrdem(node.esquerda, sb);
        if (sb.length() > 0)
            sb.append(" ");
        sb.append(node.chave);
        emOrdem(node.direita, sb);
    }

    void posOrdem(Node node, StringBuilder sb) {
        if (node == null)
            return;
        posOrdem(node.esquerda, sb);
        posOrdem(node.direita, sb);
        if (sb.length() > 0)
            sb.append(" ");
        sb.append(node.chave);
    }
    
    class Node {
        int chave;
        Node esquerda, direita;

        public Node(int item) {
            chave = item;
            esquerda = direita = null;
        }
    }

}
