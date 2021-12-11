package controls;

import models.View;
import models.entitys.*;
import org.jetbrains.annotations.NotNull;
import views.*;

import javax.swing.*;
import java.util.List;

import static models.View.MAIN_VIEW;

public class ViewControl {
    private final Control control;
    private final MainView mainView;

    private JFrame currentView;
    private AlunoView alunoView;
    private CursoView cursoView;
    private ProfessorView professorView;
    private TurmaView turmaView;
    private MatriculaView matriculaView;
    private NotasView notasView;

    public ViewControl(final Control control) {
        this.control = control;
        this.mainView = new MainView(control);
        this.changeView(MAIN_VIEW);
    }

    public void closeAllViews() {
        this.alunoView = null;
        this.cursoView = null;
        this.professorView = null;
        this.turmaView = null;
        this.matriculaView = null;
        this.notasView = null;
    }

    public void changeView(final View view) {
        this.closeAllViews();
        if (this.currentView != null && view != MAIN_VIEW) this.currentView.dispose();
        this.currentView = this.getView(view);
        if (this.currentView != null) this.currentView.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    public <T extends JFrame> T getView(@NotNull final View view) {
        switch (view) {
            case MAIN_VIEW -> { return (T) this.mainView; }
            case ALUNO_VIEW -> {
                this.alunoView = (this.alunoView == null) ? new AlunoView(this.control) : this.alunoView;
                return (T) this.alunoView;
            }
            case CURSO_VIEW -> {
                this.cursoView = (this.cursoView == null) ? new CursoView(this.control) : this.cursoView;
                return (T) this.cursoView;
            }
            case PROFESSOR_VIEW -> {
                this.professorView = (this.professorView == null) ? new ProfessorView(this.control) : this.professorView;
                return (T) this.professorView;
            }
            case TURMA_VIEW -> {
                this.turmaView = (this.turmaView == null) ? new TurmaView(this.control) : this.turmaView;
                return (T) this.turmaView;
            }
            case MATRICULA_VIEW -> {
                this.matriculaView = (this.matriculaView == null) ? new MatriculaView(this.control) : this.matriculaView;
                return (T) this.matriculaView;
            }
            case NOTAS_VIEW -> {
                this.notasView = (this.notasView == null) ? new NotasView(this.control) : this.notasView;
                return (T) this.notasView;
            }
            default -> { return null; }
        }
    }

    public void showMessage(final String message) { JOptionPane.showMessageDialog(this.currentView, message); }

    public void clearFields(@NotNull final View view) {
        switch (view) {
            case ALUNO_VIEW -> this.alunoView.clearFields();
            case CURSO_VIEW -> this.cursoView.clearFields();
            case PROFESSOR_VIEW -> this.professorView.clearFields();
            case TURMA_VIEW -> this.turmaView.clearFields();
            case MATRICULA_VIEW -> this.matriculaView.clearFields();
            case NOTAS_VIEW -> this.notasView.clearFields();
        }
    }

    public void updateListViewer(@NotNull final View view) {
        switch (view) {
            case ALUNO_VIEW -> {
                final List<Aluno> alunos = this.control.getAlunoControl().getAll();
                this.alunoView.updateListViewer(alunos);
            }
            case CURSO_VIEW -> {
                final List<Curso> cursos = this.control.getCursoControl().getAll();
                this.cursoView.updateListViewer(cursos);
            }
            case PROFESSOR_VIEW -> {
                final List<Professor> professores = this.control.getProfessorControl().getAll();
                this.professorView.updateListViewer(professores);
            }
            case TURMA_VIEW -> {
                final List<Turma> turmas = this.control.getTurmaControl().getAll();
                this.turmaView.updateListViewer(turmas);
            }
            case MATRICULA_VIEW -> {
                final List<Matricula> matriculas = this.control.getMatriculaControl().getAll();
                this.matriculaView.updateListViewer(matriculas);
            }
        }
    }
}
