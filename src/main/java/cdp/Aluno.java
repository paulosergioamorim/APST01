package cdp;

import java.time.LocalDate;

public class Aluno extends Pessoa {
	public Aluno(String nome, LocalDate dataNascimento, long cpf) {
		super(nome, dataNascimento, cpf);
	}
}