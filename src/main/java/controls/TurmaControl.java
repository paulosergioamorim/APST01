package controls;

import models.entitys.Curso;
import models.entitys.Professor;
import models.entitys.Turma;
import services.TurmaService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static views.View.TURMA_VIEW;

public record TurmaControl(Control control, TurmaService service) {
    public void save(String id,
                     LocalDate dataInicio,
                     LocalDate dataFim,
                     LocalTime horario,
                     int limite,
                     Curso curso,
                     Professor responsavel) {
        int code = service.save(id, dataInicio, dataFim, horario, limite, curso, responsavel);
        switch (code) {
            case 0 -> {
                control.showMessage("Turma salva com sucesso!");
                control.clearFields(TURMA_VIEW);
                control.updateListViewer(TURMA_VIEW);
            }
            case 1 -> control.showMessage("Turma já cadastrada!");
            case 2 -> control.showMessage("Data de início não pode ser maior que a data de fim!");
            case 3 -> control.showMessage("Data de início não pode ser menor que a data atual!");
            case 4 -> control.showMessage("Data de fim não pode ser menor que a data atual!");
            case 5 -> control.showMessage("Limite de alunos não pode ser menor que 1!");
        }
    }

    public void update(String id,
                       LocalDate dataInicio,
                       LocalDate dataFim,
                       LocalTime horario,
                       int limite,
                       Curso curso,
                       Professor responsavel) {
        int code = service.update(id, dataInicio, dataFim, horario, limite, curso, responsavel);
        switch (code) {
            case 0 -> {
                control.showMessage("Turma atualizada com sucesso!");
                control.clearFields(TURMA_VIEW);
                control.updateListViewer(TURMA_VIEW);
            }
            case 1 -> control.showMessage("Turma não encontrada!");
            case 2 -> control.showMessage("Data de início não pode ser maior que a data de fim!");
            case 3 -> control.showMessage("Data de início não pode ser menor que a data de início!");
            case 4 -> control.showMessage("Data de fim não pode ser menor que a data atual!");
            case 5 -> control.showMessage("Limite de alunos não pode ser menor que 1!");
        }
    }

    public void delete(String id) {
        int code = service.delete(id);
        switch (code) {
            case 0 -> {
                control.showMessage("Turma deletada com sucesso!");
                control.clearFields(TURMA_VIEW);
                control.updateListViewer(TURMA_VIEW);
            }
            case 1 -> control.showMessage("Turma não encontrada!");
            case 2 -> control.showMessage("Turma não pode ser deletada pois não está fechada!");
            case 3 -> control.showMessage("Turma não pode ser deletada pois ainda existem alunos matriculados!");
        }
    }

    public Turma get(String id) { return service.get(id); }

    public List<Turma> getAll() { return service.getAll(); }
}
