package cdp;

import java.util.Date;

public abstract class Pessoa {
    private String nome;
    private Date data_nascimento;
    private long cpf;

    public Pessoa(String nome, Date data_nascimento, long cpf) {
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public Date getData_nascimento() { return data_nascimento; }

    public void setData_nascimento(Date data_nascimento) { this.data_nascimento = data_nascimento; }

    public long getCpf() { return cpf; }

    public void setCpf(long cpf) { this.cpf = cpf; }
}
