package backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Idoso extends Usuario {
    private String observacoes;
    private String restricaoMedica;
    private List<Atividade> atividadesParticipa; // Idoso participa de atividades

    public Idoso(String nome, String cpf, String login, String senha, LocalDate dataNascimento, String genero, Endereco endereco, String observacoes, String restricaoMedica) {
        super(nome, cpf, login, senha, dataNascimento, genero, endereco);
        this.observacoes = observacoes;
        this.restricaoMedica = restricaoMedica;
        this.atividadesParticipa = new ArrayList<>();
    }

    // Getters
    public String getObservacoes() { return observacoes; }
    public String getRestricaoMedica() { return restricaoMedica; }
    public List<Atividade> getAtividadesParticipa() { return atividadesParticipa; }

    // Setters
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public void setRestricaoMedica(String restricaoMedica) { this.restricaoMedica = restricaoMedica; }

    // Método para marcar participação em atividade (pode ser redundante com Inscricao, dependendo do design)
    // Para simplificar, vamos considerar que a relação de participação é via Inscricao
    /*
    public void participarAtividade(Atividade atividade) {
        this.atividadesParticipa.add(atividade);
        System.out.println("Idoso " + getNome() + " agora participa da atividade " + atividade.getNome());
    }
    */

    @Override
    public String toString() {
        return "IDOSO - " + super.toString() + ", Obs: " + (observacoes.isEmpty() ? "N/A" : observacoes) + ", Restrição Médica: " + (restricaoMedica.isEmpty() ? "N/A" : restricaoMedica);
    }
}