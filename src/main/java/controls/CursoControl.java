package controls;

import models.entitys.Curso;
import services.CursoService;

import java.util.List;

import static models.View.CURSO_VIEW;

public record CursoControl(Control control, CursoService service) {

    public void save(final int id, final String nome, final String sigla, final int cargaHoraria) {
        final int code = this.service.save(id, nome, sigla, cargaHoraria);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Curso salvo com sucesso!");
                this.control.clearFields(CURSO_VIEW);
                this.control.updateListViewer(CURSO_VIEW);
            }
            case 1 -> this.control.showMessage("Curso já cadastrado!");
            case 2 -> this.control.showMessage("Esse nome não é válido!");
            case 3 -> this.control.showMessage("Já existe um curso com essa sigla!");
            case 4 -> this.control.showMessage("A sigla pode conter no máximo 5 caracteres!");
            case 5 -> this.control.showMessage("Essa carga horária não é válida!");
        }
    }

    public void delete(final int id) {
        final int code = this.service.delete(id);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Curso excluído com sucesso!");
                this.control.updateListViewer(CURSO_VIEW);
            }
            case 1 -> this.control.showMessage("Curso não encontrado!");
            case 2 -> this.control.showMessage("Curso não pode ser excluído pois possuí turmas ativas!");
        }
    }

    public void update(final int id, final String nome, final String sigla, final int cargaHoraria) {
        final int code = this.service.update(id, nome, sigla, cargaHoraria);
        switch (code) {
            case 0 -> {
                this.control.showMessage("Curso atualizado com sucesso!");
                this.control.clearFields(CURSO_VIEW);
                this.control.updateListViewer(CURSO_VIEW);
            }
            case 1 -> this.control.showMessage("Curso não encontrado!");
            case 2 -> this.control.showMessage("Esse nome não é válido!");
            case 3 -> this.control.showMessage("Já existe um curso com essa sigla!");
            case 4 -> this.control.showMessage("A sigla pode conter no máximo 5 caracteres!");
            case 5 -> this.control.showMessage("Essa carga horária não é válida!");
        }
    }

    public Curso get(final int id) { return this.service.get(id); }

    public List<Curso> getAll() { return this.service.getAll(); }
}
