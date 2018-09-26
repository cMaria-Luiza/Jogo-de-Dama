
/**
   * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 * @author Maria Luiza Martins &lt;mariacalixto@cc.ci.ufpb.br&gt;
 */
public class Jogo {

    private Tabuleiro tabuleiro;
    private int turno;
    private Casa casaProxima;
    private boolean comeu;

    /**
     * Construtor para o Jogo.
     */
    public Jogo() {
        tabuleiro = new Tabuleiro();
        turno = 0; // comeca comas brancas
        casaProxima = null;
        comeu = false;
        criarPecas();
    }
    
    /**
     * Posiciona pe�as no tabuleiro.
     * Utilizado na inicializa�ao do jogo.
     */
    private void criarPecas() {
        // posicionando pedras brancas
        for(int y = 0; y <= 2; y++) {
            for(int x = 0; x <= 7; x++) { 
                if(y%2 == 0 && x%2 == 0) {
                    Peca peca = new Peca(tabuleiro.getCasa(x, y), Peca.PEDRAS_BRANCAS);
                    tabuleiro.getCasa(x, y).colocarPeca(peca);                
                }else if(y%2 != 0 && x%2 != 0) {
                    Peca peca = new Peca(tabuleiro.getCasa(x, y), Peca.PEDRAS_BRANCAS);
                    tabuleiro.getCasa(x, y).colocarPeca(peca);
                }
            }
        }
        // posicionando pedras vermelhas
        for(int y = 5; y <= 7; y++) {
            for(int x = 0; x <= 7; x++) {
                if(y%2 == 0 && x%2 == 0) {
                        Peca peca = new Peca(tabuleiro.getCasa(x, y), Peca.PEDRAS_VERMELHAS);
                        tabuleiro.getCasa(x, y).colocarPeca(peca);
                }else if(y%2 != 0 && x%2 != 0) {
                    Peca peca = new Peca(tabuleiro.getCasa(x, y), Peca.PEDRAS_VERMELHAS);
                    tabuleiro.getCasa(x, y).colocarPeca(peca);
                }
            }
        }
    }
    
