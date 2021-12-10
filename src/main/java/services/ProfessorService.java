package services;

import datasources.ProfessorDAO;
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
        Professor responsavel = dao.get(cpf);

        nome = nome == null ? responsavel.getNome() : nome;
        sexo = sexo == null ? responsavel.getSexo() : sexo;
        dataNascimento = dataNascimento == null ? responsavel.getDataNascimento() : dataNascimento;
        titulacao = titulacao == null ? responsavel.getTitulacao() : titulacao;

        if (!nome.matches(pessoaNomePattern.pattern()))
            return 2;
        if (dataNascimento.isAfter(LocalDate.now()))
            return 3;
        if (titulacao.isEmpty())
            return 4;

        responsavel = new Professor(cpf, nome, sexo, dataNascimento, titulacao);
        dao.update(responsavel);
        return 0;
    }

    @Override
    public int delete(long cpf) {
        Professor professor = dao.get(cpf);
        if (professor == null)
            return 1;
        if (dao.isActive(professor))
            return 2;
        dao.delete(professor);
        return 0;
    }

    @Override
    public Professor get(long cpf) { return dao.get(cpf); }

    @Override
    public List<Professor> getAll() { return dao.toList(); }
}
