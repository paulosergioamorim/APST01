package database;

import org.jetbrains.annotations.NotNull;

public class DAOFactory {
    private final static String HIBERNATE_URL = "hibernate.cfg.xml";

    public static @NotNull AlunoDAO createAlunoDAO() {
        return new AlunoDAO(HIBERNATE_URL);
    }

    public static @NotNull ProfessorDAO createProfessorDAO() {
        return new ProfessorDAO(HIBERNATE_URL);
    }

    public static @NotNull CursoDAO createCursoDAO() {
        return new CursoDAO(HIBERNATE_URL);
    }

    public static @NotNull TurmaDAO createTurmaDAO() {
        return new TurmaDAO(HIBERNATE_URL);
    }

    public static @NotNull MatriculaDAO createMatriculaDAO() {
        return new MatriculaDAO(HIBERNATE_URL);
    }
}
