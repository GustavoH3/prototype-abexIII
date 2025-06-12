package backend;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Atividade implements Subject { // Implementa Subject
    private int idAtividade;
    private Professor professorResponsavel; // Professor cria atividade
    private String nome;
    private String descricao;
    private LocalDateTime dataHora;
    private int vagasTotais;
    private int vagasDisponiveis;
    private boolean recorrente;
    private String diaSemana; // Se recorrente, qual dia da semana
    private List<Observer> observadores; // Lista de observadores (usuários)
    private List<Inscricao> inscricoes; // Atividade pertence a inscrições
    private List<AgendaAtividade> agendaAtividades; // Atividade possui agendas
    private static int proximoId = 1;

    public Atividade(Professor professorResponsavel, String nome, String descricao, LocalDateTime dataHora, int vagasTotais, boolean recorrente, String diaSemana) {
        this.idAtividade = proximoId++;
        this.professorResponsavel = professorResponsavel;
        this.nome = nome;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.vagasTotais = vagasTotais;
        this.vagasDisponiveis = vagasTotais; // No início, vagas disponíveis = vagas totais
        this.recorrente = recorrente;
        this.diaSemana = diaSemana;
        this.observadores = new ArrayList<>();
        this.inscricoes = new ArrayList<>();
        this.agendaAtividades = new ArrayList<>();
    }

    // Getters
    public int getIdAtividade() { return idAtividade; }
    public Professor getProfessorResponsavel() { return professorResponsavel; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getDataHora() { return dataHora; }
    public int getVagasTotais() { return vagasTotais; }
    public int getVagasDisponiveis() { return vagasDisponiveis; }
    public boolean isRecorrente() { return recorrente; }
    public String getDiaSemana() { return diaSemana; }
    public List<Inscricao> getInscricoes() { return inscricoes; }
    public List<AgendaAtividade> getAgendaAtividades() { return agendaAtividades; }

    // Setters (para editar atividade)
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public void setVagasTotais(int vagasTotais) {
        int diferenca = vagasTotais - this.vagasTotais;
        this.vagasTotais = vagasTotais;
        this.vagasDisponiveis += diferenca; // Ajusta vagas disponíveis
    }
    public void setRecorrente(boolean recorrente) { this.recorrente = recorrente; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }

    // Métodos de Atividade (do diagrama)
    public boolean verificarDisponibilidade() {
        return vagasDisponiveis > 0;
    }

    public void inscreverUsuario(Usuario usuario) {
        if (verificarDisponibilidade()) {
            this.vagasDisponiveis--;
            System.out.println("Vagas disponíveis para " + nome + ": " + vagasDisponiveis);
            // A lógica de adicionar a inscrição ao usuário e à atividade é feita na classe Inscricao
            // e gerenciada pelo Funcionario para inscrições manuais.
        } else {
            System.out.println("Não há vagas disponíveis para a atividade " + nome + ".");
        }
    }

    public void cancelarInscricao(Usuario usuario) {
        // A lógica de cancelamento mais complexa está na classe Inscricao.
        // Se a inscrição for cancelada, as vagas disponíveis aumentam.
        this.vagasDisponiveis++;
        System.out.println("Vagas disponíveis para " + nome + ": " + vagasDisponiveis);
    }

    public void editarAtividade() {
        System.out.println("Atividade " + nome + " editada.");
        // Lógica para permitir a edição de atributos da atividade
    }

    // Métodos da interface Subject (para o padrão Observer)
    @Override
    public void adicionarObserver(Observer o) {
        if (o instanceof Usuario) { // Apenas usuários podem ser observadores de atividades
            observadores.add(o);
            System.out.println("Observer " + ((Usuario)o).getNome() + " adicionado à atividade " + nome);
        } else {
            System.out.println("Erro: Apenas Usuários podem ser observadores de Atividades.");
        }
    }

    @Override
    public void removerObserver(Observer o) {
        if (observadores.remove(o)) {
            System.out.println("Observer " + ((Usuario)o).getNome() + " removido da atividade " + nome);
        } else {
            System.out.println("Observer não encontrado para a atividade " + nome);
        }
    }

    @Override
    public void notificarObservers(String msg) {
        System.out.println("\nNotificando observadores da atividade '" + nome + "':");
        for (Observer obs : observadores) {
            obs.atualizar("Atividade '" + nome + "': " + msg);
        }
    }

    // Métodos para gerenciar inscrições e agenda
    public void adicionarInscricao(Inscricao inscricao) {
        this.inscricoes.add(inscricao);
    }

    public void removerInscricao(Inscricao inscricao) {
        this.inscricoes.remove(inscricao);
    }

    public void adicionarAgenda(AgendaAtividade agenda) {
        this.agendaAtividades.add(agenda);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataHoraStr = (dataHora != null) ? dataHora.format(formatter) : "N/A";
        String professorNome = (professorResponsavel != null) ? professorResponsavel.getNome() : "N/A";
        return "ID: " + idAtividade + ", Nome: " + nome + ", Professor: " + professorNome + ", Data/Hora: " + dataHoraStr +
               ", Vagas: " + vagasDisponiveis + "/" + vagasTotais +
               (recorrente ? ", Recorrente: Sim (" + diaSemana + ")" : ", Recorrente: Não");
    }
}