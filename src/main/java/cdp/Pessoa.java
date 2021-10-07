package cdp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Pessoa {
    @Id
    private String nome;
    private LocalDate data_nascimento;
    private long cpf;

    public Pessoa(String nome, LocalDate data_nascimento, long cpf) {
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
    }

    public Pessoa() { }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getData_nascimento() { return data_nascimento; }

    public void setData_nascimento(LocalDate data_nascimento) { this.data_nascimento = data_nascimento; }

    public long getCpf() { return cpf; }

    public void setCpf(long cpf) { this.cpf = cpf; }
}
