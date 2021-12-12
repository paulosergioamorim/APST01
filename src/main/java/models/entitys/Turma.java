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

    public Turma(int id,
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
        matriculas = new ArrayList<>();
        estado = MATRICULAS_ABERTAS;
    }

    public Turma() { }

    @Transient
    public int getVagas() { return limite - matriculas.size(); }

    @Id
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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
    public Estado getEstado() { return estado; }

    public void setEstado(Estado estado) { this.estado = estado; }

    @ManyToOne(optional = false)
    public Curso getCurso() { return curso; }

    public void setCurso(Curso curso) { this.curso = curso; }

    @ManyToOne(optional = false)
    public Professor getResponsavel() { return responsavel; }

    public void setResponsavel(Professor professor) { responsavel = professor; }

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    public List<Matricula> getMatriculas() { return matriculas; }

    public void setMatriculas(List<Matricula> matriculas) { this.matriculas = matriculas; }

    @Override
    public String toString() { return id + " - " + curso; }

    @PostPersist
    @PostUpdate
    @PostLoad
    public void updateEstado() {
        if (estado != FECHADA) {
            if (dataFim.isBefore(LocalDate.now())) estado = AULAS_ENCERRADAS;
            else if (this.getVagas() == 0) estado = MATRICULAS_ENCERRADAS;
            else if (dataInicio.isBefore(LocalDate.now())) estado = EM_ANDAMENTO;
            else estado = MATRICULAS_ABERTAS;
        }
    }
}
