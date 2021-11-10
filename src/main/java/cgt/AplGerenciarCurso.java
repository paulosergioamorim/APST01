package cgt;

import cdp.Curso;
import cdp.Professor;
import cdp.Turma;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AplGerenciarCurso {
    private final List<Curso> lstCursos = new ArrayList<>();
    private final List<Turma> lstTurmas = new ArrayList<>();

    public int cadastrarCurso(String nome, int ch) {
        try {
            Curso curso = new Curso(nome,ch);
            lstCursos.add(curso);
            return 0; // sucesso
        } catch (Exception e) {
            return 1; // erro ao cadastrar curso
        }
    }

    public int cadastrarTurma(LocalDate dataInicio, LocalDate dataFim, LocalTime horario, int limiteAlunos, Curso curso, Professor professor) {
        try {
            if (dataInicio.isBefore(LocalDate.now()))
                return 1; // data de inicio deve ser anterior a data atual
            if (dataFim.isAfter(dataInicio))
                return 2; // data de fim deve ser depois da dataInicio
            if (limiteAlunos < 0)
                return 3; // limite de alunos deve ser maior do que zero
            if (!lstCursos.contains(curso) || curso == null)
                return 4; // curso não existe
            Turma turma = new Turma(dataInicio, dataFim, horario, limiteAlunos);
            turma.setCurso(curso);
            turma.setResponsavel(professor);
            lstTurmas.add(turma);
            return 0; // sucesso
        } catch (Exception e) {
            return 5; // erro ao cadastrar turma
        }
    }
}
