package cgt;

import cdp.Curso;
import cdp.Matricula;
import cdp.Professor;
import cdp.Turma;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cgt.AplGerenciarPessoa.lstPessoas;

public class AplGerenciarCurso {
    public static final List<Curso> lstCursos;
    public static final List<Turma> lstTurmas;

    static {
        lstCursos = new ArrayList<>();
        lstTurmas = new ArrayList<>();
    }

    /**
     * Cadastra um curso.
     * @param nome nome do curso
     * @param ch carga horária do curso
     * @return 0 se o curso foi cadastrado com sucesso, 1 se o curso já existe
     */
    public int cadastrarCurso(String nome, int ch) {
        try {
            Curso curso = new Curso(nome,ch);
            lstCursos.add(curso);
            return 0; // sucesso
        } catch (Exception e) {
            return 1; // erro ao cadastrar curso
        }
    }

    /**
     * Cadastra uma turma.
     * @param dataInicio data de início da turma
     * @param dataFim data de fim da turma
     * @param horario horario de início da turma
     * @param limiteAlunos limite de alunos da turma
     * @param curso cursos que a turma pertence
     * @param professor responsável da turma
     * @return 0 se a turma foi cadastrada com sucesso, 1 se o curso não existe, 2 se o professor não existe, 3 se a turma já existe
     */
    public int cadastrarTurma(LocalDate dataInicio, LocalDate dataFim, LocalTime horario, int limiteAlunos, Curso curso, Professor professor) {
        try {
            if (dataInicio.isAfter(LocalDate.now()))
                return 1; // data de inicio deve ser anterior a data atual
            if (dataFim.isBefore(dataInicio))
                return 2; // data de fim deve ser depois da dataInicio
            if (limiteAlunos < 0)
                return 3; // limite de alunos deve ser maior do que zero
            if (!lstCursos.contains(curso) || curso == null)
                return 4; // curso não existe
            if (!lstPessoas.contains(professor) || professor == null)
                return 5; // professor não existe
            Turma turma = new Turma(dataInicio, dataFim, horario, limiteAlunos);
            turma.setCurso(curso);
            turma.setResponsavel(professor);
            lstTurmas.add(turma);
            lstCursos.get(lstCursos.indexOf(curso)).addTurma(turma);
            return 0; // sucesso
        } catch (Exception e) {
            return 6; // erro ao cadastrar turma
        }
    }

    public static List<Turma> getTurmasVagas() {
        return lstTurmas.stream()
                .filter(turma -> turma.getMatriculas()[turma.getMatriculas().length - 1] == null)
                .collect(Collectors.toList());
    }
}
