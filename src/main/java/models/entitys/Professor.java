package models.entitys;

import models.Pessoa;
import models.Sexo;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Professor extends Pessoa {
    private String titulo;

    public Professor(long cpf, String nome, Sexo sexo, LocalDate birthday, String titulo) {
        super(cpf, nome, sexo, birthday);
        this.titulo = titulo;
    }

    public Professor() { super(); }

    @Column(length = 25, nullable = false)
    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
}
