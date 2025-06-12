package backend;

import java.time.LocalDate;

public class FuncionarioFactory implements UsuarioFactory {
    @Override
    public Usuario criarUsuario(String nome, String cpf, String login, String senha, LocalDate dataNascimento, String genero, Endereco endereco) {
        return new Funcionario(nome, cpf, login, senha, dataNascimento, genero, endereco);
    }
}