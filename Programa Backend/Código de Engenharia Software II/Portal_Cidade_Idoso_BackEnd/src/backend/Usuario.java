package backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario implements Observer { // Implementa Observer
    private int idUsuario;
    private String nome;
    private String cpf;
    private String login;
    private String senha;
    private LocalDate dataNascimento;
    private String genero;
    private Endereco endereco; // Usuario reside em um endereço
    private List<Contato> contatos; // Usuario tem contatos
    private List<Notificacao> notificacoes; // Usuario recebe notificações
    private List<Inscricao> inscricoes; // Usuario faz inscrições
    private static int proximoId = 1;

    public Usuario(String nome, String cpf, String login, String senha, LocalDate dataNascimento, String genero, Endereco endereco) {
        this.idUsuario = proximoId++;
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha; // Em um sistema real, a senha deveria ser hasheada
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.endereco = endereco;
        this.contatos = new ArrayList<>();
        this.notificacoes = new ArrayList<>();
        this.inscricoes = new ArrayList<>();
    }

    // Getters
    public int getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getGenero() { return genero; }
    public Endereco getEndereco() { return endereco; }
    public List<Contato> getContatos() { return contatos; }
    public List<Notificacao> getNotificacoes() { return notificacoes; }
    public List<Inscricao> getInscricoes() { return inscricoes; }

    // Setters (para editar perfil)
    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setLogin(String login) { this.login = login; }
    public void setSenha(String senha) { this.senha = senha; } // Em um sistema real, a senha deveria ser hasheada
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    // Métodos de Usuario (do diagrama)
    public boolean fazerLogin(String login, String senha) {
        if (this.login.equals(login) && this.senha.equals(senha)) {
            System.out.println("Login bem-sucedido para " + this.nome);
            return true;
        }
        System.out.println("Login falhou para " + this.nome + ". Credenciais inválidas.");
        return false;
    }

    public void recuperarSenha() {
        System.out.println("Instruções para recuperar a senha foram enviadas para o email/telefone de " + this.nome);
    }

    public void editarPerfil() {
        System.out.println("Perfil de " + this.nome + " editado com sucesso.");
        // Em um sistema real, aqui haveria a lógica para pedir novos dados e atualizar
    }

    public void habilitarAcessibilidade() {
        System.out.println("Recursos de acessibilidade habilitados para " + this.nome + ".");
    }

    public void acessarCronogramaAtividades() {
        System.out.println("Acessando cronograma de atividades para " + this.nome + ".");
        // Lógica para exibir o cronograma
    }

    public void acessarMuralInformacoes() {
        System.out.println("Acessando mural de informações para " + this.nome + ".");
        // Lógica para exibir o mural
    }

    public void acessarInformacoesInstituicao() {
        System.out.println("Acessando informações da instituição para " + this.nome + ".");
        // Lógica para exibir informações da instituição
    }

    public void selecionarAtividade() {
        System.out.println("Selecionando atividade...");
        // Lógica para permitir ao usuário escolher uma atividade
    }

    public void inscreverAtividade(Atividade atividade) {
        // A lógica de inscrição mais complexa está na classe Inscricao e Atividade
        System.out.println("Tentando inscrever " + this.nome + " na atividade " + atividade.getNome() + ".");
    }

    public void acessarAtividadesInscritas() {
        System.out.println("Atividades inscritas por " + this.nome + ":");
        if (inscricoes.isEmpty()) {
            System.out.println("  Nenhuma atividade inscrita.");
        } else {
            for (Inscricao i : inscricoes) {
                System.out.println("  - " + i.getAtividade().getNome() + " (ID: " + i.getAtividade().getIdAtividade() + ") - Data Inscrição: " + i.getDataInscricao().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
        }
    }

    public void cancelarInscricao(Atividade atividade) {
        // A lógica de cancelamento mais complexa está na classe Inscricao e Atividade
        System.out.println("Tentando cancelar inscrição de " + this.nome + " na atividade " + atividade.getNome() + ".");
    }

    // Métodos para gerenciar contatos e notificações
    public void adicionarContato(Contato contato) {
        this.contatos.add(contato);
        System.out.println("Contato adicionado para " + nome);
    }

    public void receberNotificacao(Notificacao notificacao) {
        this.notificacoes.add(notificacao);
        System.out.println(nome + " recebeu nova notificação: " + notificacao.getMensagem());
    }

    public void adicionarInscricao(Inscricao inscricao) {
        this.inscricoes.add(inscricao);
    }

    public void removerInscricao(Inscricao inscricao) {
        this.inscricoes.remove(inscricao);
    }

    // Método da interface Observer
    @Override
    public void atualizar(String msg) {
        System.out.println("NOTIFICAÇÃO PARA " + this.nome.toUpperCase() + ": " + msg);
        // Em um sistema real, essa notificação poderia ser persistida ou enviada via push.
        this.notificacoes.add(new Notificacao(this, msg, Notificacao.TipoNotificacao.ALTERACAO, null));
    }

    @Override
    public String toString() {
        return "ID: " + idUsuario + ", Nome: " + nome + ", CPF: " + cpf + ", Login: " + login + ", Gênero: " + genero + ", Data Nasc.: " + dataNascimento + ", Endereço: [" + endereco.toString() + "]";
    }
}