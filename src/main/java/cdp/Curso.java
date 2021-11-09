package cdp;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nome;
    private int cargaHoraria;

    public Curso(String nome, int cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;

        turmas = new ArrayList<>();
    }

    // relationship
    private List<Turma> turmas;

    public String getNome() { return nome; }

    public int getCargaHoraria() { return cargaHoraria; }

    public List<Turma> getTurmas() { return turmas; }

    public void addTurma(Turma turma) { this.turmas.add(turma); }

    @Override public String toString() { return nome; }
}
