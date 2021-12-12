package controls;

import models.Estado;
import models.entitys.*;
import services.TurmaService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static models.Estado.FECHADA;
import static models.Estado.MATRICULAS_ABERTAS;
import static models.View.NOTAS_VIEW;
import static models.View.TURMA_VIEW;

public record TurmaControl(Control control, TurmaService service) {
    public void save(int id,
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

    public void update(int id,
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
            case 3 -> control.showMessage("Data de fim não pode ser menor que a data atual!");
            case 4 -> control.showMessage("Data de início não pode ser igual à data de fim!");
            case 5 -> control.showMessage("Limite de alunos deve ser maior que 0 e obedecer o número de matriculas já cadastradas!");
        }
    }

    public void close(int id) {
        int code = service.close(id);
        switch (code) {
            case 0 -> {
                control.showMessage("Turma fechada com sucesso!");
                control.clearFields(NOTAS_VIEW);
                control.updateListViewer(NOTAS_VIEW);
            }
            case 1 -> control.showMessage("Turma não encontrada!");
            case 2 -> control.showMessage("Turma já está fechada!");
        }
    }

    public void delete(int id) {
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

    public Turma get(int id) { return service.get(id); }

    public List<Turma> getAll() { return service.getAll(); }

    public List<Turma> getAllTurmasVagas() {
        return service.getAll()
                .stream()
                .filter(t -> t.getVagas() > 0 && t.getEstado() == MATRICULAS_ABERTAS)
                .toList();
    }

    public List<Turma> getAllTurmasNonClosed() {
        return service.getAll()
                .stream()
                .filter(t -> t.getEstado() != FECHADA)
                .toList();
    }

    public List<Aluno> getAlunos(int id) {
        return service.get(id)
                .getMatriculas()
                .stream()
                .map(Matricula::getAluno)
                .toList();
    }

    public boolean allContainsNotas(int id) {
        return service.get(id)
                .getMatriculas()
                .stream()
                .allMatch(m -> m.getNota() != null);
    }
}
