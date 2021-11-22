package cgt;

import cdp.Aluno;
import cdp.Pessoa;
import cdp.Professor;
import cgd.dao.AlunoDAO;
import cgd.dao.ProfessorDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AplGerenciarPessoa {
    private static final String URL_CFG = "hibernate.cfg.xml";

    public static final AlunoDAO alunoDAO;
    public static final ProfessorDAO professorDAO;

    static {
        alunoDAO = new AlunoDAO(URL_CFG);
        professorDAO = new ProfessorDAO(URL_CFG);
    }

    /**
     * Cadastra um aluno.
     * @param nome nome do aluno
     * @param dataNascimento data de nascimento do aluno
     * @param cpf cpf do aluno
     * @return 0 se sucesso, 1 se cpf já cadastrado, 2 se nome já cadastrado, 3 se data de nascimento deve ser anterior a data atual, 4 se erro ao cadastrar aluno
     */
    public int cadastrarAluno(String nome, LocalDate dataNascimento, long cpf) {
        if (getPessoas().stream().anyMatch(p -> p.getCpf() == cpf))
            return 1; // cpf já cadastrado
        else if (dataNascimento.isAfter(LocalDate.now()))
            return 2; // data de nascimento deve ser anterior a data atual
        else {
            Aluno aluno = new Aluno(nome, dataNascimento, cpf);
            alunoDAO.save(aluno);
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
        if (getPessoas().stream().anyMatch(p -> p.getCpf() == cpf))
            return 1; // cpf já cadastrado
        else if (dataNascimento.isAfter(LocalDate.now()))
            return 2; // data de nascimento deve ser anterior a data atual
        else {
            Professor professor = new Professor(nome, dataNascimento, cpf, titulacao);
            professorDAO.save(professor);
            return 0; // sucesso
        }
    }

    /**
     * Retorna uma lista com todos os alunos cadastrados.
     * @return lista de alunos
     */
    public static List<Aluno> getAlunos() { return alunoDAO.findAll(); }

    /**
     * Retorna uma lista com todos os professores cadastrados.
     * @return lista de professores
     */
    public static List<Professor> getProfessores() { return professorDAO.findAll(); }

    public static List<Pessoa> getPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.addAll(getAlunos());
        pessoas.addAll(getProfessores());
        return pessoas;
    }
}
