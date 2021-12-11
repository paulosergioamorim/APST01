package controls;

import models.entitys.Turma;
import services.MatriculaService;
import services.TurmaService;

public record NotasControl(Control control, TurmaService turmaService, MatriculaService matriculaService, Turma turma) {
}
