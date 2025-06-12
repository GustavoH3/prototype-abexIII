package backend;

import java.time.LocalDateTime;

public class AtividadesIdoso {
    private Idoso idoso;
    private Atividade atividade;
    private LocalDateTime dataRegistro; // Data que a relação foi registrada, pode ser a data de inscrição

    public AtividadesIdoso(Idoso idoso, Atividade atividade) {
        this.idoso = idoso;
        this.atividade = atividade;
        this.dataRegistro = LocalDateTime.now();
    }

    // Getters
    public Idoso getIdoso() { return idoso; }
    public Atividade getAtividade() { return atividade; }
    public LocalDateTime getDataRegistro() { return dataRegistro; }

    @Override
    public String toString() {
        return "Idoso: " + idoso.getNome() + " (" + idoso.getIdUsuario() + ") - Atividade: " + atividade.getNome() + " (" + atividade.getIdAtividade() + ") - Registrado em: " + dataRegistro.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}