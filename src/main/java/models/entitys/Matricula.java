package models.entitys;

import models.MatriculaID;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Matricula {
    private MatriculaID matriculaID;
    private LocalDate dataMatricula;
    private Aluno aluno;
    private Turma turma;
    private Double nota;

    public Matricula(final MatriculaID matriculaID, final LocalDate dataMatricula, final Double nota) {
        this.matriculaID = matriculaID;
        this.dataMatricula = dataMatricula;
        this.nota = nota;
    }

    public Matricula() { }

    @EmbeddedId
    @Access(AccessType.PROPERTY)
    public MatriculaID getMatriculaID() { return this.matriculaID; }

    public void setMatriculaID(final MatriculaID matriculaID) { this.matriculaID = matriculaID; }
    
    @Transient
    public String getSituacao() {
        if (this.nota == null) return "Cursando";
        else if (this.nota >= 6) return "Aprovado";
        else return "Reprovado";
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_cpf", nullable = false, insertable = false, updatable = false)
    public Aluno getAluno() { return this.aluno; }

    public void setAluno(final Aluno aluno) { this.aluno = aluno; }

    @ManyToOne(optional = false)
    @JoinColumn(name = "turma_id", nullable = false, insertable = false, updatable = false)
    public Turma getTurma() { return this.turma; }

    public void setTurma(final Turma turma) { this.turma = turma; }

    @Column(nullable = false)
    public LocalDate getDataMatricula() { return this.dataMatricula; }

    public void setDataMatricula(final LocalDate date) { this.dataMatricula = date; }

    @Column
    public Double getNota() { return this.nota; }

    public void setNota(final Double nota) { this.nota = nota; }

    @Override
    public String toString() { return this.getAluno() + " - " + this.getTurma().getId(); }

}
