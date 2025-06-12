package backend;

public class Contato {
    private int idContato;
    private String tipo; // Ex: Telefone, Email, WhatsApp
    private String descricao; // O número ou endereço
    private static int proximoId = 1;

    public Contato(String tipo, String descricao) {
        this.idContato = proximoId++;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    // Getters
    public int getIdContato() { return idContato; }
    public String getTipo() { return tipo; }
    public String getDescricao() { return descricao; }

    // Setters
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "ID: " + idContato + ", Tipo: " + tipo + ", Descrição: " + descricao;
    }
}