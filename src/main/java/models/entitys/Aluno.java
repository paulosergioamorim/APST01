package models.entitys;

import models.Sexo;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Aluno extends Pessoa {
    public Aluno(final long cpf, final String nome, final Sexo sexo, final LocalDate dataNascimento) {
        super(cpf, nome, sexo, dataNascimento);
    }

    public Aluno() { }
}
