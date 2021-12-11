package services;

import datasources.AlunoDAO;
import models.Sexo;
import models.entitys.Aluno;

import java.time.LocalDate;
import java.util.List;

import static models.Format.pessoaNomePattern;

public record AlunoService(AlunoDAO dao) implements IAlunoService {
    @Override
    public int save(final long cpf, final String nome, final Sexo sexo, final LocalDate dataNascimento) {
        if (this.dao.exists(cpf)) return 1;
        if (!nome.matches(pessoaNomePattern.pattern())) return 2;
        if (dataNascimento.isAfter(LocalDate.now())) return 3;
        final Aluno aluno = new Aluno(cpf, nome, sexo, dataNascimento);
        this.dao.save(aluno);
        return 0;
    }

    @Override
    public int update(final long cpf, String nome, Sexo sexo, LocalDate dataNascimento) {
        if (!this.dao.exists(cpf)) return 1;
        Aluno aluno = this.dao.get(cpf);

        nome = nome == null ? aluno.getNome() : nome;
        sexo = sexo == null ? aluno.getSexo() : sexo;
        dataNascimento = dataNascimento == null ? aluno.getDataNascimento() : dataNascimento;

        if (!nome.matches(pessoaNomePattern.pattern())) return 2;
        if (dataNascimento.isAfter(LocalDate.now())) return 3;

        aluno = new Aluno(cpf, nome, sexo, dataNascimento);
        this.dao.update(aluno);
        return 0;
    }

    @Override
    public int delete(final long cpf) {
        if (!this.dao.exists(cpf)) return 1;
        final Aluno aluno = this.dao.get(cpf);
        this.dao.delete(aluno);
        return 0;
    }

    @Override
    public Aluno get(final long cpf) { return this.dao.get(cpf); }

    @Override
    public List<Aluno> getAll() { return this.dao.toList(); }
}
