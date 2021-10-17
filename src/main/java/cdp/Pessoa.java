package cdp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Pessoa {
    @Id
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "cpf", nullable = false)
    private long cpf;

    public Pessoa(String nome, LocalDate dataNascimento, long cpf) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public Pessoa() { }

    public long getCpf() { return cpf; }

    public void setCpf(long cpf) { this.cpf = cpf; }

    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(LocalDate data_nascimento) { this.dataNascimento = data_nascimento; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return LocalDate.now().getYear() - dataNascimento.getYear(); }
}
