package controls;

import models.Sexo;
import models.View;
import models.entitys.Aluno;
import models.entitys.Curso;
import models.entitys.Professor;
import models.entitys.Turma;

import java.time.LocalDate;
import java.time.LocalTime;

import static controls.ControlFactory.*;

/**
 * Controller Class
 *
 * @author Paulo Sergio
 * @author Breno Haese
 * @author Nycolas Monjardim
 */
public final class Control {
    private final AlunoControl alunoControl;
    private final ProfessorControl professorControl;
    private final CursoControl cursoControl;
    private final TurmaControl turmaControl;
    private final MatriculaControl matriculaControl;
    private final ViewControl viewControl;

    public Control() {
        alunoControl = createAlunoControl(this);
        professorControl = createProfessorControl(this);
        cursoControl = createCursoControl(this);
        turmaControl = createTurmaControl(this);
        viewControl = createViewControl(this);
        matriculaControl = createMatriculaControl(this);
    }

    public static void main(String[] args) { new Control(); }

    public void changeView(View view) { viewControl.changeView(view); }

    public void showMessage(String message) { viewControl.showMessage(message); }

    public void updateListViewer(View view) { viewControl.updateViewer(view); }

    public void clearFields(View view) { viewControl.clearFields(view); }

    public void saveAluno(long cpf,
                          String nome,
                          Sexo sexo,
                          LocalDate dataNascimento) { alunoControl.save(cpf, nome, sexo, dataNascimento); }

    public void updateAluno(long cpf,
                            String nome,
                            Sexo sexo,
                            LocalDate dataNascimento) { alunoControl.update(cpf, nome, sexo, dataNascimento); }

    public void deleteAluno(long cpf) { alunoControl.delete(cpf); }

    public void saveCurso(int id,
                          String nome,
                          String sigla,
                          int cargaHoraria) { cursoControl.save(id, nome, sigla, cargaHoraria); }

    public void updateCurso(int id,
                            String nome,
                            String sigla,
                            int cargaHoraria) { cursoControl.update(id, nome, sigla, cargaHoraria); }

    public void deleteCurso(int id) { cursoControl.delete(id); }

    public void saveProfessor(long cpf,
                              String nome,
                              Sexo sexo,
                              LocalDate dataNascimento,
                              String titulacao) { professorControl.save(cpf, nome, sexo, dataNascimento, titulacao); }

    public void updateProfessor(long cpf,
                                String nome,
                                Sexo sexo,
                                LocalDate dataNascimento,
                                String titulacao) {
        professorControl.update(cpf, nome, sexo, dataNascimento, titulacao);
    }

    public void deleteProfessor(long cpf) { professorControl.delete(cpf); }

    public void saveTurma(int id,
                          LocalDate dataInicio,
                          LocalDate dataFim,
                          LocalTime horario,
                          int limite,
                          Curso curso,
                          Professor responsavel) {
        turmaControl.save(id, dataInicio, dataFim, horario, limite, curso, responsavel);
    }

    public void updateTurma(int id,
                            LocalDate dataInicio,
                            LocalDate dataFim,
                            LocalTime horario,
                            int limite,
                            Curso curso,
                            Professor responsavel) {
        turmaControl.update(id, dataInicio, dataFim, horario, limite, curso, responsavel);
    }

    public void deleteTurma(int id) { turmaControl.delete(id); }

    public void fecharTurma(int id) { turmaControl.fechar(id); }

    public void saveMatricula(Aluno aluno,
                              Turma turma,
                              LocalDate dataMatricula) { matriculaControl.save(aluno, turma, dataMatricula); }

    public void updateMatricula(Aluno aluno,
                                Turma turma,
                                LocalDate dataMatricula) { matriculaControl.update(aluno, turma, dataMatricula); }

    public void salvarNota(Aluno aluno,
                           Turma turma,
                           double nota) { matriculaControl.update(aluno, turma, nota); }

    public void deleteMatricula(Aluno aluno, Turma turma) { matriculaControl.delete(aluno, turma); }

    public AlunoControl getAlunoControl() { return alunoControl; }

    public ProfessorControl getProfessorControl() { return professorControl; }

    public CursoControl getCursoControl() { return cursoControl; }

    public TurmaControl getTurmaControl() { return turmaControl; }

    public MatriculaControl getMatriculaControl() { return matriculaControl; }
}
