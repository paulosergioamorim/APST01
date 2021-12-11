package controls;

import models.Sexo;
import models.entitys.Professor;
import services.ProfessorService;

import java.time.LocalDate;
import java.util.List;

import static models.View.PROFESSOR_VIEW;

public record ProfessorControl(Control control, ProfessorService service) {
    public void save(final long cpf, final String nome, final Sexo sexo, final LocalDate dataNascimento, final String titulacao) {
        final int code = this.service.save(cpf, nome, sexo, dataNascimento, titulacao);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Professor salvo com sucesso!");
                this.control.clearFields(PROFESSOR_VIEW);
                this.control.updateListViewer(PROFESSOR_VIEW);
            }
            case 1 -> this.control.showMessage("Professor já cadastrado!");
            case 2 -> this.control.showMessage("Esse nome não é válido");
            case 3 -> this.control.showMessage("A data de nascimento não pode ser futura");
            case 4 -> this.control.showMessage("Titulação não pode ser vazia");
        }
    }

    public void update(final long cpf, final String nome, final Sexo sexo, final LocalDate dataNascimento, final String titulacao) {
        final int code = this.service.update(cpf, nome, sexo, dataNascimento, titulacao);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Professor atualizado com sucesso!");
                this.control.clearFields(PROFESSOR_VIEW);
                this.control.updateListViewer(PROFESSOR_VIEW);
            }
            case 1 -> this.control.showMessage("Professor não encontrado!");
            case 2 -> this.control.showMessage("Esse nome não é válido");
            case 3 -> this.control.showMessage("A data de nascimento não pode ser futura");
            case 4 -> this.control.showMessage("Titulação não pode ser vazia");
        }
    }

    public void delete(final long cpf) {
        final int code = this.service.delete(cpf);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Professor removido com sucesso!");
                this.control.clearFields(PROFESSOR_VIEW);
                this.control.updateListViewer(PROFESSOR_VIEW);
            }
            case 1 -> this.control.showMessage("Professor não encontrado!");
            case 2 -> this.control.showMessage("Professor não pode ser removido! Ele possui turmas ativas!");
        }
    }

    public Professor get(final long cpf) { return this.service.get(cpf); }

    public List<Professor> getAll() { return this.service.getAll(); }
}
