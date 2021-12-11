package models.entitys;

import models.MatriculaID;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Matricula {
    private MatriculaID matriculaID;
    private LocalDate dataMatricula;
    private Aluno aluno;
    private Turma turma;
    private Double nota;

    public Matricula(MatriculaID matriculaID, LocalDate dataMatricula, Double nota) {
        this.matriculaID = matriculaID;
        this.dataMatricula = dataMatricula;
        this.nota = nota;
    }

    public Matricula() { }

    @EmbeddedId
    @Access(AccessType.PROPERTY)
    public MatriculaID getMatriculaID() { return matriculaID; }

    public void setMatriculaID(MatriculaID matriculaID) { this.matriculaID = matriculaID; }
    
    @Transient
    public String getSituacao() {
        if (nota == null)
            return "Cursando";
        else if (nota >= 6)
            return "Aprovado";
        else
            return "Reprovado";
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_cpf", nullable = false, insertable = false, updatable = false)
    public Aluno getAluno() { return aluno; }

    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    @ManyToOne(optional = false)
    @JoinColumn(name = "turma_id", nullable = false, insertable = false, updatable = false)
    public Turma getTurma() { return turma; }

    public void setTurma(Turma turma) { this.turma = turma; }

    @Column(nullable = false)
    public LocalDate getDataMatricula() { return dataMatricula; }

    public void setDataMatricula(LocalDate date) { this.dataMatricula = date; }

    @Column
    public Double getNota() { return nota; }

    public void setNota(Double nota) { this.nota = nota; }

    @Override
    public String toString() { return this.getAluno() + " - " + this.getTurma().getId(); }

}
