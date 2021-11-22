package cdp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Pessoa {
    private long cpf;
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento, long cpf) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public Pessoa() { }

    @Id
    @Column(name = "cpf")
    public long getCpf() { return cpf; }

    public void setCpf(long cpf) { this.cpf = cpf; }

    @Column(name = "nome", length = 45, nullable = false)
    public String getNome() { return nome; }

    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    @Column(name = "data_nascimento")
    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() { return nome; }
}
