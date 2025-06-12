package backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Inscricao {
    private int idInscricao;
    private Usuario usuario; // Inscrição pertence a um usuário
    private Atividade atividade; // Inscrição pertence a uma atividade
    private LocalDateTime dataInscricao;
    private static int proximoId = 1;

    public Inscricao(Usuario usuario, Atividade atividade) {
        this.idInscricao = proximoId++;
        this.usuario = usuario;
        this.atividade = atividade;
        this.dataInscricao = LocalDateTime.now();
    }

    // Getters
    public int getIdInscricao() { return idInscricao; }
    public Usuario getUsuario() { return usuario; }
    public Atividade getAtividade() { return atividade; }
    public LocalDateTime getDataInscricao() { return dataInscricao; }

    // Métodos de Inscrição (do diagrama)
    public boolean validarInscricao() {
        if (atividade.verificarDisponibilidade()) {
            atividade.inscreverUsuario(usuario); // Reduz vagas na atividade
            System.out.println("Inscrição de " + usuario.getNome() + " na atividade '" + atividade.getNome() + "' validada e realizada.");
            return true;
        } else {
            System.out.println("Inscrição para a atividade '" + atividade.getNome() + "' não pode ser validada. Vagas esgotadas.");
            return false;
        }
    }

    public void cancelar() {
        // Remove a inscrição do usuário e da atividade
        usuario.removerInscricao(this);
        atividade.cancelarInscricao(usuario); // Aumenta vagas na atividade
        System.out.println("Inscrição de " + usuario.getNome() + " na atividade '" + atividade.getNome() + "' cancelada.");
        atividade.notificarObservers("Inscrição de " + usuario.getNome() + " cancelada na atividade '" + atividade.getNome() + "'. Vagas disponíveis: " + atividade.getVagasDisponiveis());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "ID: " + idInscricao + ", Usuário: " + usuario.getNome() + ", Atividade: " + atividade.getNome() + ", Data Inscrição: " + dataInscricao.format(formatter);
    }
}