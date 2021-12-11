package models.entitys;

import javax.persistence.*;
import java.util.List;

@Entity
public class Curso {
    private int id;
    private String nome;
    private String sigla;
    private int cargaHoraria;
    private List<Turma> turmas;

    /**
     * Construtor de Curso
     *
     * @param id           Id do curso
     * @param nome         Nome do curso
     * @param sigla        Sigla do curso
     * @param cargaHoraria Carga horária do curso
     */
    public Curso(final int id, final String nome, final String sigla, final int cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.cargaHoraria = cargaHoraria;
    }

    public Curso() { }

    @Id
    public int getId() { return this.id; }

    public void setId(final int id) { this.id = id; }

    @Column(length = 45, nullable = false)
    public String getNome() { return this.nome; }

    public void setNome(final String nome) { this.nome = nome; }

    @Column(length = 5, nullable = false, unique = true)
    public String getSigla() { return this.sigla; }

    public void setSigla(final String sigla) { this.sigla = sigla; }

    @Column(nullable = false)
    public int getCargaHoraria() { return this.cargaHoraria; }

    public void setCargaHoraria(final int cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    public List<Turma> getTurmas() { return this.turmas; }

    public void setTurmas(final List<Turma> turmas) { this.turmas = turmas; }

    @Override
    public String toString() { return this.nome; }
}
