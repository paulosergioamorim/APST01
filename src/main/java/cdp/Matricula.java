package cdp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "matricula")
public class Matricula implements Serializable {
    private double nota;
    private LocalDate dataMatricula;
    private Aluno aluno;
    private Turma turma;

    public Matricula(Aluno aluno, Turma turma) {
        this.nota = 0;
        this.aluno = aluno;
        this.turma = turma;
        this.dataMatricula = LocalDate.now();
    }

    public Matricula() { }

    @Column(name = "nota", nullable = false)
    public double getNota() { return nota; }

    public void setNota(double nota) { this.nota = nota; }

    @Column(name = "data_matricula", nullable = false)
    public LocalDate getDataMatricula() { return dataMatricula; }

    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }

    @Transient
    public Turma getTurma() { return turma; }

    public void setTurma(Turma turma) { this.turma = turma; }

    public Aluno getAluno() { return aluno; }

    public void setAluno(Aluno aluno) { this.aluno = aluno; }
}
