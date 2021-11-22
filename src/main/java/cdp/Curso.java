package cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Curso {
    private String nome;
    private int cargaHoraria;
    private List<Turma> turmas;

    public Curso(String nome, int cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.turmas = new ArrayList<>();
    }

    public Curso() { }

    @Id
    @Column(name = "nome", length = 45, nullable = false)
    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Column(name = "carga_horaria", nullable = false)
    public int getCargaHoraria() { return cargaHoraria; }

    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    @Transient
    public List<Turma> getTurmas() { return turmas; }

    public void setTurmas(List<Turma> turmas) { this.turmas = turmas; }

    public void addTurma(Turma turma) { this.turmas.add(turma); }

    @Override public String toString() { return nome; }
}
