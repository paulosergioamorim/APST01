package models.entitys;

import models.State;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static javax.persistence.FetchType.EAGER;
import static models.State.*;

@Entity
public class Turma {
    private String id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalTime horario;
    private int limite;
    private State estado;
    private Curso curso;
    private Professor responsavel;
    private List<Matricula> matriculas;

    public Turma(String id,
                 LocalDate dataInicio,
                 LocalDate dataFim,
                 LocalTime horario,
                 int limite,
                 Curso curso,
                 Professor responsavel) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horario = horario;
        this.limite = limite;
        this.curso = curso;
        this.responsavel = responsavel;
        this.estado = MATRICULAS_ABERTAS;
    }

    public Turma() { }

    /**
     * @return o número de vagas disponíveis na turma
     */
    @Transient
    public long getVagas() {
        if (matriculas == null)
            return limite;
        return matriculas
                .stream()
                .filter(Objects::isNull)
                .count();
    }

    @Id
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    @Column(nullable = false)
    public LocalDate getDataInicio() { return dataInicio; }

    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    @Column(nullable = false)
    public LocalDate getDataFim() { return dataFim; }

    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    @Column(nullable = false)
    public LocalTime getHorario() { return horario; }

    public void setHorario(LocalTime horario) { this.horario = horario; }

    @Column(nullable = false)
    public int getLimite() { return limite; }

    public void setLimite(int limite) { this.limite = limite; }

    @Column(nullable = false)
    public State getEstado() { return estado; }

    public void setEstado(State state) { this.estado = state; }

    @ManyToOne(optional = false)
    public Curso getCurso() { return curso; }

    public void setCurso(Curso curso) { this.curso = curso; }

    @ManyToOne(optional = false)
    public Professor getResponsavel() { return responsavel; }

    public void setResponsavel(Professor professor) { this.responsavel = professor; }

    @OneToMany(mappedBy = "turma", fetch = EAGER)
    public List<Matricula> getMatriculas() { return matriculas; }

    public void setMatriculas(List<Matricula> matriculas) { this.matriculas = matriculas; }

    @Override
    public String toString() { return curso + " - " + responsavel; }

    @PostPersist
    @PostUpdate
    @PostLoad
    public void updateState() {
        if (dataFim.isBefore(LocalDate.now()))
            estado = AULAS_ENCERRADAS;
        else if (this.getVagas() == 0)
            estado = MATRICULAS_ENCERRADAS;
        else if (dataInicio.isBefore(LocalDate.now()))
            estado = EM_ANDAMENTO;
        else estado = EM_ANDAMENTO;
    }
}
