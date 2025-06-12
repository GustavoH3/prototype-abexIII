package backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Mural implements Subject {
    private int idMural;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataPublicacao;
    private List<Observer> observadores; // Observadores do mural
    private List<Noticias> noticiasPublicadas; // Mural contém notícias
    private Funcionario funcionarioGerente; // Funcionário gerencia o mural
    private static int proximoId = 1;

    public Mural(String titulo, String conteudo, Funcionario funcionarioGerente) {
        this.idMural = proximoId++;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dataPublicacao = LocalDateTime.now();
        this.observadores = new ArrayList<>();
        this.noticiasPublicadas = new ArrayList<>();
        this.funcionarioGerente = funcionarioGerente;
    }

    // Getters
    public int getIdMural() { return idMural; }
    public String getTitulo() { return titulo; }
    public String getConteudo() { return conteudo; }
    public LocalDateTime getDataPublicacao() { return dataPublicacao; }
    public Funcionario getFuncionarioGerente() { return funcionarioGerente; }
    public List<Noticias> getNoticiasPublicadas() { return noticiasPublicadas; }

    // Setters (para editar notícia)
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public void setDataPublicacao(LocalDateTime dataPublicacao) { this.dataPublicacao = dataPublicacao; }

    // Métodos de Mural (do diagrama)
    public void cadastrarNoticia(Noticias noticia) {
        this.noticiasPublicadas.add(noticia);
        System.out.println("Notícia '" + noticia.getTitulo() + "' cadastrada no mural " + this.titulo);
        notificarObservers("Nova notícia no mural: " + noticia.getTitulo());
    }

    public void editarNoticia(Noticias noticia, String novoTitulo, String novaFonte, String novaDescricao) {
        if (noticiasPublicadas.contains(noticia)) {
            noticia.setTitulo(novoTitulo);
            noticia.setFonte(novaFonte);
            noticia.setDescricao(novaDescricao);
            noticia.setDataPublicacao(LocalDateTime.now());
            System.out.println("Notícia '" + noticia.getTitulo() + "' editada no mural " + this.titulo);
            notificarObservers("Notícia '" + noticia.getTitulo() + "' no mural foi ATUALIZADA.");
        } else {
            System.out.println("Notícia não encontrada para edição neste mural.");
        }
    }

    public void removerNoticia(Noticias noticia) {
        if (noticiasPublicadas.remove(noticia)) {
            System.out.println("Notícia '" + noticia.getTitulo() + "' removida do mural " + this.titulo);
            notificarObservers("Notícia '" + noticia.getTitulo() + "' no mural foi REMOVIDA.");
        } else {
            System.out.println("Notícia não encontrada para remoção neste mural.");
        }
    }

    // Métodos da interface Subject (para o padrão Observer)
    @Override
    public void adicionarObserver(Observer o) {
        if (o instanceof Usuario) {
            observadores.add(o);
            System.out.println("Observer " + ((Usuario)o).getNome() + " adicionado ao mural " + titulo);
        } else {
            System.out.println("Erro: Apenas Usuários podem ser observadores de Murais.");
        }
    }

    @Override
    public void removerObserver(Observer o) {
        if (observadores.remove(o)) {
            System.out.println("Observer " + ((Usuario)o).getNome() + " removido do mural " + titulo);
        } else {
            System.out.println("Observer não encontrado para o mural " + titulo);
        }
    }

    @Override
    public void notificarObservers(String msg) {
        System.out.println("\nNotificando observadores do mural '" + titulo + "':");
        for (Observer obs : observadores) {
            obs.atualizar("Mural '" + titulo + "': " + msg);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("--- Mural ---\n");
        sb.append("ID: ").append(idMural).append("\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Conteúdo: ").append(conteudo).append("\n");
        sb.append("Publicado em: ").append(dataPublicacao.format(formatter)).append("\n");
        sb.append("Gerenciado por: ").append(funcionarioGerente.getNome()).append("\n");
        sb.append("Notícias Publicadas:\n");
        if (noticiasPublicadas.isEmpty()) {
            sb.append("  Nenhuma notícia publicada diretamente via Mural.\n");
        } else {
            for (Noticias n : noticiasPublicadas) {
                sb.append("  - ").append(n.getTitulo()).append(" (Fonte: ").append(n.getFonte()).append(")\n");
            }
        }
        return sb.toString();
    }
}