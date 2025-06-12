package backend;

public class Alimentos {
    private int idAlimentos;
    private String descricao;
    private static int proximoId = 1;

    public Alimentos(String descricao) {
        this.idAlimentos = proximoId++;
        this.descricao = descricao;
    }

    // Getters
    public int getIdAlimentos() { return idAlimentos; }
    public String getDescricao() { return descricao; }

    // Setters
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "ID: " + idAlimentos + ", Descrição: " + descricao;
    }
}