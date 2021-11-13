package cdp;

import java.time.LocalDate;

public class Matricula {
    private double nota;
    private final LocalDate dataMatricula;
    private Aluno aluno;
    private Turma turma;

    { this.dataMatricula = LocalDate.now(); }

    public double getNota() { return nota; }

    public void setNota(double nota) { this.nota = nota; }

    public LocalDate getDataMatricula() { return dataMatricula; }

    public Aluno getAluno() { return aluno; }

    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Turma getTurma() { return turma; }

    public void setTurma(Turma turma) { this.turma = turma; }
}
