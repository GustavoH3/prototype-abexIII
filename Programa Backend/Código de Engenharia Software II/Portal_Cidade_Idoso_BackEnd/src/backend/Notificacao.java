package backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notificacao {
    private int idNotificacao;
    private Usuario usuario; // Notificação pertence a um usuário
    private String mensagem;
    private TipoNotificacao tipo; // Enum para tipos de notificação
    private LocalDateTime dataEnvio;
    private static int proximoId = 1;

    public enum TipoNotificacao {
        LEMBRETE,
        ALTERACAO,
        GERAL
    }

    public Notificacao(Usuario usuario, String mensagem, TipoNotificacao tipo, Mural muralReferencia) { // Mural opcional
        this.idNotificacao = proximoId++;
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.dataEnvio = LocalDateTime.now();
        // Em um sistema real, você poderia ter um link para o mural ou atividade
    }

    // Getters
    public int getIdNotificacao() { return idNotificacao; }
    public Usuario getUsuario() { return usuario; }
    public String getMensagem() { return mensagem; }
    public TipoNotificacao getTipo() { return tipo; }
    public LocalDateTime getDataEnvio() { return dataEnvio; }

    // Setter (se precisar alterar a mensagem ou tipo antes de enviar)
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public void setTipo(TipoNotificacao tipo) { this.tipo = tipo; }

    // Método de Notificação (do diagrama)
    public void enviar() {
        System.out.println("Enviando Notificação " + tipo + " para " + usuario.getNome() + ": " + mensagem);
        // Em um sistema real, aqui estaria a lógica para enviar por email, push, etc.
        usuario.receberNotificacao(this); // Adiciona a notificação à lista do usuário
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "ID: " + idNotificacao + ", Para: " + usuario.getNome() + ", Tipo: " + tipo + ", Mensagem: '" + mensagem + "', Enviado em: " + dataEnvio.format(formatter);
    }
}