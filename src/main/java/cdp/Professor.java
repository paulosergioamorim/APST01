package cdp;

import java.time.LocalDate;

public class Professor extends Pessoa {
    private String titulacao;

    public Professor(String nome, LocalDate dataNascimento, long cpf, String titulacao) {
        super(nome, dataNascimento, cpf);
        this.titulacao = titulacao;
    }
}
