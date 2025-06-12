package backend;

import java.time.LocalDate;

public interface UsuarioFactory {
    // Métodos para criar usuários com seus dados básicos
    Usuario criarUsuario(String nome, String cpf, String login, String senha, LocalDate dataNascimento, String genero, Endereco endereco);
}