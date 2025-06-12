package backend;

import java.time.LocalDate;

public class IdosoFactory implements UsuarioFactory {
    // Para o Idoso, a fábrica deve permitir passar 'obs' e 'restricaoMedica'
    // Uma alternativa é ter um método 'criarUsuarioCompleto' ou sobrecarregar
    // Para simplificar o exemplo da factory, vamos usar o método base e definir obs/restricao depois
    // ou, para ser mais preciso, mudar a interface da factory para IdosoFactory
    // Vamos adaptar para aceitar os campos extras para Idoso, demonstrando sobrecarga ou especialização.
    // Para o exemplo, vamos usar um método mais genérico na factory base.

    @Override
    public Usuario criarUsuario(String nome, String cpf, String login, String senha, LocalDate dataNascimento, String genero, Endereco endereco) {
        // Por padrão, a fábrica cria um Idoso com obs/restricao vazias.
        // Se precisar de valores específicos, o Main deve setá-los ou usar uma sobrecarga/especialização da fábrica.
        return new Idoso(nome, cpf, login, senha, dataNascimento, genero, endereco, "", "");
    }

    // Um método específico para Idoso na fábrica, se necessário
    public Idoso criarIdosoComDetalhes(String nome, String cpf, String login, String senha, LocalDate dataNascimento, String genero, Endereco endereco, String obs, String restricaoMedica) {
        return new Idoso(nome, cpf, login, senha, dataNascimento, genero, endereco, obs, restricaoMedica);
    }
}