package services;

import datasources.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static datasources.DAOFactory.*;

public class ServiceFactory {
    private static final AlunoDAO alunoDAO;
    private static final ProfessorDAO professorDAO;
    private static final CursoDAO cursoDAO;
    private static final TurmaDAO turmaDAO;
    private static final MatriculaDAO matriculaDAO;

    static {
        alunoDAO = createAlunoDAO();
        professorDAO = createProfessorDAO();
        cursoDAO = createCursoDAO();
        turmaDAO = createTurmaDAO();
        matriculaDAO = createMatriculaDAO();
    }

    public static @NotNull AlunoService createAlunoService() { return new AlunoService(alunoDAO); }

    public static @NotNull ProfessorService createProfessorService() { return new ProfessorService(professorDAO); }

    public static @NotNull CursoService createCursoService() { return new CursoService(cursoDAO); }

    public static @NotNull TurmaService createTurmaService() { return new TurmaService(turmaDAO); }

    public static @NotNull MatriculaService createMatriculaService() { return new MatriculaService(matriculaDAO); }
}
