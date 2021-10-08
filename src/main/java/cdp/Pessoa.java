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
    private LocalDate data_nascimento;

    @Column(name = "cpf", nullable = false)
    private int cpf;

    public Pessoa(String nome, LocalDate data_nascimento, int cpf) {
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
    }

    public Pessoa() { }

    public int getCpf() { return cpf; }

    public void setCpf(int cpf) { this.cpf = cpf; }

    public LocalDate getData_nascimento() { return data_nascimento; }

    public void setData_nascimento(LocalDate data_nascimento) { this.data_nascimento = data_nascimento; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return LocalDate.now().getYear() - data_nascimento.getYear(); }
}
