package cdp;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Aluno extends Pessoa {
    public Aluno(String nome, LocalDate dataNascimento, long cpf) {
        super(nome, dataNascimento, cpf);
    }

    public Aluno() { super(); }
}
