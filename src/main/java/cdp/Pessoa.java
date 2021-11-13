package cdp;

import java.time.LocalDate;

public abstract class Pessoa {
    private String nome;

    private LocalDate dataNascimento;

    private long cpf;

    public Pessoa(String nome, LocalDate dataNascimento, long cpf) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public int getIdade() { return LocalDate.now().getYear() - dataNascimento.getYear(); }

    public Pessoa() { }

    public long getCpf() { return cpf; }

    public void setCpf(long cpf) { this.cpf = cpf; }

    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(LocalDate data_nascimento) { this.dataNascimento = data_nascimento; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Override public String toString() { return nome; }
}
