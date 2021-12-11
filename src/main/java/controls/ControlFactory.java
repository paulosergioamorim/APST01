package controls;

import org.jetbrains.annotations.Contract;
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

    @Contract("_ -> new")
    public static @NotNull AlunoControl createAlunoControl(final Control control) {
        return new AlunoControl(control, alunoService);
    }

    @Contract("_ -> new")
    public static @NotNull ProfessorControl createProfessorControl(final Control control) {
        return new ProfessorControl(control, professorService);
    }

    @Contract("_ -> new")
    public static @NotNull CursoControl createCursoControl(final Control control) {
        return new CursoControl(control, cursoService);
    }

    @Contract("_ -> new")
    public static @NotNull TurmaControl createTurmaControl(final Control control) {
        return new TurmaControl(control, turmaService);
    }

    @Contract("_ -> new")
    public static @NotNull MatriculaControl createMatriculaControl(final Control control) {
        return new MatriculaControl(control, matriculaService);
    }

    @Contract("_ -> new")
    public static @NotNull ViewControl createViewControl(final Control control) { return new ViewControl(control); }
}
