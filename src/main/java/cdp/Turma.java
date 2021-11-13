package cdp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

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

    public Turma(LocalDate dataInicio, LocalDate dataFim, LocalTime horario, int limiteAlunos, Curso curso, Professor responsavel) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horario = horario;
        this.limiteAlunos = limiteAlunos;
        this.curso = curso;
        this.responsavel = responsavel;
        this.fechada = false;

        matriculas = new Matricula[limiteAlunos];
    }

    public String obterEstado() {
        if (dataInicio.isBefore(LocalDate.now()))
            estado = "Em andamento";
        else if (vagas() == 0)
            estado = "Matriculas Encerradas";
        else if (dataFim.isBefore(LocalDate.now()))
            estado = "Aulas Encerradas";
        else if (fechada)
            estado = "Fechada";
        else estado = "Matriculas Abertas";
        return estado;
    }

    /**
     * Adiciona uma matrícula na turma
     * @param matricula Matricula a ser adicionada na turma
     */
    public void addMatricula(Matricula matricula) {
        if (vagas() == 0) return;
        for (Matricula m : matriculas) {
            if (m == null) {
                m = matricula;
                obterEstado();
                return;
            }
        }
    }

    /**
     * Verifica se a turma possui vagas disponíveis
     * @return o número de vagas disponíveis
     */
    public long vagas() { return Arrays.stream(matriculas).filter(Objects::isNull).count(); }

    public LocalDate getDataInicio() { return dataInicio; }

    public LocalDate getDataFim() { return dataFim; }

    public LocalTime getHorario() { return horario; }

    public int getLimiteAlunos() { return limiteAlunos; }

    public boolean getFechada() { return fechada; }

    public Curso getCurso() { return curso; }

    public Professor getResponsavel() { return responsavel; }

    public Matricula[] getMatriculas() { return matriculas; }

    @Override
    public String toString() { return "[" + dataInicio + " - " + dataFim + "] " + horario + " - " + curso + " - " + responsavel; }
}
