package controls;

import models.Sexo;
import models.entitys.Professor;
import services.ProfessorService;

import java.time.LocalDate;
import java.util.List;

import static models.View.PROFESSOR_VIEW;

public record ProfessorControl(Control control, ProfessorService service) {
    public void save(long cpf, String nome, Sexo sexo, LocalDate dataNascimento, String titulacao) {
        int code = service.save(cpf, nome, sexo, dataNascimento, titulacao);
        switch (code) {
            case 0 -> {
                control.showMessage("Professor salvo com sucesso!");
                control.clearFields(PROFESSOR_VIEW);
                control.updateListViewer(PROFESSOR_VIEW);
            }
            case 1 -> control.showMessage("Professor já cadastrado!");
            case 2 -> control.showMessage("Esse nome não é válido");
            case 3 -> control.showMessage("A data de nascimento não pode ser futura");
            case 4 -> control.showMessage("Titulação não pode ser vazia");
        }
    }

    public void update(long cpf, String nome, Sexo sexo, LocalDate dataNascimento, String titulacao) {
        int code = service.update(cpf, nome, sexo, dataNascimento, titulacao);
        switch (code) {
            case 0 -> {
                control.showMessage("Professor atualizado com sucesso!");
                control.clearFields(PROFESSOR_VIEW);
                control.updateListViewer(PROFESSOR_VIEW);
            }
            case 1 -> control.showMessage("Professor não encontrado!");
            case 2 -> control.showMessage("Esse nome não é válido");
            case 3 -> control.showMessage("A data de nascimento não pode ser futura");
            case 4 -> control.showMessage("Titulação não pode ser vazia");
        }
    }

    public void delete(long cpf) {
        int code = service.delete(cpf);
        switch (code) {
            case 0 -> {
                control.showMessage("Professor removido com sucesso!");
                control.clearFields(PROFESSOR_VIEW);
                control.updateListViewer(PROFESSOR_VIEW);
            }
            case 1 -> control.showMessage("Professor não encontrado!");
            case 2 -> control.showMessage("Professor não pode ser removido! Ele possui turmas ativas!");
        }
    }

    public Professor get(long cpf) { return service.get(cpf); }

    public List<Professor> getAll() { return service.getAll(); }
}
