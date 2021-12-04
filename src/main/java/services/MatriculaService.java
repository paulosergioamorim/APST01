package services;

import database.MatriculaDAO;

public record MatriculaService(MatriculaDAO dao) implements IMatriculaService {
}
