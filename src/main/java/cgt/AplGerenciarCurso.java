package cgt;

import cdp.Curso;
import cdp.Professor;
import cdp.Turma;
import cgd.dao.CursoDAO;
import cgd.dao.TurmaDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static cgt.AplGerenciarPessoa.professorDAO;

public class AplGerenciarCurso {
    private static final String URL_CFG = "hibernate.cfg.xml";
    public static final CursoDAO cursoDAO;
    public static final TurmaDAO turmaDAO;

    static {
        cursoDAO = new CursoDAO(URL_CFG);
        turmaDAO = new TurmaDAO(URL_CFG);
    }

    /**
     * Cadastra um curso.
     * @param nome nome do curso
     * @param ch carga horária do curso
     * @return 0 se o curso foi cadastrado com sucesso, 1 se o curso já existe
     */
    public int cadastrarCurso(String nome, int ch) {
        if (nome.isEmpty() || ch <= 0)
            return 1; // nome e carga horária não podem ser vazios
        else if (cursoDAO.findAll()
                .stream()
                .anyMatch(c -> c.getNome().equals(nome)))
            return 2; // um curso com esse nome já existe
        else {
            Curso curso = new Curso(nome,ch);
            cursoDAO.save(curso);
            return 0; // sucesso
        }
    }

    /**
     * Cadastra uma turma.
     * @param dataInicio data de início da turma
     * @param dataFim data de fim da turma
     * @param horario horario de início da turma
     * @param limiteAlunos limite de alunos da turma
     * @param curso cursos que a turma pertence
     * @param responsavel responsável da turma
     * @return 0 se a turma foi cadastrada com sucesso, 1 se o curso não existe, 2 se o responsavel não existe, 3 se a turma já existe
     */
    public int cadastrarTurma(LocalDate dataInicio, LocalDate dataFim, LocalTime horario, int limiteAlunos, Curso curso, Professor responsavel) {
        if (dataFim.isBefore(dataInicio))
            return 1; // data de fim deve ser depois da dataInicio
        else if (limiteAlunos < 0)
            return 2; // limite de alunos deve ser maior do que zero
        else if (!cursoDAO.exists(curso.getNome()))
            return 3; // curso não existe
        else if (!professorDAO.exists(responsavel.getCpf()))
            return 4; // responsavel não existe
        else {
            Turma turma = new Turma(dataInicio, dataFim, horario, limiteAlunos, curso, responsavel);
            turmaDAO.save(turma);
            curso.addTurma(turma);
            cursoDAO.update(curso);
            return 0; // sucesso
        }
    }

    /**
     * Lista de turmas que possuem vagas disponíveis para alunos se matricularem
     * @return Uma lista de turmas que vagas > 0
     */
    public static List<Turma> getTurmasVagas() {
        return turmaDAO.findAll()
                .stream()
                .filter(t -> t.getVagas() > 0)
                .collect(Collectors.toList());
    }
}
