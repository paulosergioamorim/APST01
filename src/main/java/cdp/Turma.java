package cdp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Turma {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalTime horario;
    private int limiteAlunos;
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
        this.matriculas = new Matricula[limiteAlunos];
    }

    /**
     * Define e retorna o estado da turma
     * @return estado da turma
     */
    public String obterEstado() {
        if (fechada)
            estado = "Fechada";
        else if (dataFim.isBefore(LocalDate.now()))
            estado = "Aulas Encerradas";
        else if (vagas() == 0)
            estado = "Matriculas Encerradas";
        else if (dataInicio.isBefore(LocalDate.now()))
            estado = "Em andamento";
        else estado = "Matriculas Abertas";
        return estado;
    }

    /**
     * Adiciona uma matrícula na turma
     * @param matricula Matricula a ser adicionada na turma
     */
    public void addMatricula(Matricula matricula) {
        if (vagas() == 0) return;
        for (int i = 0; i < matriculas.length; i++)
            if (matriculas[i] == null) {
                matriculas[i] = matricula;
                return;
            }
    }

    /**
     * Verifica se a turma possui vagas disponíveis
     * @return o número de vagas disponíveis
     */
    public long vagas() { return Arrays.stream(matriculas).filter(Objects::isNull).count(); }

    public LocalDate getDataInicio() { return dataInicio; }

    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }

    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public LocalTime getHorario() { return horario; }

    public void setHorario(LocalTime horario) { this.horario = horario; }

    public int getLimiteAlunos() { return limiteAlunos; }

    public void setLimiteAlunos(int limiteAlunos) { this.limiteAlunos = limiteAlunos; }

    public boolean getFechada() { return fechada; }

    public void setFechada(boolean fechada) { this.fechada = fechada; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public Curso getCurso() { return curso; }

    public void setCurso(Curso curso) { this.curso = curso; }

    public Professor getResponsavel() { return responsavel; }

    public void setResponsavel(Professor responsavel) { this.responsavel = responsavel; }

    public Matricula[] getMatriculas() { return matriculas; }

    @Override
    public String toString() { return "[" + dataInicio + " - " + dataFim + "] " + horario + " - " + curso + " - " + responsavel; }
}
