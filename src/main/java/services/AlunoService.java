package services;

import database.AlunoDAO;
import models.Sexo;
import models.entitys.Aluno;

import java.time.LocalDate;
import java.util.List;

import static models.Format.pessoaNomePattern;

public record AlunoService(AlunoDAO dao) implements IAlunoService {
    @Override
    public int save(long cpf, String nome, Sexo sexo, LocalDate dataNascimento) {
        if (dao.exists(cpf))
            return 1;
        if (!nome.matches(pessoaNomePattern.pattern()))
            return 2;
        if (dataNascimento.isAfter(LocalDate.now()))
            return 3;
        Aluno aluno = new Aluno(cpf, nome, sexo, dataNascimento);
        dao.save(aluno);
        return 0;
    }

    @Override
    public int delete(long cpf) {
        Aluno aluno = dao.find(cpf);
        if (aluno == null)
            return 1;
        dao.delete(aluno);
        return 0;
    }

    @Override
    public int update(long cpf, String nome, Sexo sexo, LocalDate dataNascimento) {
        if (!dao.exists(cpf))
            return 1;
        if (nome != null && ! nome.matches(pessoaNomePattern.pattern()))
            return 2;
        if (dataNascimento != null && dataNascimento.isAfter(LocalDate.now()))
            return 3;
        Aluno aluno = dao.find(cpf);
        if (nome != null)
            aluno.setNome(nome);
        if (sexo != null)
            aluno.setSexo(sexo);
        dao.update(aluno);
        return 0;
    }

    @Override
    public Aluno get(long cpf) { return dao.find(cpf); }

    @Override
    public List<Aluno> getAll() { return dao.findAll(); }

}
