package cdp;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Professor extends Pessoa {
    private Titulo titulacao;

    public Professor(String nome, LocalDate dataNascimento, long cpf, Titulo titulacao) {
        super(nome, dataNascimento, cpf);
        this.titulacao = titulacao;
    }

    public Professor() { super(); }

    public Titulo getTitulacao() { return titulacao; }

    public void setTitulacao(Titulo titulacao) { this.titulacao = titulacao; }
}
