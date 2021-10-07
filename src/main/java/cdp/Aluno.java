package cdp;

import java.time.LocalDate;

public class Aluno extends Pessoa {
    public Aluno(String nome, LocalDate data_nascimento, long cpf) {
        super(nome, data_nascimento, cpf);
    }
}
