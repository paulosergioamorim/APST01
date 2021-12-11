package models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MatriculaID implements Serializable {
    private long aluno_cpf;
    private int turma_id;

    public MatriculaID(final long aluno_cpf, final int turma_id) {
        this.aluno_cpf = aluno_cpf;
        this.turma_id = turma_id;
    }

    public MatriculaID() { }

    public long getAluno_cpf() { return this.aluno_cpf; }

    public void setAluno_cpf(final long aluno_cpf) { this.aluno_cpf = aluno_cpf; }

    public int getTurma_id() { return this.turma_id; }

    public void setTurma_id(final int turma_id) { this.turma_id = turma_id; }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        final MatriculaID that = (MatriculaID) o;
        return Objects.equals(this.aluno_cpf, that.aluno_cpf) && Objects.equals(this.turma_id, that.turma_id);
    }

    @Override
    public int hashCode() { return Objects.hash(this.aluno_cpf, this.turma_id); }
}
