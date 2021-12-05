package services;

import database.DAO;
import database.ProfessorDAO;
import models.Format;
import models.Sexo;
import models.entitys.Professor;

import java.time.LocalDate;
import java.util.List;

import static models.Format.pessoaNomePattern;

public record ProfessorService(ProfessorDAO dao) implements IProfessorService {
    @Override
    public int save(long cpf, String nome, Sexo sexo, LocalDate dataNascimento, String titulacao) {
        if (dao.exists(cpf))
            return 1;
        if (!nome.matches(pessoaNomePattern.pattern()))
            return 2;
        if (dataNascimento.isAfter(LocalDate.now()))
            return 3;
        if (titulacao.isEmpty())
            return 4;
        Professor professor = new Professor(cpf, nome, sexo, dataNascimento, titulacao);
        dao.save(professor);
        return 0;
    }

    @Override
    public int update(long cpf, String nome, Sexo sexo, LocalDate dataNascimento, String titulacao) {
        if (!dao.exists(cpf))
            return 1;
        if (nome != null && ! nome.matches(pessoaNomePattern.pattern()))
            return 2;
        if (dataNascimento != null && dataNascimento.isAfter(LocalDate.now()))
            return 3;
        if (titulacao != null && titulacao.isEmpty())
            return 4;
        Professor professor = dao.find(cpf);
        if (nome != null)
            professor.setNome(nome);
        if (sexo != null)
            professor.setSexo(sexo);
        if (dataNascimento != null)
            professor.setDataNascimento(dataNascimento);
        if (titulacao != null)
            professor.setTitulacao(titulacao);
        dao.update(professor);
        return 0;
    }

    @Override
    public int delete(long cpf) {
        Professor professor = dao.find(cpf);
        if (professor == null)
            return 1;
        if (dao.isActive(professor))
            return 2;
        dao.delete(professor);
        return 0;
    }

    @Override
    public Professor get(long cpf) { return dao.find(cpf); }

    @Override
    public List<Professor> getAll() { return dao.findAll(); }
}
