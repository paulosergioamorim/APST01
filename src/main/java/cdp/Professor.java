package cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Professor extends Pessoa {
    private String titulacao;

    public Professor(String nome, LocalDate dataNascimento, long cpf, String titulacao) {
        super(nome, dataNascimento, cpf);
        this.titulacao = titulacao;
    }

    public Professor() { }

    @Column(name = "titulacao")
    public String getTitulacao() { return titulacao; }

    public void setTitulacao(String titulacao) { this.titulacao = titulacao; }
}
