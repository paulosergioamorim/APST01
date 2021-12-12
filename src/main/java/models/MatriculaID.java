package models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MatriculaID implements Serializable {
    private long aluno_cpf;
    private int turma_id;

    public MatriculaID(long aluno_cpf, int turma_id) {
        this.aluno_cpf = aluno_cpf;
        this.turma_id = turma_id;
    }

    public MatriculaID() { }

    public long getAluno_cpf() { return aluno_cpf; }

    public void setAluno_cpf(long aluno_cpf) { this.aluno_cpf = aluno_cpf; }

    public int getTurma_id() { return turma_id; }

    public void setTurma_id(int turma_id) { this.turma_id = turma_id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        MatriculaID that = (MatriculaID) o;
        return Objects.equals(aluno_cpf, that.aluno_cpf) && Objects.equals(turma_id, that.turma_id);
    }

    @Override
    public int hashCode() { return Objects.hash(aluno_cpf, turma_id); }
}
