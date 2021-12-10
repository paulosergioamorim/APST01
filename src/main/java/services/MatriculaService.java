package services;

import datasources.MatriculaDAO;
import models.MatriculaID;
import models.entitys.Matricula;

import java.time.LocalDate;
import java.util.List;

import static models.Estado.FECHADA;

public record MatriculaService(MatriculaDAO dao) implements IMatriculaService {
    //TODO: Possivelmente retirar a nota como parâmetro, já que a nota inicia em 0
    @Override
    public int save(MatriculaID matriculaID, LocalDate dataMatricula, double nota) {
        if (dao.exists(matriculaID))
            return 1;
        if (dataMatricula.isAfter(LocalDate.now()))
            return 2;
        if (dataMatricula.isAfter(matriculaID.getTurma().getDataInicio()))
            return 3;
        if (nota < 0 || nota > 10)
            return 4;
        Matricula matricula = new Matricula(matriculaID, dataMatricula, nota);
        dao.save(matricula);
        return 0;
    }

    //TODO: Colocar a data de matricula em função da data de início da turma
    @Override
    public int update(MatriculaID matriculaID, LocalDate dataMatricula, double nota) {
        if (!dao.exists(matriculaID))
            return 1;
        Matricula matricula = dao.get(matriculaID);

        dataMatricula = dataMatricula == null ? matricula.getDataMatricula() : dataMatricula;
        nota = nota == 0 ? matricula.getNota() : nota;

        if (dataMatricula.isAfter(LocalDate.now()))
            return 2;
        if (dataMatricula.isAfter(matricula.getMatriculaID().getTurma().getDataInicio()))
            return 3;
        if (nota < 0 || nota > 10)
            return 4;

        matricula = new Matricula(matriculaID, dataMatricula, nota);
        dao.update(matricula);
        return 0;
    }

    @Override
    public int delete(MatriculaID matriculaID) {
        Matricula matricula = dao.get(matriculaID);
        if (matricula == null)
            return 1;
        if (matriculaID.getTurma().getEstado() != FECHADA)
            return 2;
        dao.delete(matricula);
        return 0;
    }

    @Override
    public Matricula get(MatriculaID matriculaID) { return dao.get(matriculaID); }

    @Override
    public List<Matricula> getAll() { return dao.toList(); }
}
