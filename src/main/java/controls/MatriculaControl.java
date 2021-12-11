package controls;

import models.MatriculaID;
import models.entitys.Aluno;
import models.entitys.Matricula;
import models.entitys.Turma;
import org.jetbrains.annotations.NotNull;
import services.MatriculaService;

import java.time.LocalDate;
import java.util.List;

import static models.View.MATRICULA_VIEW;

public record MatriculaControl(Control control, MatriculaService service) {
    public void save(Aluno aluno, Turma turma, LocalDate dataMatricula) {
        int code = service.save(aluno, turma, dataMatricula, null);
        switch (code) {
            case 0 -> {
                control.showMessage("Matrícula salva com sucesso!");
                control.clearFields(MATRICULA_VIEW);
                control.updateListViewer(MATRICULA_VIEW);
            }
            case 1 -> control.showMessage("Matrícula já existe!");
            case 2 -> control.showMessage("Data da matrícula não pode ser depois da data atual!");
            case 3 -> control.showMessage("Data da matrícula não pode ser antes da data de início da turma!");
        }
    }

    public void update(Aluno aluno, Turma turma, LocalDate dataMatricula, double nota) {
        int code = service.update(aluno, turma, dataMatricula, nota);
        switch (code) {
            case 0 -> {
                control.showMessage("Matrícula atualizada com sucesso!");
                control.clearFields(MATRICULA_VIEW);
                control.updateListViewer(MATRICULA_VIEW);
            }
            case 1 -> control.showMessage("Matrícula não existe!");
            case 2 -> control.showMessage("Data da matrícula não pode ser depois da data atual!");
            case 3 -> control.showMessage("Data da matrícula não pode ser antes da data de início da turma!");
            case 4 -> control.showMessage("A nota deve estar entre 0 e 10!");
        }
    }

    public void delete(@NotNull Aluno aluno, @NotNull Turma turma) {
        MatriculaID matriculaID = new MatriculaID(aluno.getCpf(), turma.getId());
        int code = service.delete(matriculaID);
        switch (code) {
            case 0 -> {
                control.showMessage("Matrícula excluída com sucesso!");
                control.clearFields(MATRICULA_VIEW);
                control.updateListViewer(MATRICULA_VIEW);
            }
            case 1 -> control.showMessage("Matrícula não existe!");
            case 2 -> control.showMessage("A matrícula não pode ser excluída pois não possui nota!");
        }
    }

    public Matricula get(@NotNull Aluno aluno, @NotNull Turma turma) {
        MatriculaID matriculaID = new MatriculaID(aluno.getCpf(), turma.getId());
        return service.get(matriculaID);
    }

    public List<Matricula> getAll() { return service.getAll(); }
}
