package cdp;

import java.util.ArrayList;
import java.util.List;

public class Curso {
	private String      nome;
	private int         cargaHoraria;
	private List<Turma> turmas;

	public Curso(String nome, int cargaHoraria) {
		this.nome         = nome;
		this.cargaHoraria = cargaHoraria;
		this.turmas       = new ArrayList<>();
	}

	public String getNome()                       {return nome;}

	public void setNome(String nome)              {this.nome = nome;}

	public int getCargaHoraria()                  {return cargaHoraria;}

	public void setCargaHoraria(int cargaHoraria) {this.cargaHoraria = cargaHoraria;}

	public List<Turma> getTurmas()                {return turmas;}

	public void setTurmas(List<Turma> turmas)     {this.turmas = turmas;}

	public void addTurma(Turma turma)             {this.turmas.add(turma);}

	@Override
	public String toString()                      {return nome;}
}
