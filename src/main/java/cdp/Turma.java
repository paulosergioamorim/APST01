package cdp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Turma {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalTime horario;
    private int limiteAlunos;
    private boolean fechada;

    private Object estado;

    private Curso curso;
    private Professor responsavel;
    private final List<Matricula> matriculas;

    public Turma(LocalDate dataInicio, LocalDate dataFim, LocalTime horario, int limiteAlunos, Curso curso) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horario = horario;
        this.limiteAlunos = limiteAlunos;
        this.curso = curso;
        this.fechada = false;

        matriculas = new ArrayList<>();
    }

    public Object obterEstado() { return estado; }

    public LocalDate getDataInicio() { return dataInicio; }

    public LocalDate getDataFim() { return dataFim; }

    public LocalTime getHorario() { return horario; }

    public int getLimiteAlunos() { return limiteAlunos; }

    public boolean getFechada() { return fechada; }

    public Curso getCurso() { return curso; }

    public Professor getResponsavel() { return responsavel; }

    public List<Matricula> getMatriculas() { return matriculas; }
}
