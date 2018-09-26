
/**
 * Representa uma Casa do tabuleiro.
 * Possui uma posi�ao (x,y) e pode conter uma Pe�a.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 * @author Maria Luiza Martins &lt;mariacalixto@cc.ci.ufpb.br&gt;
 */
public class Casa {

    private int x;
    private int y;
    private Peca peca;

    /**
     * Construtor para a Classe Casa
     * @param coordenada x e coordenada y no tabuleiro
     */
    public Casa(int x, int y) {
        this.x = x;
        this.y = y;
        this.peca = null;
    }
    
    /**
     * @return a coordenada x da casa
     */
    public int getX() {
        return x;
    }
    
    /**
     * @return a coordenada y da casa
     */
    public int getY() {
        return y;
    }
    
    /**
     * @param peca a Pe�a a ser posicionada nesta Casa.
     */
    public void colocarPeca(Peca peca) {
        this.peca = peca;
    }
    
    /**
     * Remove a peca posicionada nesta casa, se houver.
     */
    public void removerPeca() {
        peca = null;
    }
    
    /**
     * @return a Peca posicionada nesta Casa, ou Null se a casa estiver livre.
     */
    public Peca getPeca() {
        return peca;
    }
    
    /**
     * @return tipo da peca posicionada na casa
     */
    public int getTipoPeca() {
        if (possuiPeca()) {
            return getPeca().getTipo();
        }
        return -1;
    }
    
    /**
     * @return true se existe uma pe�a nesta casa, caso contrario false.
     */
    public boolean possuiPeca() {
        return peca != null;
    }
}
