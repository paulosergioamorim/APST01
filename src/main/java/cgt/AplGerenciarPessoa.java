package cgt;

import cdp.Aluno;
import cdp.Pessoa;
import cdp.Professor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AplGerenciarPessoa {
    private final List<Pessoa> lstPessoas = new ArrayList<>();

    public int cadastrarAluno(String nome, LocalDate dataNascimento, long cpf) {
        try {
            if (lstPessoas.stream().anyMatch(p -> p.getCpf() == cpf))
                return 1; // cpf já cadastrado
            if (lstPessoas.stream().anyMatch(p -> p.getNome().equals(nome)))
                return 2; // nome já cadastrado
            if (dataNascimento.isAfter(LocalDate.now()))
                return 3; // data de nascimento deve ser anterior a data atual
            Aluno aluno = new Aluno(nome, dataNascimento, cpf);
            lstPessoas.add(aluno);
            return 0; // sucesso
        } catch (Exception e) {
            return 4; // erro ao cadastrar aluno
        }
    }

    public int cadastrarProfessor(String nome, LocalDate dataNascimento, long cpf, String titulacao) {
        try {
            if (lstPessoas.stream().anyMatch(p -> p.getCpf() == cpf))
                return 1; // cpf já cadastrado
            if (lstPessoas.stream().anyMatch(p -> p.getNome().equals(nome)))
                return 2; // nome já cadastrado
            if (dataNascimento.isAfter(LocalDate.now()))
                return 3; // data de nascimento deve ser anterior a data atual
            Professor professor = new Professor(nome, dataNascimento, cpf, titulacao);
            lstPessoas.add(professor);
            return 0; // sucesso
        } catch (Exception e) {
            return 4; // erro ao cadastrar professor
        }
    }

    public List<Aluno> getAlunos() { return (List<Aluno>) lstPessoas.stream().filter(p -> p instanceof Aluno); }

    public List<Professor> getProfessores() { return (List<Professor>) lstPessoas.stream().filter(p -> p instanceof Professor); }
}
