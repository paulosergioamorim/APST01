package controls;

import models.Sexo;
import models.entitys.Aluno;
import models.entitys.Matricula;
import models.entitys.Turma;
import org.jetbrains.annotations.NotNull;
import services.AlunoService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static models.View.ALUNO_VIEW;

public record AlunoControl(Control control, AlunoService service) {
    public void save(long cpf, String nome, Sexo sexo, LocalDate dataNascimento) {
        int code = service.save(cpf, nome, sexo, dataNascimento);
        switch (code) {
            case 0 -> {
                control.showMessage("Aluno cadastrado com sucesso!");
                control.clearFields(ALUNO_VIEW);
                control.updateListViewer(ALUNO_VIEW);
            }
            case 1 -> control.showMessage("Aluno já cadastrado!");
            case 2 -> control.showMessage("Esse nome não é válido");
            case 3 -> control.showMessage("A data de nascimento não pode ser futura");
        }
    }

    public void delete(long cpf) {
        int code = service.delete(cpf);
        switch (code) {
            case 0 -> {
                control.showMessage("Aluno removido com sucesso!");
                control.updateListViewer(ALUNO_VIEW);
            }
            case 1 -> control.showMessage("Aluno não encontrado!");
        }
    }

    public void update(long cpf, String nome, Sexo sexo, LocalDate dataNascimento) {
        int code = service.update(cpf, nome, sexo, dataNascimento);
        switch (code) {
            case 0 -> {
                control.showMessage("Aluno atualizado com sucesso!");
                control.clearFields(ALUNO_VIEW);
                control.updateListViewer(ALUNO_VIEW);
            }
            case 1 -> control.showMessage("Aluno não encontrado!");
            case 2 -> control.showMessage("Esse nome não é válido");
            case 3 -> control.showMessage("A data de nascimento não pode ser futura");
        }
    }

    public Aluno get(long cpf) { return service.get(cpf); }

    public List<Aluno> getAll() { return service.getAll(); }
}
