package cdp;

import java.util.Date;

public class Turma {
    private Date data_inicio;
    private Date data_fim;

    private String horario;
    private int limite_alunos;
    private boolean fechada = false;

    public Turma(Date data_inicio, Date data_fim, String horario, int limite_alunos, Curso curso) {
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.horario = horario;
        this.limite_alunos = limite_alunos;
        this.curso = curso;
    }

    // relationship
    private Curso curso;

    public Date getData_inicio() { return data_inicio; }

    public void setData_inicio(Date data_inicio) { this.data_inicio = data_inicio; }

    public Date getData_fim() { return data_fim; }

    public void setData_fim(Date data_fim) { this.data_fim = data_fim; }

    public String getHorario() { return horario; }

    public void setHorario(String horario) { this.horario = horario; }

    public int getLimite_alunos() { return limite_alunos; }

    public void setLimite_alunos(int limite_alunos) { this.limite_alunos = limite_alunos; }

    public boolean getFechada() { return fechada; }

    public void setFechada(boolean fechada) { this.fechada = fechada; }

    public Curso getCurso() { return curso; }

    public void setCurso(Curso curso) { this.curso = curso; }

    public void obterEstado() { }
}
