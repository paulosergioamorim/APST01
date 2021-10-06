package cdp;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Matricula {
    private double nota;
    @NotNull
    private Date data_matricula;

    public Matricula(double nota, @NotNull Date data_matricula, Aluno aluno, Turma turma) {
        this.nota = nota;
        this.data_matricula = data_matricula;
        this.aluno = aluno;
        this.turma = turma;
    }

    // relationships
    private Aluno aluno;
    private Turma turma;

    public double getNota() { return nota; }

    public void setNota(double nota) { this.nota = nota; }

    public @NotNull Date getData_matricula() { return data_matricula; }

    public void setData_matricula(@NotNull Date data_matricula) { this.data_matricula = data_matricula; }

    public Aluno getAluno() { return aluno; }

    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Turma getTurma() { return turma; }

    public void setTurma(Turma turma) { this.turma = turma; }
}
