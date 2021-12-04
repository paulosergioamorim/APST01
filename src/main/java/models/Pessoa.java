package models;

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

    public Pessoa(long cpf, String nome, Sexo sexo, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa() { }

    @Transient
    public int getIdade() { return LocalDate.now().getYear() - dataNascimento.getYear(); }

    @Id
    public long getCpf() { return cpf; }

    public void setCpf(long cpf) { this.cpf = cpf; }

    @Column(length = 45, nullable = false)
    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Column(nullable = false)
    public Sexo getSexo() { return sexo; }

    public void setSexo(Sexo sexo) { this.sexo = sexo; }

    @Column(columnDefinition = "date")
    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(LocalDate birthday) { this.dataNascimento = birthday; }

    @Override
    public String toString() { return nome; }
}
