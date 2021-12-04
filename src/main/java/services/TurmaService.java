package services;

import database.TurmaDAO;

public record TurmaService(TurmaDAO dao) implements ITurmaService {
}
