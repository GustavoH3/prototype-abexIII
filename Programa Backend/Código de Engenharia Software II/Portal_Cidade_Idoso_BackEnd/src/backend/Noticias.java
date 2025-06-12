package backend;

import java.time.LocalDateTime;

public class Noticias { // Representa uma notícia, mas o Mural é quem gerencia e publica
    private int idNoticias;
    private Funcionario funcionarioPublicador; // Funcionário publica notícia
    private String titulo;
    private String fonte;
    private String descricao;
    private LocalDateTime dataPublicacao; // Adicionado para rastrear a publicação
    private static int proximoId = 1;

    public Noticias(Funcionario funcionarioPublicador, String titulo, String fonte, String descricao) {
        this.idNoticias = proximoId++;
        this.funcionarioPublicador = funcionarioPublicador;
        this.titulo = titulo;
        this.fonte = fonte;
        this.descricao = descricao;
        this.dataPublicacao = LocalDateTime.now();
    }

    // Getters
    public int getIdNoticias() { return idNoticias; }
    public Funcionario getFuncionarioPublicador() { return funcionarioPublicador; }
    public String getTitulo() { return titulo; }
    public String getFonte() { return fonte; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getDataPublicacao() { return dataPublicacao; }

    // Setters
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setFonte(String fonte) { this.fonte = fonte; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setDataPublicacao(LocalDateTime dataPublicacao) { this.dataPublicacao = dataPublicacao; }

    @Override
    public String toString() {
        return "ID: " + idNoticias + ", Título: " + titulo + ", Fonte: " + fonte + ", Publicado por: " + funcionarioPublicador.getNome() + ", Data: " + dataPublicacao.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}