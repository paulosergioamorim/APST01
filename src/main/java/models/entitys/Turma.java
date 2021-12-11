package models.entitys;

import models.Estado;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static models.Estado.*;

@Entity
public class Turma {
    private int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalTime horario;
    private int limite;
    private Estado estado;
    private Curso curso;
    private Professor responsavel;
    private List<Matricula> matriculas;

    public Turma(final int id,
                 final LocalDate dataInicio,
                 final LocalDate dataFim,
                 final LocalTime horario,
                 final int limite,
                 final Curso curso,
                 final Professor responsavel) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horario = horario;
        this.limite = limite;
        this.curso = curso;
        this.responsavel = responsavel;
        this.matriculas = new ArrayList<>();
        this.estado = MATRICULAS_ABERTAS;
    }

    public Turma() { }

    @Transient
    public int getVagas() { return this.limite - this.matriculas.size(); }

    @Id
    public int getId() { return this.id; }

    public void setId(final int id) { this.id = id; }

    @Column(nullable = false)
    public LocalDate getDataInicio() { return this.dataInicio; }

    public void setDataInicio(final LocalDate dataInicio) { this.dataInicio = dataInicio; }

    @Column(nullable = false)
    public LocalDate getDataFim() { return this.dataFim; }

    public void setDataFim(final LocalDate dataFim) { this.dataFim = dataFim; }

    @Column(nullable = false)
    public LocalTime getHorario() { return this.horario; }

    public void setHorario(final LocalTime horario) { this.horario = horario; }

    @Column(nullable = false)
    public int getLimite() { return this.limite; }

    public void setLimite(final int limite) { this.limite = limite; }

    @Column(nullable = false)
    public Estado getEstado() { return this.estado; }

    public void setEstado(final Estado estado) { this.estado = estado; }

    @ManyToOne(optional = false)
    public Curso getCurso() { return this.curso; }

    public void setCurso(final Curso curso) { this.curso = curso; }

    @ManyToOne(optional = false)
    public Professor getResponsavel() { return this.responsavel; }

    public void setResponsavel(final Professor professor) { this.responsavel = professor; }

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    public List<Matricula> getMatriculas() { return this.matriculas; }

    public void setMatriculas(final List<Matricula> matriculas) { this.matriculas = matriculas; }

    @Override
    public String toString() { return this.id + " - " + this.curso; }

    @PostPersist
    @PostUpdate
    @PostLoad
    public void updateEstado() {
        if (this.estado != FECHADA) {
            if (this.dataFim.isBefore(LocalDate.now())) this.estado = AULAS_ENCERRADAS;
            else if (this.getVagas() == 0) this.estado = MATRICULAS_ENCERRADAS;
            else if (this.dataInicio.isBefore(LocalDate.now())) this.estado = EM_ANDAMENTO;
            else this.estado = MATRICULAS_ABERTAS;
        }
    }
}
