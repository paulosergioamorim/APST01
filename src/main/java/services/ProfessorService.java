package services;

import datasources.ProfessorDAO;
import models.Sexo;
import models.entitys.Professor;

import java.time.LocalDate;
import java.util.List;

import static models.Format.pessoaNomePattern;

public record ProfessorService(ProfessorDAO dao) implements IProfessorService {
    @Override
    public int save(final long cpf, final String nome, final Sexo sexo, final LocalDate dataNascimento, final String titulacao) {
        if (this.dao.exists(cpf))
            return 1;
        if (!nome.matches(pessoaNomePattern.pattern()))
            return 2;
        if (dataNascimento.isAfter(LocalDate.now()))
            return 3;
        if (titulacao.isEmpty())
            return 4;
        final Professor professor = new Professor(cpf, nome, sexo, dataNascimento, titulacao);
        this.dao.save(professor);
        return 0;
    }

    @Override
    public int update(final long cpf, String nome, Sexo sexo, LocalDate dataNascimento, String titulacao) {
        if (! this.dao.exists(cpf))
            return 1;
        Professor responsavel = this.dao.get(cpf);

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
        this.dao.update(responsavel);
        return 0;
    }

    @Override
    public int delete(final long cpf) {
        final Professor professor = this.dao.get(cpf);
        if (professor == null)
            return 1;
        if (this.dao.isActive(professor))
            return 2;
        this.dao.delete(professor);
        return 0;
    }

    @Override
    public Professor get(final long cpf) { return this.dao.get(cpf); }

    @Override
    public List<Professor> getAll() { return this.dao.toList(); }
}
