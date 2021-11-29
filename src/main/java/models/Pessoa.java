package models;

import java.time.LocalDate;

public abstract class Pessoa {
    private final long cpf;
    private final String nome;
    private final String email;
    private final Sexo sexo;
    private final LocalDate birthday;

    public Pessoa(long cpf, String nome, String email, Sexo sexo, LocalDate birthday) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        this.birthday = birthday;
    }

    public long getCpf() { return cpf; }

    public String getNome() { return nome; }

    public String getEmail() { return email; }

    public Sexo getSexo() { return sexo; }

    public LocalDate getBirthday() { return birthday; }
}
