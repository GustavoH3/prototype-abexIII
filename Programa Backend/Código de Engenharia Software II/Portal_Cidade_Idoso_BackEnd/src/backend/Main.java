package backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Atividade> atividades = new ArrayList<>();
    private static List<Mural> murais = new ArrayList<>();
    private static List<Instituicao> instituicoes = new ArrayList<>();
    private static List<Alimentos> alimentos = new ArrayList<>();
    private static List<Cardapio> cardapios = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Sistema SeniorCare!");

        // --- Dados Iniciais para Teste ---
        Endereco end1 = new Endereco("Chapecó", 123, "Rua A", "89801-000", "Apto 101", "Centro");
        Endereco end2 = new Endereco("Chapecó", 456, "Rua B", "89802-000", "", "Efapi");
        Endereco end3 = new Endereco("Chapecó", 789, "Rua C", "89803-000", "Sala 1", "Jardim Italia");

        // Criando usuários com Factory Method
        UsuarioFactory professorFactory = new ProfessorFactory();
        Professor prof1 = (Professor) professorFactory.criarUsuario("Carlos Silva", "111.111.111-11", "carlos.prof", "123", LocalDate.of(1980, 5, 15), "Masculino", end1);
        usuarios.add(prof1);

        UsuarioFactory funcionarioFactory = new FuncionarioFactory();
        Funcionario func1 = (Funcionario) funcionarioFactory.criarUsuario("Ana Paula", "222.222.222-22", "ana.func", "123", LocalDate.of(1990, 8, 20), "Feminino", end2);
        usuarios.add(func1);

        IdosoFactory idosoFactory = new IdosoFactory();
        Idoso idoso1 = idosoFactory.criarIdosoComDetalhes("Maria Santos", "333.333.333-33", "maria.idosa", "123", LocalDate.of(1945, 1, 10), "Feminino", end3, "Não gosta de barulho", "Diabetes");
        usuarios.add(idoso1);
        Idoso idoso2 = idosoFactory.criarIdosoComDetalhes("José Pereira", "444.444.444-44", "jose.idoso", "123", LocalDate.of(1950, 11, 25), "Masculino", end1, "", "Hipertensão");
        usuarios.add(idoso2);

        // Criando uma instituição
        Instituicao inst1 = new Instituicao("Prefeitura de Chapecó", end1);
        instituicoes.add(inst1);

        // Adicionando alguns alimentos
        alimentos.add(new Alimentos("Arroz"));
        alimentos.add(new Alimentos("Feijão"));
        alimentos.add(new Alimentos("Frango Grelhado"));
        alimentos.add(new Alimentos("Salada Mista"));

        // Criando um mural de exemplo
        Mural muralGeral = new Mural("Mural de Avisos Gerais", "Bem-vindos ao centro de convivência!", func1);
        murais.add(muralGeral);

        // Adicionando observadores ao mural
        muralGeral.adicionarObserver(idoso1);
        muralGeral.adicionarObserver(idoso2);
        muralGeral.adicionarObserver(prof1); // Professores também podem querer ver avisos

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    gerenciarUsuarios();
                    break;
                case 2:
                    gerenciarAtividades();
                    break;
                case 3:
                    gerenciarMurais();
                    break;
                case 4:
                    gerenciarCardapios();
                    break;
                case 5:
                    exibirInformacoesGerais();
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine(); // Consome o Enter restante
            scanner.nextLine(); // Espera por um novo Enter
        } while (opcao != 0);

        scanner.close();
    }

    // --- Métodos de Menu ---

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Gerenciar Usuários");
        System.out.println("2. Gerenciar Atividades");
        System.out.println("3. Gerenciar Murais de Notícias");
        System.out.println("4. Gerenciar Cardápios");
        System.out.println("5. Exibir Informações Gerais");
        System.out.println("0. Sair");
    }

    private static void gerenciarUsuarios() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR USUÁRIOS ---");
            System.out.println("1. Cadastrar Novo Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Editar Usuário (Perfil)");
            System.out.println("4. Fazer Login");
            System.out.println("5. Acessar Atividades Inscritas (para Idoso/Professor)");
            System.out.println("0. Voltar ao Menu Principal");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarNovoUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    editarUsuario();
                    break;
                case 4:
                    simularLogin();
                    break;
                case 5:
                    acessarAtividadesInscritas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void gerenciarAtividades() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR ATIVIDADES ---");
            System.out.println("1. Cadastrar Atividade");
            System.out.println("2. Listar Atividades");
            System.out.println("3. Editar Atividade");
            System.out.println("4. Remover Atividade");
            System.out.println("5. Inscrever Usuário em Atividade (Manual por Funcionário)");
            System.out.println("6. Observar Atividade (Usuário se inscreve para receber notificações)");
            System.out.println("0. Voltar ao Menu Principal");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAtividade();
                    break;
                case 2:
                    listarAtividades();
                    break;
                case 3:
                    editarAtividade();
                    break;
                case 4:
                    removerAtividade();
                    break;
                case 5:
                    inscreverUsuarioEmAtividade();
                    break;
                case 6:
                    adicionarObservadorAtividade();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void gerenciarMurais() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR MURAIS ---");
            System.out.println("1. Criar Novo Mural");
            System.out.println("2. Listar Murais");
            System.out.println("3. Publicar Notícia no Mural");
            System.out.println("4. Editar Notícia no Mural");
            System.out.println("5. Remover Notícia do Mural");
            System.out.println("6. Observar Mural (Usuário se inscreve para receber notificações)");
            System.out.println("0. Voltar ao Menu Principal");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    criarMural();
                    break;
                case 2:
                    listarMurais();
                    break;
                case 3:
                    publicarNoticiaNoMural();
                    break;
                case 4:
                    editarNoticiaNoMural();
                    break;
                case 5:
                    removerNoticiaDoMural();
                    break;
                case 6:
                    adicionarObservadorMural();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void gerenciarCardapios() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR CARDÁPIOS ---");
            System.out.println("1. Cadastrar Alimento");
            System.out.println("2. Listar Alimentos");
            System.out.println("3. Criar Novo Cardápio");
            System.out.println("4. Adicionar Alimento a Cardápio");
            System.out.println("5. Listar Cardápios");
            System.out.println("0. Voltar ao Menu Principal");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAlimento();
                    break;
                case 2:
                    listarAlimentos();
                    break;
                case 3:
                    criarNovoCardapio();
                    break;
                case 4:
                    adicionarAlimentoACardapio();
                    break;
                case 5:
                    listarCardapios();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void exibirInformacoesGerais() {
        System.out.println("\n--- INFORMAÇÕES GERAIS ---");
        System.out.println("Instituições Cadastradas:");
        if (instituicoes.isEmpty()) {
            System.out.println("  Nenhuma instituição cadastrada.");
        } else {
            instituicoes.forEach(System.out::println);
        }

        System.out.println("\nNotificações dos Usuários (Exemplo de Observer em ação):");
        for (Usuario u : usuarios) {
            System.out.println("  - Notificações de " + u.getNome() + ":");
            if (u.getNotificacoes().isEmpty()) {
                System.out.println("    Nenhuma notificação.");
            } else {
                u.getNotificacoes().forEach(n -> System.out.println("    > " + n.getMensagem()));
            }
        }
    }


    // --- Métodos de Implementação das Operações ---

    private static void cadastrarNovoUsuario() {
        System.out.println("\n--- CADASTRO DE NOVO USUÁRIO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        LocalDate dataNascimento = lerData("Data de Nascimento (AAAA-MM-DD): ");
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        System.out.println("\n--- Endereço ---");
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        int numero = lerInteiro("Número: ");
        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("CEP: ");
        String cep = scanner.nextLine();
        System.out.print("Complemento (opcional): ");
        String complemento = scanner.nextLine();
        Endereco endereco = new Endereco(cidade, numero, rua, cep, complemento, bairro);

        System.out.println("\nTipo de Usuário:");
        System.out.println("1. Idoso");
        System.out.println("2. Professor");
        System.out.println("3. Funcionário");
        int tipo = lerInteiro("Escolha o tipo de usuário: ");

        Usuario novoUsuario = null;
        switch (tipo) {
            case 1:
                System.out.print("Observações (opcional): ");
                String obs = scanner.nextLine();
                System.out.print("Restrições Médicas (opcional): ");
                String restricao = scanner.nextLine();
                IdosoFactory idosoFactory = new IdosoFactory();
                novoUsuario = idosoFactory.criarIdosoComDetalhes(nome, cpf, login, senha, dataNascimento, genero, endereco, obs, restricao);
                break;
            case 2:
                ProfessorFactory professorFactory = new ProfessorFactory();
                novoUsuario = professorFactory.criarUsuario(nome, cpf, login, senha, dataNascimento, genero, endereco);
                break;
            case 3:
                FuncionarioFactory funcionarioFactory = new FuncionarioFactory();
                novoUsuario = funcionarioFactory.criarUsuario(nome, cpf, login, senha, dataNascimento, genero, endereco);
                break;
            default:
                System.out.println("Tipo de usuário inválido.");
                return;
        }

        usuarios.add(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso: " + novoUsuario.getNome() + " (ID: " + novoUsuario.getIdUsuario() + ")");
    }

    private static void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    private static void editarUsuario() {
        listarUsuarios();
        if (usuarios.isEmpty()) return;

        int idUsuario = lerInteiro("Digite o ID do usuário que deseja editar: ");
        Usuario usuarioParaEditar = encontrarUsuarioPorId(idUsuario);

        if (usuarioParaEditar == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.println("Editando perfil de: " + usuarioParaEditar.getNome());
        System.out.print("Novo Nome (" + usuarioParaEditar.getNome() + "): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isEmpty()) usuarioParaEditar.setNome(novoNome);

        System.out.print("Novo Email (" + usuarioParaEditar.getLogin() + "): "); // Usando login como email de exemplo
        String novoEmail = scanner.nextLine();
        if (!novoEmail.isEmpty()) usuarioParaEditar.setLogin(novoEmail); // Atualizando login como email

        // Adicione mais campos para edição se desejar
        usuarioParaEditar.editarPerfil(); // Chama o método genérico de edição
        System.out.println("Perfil atualizado com sucesso!");
    }

    private static void simularLogin() {
        System.out.println("\n--- SIMULAR LOGIN ---");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuarioLogado = null;
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                usuarioLogado = u;
                break;
            }
        }

        if (usuarioLogado != null) {
            usuarioLogado.fazerLogin(login, senha);
        } else {
            System.out.println("Login ou senha incorretos.");
        }
    }

    private static void acessarAtividadesInscritas() {
        listarUsuarios();
        if (usuarios.isEmpty()) return;

        int idUsuario = lerInteiro("Digite o ID do usuário para ver atividades inscritas: ");
        Usuario usuario = encontrarUsuarioPorId(idUsuario);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        usuario.acessarAtividadesInscritas();
    }


    private static void cadastrarAtividade() {
        System.out.println("\n--- CADASTRAR ATIVIDADE ---");
        listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum professor cadastrado para criar atividades.");
            return;
        }

        Professor professor = null;
        while (professor == null) {
            int idProfessor = lerInteiro("Digite o ID do Professor responsável: ");
            Usuario u = encontrarUsuarioPorId(idProfessor);
            if (u instanceof Professor) {
                professor = (Professor) u;
            } else {
                System.out.println("ID inválido ou não pertence a um Professor. Tente novamente.");
            }
        }

        System.out.print("Nome da Atividade: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        LocalDateTime dataHora = lerDataHora("Data e Hora da Atividade (AAAA-MM-DD HH:MM): ");
        int vagas = lerInteiro("Número de Vagas Totais: ");
        System.out.print("É recorrente? (sim/não): ");
        boolean recorrente = scanner.nextLine().equalsIgnoreCase("sim");
        String diaSemana = "";
        if (recorrente) {
            System.out.print("Dia da semana (ex: Segunda, Terça): ");
            diaSemana = scanner.nextLine();
        }

        Atividade novaAtividade = new Atividade(professor, nome, descricao, dataHora, vagas, recorrente, diaSemana);
        atividades.add(novaAtividade);
        // O funcionário que está logado (ou simulado) deve registrar a atividade
        // Para este exemplo, apenas adicionamos à lista global.
        System.out.println("Atividade '" + nome + "' cadastrada com sucesso! (ID: " + novaAtividade.getIdAtividade() + ")");
    }

    private static void listarAtividades() {
        System.out.println("\n--- LISTA DE ATIVIDADES ---");
        if (atividades.isEmpty()) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }
        for (Atividade a : atividades) {
            System.out.println(a);
        }
    }

    private static void editarAtividade() {
        listarAtividades();
        if (atividades.isEmpty()) return;

        int idAtividade = lerInteiro("Digite o ID da atividade que deseja editar: ");
        Atividade atividadeParaEditar = encontrarAtividadePorId(idAtividade);

        if (atividadeParaEditar == null) {
            System.out.println("Atividade não encontrada.");
            return;
        }

        System.out.println("Editando atividade: " + atividadeParaEditar.getNome());
        System.out.print("Novo Nome (" + atividadeParaEditar.getNome() + "): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isEmpty()) atividadeParaEditar.setNome(novoNome);

        System.out.print("Nova Descrição (" + atividadeParaEditar.getDescricao() + "): ");
        String novaDescricao = scanner.nextLine();
        if (!novaDescricao.isEmpty()) atividadeParaEditar.setDescricao(novaDescricao);

        int novasVagas = lerInteiro("Novas Vagas Totais (" + atividadeParaEditar.getVagasTotais() + ", -1 para manter): ");
        if (novasVagas != -1) atividadeParaEditar.setVagasTotais(novasVagas);

        // Notificar observadores da atividade sobre a mudança
        atividadeParaEditar.notificarObservers("A atividade foi editada!");
        System.out.println("Atividade atualizada com sucesso!");
    }

    private static void removerAtividade() {
        listarAtividades();
        if (atividades.isEmpty()) return;

        int idAtividade = lerInteiro("Digite o ID da atividade que deseja remover: ");
        Atividade atividadeParaRemover = encontrarAtividadePorId(idAtividade);

        if (atividadeParaRemover == null) {
            System.out.println("Atividade não encontrada.");
            return;
        }

        // Notificar observadores antes de remover
        atividadeParaRemover.notificarObservers("A atividade foi REMOVIDA!");
        atividades.remove(atividadeParaRemover);
        System.out.println("Atividade '" + atividadeParaRemover.getNome() + "' removida com sucesso!");
    }

    private static void inscreverUsuarioEmAtividade() {
        System.out.println("\n--- INSCREVER USUÁRIO EM ATIVIDADE (MANUAL) ---");
        listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        int idUsuario = lerInteiro("Digite o ID do Usuário para inscrever: ");
        Usuario usuario = encontrarUsuarioPorId(idUsuario);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        if (!(usuario instanceof Idoso)) {
            System.out.println("Apenas Idosos podem ser inscritos em atividades neste fluxo.");
            return;
        }

        listarAtividades();
        if (atividades.isEmpty()) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }

        int idAtividade = lerInteiro("Digite o ID da Atividade para inscrição: ");
        Atividade atividade = encontrarAtividadePorId(idAtividade);

        if (atividade == null) {
            System.out.println("Atividade não encontrada.");
            return;
        }

        // Simular que um funcionário faz a inscrição
        // Para um cenário mais robusto, teríamos que ter um funcionário logado
        // Pegaremos o primeiro funcionário da lista para simular
        Funcionario funcionario = null;
        for (Usuario u : usuarios) {
            if (u instanceof Funcionario) {
                funcionario = (Funcionario) u;
                break;
            }
        }
        if (funcionario == null) {
            System.out.println("Nenhum funcionário encontrado para realizar a inscrição manual.");
            return;
        }

        funcionario.realizarInscricaoManualIdosoAtividade((Idoso) usuario, atividade);
    }

    private static void adicionarObservadorAtividade() {
        System.out.println("\n--- ADICIONAR OBSERVADOR A ATIVIDADE ---");
        listarAtividades();
        if (atividades.isEmpty()) return;

        int idAtividade = lerInteiro("Digite o ID da atividade para adicionar observador: ");
        Atividade atividade = encontrarAtividadePorId(idAtividade);

        if (atividade == null) {
            System.out.println("Atividade não encontrada.");
            return;
        }

        listarUsuarios();
        if (usuarios.isEmpty()) return;

        int idUsuario = lerInteiro("Digite o ID do Usuário (Observer) para adicionar: ");
        Usuario usuario = encontrarUsuarioPorId(idUsuario);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        atividade.adicionarObserver(usuario);
    }


    private static void criarMural() {
        System.out.println("\n--- CRIAR NOVO MURAL ---");
        listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado para criar murais.");
            return;
        }

        Funcionario funcionario = null;
        while (funcionario == null) {
            int idFuncionario = lerInteiro("Digite o ID do Funcionário responsável pelo mural: ");
            Usuario u = encontrarUsuarioPorId(idFuncionario);
            if (u instanceof Funcionario) {
                funcionario = (Funcionario) u;
            } else {
                System.out.println("ID inválido ou não pertence a um Funcionário. Tente novamente.");
            }
        }

        System.out.print("Título do Mural: ");
        String titulo = scanner.nextLine();
        System.out.print("Conteúdo Inicial do Mural: ");
        String conteudo = scanner.nextLine();

        Mural novoMural = new Mural(titulo, conteudo, funcionario);
        murais.add(novoMural);
        System.out.println("Mural '" + titulo + "' criado com sucesso! (ID: " + novoMural.getIdMural() + ")");
    }

    private static void listarMurais() {
        System.out.println("\n--- LISTA DE MURAIS ---");
        if (murais.isEmpty()) {
            System.out.println("Nenhum mural cadastrado.");
            return;
        }
        for (Mural m : murais) {
            System.out.println(m);
        }
    }

    private static void publicarNoticiaNoMural() {
        listarMurais();
        if (murais.isEmpty()) return;

        int idMural = lerInteiro("Digite o ID do Mural para publicar a notícia: ");
        Mural mural = encontrarMuralPorId(idMural);

        if (mural == null) {
            System.out.println("Mural não encontrado.");
            return;
        }

        // Simular que o funcionário do mural está publicando
        Funcionario funcionarioPublicador = mural.getFuncionarioGerente();
        if (funcionarioPublicador == null) {
            System.out.println("Mural sem funcionário gerente definido.");
            return;
        }

        System.out.print("Título da Notícia: ");
        String tituloNoticia = scanner.nextLine();
        System.out.print("Fonte da Notícia: ");
        String fonteNoticia = scanner.nextLine();
        System.out.print("Descrição da Notícia: ");
        String descricaoNoticia = scanner.nextLine();

        Noticias novaNoticia = new Noticias(funcionarioPublicador, tituloNoticia, fonteNoticia, descricaoNoticia);
        mural.cadastrarNoticia(novaNoticia);
        System.out.println("Notícia publicada com sucesso no mural '" + mural.getTitulo() + "'!");
    }

    private static void editarNoticiaNoMural() {
        listarMurais();
        if (murais.isEmpty()) return;

        int idMural = lerInteiro("Digite o ID do Mural que contém a notícia a ser editada: ");
        Mural mural = encontrarMuralPorId(idMural);

        if (mural == null) {
            System.out.println("Mural não encontrado.");
            return;
        }

        System.out.println("Notícias no Mural '" + mural.getTitulo() + "':");
        if (mural.getNoticiasPublicadas().isEmpty()) {
            System.out.println("  Nenhuma notícia neste mural.");
            return;
        }
        mural.getNoticiasPublicadas().forEach(System.out::println);

        int idNoticia = lerInteiro("Digite o ID da Notícia a ser editada: ");
        Noticias noticiaParaEditar = encontrarNoticiaNoMuralPorId(mural, idNoticia);

        if (noticiaParaEditar == null) {
            System.out.println("Notícia não encontrada neste mural.");
            return;
        }

        System.out.print("Novo Título (" + noticiaParaEditar.getTitulo() + "): ");
        String novoTitulo = scanner.nextLine();
        if (!novoTitulo.isEmpty()) noticiaParaEditar.setTitulo(novoTitulo);

        System.out.print("Nova Fonte (" + noticiaParaEditar.getFonte() + "): ");
        String novaFonte = scanner.nextLine();
        if (!novaFonte.isEmpty()) noticiaParaEditar.setFonte(novaFonte);

        System.out.print("Nova Descrição (" + noticiaParaEditar.getDescricao() + "): ");
        String novaDescricao = scanner.nextLine();
        if (!novaDescricao.isEmpty()) noticiaParaEditar.setDescricao(novaDescricao);

        mural.editarNoticia(noticiaParaEditar, noticiaParaEditar.getTitulo(), noticiaParaEditar.getFonte(), noticiaParaEditar.getDescricao());
        System.out.println("Notícia editada com sucesso no mural '" + mural.getTitulo() + "'!");
    }

    private static void removerNoticiaDoMural() {
        listarMurais();
        if (murais.isEmpty()) return;

        int idMural = lerInteiro("Digite o ID do Mural que contém a notícia a ser removida: ");
        Mural mural = encontrarMuralPorId(idMural);

        if (mural == null) {
            System.out.println("Mural não encontrado.");
            return;
        }

        System.out.println("Notícias no Mural '" + mural.getTitulo() + "':");
        if (mural.getNoticiasPublicadas().isEmpty()) {
            System.out.println("  Nenhuma notícia neste mural.");
            return;
        }
        mural.getNoticiasPublicadas().forEach(System.out::println);

        int idNoticia = lerInteiro("Digite o ID da Notícia a ser removida: ");
        Noticias noticiaParaRemover = encontrarNoticiaNoMuralPorId(mural, idNoticia);

        if (noticiaParaRemover == null) {
            System.out.println("Notícia não encontrada neste mural.");
            return;
        }

        mural.removerNoticia(noticiaParaRemover);
        System.out.println("Notícia removida com sucesso do mural '" + mural.getTitulo() + "'!");
    }

    private static void adicionarObservadorMural() {
        System.out.println("\n--- ADICIONAR OBSERVADOR A MURAL ---");
        listarMurais();
        if (murais.isEmpty()) return;

        int idMural = lerInteiro("Digite o ID do mural para adicionar observador: ");
        Mural mural = encontrarMuralPorId(idMural);

        if (mural == null) {
            System.out.println("Mural não encontrado.");
            return;
        }

        listarUsuarios();
        if (usuarios.isEmpty()) return;

        int idUsuario = lerInteiro("Digite o ID do Usuário (Observer) para adicionar: ");
        Usuario usuario = encontrarUsuarioPorId(idUsuario);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        mural.adicionarObserver(usuario);
    }


    private static void cadastrarAlimento() {
        System.out.println("\n--- CADASTRAR ALIMENTO ---");
        System.out.print("Descrição do Alimento: ");
        String descricao = scanner.nextLine();
        Alimentos novoAlimento = new Alimentos(descricao);
        alimentos.add(novoAlimento);
        System.out.println("Alimento '" + descricao + "' cadastrado com sucesso! (ID: " + novoAlimento.getIdAlimentos() + ")");
    }

    private static void listarAlimentos() {
        System.out.println("\n--- LISTA DE ALIMENTOS ---");
        if (alimentos.isEmpty()) {
            System.out.println("Nenhum alimento cadastrado.");
            return;
        }
        alimentos.forEach(System.out::println);
    }

    private static void criarNovoCardapio() {
        System.out.println("\n--- CRIAR NOVO CARDÁPIO ---");
        listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado para criar cardápios.");
            return;
        }

        Funcionario funcionario = null;
        while (funcionario == null) {
            int idFuncionario = lerInteiro("Digite o ID do Funcionário criador do cardápio: ");
            Usuario u = encontrarUsuarioPorId(idFuncionario);
            if (u instanceof Funcionario) {
                funcionario = (Funcionario) u;
            } else {
                System.out.println("ID inválido ou não pertence a um Funcionário. Tente novamente.");
            }
        }

        System.out.print("Conteúdo do Cardápio (texto livre): ");
        String conteudo = scanner.nextLine();

        Cardapio novoCardapio = new Cardapio(funcionario, conteudo);
        cardapios.add(novoCardapio);
        System.out.println("Cardápio criado com sucesso! (ID: " + novoCardapio.getIdCardapio() + ")");
    }

    private static void adicionarAlimentoACardapio() {
        listarCardapios();
        if (cardapios.isEmpty()) return;

        int idCardapio = lerInteiro("Digite o ID do Cardápio para adicionar alimentos: ");
        Cardapio cardapio = encontrarCardapioPorId(idCardapio);

        if (cardapio == null) {
            System.out.println("Cardápio não encontrado.");
            return;
        }

        listarAlimentos();
        if (alimentos.isEmpty()) {
            System.out.println("Nenhum alimento cadastrado para adicionar.");
            return;
        }

        int idAlimento = lerInteiro("Digite o ID do Alimento a ser adicionado: ");
        Alimentos alimento = encontrarAlimentoPorId(idAlimento);

        if (alimento == null) {
            System.out.println("Alimento não encontrado.");
            return;
        }

        int quantidade = lerInteiro("Quantidade: ");
        cardapio.adicionarAlimentoAoCardapio(alimento, quantidade);
        System.out.println("Alimento adicionado ao cardápio com sucesso!");
    }

    private static void listarCardapios() {
        System.out.println("\n--- LISTA DE CARDÁPIOS ---");
        if (cardapios.isEmpty()) {
            System.out.println("Nenhum cardápio cadastrado.");
            return;
        }
        for (Cardapio c : cardapios) {
            System.out.println(c);
        }
    }


    // --- Métodos Auxiliares para Leitura e Busca ---

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                scanner.nextLine(); // Limpar o buffer
            }
        }
    }

    private static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String dataStr = scanner.nextLine();
            try {
                return LocalDate.parse(dataStr);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use AAAA-MM-DD.");
            }
        }
    }

    private static LocalDateTime lerDataHora(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String dataHoraStr = scanner.nextLine();
            try {
                return LocalDateTime.parse(dataHoraStr, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data e hora inválido. Use AAAA-MM-DD HH:MM.");
            }
        }
    }

    private static Usuario encontrarUsuarioPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == id) {
                return u;
            }
        }
        return null;
    }

    private static Atividade encontrarAtividadePorId(int id) {
        for (Atividade a : atividades) {
            if (a.getIdAtividade() == id) {
                return a;
            }
        }
        return null;
    }

    private static Mural encontrarMuralPorId(int id) {
        for (Mural m : murais) {
            if (m.getIdMural() == id) {
                return m;
            }
        }
        return null;
    }

    private static Noticias encontrarNoticiaNoMuralPorId(Mural mural, int idNoticia) {
        for (Noticias n : mural.getNoticiasPublicadas()) {
            if (n.getIdNoticias() == idNoticia) {
                return n;
            }
        }
        return null;
    }

    private static Alimentos encontrarAlimentoPorId(int id) {
        for (Alimentos a : alimentos) {
            if (a.getIdAlimentos() == id) {
                return a;
            }
        }
        return null;
    }

    private static Cardapio encontrarCardapioPorId(int id) {
        for (Cardapio c : cardapios) {
            if (c.getIdCardapio() == id) {
                return c;
            }
        }
        return null;
    }
}