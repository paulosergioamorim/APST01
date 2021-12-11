package models.entitys;

import models.Sexo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Pessoa {
    private long cpf;
    private String nome;
    private Sexo sexo;
    private LocalDate dataNascimento;

    public Pessoa(final long cpf, final String nome, final Sexo sexo, final LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa() { }

    @Transient
    public int getIdade() { return LocalDate.now().getYear() - this.dataNascimento.getYear(); }

    @Id
    public long getCpf() { return this.cpf; }

    public void setCpf(final long cpf) { this.cpf = cpf; }

    @Column(length = 45, nullable = false)
    public String getNome() { return this.nome; }

    public void setNome(final String nome) { this.nome = nome; }

    @Column(nullable = false)
    public Sexo getSexo() { return this.sexo; }

    public void setSexo(final Sexo sexo) { this.sexo = sexo; }

    @Column(nullable = false)
    public LocalDate getDataNascimento() { return this.dataNascimento; }

    public void setDataNascimento(final LocalDate birthday) { this.dataNascimento = birthday; }

    @Override
    public String toString() { return this.nome; }
}
