package controls;

import org.jetbrains.annotations.NotNull;
import services.*;

import static services.ServiceFactory.*;

public class ControlFactory {
    private static final AlunoService alunoService;
    private static final ProfessorService professorService;
    private static final CursoService cursoService;
    private static final TurmaService turmaService;
    private static final MatriculaService matriculaService;

    static {
        alunoService = createAlunoService();
        professorService = createProfessorService();
        cursoService = createCursoService();
        turmaService = createTurmaService();
        matriculaService = createMatriculaService();
    }

    public static @NotNull AlunoControl createAlunoControl(Control control) {
        return new AlunoControl(control, alunoService);
    }

    public static @NotNull ProfessorControl createProfessorControl(Control control) {
        return new ProfessorControl(control, professorService);
    }

    public static @NotNull CursoControl createCursoControl(Control control) {
        return new CursoControl(control, cursoService);
    }

    public static @NotNull TurmaControl createTurmaControl(Control control) {
        return new TurmaControl(control, turmaService);
    }

    public static @NotNull MatriculaControl createMatriculaControl(Control control) {
        return new MatriculaControl(control, matriculaService);
    }

    public static @NotNull ViewControl createViewControl(Control control) { return new ViewControl(control); }
}
