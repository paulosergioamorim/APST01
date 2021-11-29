package cdp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Entity
public class Turma {
    private long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalTime horario;
    private int limiteAlunos;
    private boolean fechada;

    private Curso curso;
    private Professor responsavel;
    private Set<Matricula> matriculas;

    public Turma(LocalDate dataInicio, LocalDate dataFim, LocalTime horario, int limiteAlunos, Curso curso, Professor responsavel) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horario = horario;
        this.limiteAlunos = limiteAlunos;
        this.curso = curso;
        this.responsavel = responsavel;
        this.fechada = false;
        this.matriculas = new HashSet<>(limiteAlunos);
    }

    public Turma() { }

    /**
     * Define e retorna o estado da turma
     * @return estado da turma
     */
    public String obterEstado() {
        if (fechada)
            return "Fechada";
        else if (dataFim.isBefore(LocalDate.now()))
            return  "Aulas Encerradas";
        else if (getVagas() == 0)
            return  "Matriculas Encerradas";
        else if (dataInicio.isBefore(LocalDate.now()))
            return  "Em Andamento";
        else return "Matriculas Abertas";
    }

    /**
     * Adiciona uma matrícula na turma
     * @param matricula Matricula a ser adicionada na turma
     */
    public void addMatricula(Matricula matricula) {
        if (getVagas() == 0) return;
        for (int i = 0; i < matriculas.size(); i++)
            if (matriculas.toArray()[i] == null) {
                matriculas.toArray()[i] = matricula;
                return;
            }
    }

    /**
     * @return o número de vagas disponíveis na turma
     */
    @Transient
    public long getVagas() {
        return Arrays.stream(matriculas.toArray())
                .filter(Objects::isNull)
                .count();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    @Column(name = "data_inicio", nullable = false)
    public LocalDate getDataInicio() { return dataInicio; }

    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    @Column(name = "data_fim", nullable = false)
    public LocalDate getDataFim() { return dataFim; }

    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    @Column(name = "horario", nullable = false)
    public LocalTime getHorario() { return horario; }

    public void setHorario(LocalTime horario) { this.horario = horario; }

    @Column(name = "limite_alunos", nullable = false)
    public int getLimiteAlunos() { return limiteAlunos; }

    public void setLimiteAlunos(int limiteAlunos) { this.limiteAlunos = limiteAlunos; }

    @Column(name = "fechada", nullable = false)
    public boolean getFechada() { return fechada; }

    public void setFechada(boolean fechada) { this.fechada = fechada; }

    @ManyToOne
    public Curso getCurso() { return curso; }

    public void setCurso(Curso curso) { this.curso = curso; }

    @ManyToOne
    public Professor getResponsavel() { return responsavel; }

    public void setResponsavel(Professor responsavel) { this.responsavel = responsavel; }

    @OneToMany(mappedBy = "turma")
    public Set<Matricula> getMatriculas() { return matriculas; }

    public void setMatriculas(Set<Matricula> matriculas) { this.matriculas = matriculas; }

    @Override
    public String toString() { return "[" + dataInicio + " - " + dataFim + "]" + " - " + curso + " - " + responsavel; }
}
