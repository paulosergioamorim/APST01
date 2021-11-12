package cdp;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turma {
    private final LocalDate dataInicio;
    private final LocalDate dataFim;
    private final LocalTime horario;
    private final int limiteAlunos;
    private boolean fechada;

    private String estado;

    private Curso curso;
    private Professor responsavel;
    private final Matricula[] matriculas;

    public Turma(LocalDate dataInicio, LocalDate dataFim, LocalTime horario, int limiteAlunos) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horario = horario;
        this.limiteAlunos = limiteAlunos;
        this.fechada = false;

        matriculas = new Matricula[limiteAlunos];
    }

    public String obterEstado() {
        if (dataInicio.isBefore(LocalDate.now()))
            estado = "Em andamento";
        else if (matriculas.length == limiteAlunos)
            estado = "Matriculas Encerradas";
        else if (dataFim.isBefore(LocalDate.now()))
            estado = "Aulas Encerradas";
        else if (fechada)
            estado = "Fechada";
        else estado = "Matriculas Abertas";
        return estado;
    }

    public void addMatricula(Matricula matricula) {
        if (matriculas.length == limiteAlunos)
            return;
        for (Matricula m : matriculas)
            if (m == null) m = matricula;
    }

    public int vagas() {
        for (int i = matriculas.length; i > 0; i--)
            if (matriculas[i - 1] != null)
                return i;
        return matriculas.length;
    }

    public LocalDate getDataInicio() { return dataInicio; }

    public LocalDate getDataFim() { return dataFim; }

    public LocalTime getHorario() { return horario; }

    public int getLimiteAlunos() { return limiteAlunos; }

    public boolean getFechada() { return fechada; }

    public Curso getCurso() { return curso; }

    public void setCurso(Curso curso) { this.curso = curso; }

    public Professor getResponsavel() { return responsavel; }

    public void setResponsavel(Professor responsavel) { this.responsavel = responsavel; }

    public Matricula[] getMatriculas() { return matriculas; }

    @Override
    public String toString() { return "[" + dataInicio + " - " + dataFim + "] " + horario + " - " + curso; }
}
