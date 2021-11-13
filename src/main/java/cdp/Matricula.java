package cdp;

import java.time.LocalDate;

public class Matricula {
    private double nota;
    private final LocalDate dataMatricula;
    private final Aluno aluno;
    private final Turma turma;

    public Matricula(Aluno aluno, Turma turma) {
        this.aluno = aluno;
        this.turma = turma;
        this.dataMatricula = LocalDate.now();
    }

    public double getNota() { return nota; }

    public void setNota(double nota) { this.nota = nota; }

    public LocalDate getDataMatricula() { return dataMatricula; }

    public Aluno getAluno() { return aluno; }

    public Turma getTurma() { return turma; }

}
