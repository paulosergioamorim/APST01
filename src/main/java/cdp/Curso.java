package cdp;

import java.util.Collection;

public class Curso {
    private String nome;
    private int carga_horaria;

    public Curso(String nome, int carga_horaria) {
        this.nome = nome;
        this.carga_horaria = carga_horaria;
    }

    // relationship
    private Collection<Turma> turmas;

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getCarga_horaria() { return carga_horaria; }

    public void setCarga_horaria(int carga_horaria) { this.carga_horaria = carga_horaria; }

    public Collection<Turma> getTurmas() { return turmas; }

    public void setTurmas(Collection<Turma> turmas) { this.turmas = turmas; }

    public void addTurma(Turma turma) { this.turmas.add(turma); }
}
