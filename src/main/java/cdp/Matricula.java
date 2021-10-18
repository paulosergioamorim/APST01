package cdp;

import java.time.LocalDate;

public class Matricula {
    private double nota;
    private LocalDate dataMatricula;

    public Matricula(double nota, LocalDate dataMatricula, Aluno aluno, Turma turma) {
        this.nota = nota;
        this.dataMatricula = dataMatricula;
        this.aluno = aluno;
        this.turma = turma;
    }

    // relationships
    private Aluno aluno;
    private Turma turma;

    public double getNota() { return nota; }

    public void setNota(double nota) { this.nota = nota; }

    public LocalDate getDataMatricula() { return dataMatricula; }

    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }

    public Aluno getAluno() { return aluno; }

    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Turma getTurma() { return turma; }

    public void setTurma(Turma turma) { this.turma = turma; }
}
