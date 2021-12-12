package services;

import datasources.AlunoDAO;
import models.Sexo;
import models.entitys.Aluno;

import java.time.LocalDate;
import java.util.List;

import static models.Format.pessoaNomePattern;

public record AlunoService(AlunoDAO dao) implements IAlunoService {
    @Override
    public int save(long cpf, String nome, Sexo sexo, LocalDate dataNascimento) {
        if (dao.exists(cpf)) return 1;
        if (!nome.matches(pessoaNomePattern.pattern())) return 2;
        if (dataNascimento.isAfter(LocalDate.now())) return 3;
        Aluno aluno = new Aluno(cpf, nome, sexo, dataNascimento);
        dao.save(aluno);
        return 0;
    }

    @Override
    public int update(long cpf, String nome, Sexo sexo, LocalDate dataNascimento) {
        if (!dao.exists(cpf)) return 1;
        Aluno aluno = dao.get(cpf);

        nome = nome == null ? aluno.getNome() : nome;
        sexo = sexo == null ? aluno.getSexo() : sexo;
        dataNascimento = dataNascimento == null ? aluno.getDataNascimento() : dataNascimento;

        if (!nome.matches(pessoaNomePattern.pattern())) return 2;
        if (dataNascimento.isAfter(LocalDate.now())) return 3;

        aluno = new Aluno(cpf, nome, sexo, dataNascimento);
        dao.update(aluno);
        return 0;
    }

    @Override
    public int delete(long cpf) {
        if (!dao.exists(cpf)) return 1;
        Aluno aluno = dao.get(cpf);
        dao.delete(aluno);
        return 0;
    }

    @Override
    public Aluno get(long cpf) { return dao.get(cpf); }

    @Override
    public List<Aluno> getAll() { return dao.toList(); }
}
