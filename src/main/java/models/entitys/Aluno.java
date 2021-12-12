package models.entitys;

import models.Sexo;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Aluno extends Pessoa {
    public Aluno(long cpf, String nome, Sexo sexo, LocalDate dataNascimento) {
        super(cpf, nome, sexo, dataNascimento);
    }

    public Aluno() { }
}
