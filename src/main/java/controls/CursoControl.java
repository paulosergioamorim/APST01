package controls;

import models.entitys.Curso;
import services.CursoService;

import java.util.List;

import static models.View.CURSO_VIEW;

public record CursoControl(Control control, CursoService service) {

    public void save(int id, String nome, String sigla, int cargaHoraria) {
        int code = service.save(id, nome, sigla, cargaHoraria);
        switch (code) {
            case 0 -> {
                control.showMessage("Curso salvo com sucesso!");
                control.clearFields(CURSO_VIEW);
                control.updateListViewer(CURSO_VIEW);
            }
            case 1 -> control.showMessage("Curso já cadastrado!");
            case 2 -> control.showMessage("Esse nome não é válido!");
            case 3 -> control.showMessage("Já existe um curso com essa sigla!");
            case 4 -> control.showMessage("A sigla pode conter no máximo 5 caracteres!");
            case 5 -> control.showMessage("Essa carga horária não é válida!");
        }
    }

    public void delete(int id) {
        int code = service.delete(id);
        switch (code) {
            case 0 -> {
                control.showMessage("Curso excluído com sucesso!");
                control.updateListViewer(CURSO_VIEW);
            }
            case 1 -> control.showMessage("Curso não encontrado!");
            case 2 -> control.showMessage("Curso não pode ser excluído pois possuí turmas!");
        }
    }

    public void update(int id, String nome, String sigla, int cargaHoraria) {
        int code = service.update(id, nome, sigla, cargaHoraria);
        switch (code) {
            case 0 -> {
                control.showMessage("Curso atualizado com sucesso!");
                control.clearFields(CURSO_VIEW);
                control.updateListViewer(CURSO_VIEW);
            }
            case 1 -> control.showMessage("Curso não encontrado!");
            case 2 -> control.showMessage("Esse nome não é válido!");
            case 3 -> control.showMessage("Já existe um curso com essa sigla!");
            case 4 -> control.showMessage("A sigla pode conter no máximo 5 caracteres!");
            case 5 -> control.showMessage("Essa carga horária não é válida!");
        }
    }

    public Curso get(int id) { return service.get(id); }

    public List<Curso> getAll() { return service.getAll(); }
}
