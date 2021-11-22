package cdp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "matricula")
public class Matricula implements Serializable {
    private double nota;
    private LocalDate dataMatricula;
    private long idAluno;
    private Aluno aluno;
    private long idTurma;
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
    public Aluno getAluno() { return aluno; }

    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    @Transient
    public Turma getTurma() { return turma; }

    public void setTurma(Turma turma) { this.turma = turma; }

    @Id
    @Column(name = "id_aluno", nullable = false)
    public long getIdAluno() { return idAluno; }

    public void setIdAluno(long idAluno) { this.idAluno = idAluno; }

    @Id
    @Column(name = "id_turma", nullable = false)
    public long getIdTurma() { return idTurma; }

    public void setIdTurma(long idTurma) { this.idTurma = idTurma; }
}
