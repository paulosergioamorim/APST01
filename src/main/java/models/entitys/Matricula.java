package models.entitys;

import models.MatriculaID;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Matricula {
    private MatriculaID matriculaID;
    private LocalDate dataMatricula;
    private Aluno aluno;
    private Turma turma;
    private double nota;

    public Matricula(Aluno aluno, Turma turma, LocalDate dataMatricula, double nota) {
        this.matriculaID = new MatriculaID(aluno, turma);
        this.dataMatricula = dataMatricula;
        this.nota = nota;
    }

    public Matricula() { }

    @EmbeddedId
    @Access(AccessType.PROPERTY)
    public MatriculaID getMatriculaID() { return matriculaID; }

    public void setMatriculaID(MatriculaID matriculaID) { this.matriculaID = matriculaID; }

    @Column(nullable = false)
    public LocalDate getDataMatricula() { return dataMatricula; }

    public void setDataMatricula(LocalDate date) { this.dataMatricula = date; }

    @Column(nullable = false)
    public double getNota() { return nota; }

    public void setNota(double nota) { this.nota = nota; }

    @ManyToOne
    @JoinColumn(name = "aluno_cpf", insertable = false, updatable = false)
    public Aluno getAluno() { return aluno; }

    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    @ManyToOne
    @JoinColumn(name = "turma_id", insertable = false, updatable = false)
    public Turma getTurma() { return turma; }

    public void setTurma(Turma turma) { this.turma = turma; }

    @Override
    public String toString() { return this.getAluno() + " - " + this.getTurma(); }
}
