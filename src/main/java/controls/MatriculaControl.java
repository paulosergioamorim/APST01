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
import static models.View.NOTAS_VIEW;

public record MatriculaControl(Control control, MatriculaService service) {
    public void save(final Aluno aluno, final Turma turma, final LocalDate dataMatricula) {
        final int code = this.service.save(aluno, turma, dataMatricula, null);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Matrícula salva com sucesso!");
                this.control.clearFields(MATRICULA_VIEW);
                this.control.updateListViewer(MATRICULA_VIEW);
            }
            case 1 -> this.control.showMessage("Matrícula já existe!");
            case 2 -> this.control.showMessage("Data da matrícula não pode ser depois da data atual!");
            case 3 -> this.control.showMessage("Data da matrícula não pode ser antes da data de início da turma!");
            case 4 -> this.control.showMessage("Essa turma não está com matrículas abertas!");
        }
    }

    public void update(final Aluno aluno, final Turma turma, final LocalDate dataMatricula) {
        final int code = this.service.update(aluno, turma, dataMatricula, null);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Matrícula atualizada com sucesso!");
                this.control.clearFields(MATRICULA_VIEW);
                this.control.updateListViewer(MATRICULA_VIEW);
            }
            case 1 -> this.control.showMessage("Matrícula não existe!");
            case 2 -> this.control.showMessage("Data da matrícula não pode ser depois da data atual!");
            case 3 -> this.control.showMessage("Data da matrícula não pode ser antes da data de início da turma!");
            case 4 -> this.control.showMessage("A nota deve estar entre 0 e 10!");
        }
    }

    public void update(@NotNull final Aluno aluno, @NotNull final Turma turma, final double nota) {
        final int code = this.service.update(aluno, turma, null, nota);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Nota atualizada com sucesso!");
                this.control.clearFields(NOTAS_VIEW);
            }
            case 1 -> this.control.showMessage("Matrícula não existe!");
            case 4 -> this.control.showMessage("A nota deve estar entre 0 e 10!");
        }
    }

    public void delete(@NotNull final Aluno aluno, @NotNull final Turma turma) {
        final MatriculaID matriculaID = new MatriculaID(aluno.getCpf(), turma.getId());
        final int code = this.service.delete(matriculaID);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Matrícula excluída com sucesso!");
                this.control.clearFields(MATRICULA_VIEW);
                this.control.updateListViewer(MATRICULA_VIEW);
            }
            case 1 -> this.control.showMessage("Matrícula não existe!");
            case 2 -> this.control.showMessage("A matrícula não pode ser excluída pois já possui notas!");
        }
    }

    public Matricula get(@NotNull final Aluno aluno, @NotNull final Turma turma) {
        final MatriculaID matriculaID = new MatriculaID(aluno.getCpf(), turma.getId());
        return this.service.get(matriculaID);
    }

    public List<Matricula> getAll() { return this.service.getAll(); }
}
