package models.entitys;

import models.MatriculaID;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Matricula {
    private MatriculaID matriculaID;
    private LocalDate dataMatricula;
    private double nota;

    public Matricula(MatriculaID matriculaID, LocalDate dataMatricula, double nota) {
        this.matriculaID = matriculaID;
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

    @Override
    public String toString() { return matriculaID.getAluno() + " - " + matriculaID.getTurma(); }

}
