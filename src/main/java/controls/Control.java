package controls;

import models.Sexo;
import models.View;
import models.entitys.*;
import views.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static controls.ControlFactory.*;
import static models.Format.dateFormatter;
import static models.Format.timeFormatter;
import static models.View.*;

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
        this.alunoControl = createAlunoControl(this);
        this.professorControl = createProfessorControl(this);
        this.cursoControl = createCursoControl(this);
        this.turmaControl = createTurmaControl(this);
        this.viewControl = createViewControl(this);
        this.matriculaControl = createMatriculaControl(this);
    }

    public static void main(final String[] args) { new Control(); }

    public void changeView(final View view) { this.viewControl.changeView(view); }

    public void showMessage(final String message) { this.viewControl.showMessage(message); }

    public void updateListViewer(final View view) { this.viewControl.updateListViewer(view); }

    public void clearFields(final View view) { this.viewControl.clearFields(view); }

    public void saveAluno() {
        final long cpf;
        final String nome;
        final Sexo sexo;
        final LocalDate dataNascimento;
        final AlunoView alunoView = this.viewControl.getView(ALUNO_VIEW);
        try {
            cpf = Long.parseLong(alunoView.getCpf().replaceAll("\\D", ""));
            nome = alunoView.getNome();
            sexo = alunoView.getSexo();
            dataNascimento = LocalDate.parse(alunoView.getDataNascimento(), dateFormatter);
            this.alunoControl.save(cpf, nome, sexo, dataNascimento);
        } catch (final Exception e) {
            this.showMessage("Erro ao salvar aluno");
        }
    }

    public void updateAluno() {
        final long cpf;
        final String nome;
        final Sexo sexo;
        final LocalDate dataNascimento;
        final AlunoView alunoView = this.viewControl.getView(ALUNO_VIEW);
        try {
            cpf = Long.parseLong(alunoView.getCpf().replaceAll("\\D", ""));
            nome = (alunoView.getNome().isEmpty())
                    ? null : alunoView.getNome();
            sexo = (alunoView.getSexo() == null)
                    ? null : alunoView.getSexo();
            dataNascimento = alunoView.getDataNascimento().equals("__/__/____")
                    ? null : LocalDate.parse(alunoView.getDataNascimento(), dateFormatter);
            this.alunoControl.update(cpf, nome, sexo, dataNascimento);
        } catch (final Exception e) {
            this.showMessage("Erro ao atualizar aluno");
        }
    }

    public void deleteAluno() {
        final long cpf;
        final AlunoView alunoView = this.viewControl.getView(ALUNO_VIEW);
        try {
            cpf = alunoView.getListView().getSelectedValue().getCpf();
            this.alunoControl.delete(cpf);
        } catch (final Exception e) {
            this.showMessage("Erro ao deletar aluno");
        }
    }

    public void saveCurso() {
        final int id;
        final String nome;
        final String sigla;
        final int cargaHoraria;
        final CursoView cursoView = this.viewControl.getView(CURSO_VIEW);
        try {
            id = Integer.parseInt(cursoView.getId());
            nome = cursoView.getNome();
            cargaHoraria = Integer.parseInt(cursoView.getCargaHoraria());
            sigla = cursoView.getSigla();
            this.cursoControl.save(id, nome, sigla, cargaHoraria);
        } catch (final Exception e) {
            this.showMessage("Erro ao salvar curso");
        }
    }

    public void updateCurso() {
        final int id;
        final String nome;
        final String sigla;
        final int cargaHoraria;
        final CursoView cursoView = this.viewControl.getView(CURSO_VIEW);
        try {
            id = Integer.parseInt(cursoView.getId());
            nome = (cursoView.getNome().isEmpty())
                    ? null : cursoView.getNome();
            cargaHoraria = (cursoView.getCargaHoraria().isEmpty()) ?
                    0 : Integer.parseInt(cursoView.getCargaHoraria());
            sigla = (cursoView.getSigla().isEmpty())
                    ? null : cursoView.getSigla();
            this.cursoControl.update(id, nome, sigla, cargaHoraria);
        } catch (final Exception e) {
            this.showMessage("Erro ao atualizar curso");
        }
    }

    public void deleteCurso() {
        final Curso curso;
        final CursoView cursoView = this.viewControl.getView(CURSO_VIEW);
        try {
            curso = cursoView.getListView().getSelectedValue();
            final int id = curso.getId();
            this.cursoControl.delete(id);
        } catch (final Exception e) {
            this.showMessage("Erro ao deletar curso");
        }
    }

    public void saveProfessor() {
        final long cpf;
        final String nome;
        final Sexo sexo;
        final LocalDate dataNascimento;
        final String titulacao;
        final ProfessorView professorView = this.viewControl.getView(PROFESSOR_VIEW);
        try {
            cpf = Long.parseLong(professorView.getCpf().replaceAll("\\D", ""));
            nome = professorView.getNome();
            sexo = professorView.getSexo();
            dataNascimento = LocalDate.parse(professorView.getDataNascimento(), dateFormatter);
            titulacao = professorView.getTitulacao();
            this.professorControl.save(cpf, nome, sexo, dataNascimento, titulacao);
        } catch (final Exception e) {
            this.showMessage("Erro ao salvar professor");
        }
    }

    public void updateProfessor() {
        final long cpf;
        final String nome;
        final Sexo sexo;
        final LocalDate dataNascimento;
        final String titulacao;
        final ProfessorView professorView = this.viewControl.getView(PROFESSOR_VIEW);
        try {
            cpf = Long.parseLong(professorView.getCpf().replaceAll("\\D", ""));
            nome = (professorView.getNome().isEmpty()) ?
                    null : professorView.getNome();
            sexo = (professorView.getSexo() == null) ?
                    null : professorView.getSexo();
            dataNascimento = professorView.getDataNascimento().equals("__/__/____") ?
                    null : LocalDate.parse(professorView.getDataNascimento(), dateFormatter);
            titulacao = (professorView.getTitulacao().isEmpty()) ?
                    null : professorView.getTitulacao();
            this.professorControl.update(cpf, nome, sexo, dataNascimento, titulacao);
        } catch (final Exception e) {
            this.showMessage("Erro ao atualizar professor");
        }
    }

    public void deleteProfessor() {
        final Professor professor;
        final ProfessorView professorView = this.viewControl.getView(PROFESSOR_VIEW);
        try {
            professor = professorView.getListView().getSelectedValue();
            final long cpf = professor.getCpf();
            this.professorControl.delete(cpf);
        } catch (final Exception e) {
            this.showMessage("Erro ao deletar professor");
        }
    }

    public void saveTurma() {
        final int id;
        final LocalDate dataInicio;
        final LocalDate dataFim;
        final LocalTime horario;
        final int limite;
        final Curso curso;
        final Professor responsavel;
        final TurmaView turmaView = this.viewControl.getView(TURMA_VIEW);
        try {
            id = Integer.parseInt(turmaView.getId());
            dataInicio = LocalDate.parse(turmaView.getDataInicio(), dateFormatter);
            dataFim = LocalDate.parse(turmaView.getDataFim(), dateFormatter);
            horario = LocalTime.parse(turmaView.getHorario(), timeFormatter);
            limite = Integer.parseInt(turmaView.getLimite());
            curso = turmaView.getCurso();
            responsavel = turmaView.getResponsavel();
            this.turmaControl.save(id, dataInicio, dataFim, horario, limite, curso, responsavel);
        } catch (final Exception e) {
            this.showMessage("Erro ao salvar turma");
        }
    }

    public void updateTurma() {
        final int id;
        final LocalDate dataInicio;
        final LocalDate dataFim;
        final LocalTime horario;
        final int limite;
        final Curso curso;
        final Professor responsavel;
        final TurmaView turmaView = this.viewControl.getView(TURMA_VIEW);
        try {
            id = Integer.parseInt(turmaView.getId());
            dataInicio = turmaView.getDataInicio().equals("__/__/____") ?
                    null : LocalDate.parse(turmaView.getDataInicio(), dateFormatter);
            dataFim = turmaView.getDataFim().equals("__/__/____") ?
                    null : LocalDate.parse(turmaView.getDataFim(), dateFormatter);
            horario = turmaView.getHorario().equals("__:__") ?
                    null : LocalTime.parse(turmaView.getHorario(), timeFormatter);
            limite = turmaView.getLimite().equals("00") ?
                    0 : Integer.parseInt(turmaView.getLimite());
            curso = turmaView.getCurso();
            responsavel = turmaView.getResponsavel();
            this.turmaControl.update(id, dataInicio, dataFim, horario, limite, curso, responsavel);
        } catch (final Exception e) {
            this.showMessage("Erro ao atualizar turma");
        }
    }

    public void deleteTurma() {
        final Turma turma;
        final TurmaView turmaView = this.viewControl.getView(TURMA_VIEW);
        try {
            turma = turmaView.getListView().getSelectedValue();
            final int id = turma.getId();
            this.turmaControl.delete(id);
        } catch (final Exception e) {
            this.showMessage("Erro ao deletar turma");
        }
    }

    public void closeTurma() {
        final Turma turma;
        final NotasView notasView = this.viewControl.getView(NOTAS_VIEW);
        try {
            turma = notasView.getTurma();
            final int id = turma.getId();
            this.turmaControl.close(id);
        } catch (final Exception e) {
            this.showMessage("Erro ao fechar turma");
        }
    }

    public void saveMatricula() {
        final Aluno aluno;
        final Turma turma;
        final LocalDate dataMatricula;
        final MatriculaView matriculaView = this.viewControl.getView(MATRICULA_VIEW);
        try {
            aluno = matriculaView.getAluno();
            turma = matriculaView.getTurma();
            dataMatricula = matriculaView.getDataMatricula().equals("__/__/____") ?
                    LocalDate.now() : LocalDate.parse(matriculaView.getDataMatricula(), dateFormatter);
            this.matriculaControl.save(aluno, turma, dataMatricula);
        } catch (final Exception e) {
            this.showMessage("Erro ao salvar matricula");
        }
    }

    public void updateMatricula() {
        final Aluno aluno;
        final Turma turma;
        final LocalDate dataMatricula;
        final MatriculaView matriculaView = this.viewControl.getView(MATRICULA_VIEW);
        try {
            aluno = matriculaView.getAluno();
            turma = matriculaView.getTurma();
            dataMatricula = matriculaView.getDataMatricula().equals("__/__/____") ?
                    null : LocalDate.parse(matriculaView.getDataMatricula(), dateFormatter);
            this.matriculaControl.update(aluno, turma, dataMatricula);
        } catch (final Exception e) {
            this.showMessage("Erro ao atualizar matricula");
        }
    }

    public void setNota() {
        final Aluno aluno;
        final Turma turma;
        final double nota;
        final NotasView notasView = this.viewControl.getView(NOTAS_VIEW);
        try {
            aluno = notasView.getAluno();
            turma = notasView.getTurma();
            nota = Double.parseDouble(notasView.getNota());
            this.matriculaControl.update(aluno, turma, nota);
        } catch (final Exception e) {
            this.showMessage("Erro ao atualizar nota");
        }
    }

    public void deleteMatricula() {
        final Matricula matricula;
        final MatriculaView matriculaView = this.viewControl.getView(MATRICULA_VIEW);
        try {
            matricula = matriculaView.getListView().getSelectedValue();
            final Aluno aluno = matricula.getAluno();
            final Turma turma = matricula.getTurma();
            this.matriculaControl.delete(aluno, turma);
        } catch (final Exception e) {
            this.showMessage("Erro ao deletar matricula");
        }
    }

    public AlunoControl getAlunoControl() { return this.alunoControl; }

    public ProfessorControl getProfessorControl() { return this.professorControl; }

    public CursoControl getCursoControl() { return this.cursoControl; }

    public TurmaControl getTurmaControl() { return this.turmaControl; }

    public MatriculaControl getMatriculaControl() { return this.matriculaControl; }
}
