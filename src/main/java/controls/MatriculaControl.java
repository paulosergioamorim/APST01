package controls;

import models.MatriculaID;
import models.entitys.Aluno;
import models.entitys.Matricula;
import models.entitys.Turma;
import services.MatriculaService;

import java.time.LocalDate;
import java.util.List;

import static models.View.MATRICULA_VIEW;

public record MatriculaControl(Control control, MatriculaService service) {
    public void save(Aluno aluno, Turma turma, LocalDate dataMatricula) {
        MatriculaID matriculaID = new MatriculaID(aluno, turma);
        int code = service.save(matriculaID, dataMatricula, 0);
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
        MatriculaID matriculaID = new MatriculaID(aluno, turma);
        int code = service.update(matriculaID, dataMatricula, nota);
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

    public void delete(Aluno aluno, Turma turma) {
        MatriculaID matriculaID = new MatriculaID(aluno, turma);
        int code = service.delete(matriculaID);
        switch (code) {
            case 0 -> {
                control.showMessage("Matrícula excluída com sucesso!");
                control.clearFields(MATRICULA_VIEW);
                control.updateListViewer(MATRICULA_VIEW);
            }
            case 1 -> control.showMessage("Matrícula não existe!");
            case 2 -> control.showMessage("A matrícula não pode ser excluída pois a turma não está fechada!");
        }
    }

    public Matricula get(Aluno aluno, Turma turma) {
        MatriculaID matriculaID = new MatriculaID(aluno, turma);
        return service.get(matriculaID);
    }

    public List<Matricula> getAll() { return service.getAll(); }
}
