package cgt;

import cdp.Aluno;
import cdp.Pessoa;
import cdp.Professor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AplGerenciarPessoa {
    public static final List<Pessoa> lstPessoas;

    static { lstPessoas = new ArrayList<>(); }

    /**
     * Cadastra um aluno.
     * @param nome nome do aluno
     * @param dataNascimento data de nascimento do aluno
     * @param cpf cpf do aluno
     * @return 0 se sucesso, 1 se cpf já cadastrado, 2 se nome já cadastrado, 3 se data de nascimento deve ser anterior a data atual, 4 se erro ao cadastrar aluno
     */
    public int cadastrarAluno(String nome, LocalDate dataNascimento, long cpf) {
        if (lstPessoas.stream().anyMatch(p -> p.getCpf() == cpf))
            return 1; // cpf já cadastrado
        else if (dataNascimento.isAfter(LocalDate.now()))
            return 2; // data de nascimento deve ser anterior a data atual
        else {
            Aluno aluno = new Aluno(nome, dataNascimento, cpf);
            lstPessoas.add(aluno);
            return 0; // sucesso
        }
    }

    /**
     * Cadastra um professor.
     * @param nome nome do professor
     * @param dataNascimento data de nascimento do professor
     * @param cpf cpf do professor
     * @param titulacao titulação do professor
     * @return 0 se sucesso, 1 se cpf já cadastrado, 2 se nome já cadastrado, 3 se data de nascimento deve ser anterior a data atual, 4 se erro ao cadastrar professor
     */
    public int cadastrarProfessor(String nome, LocalDate dataNascimento, long cpf, String titulacao) {
        if (lstPessoas.stream().anyMatch(p -> p.getCpf() == cpf))
            return 1; // cpf já cadastrado
        else if (dataNascimento.isAfter(LocalDate.now()))
            return 2; // data de nascimento deve ser anterior a data atual
        else {
            Professor professor = new Professor(nome, dataNascimento, cpf, titulacao);
            lstPessoas.add(professor);
            return 0; // sucesso
        }
    }

    /**
     * Retorna uma lista com todos os alunos cadastrados.
     * @return lista de alunos
     */
    public static List<Aluno> getAlunos() {
        return lstPessoas.stream()
                .filter(aluno -> aluno instanceof Aluno)
                .map(p -> (Aluno) p)
                .collect(Collectors.toList());
    }

    /**
     * Retorna uma lista com todos os professores cadastrados.
     * @return lista de professores
     */
    public static List<Professor> getProfessores() {
        return lstPessoas.stream()
                .filter(professor -> professor instanceof Professor)
                .map(professor -> (Professor) professor)
                .collect(Collectors.toList());
    }
}
