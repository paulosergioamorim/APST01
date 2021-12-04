package models;

import models.entitys.Aluno;
import models.entitys.Turma;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MatriculaID implements Serializable {
    private Aluno aluno;
    private Turma turma;

    public MatriculaID(Aluno aluno, Turma turma) {
        this.aluno = aluno;
        this.turma = turma;
    }

    public MatriculaID() { }

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_cpf")
    public Aluno getAluno() { return aluno; }

    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    @ManyToOne(optional = false)
    @JoinColumn(name = "turma_id")
    public Turma getTurma() { return turma; }

    public void setTurma(Turma turma) { this.turma = turma; }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MatriculaID that = (MatriculaID) o;
        return Objects.equals(aluno, that.aluno)
            && Objects.equals(turma, that.turma);
    }

    @Override
    public int hashCode() { return Objects.hash(aluno, turma); }
}
