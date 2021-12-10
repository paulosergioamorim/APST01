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

    public ViewControl(Control control) {
        this.control = control;
        this.mainView = new MainView(control);
        this.changeView(MAIN_VIEW);
    }

    public void closeAllViews() {
        alunoView = null;
        cursoView = null;
        professorView = null;
        turmaView = null;
        matriculaView = null;
    }

    public void changeView(View view) {
        this.closeAllViews();
        if (currentView != null && view != MAIN_VIEW)
            currentView.dispose();
        currentView = this.getView(view);
        if (currentView != null)
            currentView.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    public <T extends JFrame> T getView(@NotNull View view) {
        switch (view) {
            case MAIN_VIEW -> { return (T) mainView; }
            case ALUNO_VIEW -> {
                alunoView = (alunoView == null) ? new AlunoView(control) : alunoView;
                return (T) alunoView;
            }
            case CURSO_VIEW -> {
                cursoView = (cursoView == null) ? new CursoView(control) : cursoView;
                return (T) cursoView;
            }
            case PROFESSOR_VIEW -> {
                professorView = (professorView == null) ? new ProfessorView(control) : professorView;
                return (T) professorView;
            }
            case TURMA_VIEW -> {
                turmaView = (turmaView == null) ? new TurmaView(control) : turmaView;
                return (T) turmaView;
            }
            case MATRICULA_VIEW -> {
                matriculaView = (matriculaView == null) ? new MatriculaView(control) : matriculaView;
                return (T) matriculaView;
            }
            default -> { return null; }
        }
    }

    public void showMessage(String message) { JOptionPane.showMessageDialog(currentView, message); }

    public void clearFields(@NotNull View view) {
        switch (view) {
            case ALUNO_VIEW -> alunoView.clearFields();
            case CURSO_VIEW -> cursoView.clearFields();
            case PROFESSOR_VIEW -> professorView.clearFields();
            case TURMA_VIEW -> turmaView.clearFields();
            case MATRICULA_VIEW -> matriculaView.clearFields();
        }
    }

    public void updateListViewer(@NotNull View view) {
        switch (view) {
            case ALUNO_VIEW -> {
                List<Aluno> alunos = control.getAlunoControl().getAll();
                alunoView.updateListViewer(alunos);
            }
            case CURSO_VIEW -> {
                List<Curso> cursos = control.getCursoControl().getAll();
                cursoView.updateListViewer(cursos);
            }
            case PROFESSOR_VIEW -> {
                List<Professor> professores = control.getProfessorControl().getAll();
                professorView.updateListViewer(professores);
            }
            case TURMA_VIEW -> {
                List<Turma> turmas = control.getTurmaControl().getAll();
                turmaView.updateListViewer(turmas);
            }
            case MATRICULA_VIEW -> {
                List<Matricula> matriculas = control.getMatriculaControl().getAll();
                matriculaView.updateListViewer(matriculas);
            }
        }
    }
}
