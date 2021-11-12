package cdp;

import java.time.LocalDate;

public abstract class Pessoa {
    private final long cpf;
    private final String nome;
    private final LocalDate dataNascimento;

    public int getIdade() { return LocalDate.now().getYear() - dataNascimento.getYear(); }

    public Pessoa(String nome, LocalDate dataNascimento, long cpf) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public long getCpf() { return cpf; }

    public LocalDate getDataNascimento() { return dataNascimento; }

    public String getNome() { return nome; }

    @Override
    public String toString() { return nome; }
}
