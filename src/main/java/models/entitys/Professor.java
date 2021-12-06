package models.entitys;

import models.Sexo;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Professor extends Pessoa {
    private String titulacao;

    public Professor(long cpf, String nome, Sexo sexo, LocalDate birthday, String titulacao) {
        super(cpf, nome, sexo, birthday);
        this.titulacao = titulacao;
    }

    public Professor() { super(); }

    @Column(length = 25, nullable = false)
    public String getTitulacao() { return titulacao; }

    public void setTitulacao(String titulo) { this.titulacao = titulo; }
}
