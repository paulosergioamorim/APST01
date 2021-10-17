package cdp;

import java.time.LocalDate;
import java.util.List;

public class Turma {
    private LocalDate dataInicio;
    private LocalDate dataFim;

    private String horario;
    private int limiteAlunos;
    private Estado estado;
    private boolean fechada;

    public Turma(LocalDate dataInicio, LocalDate dataFim, String horario, int limiteAlunos, Curso curso) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horario = horario;
        this.limiteAlunos = limiteAlunos;
        this.curso = curso;
        this.estado = Estado.MatriculasAbertas;
    }

    // relationship
    private Curso curso;
    private Professor responsavel;
    private List<Matricula> matriculas;

    public LocalDate getDataInicio() { return dataInicio; }

    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }

    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public String getHorario() { return horario; }

    public void setHorario(String horario) { this.horario = horario; }

    public int getLimiteAlunos() { return limiteAlunos; }

    public void setLimiteAlunos(int limiteAlunos) { this.limiteAlunos = limiteAlunos; }

    public boolean getFechada() { return fechada; }

    public void setFechada(boolean fechada) { this.fechada = fechada; }

    public Curso getCurso() { return curso; }

    public void setCurso(Curso curso) { this.curso = curso; }

    public Estado getEstado() { return estado; }

    public void setEstado(Estado estado) { this.estado = estado; }

    public Professor getResponsavel() { return responsavel; }

    public void setResponsavel(Professor responsavel) { this.responsavel = responsavel; }

    public List<Matricula> getMatriculas() { return matriculas; }

    public Estado obterEstado() { return estado; }

    public enum Estado {
        MatriculasAbertas {
            @Override
            public Estado next() {
                if (turma.matriculas.size() == turma.limiteAlunos) return MatriculasEncerradas;
                if (LocalDate.now().isBefore(turma.dataInicio)) return EmAndamento;
                else return null;
            }
        },
        MatriculasEncerradas {
            @Override
            public Estado next() {
                if (LocalDate.now().isBefore(turma.dataInicio)) return EmAndamento;
                else return null;
            }
        },
        EmAndamento {
            @Override
            public Estado next() {
                if (LocalDate.now().isBefore(turma.dataFim)) return AulasEncerradas;
                else return null;
            }
        },
        AulasEncerradas {
            @Override
            public Estado next() {
                return Fechada;
            }
        },
        Fechada {
            @Override public Estado next() { return null; }
        };

        public Turma turma;

        Estado(Turma turma) {
            this.turma = turma;
        }

        Estado() { }

        public abstract Estado next();
    }
}
