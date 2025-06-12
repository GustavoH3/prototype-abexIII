package backend;

import java.time.LocalDate;

public class Professor extends Usuario {
    public Professor(String nome, String cpf, String login, String senha, LocalDate dataNascimento, String genero, Endereco endereco) {
        super(nome, cpf, login, senha, dataNascimento, genero, endereco);
    }

    // O Professor pode ter métodos específicos se necessário, além dos de Usuário
    // Por exemplo, ele 'cria' atividades, mas essa lógica está no 'Funcionario' que é quem gerencia o sistema
    // para um sistema mais realista, um professor poderia ter um método para ver suas atividades criadas
    public void visualizarAtividadesCriadas() {
        System.out.println("Professor " + getNome() + " visualizando atividades que criou.");
        // Lógica para listar atividades onde ele é o professor
    }

    @Override
    public String toString() {
        return "PROFESSOR - " + super.toString();
    }
}