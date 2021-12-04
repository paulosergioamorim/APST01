package services;

import database.*;
import org.jetbrains.annotations.NotNull;

import static database.DAOFactory.*;

public class ServiceFactory {
    public static @NotNull AlunoService createAlunoService() {
        AlunoDAO alunoDAO = createAlunoDAO();
        return new AlunoService(alunoDAO);
    }

    public static @NotNull ProfessorService createProfessorService() {
        ProfessorDAO professorDAO = createProfessorDAO();
        return new ProfessorService(professorDAO);
    }

    public static @NotNull CursoService createCursoService() {
        CursoDAO cursoDAO = createCursoDAO();
        return new CursoService(cursoDAO);
    }

    public static @NotNull TurmaService createTurmaService() {
        TurmaDAO turmaDAO = createTurmaDAO();
        return new TurmaService(turmaDAO);
    }

    public static @NotNull MatriculaService createMatriculaService() {
        MatriculaDAO matriculaDAO = createMatriculaDAO();
        return new MatriculaService(matriculaDAO);
    }
}
