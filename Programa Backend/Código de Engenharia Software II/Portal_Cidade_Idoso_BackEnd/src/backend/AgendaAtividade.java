package backend;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AgendaAtividade {
    private int idAgendaAtividade;
    private Atividade atividade; // Agenda possui uma atividade
    private LocalDate data;
    private LocalTime hora;
    private static int proximoId = 1;

    public AgendaAtividade(Atividade atividade, LocalDate data, LocalTime hora) {
        this.idAgendaAtividade = proximoId++;
        this.atividade = atividade;
        this.data = data;
        this.hora = hora;
    }

    // Getters
    public int getIdAgendaAtividade() { return idAgendaAtividade; }
    public Atividade getAtividade() { return atividade; }
    public LocalDate getData() { return data; }
    public LocalTime getHora() { return hora; }

    // Setters
    public void setData(LocalDate data) { this.data = data; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return "ID: " + idAgendaAtividade + ", Atividade: " + atividade.getNome() + ", Data: " + data.format(dateFormatter) + ", Hora: " + hora.format(timeFormatter);
    }
}