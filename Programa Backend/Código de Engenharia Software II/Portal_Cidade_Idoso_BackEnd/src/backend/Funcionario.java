package backend;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Funcionario extends Usuario {
    // Para este exemplo, manteremos as listas de gerenciamento aqui
    // Em um sistema real, isso estaria em classes de serviço/repositórios
    private List<Atividade> atividadesGerenciadas;
    private List<Noticias> noticiasGerenciadas;
    private List<Cardapio> cardapiosGerenciados;
    private List<Mural> muraisGerenciados;


    public Funcionario(String nome, String cpf, String login, String senha, LocalDate dataNascimento, String genero, Endereco endereco) {
        super(nome, cpf, login, senha, dataNascimento, genero, endereco);
        this.atividadesGerenciadas = new ArrayList<>();
        this.noticiasGerenciadas = new ArrayList<>();
        this.cardapiosGerenciados = new ArrayList<>();
        this.muraisGerenciados = new ArrayList<>();
    }

    // Métodos de Funcionario (do diagrama)
    public void cadastrarAtividade(Atividade atividade) {
        this.atividadesGerenciadas.add(atividade);
        System.out.println("Funcionário " + getNome() + " cadastrou a atividade: " + atividade.getNome());
    }

    public void editarAtividade(Atividade atividade, String novoNome, String novaDescricao, int novasVagas) {
        if (atividadesGerenciadas.contains(atividade)) {
            atividade.setNome(novoNome);
            atividade.setDescricao(novaDescricao);
            atividade.setVagasTotais(novasVagas); // Isso precisa ser tratado com cuidado para não exceder inscritos
            atividade.notificarObservers("A atividade '" + atividade.getNome() + "' foi ALTERADA. Novas vagas: " + novasVagas);
            System.out.println("Funcionário " + getNome() + " editou a atividade: " + atividade.getNome());
        } else {
            System.out.println("Atividade não encontrada ou não gerenciada por este funcionário.");
        }
    }

    public void removerAtividade(Atividade atividade) {
        if (atividadesGerenciadas.remove(atividade)) {
            atividade.notificarObservers("A atividade '" + atividade.getNome() + "' foi CANCELADA.");
            System.out.println("Funcionário " + getNome() + " removeu a atividade: " + atividade.getNome());
        } else {
            System.out.println("Atividade não encontrada ou não gerenciada por este funcionário.");
        }
    }

    public void cadastrarNoticia(Mural noticia) {
        this.muraisGerenciados.add(noticia);
        System.out.println("Funcionário " + getNome() + " cadastrou a notícia no mural: " + noticia.getTitulo());
        noticia.notificarObservers("Nova notícia no mural: " + noticia.getTitulo());
    }

    public void editarNoticia(Mural noticia, String novoTitulo, String novoConteudo) {
        if (muraisGerenciados.contains(noticia)) {
            noticia.setTitulo(novoTitulo);
            noticia.setConteudo(novoConteudo);
            noticia.setDataPublicacao(java.time.LocalDateTime.now()); // Atualiza a data de publicação
            noticia.notificarObservers("A notícia '" + noticia.getTitulo() + "' foi ATUALIZADA.");
            System.out.println("Funcionário " + getNome() + " editou a notícia: " + noticia.getTitulo());
        } else {
            System.out.println("Notícia não encontrada ou não gerenciada por este funcionário.");
        }
    }

    public void removerNoticia(Mural noticia) {
        if (muraisGerenciados.remove(noticia)) {
            noticia.notificarObservers("A notícia '" + noticia.getTitulo() + "' foi REMOVIDA.");
            System.out.println("Funcionário " + getNome() + " removeu a notícia: " + noticia.getTitulo());
        } else {
            System.out.println("Notícia não encontrada ou não gerenciada por este funcionário.");
        }
    }

    public void inscreverUsuario(Usuario usuario, Atividade atividade) {
        // A lógica de inscrição é centralizada na classe Inscricao e Atividade
        System.out.println("Funcionário " + getNome() + " tentando inscrever " + usuario.getNome() + " na atividade " + atividade.getNome());
    }

    // Métodos do diagrama que são ações mais gerais e podem ser chamados no Main
    public void acessarCronograma() {
        System.out.println("Funcionário " + getNome() + " acessando o cronograma geral.");
        // Lógica para exibir todas as atividades agendadas
    }

    public void gerarRelatorioCronograma() {
        System.out.println("Funcionário " + getNome() + " gerando relatório do cronograma.");
        // Lógica para gerar um relatório, talvez em PDF ou CSV
    }

    public void acessarTurmas() {
        System.out.println("Funcionário " + getNome() + " acessando informações de turmas.");
        // Lógica para ver detalhes de turmas/atividades e seus inscritos
    }

    public void imprimirListaTurmas() {
        System.out.println("Funcionário " + getNome() + " imprimindo lista de turmas.");
        // Lógica para imprimir as listas
    }

    public void realizarInscricaoManualIdosoAtividade(Idoso idoso, Atividade atividade) {
        System.out.println("Funcionário " + getNome() + " realizando inscrição manual para Idoso " + idoso.getNome() + " na atividade " + atividade.getNome());
        Inscricao inscricao = new Inscricao(idoso, atividade);
        if (inscricao.validarInscricao()) {
            // Adiciona a inscrição nas listas do usuário e da atividade
            idoso.adicionarInscricao(inscricao);
            atividade.adicionarInscricao(inscricao);
            atividade.notificarObservers("Inscrição manual de " + idoso.getNome() + " na atividade '" + atividade.getNome() + "' realizada.");
            System.out.println("Inscrição manual realizada com sucesso!");
        } else {
            System.out.println("Não foi possível realizar a inscrição manual.");
        }
    }

    // Métodos para gerenciar cardápios e notícias (Mural)
    public void criarCardapio(Cardapio cardapio) {
        this.cardapiosGerenciados.add(cardapio);
        System.out.println("Funcionário " + getNome() + " criou um novo cardápio para " + cardapio.getDataHora().toLocalDate());
    }

    public void gerenciarNoticias(Noticias noticia) {
        // Isso é mais um exemplo, a lógica real estaria em cadastrar/editar/remover notícias do Mural
        System.out.println("Funcionário " + getNome() + " gerenciando a notícia: " + noticia.getTitulo());
    }


    @Override
    public String toString() {
        return "FUNCIONÁRIO - " + super.toString();
    }
}