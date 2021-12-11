package controls;

import models.Sexo;
import models.entitys.Aluno;
import services.AlunoService;

import java.time.LocalDate;
import java.util.List;

import static models.View.ALUNO_VIEW;

public record AlunoControl(Control control, AlunoService service) {
    public void save(final long cpf, final String nome, final Sexo sexo, final LocalDate dataNascimento) {
        final int code = this.service.save(cpf, nome, sexo, dataNascimento);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Aluno cadastrado com sucesso!");
                this.control.clearFields(ALUNO_VIEW);
                this.control.updateListViewer(ALUNO_VIEW);
            }
            case 1 -> this.control.showMessage("Aluno já cadastrado!");
            case 2 -> this.control.showMessage("Esse nome não é válido");
            case 3 -> this.control.showMessage("A data de nascimento não pode ser futura");
        }
    }

    public void delete(final long cpf) {
        final int code = this.service.delete(cpf);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Aluno removido com sucesso!");
                this.control.updateListViewer(ALUNO_VIEW);
            }
            case 1 -> this.control.showMessage("Aluno não encontrado!");
        }
    }

    public void update(final long cpf, final String nome, final Sexo sexo, final LocalDate dataNascimento) {
        final int code = this.service.update(cpf, nome, sexo, dataNascimento);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Aluno atualizado com sucesso!");
                this.control.clearFields(ALUNO_VIEW);
                this.control.updateListViewer(ALUNO_VIEW);
            }
            case 1 -> this.control.showMessage("Aluno não encontrado!");
            case 2 -> this.control.showMessage("Esse nome não é válido");
            case 3 -> this.control.showMessage("A data de nascimento não pode ser futura");
        }
    }

    public Aluno get(final long cpf) { return this.service.get(cpf); }

    public List<Aluno> getAll() { return this.service.getAll(); }
}
