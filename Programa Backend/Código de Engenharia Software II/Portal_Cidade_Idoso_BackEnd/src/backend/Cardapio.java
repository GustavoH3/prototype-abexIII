package backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    private int idCardapio;
    private Funcionario funcionarioCriador; // Funcionário cria cardápio
    private String cardapioTexto; // Conteúdo do cardápio em texto (simplificado)
    private LocalDateTime dataHora;
    private List<AlimentosCardapio> alimentosNoCardapio; // Cardapio contém AlimentosCardapio
    private static int proximoId = 1;

    public Cardapio(Funcionario funcionarioCriador, String cardapioTexto) {
        this.idCardapio = proximoId++;
        this.funcionarioCriador = funcionarioCriador;
        this.cardapioTexto = cardapioTexto;
        this.dataHora = LocalDateTime.now();
        this.alimentosNoCardapio = new ArrayList<>();
    }

    // Getters
    public int getIdCardapio() { return idCardapio; }
    public Funcionario getFuncionarioCriador() { return funcionarioCriador; }
    public String getCardapioTexto() { return cardapioTexto; }
    public LocalDateTime getDataHora() { return dataHora; }
    public List<AlimentosCardapio> getAlimentosNoCardapio() { return alimentosNoCardapio; }

    // Setters
    public void setCardapioTexto(String cardapioTexto) { this.cardapioTexto = cardapioTexto; }

    // Métodos para gerenciar alimentos no cardápio
    public void adicionarAlimentoAoCardapio(Alimentos alimento, int quantidade) {
        AlimentosCardapio item = new AlimentosCardapio(this, alimento, quantidade);
        this.alimentosNoCardapio.add(item);
        System.out.println("Alimento '" + alimento.getDescricao() + "' adicionado ao cardápio " + idCardapio);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("--- Cardápio ---\n");
        sb.append("ID: ").append(idCardapio).append("\n");
        sb.append("Criado por: ").append(funcionarioCriador.getNome()).append(" em ").append(dataHora.format(formatter)).append("\n");
        sb.append("Conteúdo: \n").append(cardapioTexto).append("\n");
        sb.append("Alimentos Detalhados:\n");
        if (alimentosNoCardapio.isEmpty()) {
            sb.append("  Nenhum alimento detalhado.\n");
        } else {
            for (AlimentosCardapio ac : alimentosNoCardapio) {
                sb.append("  - ").append(ac.getAlimento().getDescricao()).append(" (quantidade: ").append(ac.getQuantidade()).append(")\n");
            }
        }
        return sb.toString();
    }
}