    /**
     * Comanda uma Pe�a na posicao (origemX, origemY) fazer um movimento 
     * para (destinoX, destinoY).
     * 
     * @param origemX linha da Casa de origem.
     * @param origemY coluna da Casa de origem.
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino.
     */
    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        peca.mover(destino);
    }
    
    /**
     * @return o Tabuleiro em jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }     
    
    /**
     * Verifica se o movemento é permitido e come a peca se for solto.
     * @param coordenadas das casas origem e destino; o tipo da peca da casa origem
     * @return valor booleano
     */
    public boolean movimentoPermitido(int xOrigem, int yOrigem, int xDestino, int yDestino, int tipoPeca) {
        Casa casaOrigem = tabuleiro.getCasa(xOrigem, yOrigem);
        
        if (!comeu && (xDestino == xOrigem + 1 || xDestino == xOrigem - 1)) {
            if (tipoPeca == 0 && yDestino == yOrigem + 1) {
                return true;
            }
            else if (tipoPeca == 1 && yDestino == yOrigem - 1) {
                return true;
            }
            else if ((tipoPeca == 2 || tipoPeca == 3) && (yDestino == yOrigem + 1 || yDestino == yOrigem - 1)) {
                return true;
            }
        }
        else if (xDestino == xOrigem + 2  && yDestino == yOrigem + 2)  {
            casaProxima = tabuleiro.getCasa(xOrigem + 1, yOrigem + 1);
            // verifica se tem uma peca permitida para comer 
            if (podeComer(casaOrigem, casaProxima)) {
                comerPeca();
                return true;
            }
        }
        else if (xDestino == xOrigem - 2  && yDestino == yOrigem + 2)  {
            casaProxima = tabuleiro.getCasa(xOrigem - 1, yOrigem + 1);
            // verifica se tem uma peca permitida para comer 
            if (podeComer(casaOrigem, casaProxima)) {
                comerPeca();
                return true;
            }
        }
        else if (xDestino == xOrigem + 2  && yDestino == yOrigem - 2)  {
            casaProxima = tabuleiro.getCasa(xOrigem + 1, yOrigem - 1);
            // verifica se tem uma peca permitida para comer 
            if (podeComer(casaOrigem, casaProxima)) {
                comerPeca();
                return true;
            }
        }
        else if (xDestino == xOrigem - 2  && yDestino == yOrigem - 2)  {
            casaProxima = tabuleiro.getCasa(xOrigem - 1, yOrigem - 1);
            // verifica se tem uma peca permitida para comer 
            if (podeComer(casaOrigem, casaProxima)) {
                comerPeca();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica se é permitido comer a peca da casa proxima;
     * @param a Casa proxima e Casa origem 
     * @return valor booleano
     */
    public boolean podeComer(Casa origem, Casa proxima) {
        int tipoOrigem = origem.getTipoPeca();
        int tipoProxima = proxima.getTipoPeca();
        if (proxima.possuiPeca() && tipoOrigem != tipoProxima && tipoOrigem%2 != tipoProxima%2) {
            return true;
        }
        return false;
    }
    
    /**
     * Come a peca armazenada em casaProxima.
     */
    public void comerPeca() {
        // se o movimento for salto, remove a peca entre a casaOrigem e a casaDestino
        casaProxima.removerPeca();
        comeu = true;
    }
    
    
    /**
     * Verifica se é a vez da peca recebida como parametro.
     * @param tipo da peca 
     * @return valor booleano 
     */
    public boolean suaVez(int tipo) {
        if (tipo%2 == turno) {
            return true;
        }
        return false;
    }
    
    /**
     * Muda o turno do jogo.
     */
    public void mudarTurno() {
       if (turno == 0) {
           turno = 1;
       }
       else {
           turno = 0;
       }
    }
    
    /**
     * Verifica se o valor da instancia é verdadeiro ou falso; é usado na Classe Janela Principal.
     * @return comeu
     */
    public boolean comeu() {
        return comeu;
    }
    
    /**
     * Determina a variavel comeu como falsa. 
     */
    public void naoComeu() { 
        comeu = false;
    }
    
    /**
     * Determina o turno inicial como 0(vez das brancas).
     */
    public void setVezInicial() {
        turno = 0;
    }
    
    /**
     * @return turno
     */
    public int getTurno() {
        return turno;
    }
    
    /**
     * Torna a pedra uma dama.
     * @param casa que a peca esta posicionada e o tipo dela 
     */
    public void virarDama(Casa casa, int tipoPeca) {
        if (tipoPeca == 0) {
            casa.removerPeca();
            Peca dama = new Peca(casa, 2);
            casa.colocarPeca(dama);
        }
        else if (tipoPeca == 1) {
            casa.removerPeca();
            Peca dama = new Peca(casa, 3);
            casa.colocarPeca(dama);
        }
    }
  
    /**
     * verifica se o usuario deve continuar a comer
     * @param tabuleiro do jogo e a ultima casa destino
     * @return valor boolean 
     */
    public boolean deveContinuar(CasaGUI novaCasaOrigem) {
         int x = novaCasaOrigem.getPosicaoX();
         int y = novaCasaOrigem.getPosicaoY();
         // verifica se a peca deve continuar comendo 
         if (tabuleiro.getCasa(x+1, y+1) != null && tabuleiro.getCasa(x+2, y+2) != null 
                && tabuleiro.getCasa(x+1, y+1).possuiPeca() && !tabuleiro.getCasa(x+2, y+2).possuiPeca()
                    && podeComer(tabuleiro.getCasa(x, y), tabuleiro.getCasa(x+1, y+1))) {
                return true;
         }
         else if (tabuleiro.getCasa(x-1, y+1) != null && tabuleiro.getCasa(x-2, y+2) != null 
                && tabuleiro.getCasa(x-1, y+1).possuiPeca() && !tabuleiro.getCasa(x-2, y+2).possuiPeca()
                    && podeComer(tabuleiro.getCasa(x, y), tabuleiro.getCasa(x-1, y+1))) {
                return true;
         }
         else if (tabuleiro.getCasa(x+1, y-1) != null && tabuleiro.getCasa(x+2, y-2) != null 
                && tabuleiro.getCasa(x+1, y-1).possuiPeca() && !tabuleiro.getCasa(x+2, y-2).possuiPeca()
                    && podeComer(tabuleiro.getCasa(x, y), tabuleiro.getCasa(x+1, y-1))) {
                return true;
         }
         else if (tabuleiro.getCasa(x-1, y-1) != null && tabuleiro.getCasa(x-2, y-2) != null 
                && tabuleiro.getCasa(x-1, y-1).possuiPeca() && !tabuleiro.getCasa(x-2, y-2).possuiPeca()
                    && podeComer(tabuleiro.getCasa(x, y), tabuleiro.getCasa(x-1, y-1))) {
                return true;
         }
         return false;
    }
    
    /**
     * Verifica se determinado tipo de peca venceu a partida.
     * @param tipo da peca da vez
     * @return valor booleano
     */
    public boolean ganhou(int tipoPeca) {
        if (tipoPeca == 0) {
            for (int x = 0; x < 8; x++) {
                for (int y = 7; y >= 0; y--) {
                    if (tabuleiro.getCasa(x, y).possuiPeca() && tabuleiro.getCasa(x, y).getTipoPeca()%2 == 1) {
                        return false;
                    }
                }
            }
        }
        else if (tipoPeca == 1) {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (tabuleiro.getCasa(x, y).possuiPeca() && tabuleiro.getCasa(x, y).getTipoPeca()%2 == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}