package datasources;

import models.entitys.*;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DAOFactory {
    private final static String hibernate_url;
    private final static Configuration configuration;

    static {
        hibernate_url = "hibernate.cfg.xml";
        configuration = new Configuration().configure(hibernate_url);
    }

    @Contract(" -> new")
    public static @NotNull AlunoDAO createAlunoDAO() { return new AlunoDAO(configuration, Aluno.class); }

    @Contract(" -> new")
    public static @NotNull ProfessorDAO createProfessorDAO() { return new ProfessorDAO(configuration, Professor.class); }

    @Contract(" -> new")
    public static @NotNull CursoDAO createCursoDAO() { return new CursoDAO(configuration, Curso.class); }

    @Contract(" -> new")
    public static @NotNull TurmaDAO createTurmaDAO() { return new TurmaDAO(configuration, Turma.class); }

    @Contract(" -> new")
    public static @NotNull MatriculaDAO createMatriculaDAO() { return new MatriculaDAO(configuration, Matricula.class); }
}
