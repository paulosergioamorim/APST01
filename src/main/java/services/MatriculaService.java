package services;

import database.MatriculaDAO;
import models.MatriculaID;
import models.entitys.Aluno;
import models.entitys.Matricula;
import models.entitys.Turma;

import java.time.LocalDate;
import java.util.List;

public record MatriculaService(MatriculaDAO dao) implements IMatriculaService {
    //TODO: Possivelmente retirar a nota como parâmetro, já que a nota inicia em 0
    @Override
    public int save(Aluno aluno, Turma turma, LocalDate dataMatricula, double nota) {
        MatriculaID matriculaID = new MatriculaID(aluno, turma);
        if (dao.exists(matriculaID))
            return 1;
        if (dataMatricula.isAfter(LocalDate.now()))
            return 2;
        if (nota < 0 || nota > 10)
            return 3;
        Matricula matricula = new Matricula(aluno, turma, dataMatricula, nota);
        dao.save(matricula);
        return 0;
    }

    //TODO: Colocar a data de matricula em função da data de início da turma
    @Override
    public int update(Aluno aluno, Turma turma, LocalDate dataMatricula, double nota) {
        MatriculaID matriculaID = new MatriculaID(aluno, turma);
        if (!dao.exists(matriculaID))
            return 1;
        if (dataMatricula != null && dataMatricula.isAfter(LocalDate.now()))
            return 2;
        if (nota != -1 && (nota < 0 || nota > 10))
            return 3;
        Matricula matricula = new Matricula(aluno, turma, dataMatricula, nota);
        if (dataMatricula != null)
            matricula.setDataMatricula(dataMatricula);
        if (nota != -1)
            matricula.setNota(nota);
        dao.update(matricula);
        return 0;
    }

    @Override
    public int delete(Aluno aluno, Turma turma) {
        MatriculaID matriculaID = new MatriculaID(aluno, turma);
        Matricula matricula = dao.find(matriculaID);
        if (matricula == null)
            return 1;
        if (!turma.isFechada())
            return 2;
        dao.delete(matricula);
        return 0;
    }

    @Override
    public Matricula get(Aluno aluno, Turma turma) {
        MatriculaID matriculaID = new MatriculaID(aluno, turma);
        return dao.find(matriculaID);
    }

    @Override
    public List<Matricula> getAll() { return dao.findAll(); }
}
