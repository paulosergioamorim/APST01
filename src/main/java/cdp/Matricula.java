package cdp;


import java.util.Date;

public class Matricula {
    private double nota;
    private Date dataMatricula;

    public Matricula(double nota, Date dataMatricula, Aluno aluno, Turma turma) {
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

    public Date getDataMatricula() { return dataMatricula; }

    public void setDataMatricula(Date dataMatricula) { this.dataMatricula = dataMatricula; }

    public Aluno getAluno() { return aluno; }

    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Turma getTurma() { return turma; }

    public void setTurma(Turma turma) { this.turma = turma; }
}
