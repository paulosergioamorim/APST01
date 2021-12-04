package services;

import models.entitys.Aluno;
import models.entitys.Curso;

import java.util.List;

public interface ICursoService {
    int save(int id, String nome, String sigla, int cargaHoraria);
    int update(int id, String nome, String sigla, int cargaHoraria);
    int delete(int id);
    Curso get(int id);
    List<Curso> getAll();
}
