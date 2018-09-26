
/**
 * Representa uma Pe�a do jogo.
 * Possui uma casa e um tipo associado.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Peca {

    public static final int PEDRAS_BRANCAS = 0;
    public static final int PEDRAS_VERMELHAS = 1;
    public static final int DAMAS_BRANCAS = 2;
     public static final int DAMAS_VERMELHAS = 3;

    private Casa casa;
    private int tipo;

    /**
     * Construtor para a Classe Peca.
     * @param a casa e tipo da peca
     */
    public Peca(Casa casa, int tipo) {
        this.casa = casa;
        this.tipo = tipo;
        casa.colocarPeca(this);
    }
    
    /**
     * Movimenta a peca para uma nova casa.
     * @param destino nova casa que ira conter esta peca.
     */
    public void mover(Casa destino) {
        casa.removerPeca();
        destino.colocarPeca(this);
        casa = destino;
    }

    /**
     * Valor    Tipo
     *   0   Branca (Pedra)
     *   1   Vermelha (Pedra)
     *   2   Branca (Dama)
     *   3   Vermelha (Dama)
     * @return o tipo da peca.
     */
    public int getTipo() {
        return tipo;
    }
}
