package models.entitys;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Turma {
    private String code;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalTime horario;
    private int limite;
    private boolean fechada;
    private Curso curso;
    private Professor professor;
    private List<Matricula> matriculas;

    /**
     * Construtor da Turma
     *
     * @param code       Código da turma
     * @param dataInicio Data de início da turma
     * @param dataFim    Data de término da turma
     * @param horario    Horário da turma
     * @param limite     Limite de alunos na turma
     * @param fechada    Se a turma está fechada ou não
     * @param curso      Curso da turma
     * @param professor  Professor da turma
     */
    public Turma(String code, LocalDate dataInicio, LocalDate dataFim, LocalTime horario, int limite, boolean fechada, Curso curso, Professor professor) {
        this.code = code;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horario = horario;
        this.limite = limite;
        this.fechada = fechada;
        this.curso = curso;
        this.professor = professor;
    }

    public Turma() { }

    /**
     * Define e retorna o estado da turma
     *
     * @return estado da turma
     */
    @Transient
    public String getEstado() {
        if (fechada)
            return "Fechada";
        else if (dataFim.isBefore(LocalDate.now()))
            return "Aulas Encerradas";
        else if (this.getVagas() == 0)
            return "Matriculas Encerradas";
        else if (dataInicio.isBefore(LocalDate.now()))
            return "Em Andamento";
        else return "Matriculas Abertas";
    }

    /**
     * @return o número de vagas disponíveis na turma
     */
    @Transient
    public long getVagas() {
        return matriculas
                .stream()
                .filter(Objects::isNull)
                .count();
    }

    @Id
    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    @Column(nullable = false, columnDefinition = "date")
    public LocalDate getDataInicio() { return dataInicio; }

    public void setDataInicio(LocalDate dateInit) { this.dataInicio = dateInit; }

    @Column(nullable = false, columnDefinition = "date")
    public LocalDate getDataFim() { return dataFim; }

    public void setDataFim(LocalDate dateEnd) { this.dataFim = dateEnd; }

    @Column(nullable = false)
    public LocalTime getHorario() { return horario; }

    public void setHorario(LocalTime horario) { this.horario = horario; }

    @Column(nullable = false)
    public int getLimite() { return limite; }

    public void setLimite(int limit) { this.limite = limit; }

    @Column(nullable = false)
    public boolean isFechada() { return fechada; }

    public void setFechada(boolean fechada) { this.fechada = fechada; }

    @ManyToOne(optional = false)
    public Curso getCurso() { return curso; }

    public void setCurso(Curso curso) { this.curso = curso; }

    @ManyToOne(optional = false)
    public Professor getProfessor() { return professor; }

    public void setProfessor(Professor professor) { this.professor = professor; }

    @OneToMany(mappedBy = "turma")
    public List<Matricula> getMatriculas() { return matriculas; }

    public void setMatriculas(List<Matricula> matriculas) { this.matriculas = matriculas; }

    @Override
    public String toString() { return curso + " - " + professor; }
}
