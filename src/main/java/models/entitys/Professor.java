package models.entitys;

import models.Sexo;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Professor extends Pessoa {
    private String titulacao;

    public Professor(final long cpf,
                     final String nome,
                     final Sexo sexo,
                     final LocalDate birthday,
                     final String titulacao) {
        super(cpf, nome, sexo, birthday);
        this.titulacao = titulacao;
    }

    public Professor() { }

    @Column(length = 25, nullable = false)
    public String getTitulacao() { return this.titulacao; }

    public void setTitulacao(final String titulo) { this.titulacao = titulo; }
}
