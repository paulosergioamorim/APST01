package models.entitys;

import models.Sexo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Aluno extends Pessoa {
    public Aluno(long cpf, String nome, Sexo sexo, LocalDate dataNascimento) {
        super(cpf, nome, sexo, dataNascimento);
    }

    public Aluno() { }
}
