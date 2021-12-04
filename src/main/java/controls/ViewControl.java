package controls;

import models.entitys.Aluno;
import models.entitys.Curso;
import org.jetbrains.annotations.NotNull;
import views.*;

import javax.swing.*;

import java.util.List;

import static views.View.MAIN_VIEW;

public class ViewControl {
    private final Control control;
    private final MainView mainView;

    private JFrame currentView;
    private AlunoView alunoView;
    private CursoView cursoView;

    public ViewControl(Control control) {
        this.control = control;
        this.mainView = new MainView(control);
        this.changeView(MAIN_VIEW);
    }

    public void closeAllViews() {
        alunoView = null;
        cursoView = null;
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
            case MAIN_VIEW-> { return (T) mainView; }
            case ALUNO_VIEW -> {
                alunoView = (alunoView == null) ? new AlunoView(control) : alunoView;
                return (T) alunoView;
            }
            case CURSO_VIEW -> {
                cursoView = (cursoView == null) ? new CursoView(control) : cursoView;
                return (T) cursoView;
            }
            default -> { return null; }
        }
    }

    public void showMessage(String message) { JOptionPane.showMessageDialog(currentView, message); }

    public void clearFields(@NotNull View view) {
        switch (view) {
            case ALUNO_VIEW -> alunoView.clearFields();
            case CURSO_VIEW -> cursoView.clearFields();
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
        }
    }
}
