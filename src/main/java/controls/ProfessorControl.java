package controls;

import models.entitys.Professor;
import services.ProfessorService;

import java.util.List;

public record ProfessorControl(Control control, ProfessorService service) {

    public List<Professor> getAll() { return service.getAll(); }
}
