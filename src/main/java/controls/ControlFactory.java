package controls;

import org.jetbrains.annotations.NotNull;
import services.AlunoService;
import services.CursoService;
import services.ProfessorService;
import services.TurmaService;

import static services.ServiceFactory.*;

public class ControlFactory {
    public static @NotNull AlunoControl createAlunoControl(Control control) {
        AlunoService alunoService = createAlunoService();
        return new AlunoControl(control, alunoService);
    }

    public static @NotNull ProfessorControl createProfessorControl(Control control) {
        ProfessorService professorService = createProfessorService();
        return new ProfessorControl(control, professorService);
    }

    public static @NotNull CursoControl createCursoControl(Control control) {
        CursoService cursoService = createCursoService();
        return new CursoControl(control, cursoService);
    }

    public static @NotNull TurmaControl createTurmaControl(Control control) {
        TurmaService turmaService = createTurmaService();
        return new TurmaControl(control, turmaService);
    }

    public static @NotNull ViewControl createViewControl(Control control) { return new ViewControl(control); }
}
