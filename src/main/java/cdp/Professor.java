package cdp;

import java.util.Date;

public class Professor extends Pessoa {
    private Titulo titulacao;

    public Professor(String nome, Date data_nascimento, long cpf, Titulo titulacao) {
        super(nome, data_nascimento, cpf);
        this.titulacao = titulacao;
    }

    public Titulo getTitulacao() { return titulacao; }

    public void setTitulacao(Titulo titulacao) { this.titulacao = titulacao; }
}
