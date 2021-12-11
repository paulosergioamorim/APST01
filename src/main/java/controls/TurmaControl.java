package controls;

import models.entitys.*;
import services.TurmaService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static models.View.NOTAS_VIEW;
import static models.View.TURMA_VIEW;

public record TurmaControl(Control control, TurmaService service) {
    public void save(final int id,
                     final LocalDate dataInicio,
                     final LocalDate dataFim,
                     final LocalTime horario,
                     final int limite,
                     final Curso curso,
                     final Professor responsavel) {
        final int code = this.service.save(id, dataInicio, dataFim, horario, limite, curso, responsavel);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Turma salva com sucesso!");
                this.control.clearFields(TURMA_VIEW);
                this.control.updateListViewer(TURMA_VIEW);
            }
            case 1 -> this.control.showMessage("Turma já cadastrada!");
            case 2 -> this.control.showMessage("Data de início não pode ser maior que a data de fim!");
            case 3 -> this.control.showMessage("Data de início não pode ser menor que a data atual!");
            case 4 -> this.control.showMessage("Data de fim não pode ser menor que a data atual!");
            case 5 -> this.control.showMessage("Limite de alunos não pode ser menor que 1!");
        }
    }

    public void update(final int id,
                       final LocalDate dataInicio,
                       final LocalDate dataFim,
                       final LocalTime horario,
                       final int limite,
                       final Curso curso,
                       final Professor responsavel) {
        final int code = this.service.update(id, dataInicio, dataFim, horario, limite, curso, responsavel);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Turma atualizada com sucesso!");
                this.control.clearFields(TURMA_VIEW);
                this.control.updateListViewer(TURMA_VIEW);
            }
            case 1 -> this.control.showMessage("Turma não encontrada!");
            case 2 -> this.control.showMessage("Data de início não pode ser maior que a data de fim!");
            case 3 -> this.control.showMessage("Data de fim não pode ser menor que a data atual!");
            case 4 -> this.control.showMessage("Data de início não pode ser igual à data de fim!");
            case 5 -> this.control.showMessage("Limite de alunos deve ser maior que 0 e obedecer o número de matriculas já cadastradas!");
        }
    }

    public void close(final int id) {
        final int code = this.service.close(id);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Turma fechada com sucesso!");
                this.control.clearFields(NOTAS_VIEW);
            }
            case 1 -> this.control.showMessage("Turma não encontrada!");
            case 2 -> this.control.showMessage("Turma já está fechada!");
        }
    }

    public void delete(final int id) {
        final int code = this.service.delete(id);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Turma deletada com sucesso!");
                this.control.clearFields(TURMA_VIEW);
                this.control.updateListViewer(TURMA_VIEW);
            }
            case 1 -> this.control.showMessage("Turma não encontrada!");
            case 2 -> this.control.showMessage("Turma não pode ser deletada pois não está fechada!");
            case 3 -> this.control.showMessage("Turma não pode ser deletada pois ainda existem alunos matriculados!");
        }
    }

    public Turma get(final int id) { return this.service.get(id); }

    public List<Turma> getAll() { return this.service.getAll(); }

    public List<Aluno> getAlunos(final int id) {
        return this.service.get(id)
                .getMatriculas()
                .stream()
                .map(Matricula::getAluno)
                .toList();
    }

    public boolean allContainsNotas(final int id) {
        return this.service.get(id)
                .getMatriculas()
                .stream()
                .allMatch(m -> m.getNota() != null);
    }
}
