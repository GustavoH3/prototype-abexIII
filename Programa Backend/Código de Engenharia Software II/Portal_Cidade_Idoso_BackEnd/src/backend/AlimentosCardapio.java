package backend;

public class AlimentosCardapio {
    private Cardapio cardapio;
    private Alimentos alimento;
    private int quantidade; // Ex: 100g, 1 unidade, etc.

    public AlimentosCardapio(Cardapio cardapio, Alimentos alimento, int quantidade) {
        this.cardapio = cardapio;
        this.alimento = alimento;
        this.quantidade = quantidade;
    }

    // Getters
    public Cardapio getCardapio() { return cardapio; }
    public Alimentos getAlimento() { return alimento; }
    public int getQuantidade() { return quantidade; }

    // Setter
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return "Cardápio ID: " + cardapio.getIdCardapio() + ", Alimento: " + alimento.getDescricao() + ", Quantidade: " + quantidade;
    }
}