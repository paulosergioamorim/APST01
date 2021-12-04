package models.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    public Curso(int id, String nome, String sigla, int cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.cargaHoraria = cargaHoraria;
    }

    public Curso() { }

    @Id
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @Column(length = 45, nullable = false)
    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Column(length = 5, nullable = false, unique = true)
    public String getSigla() { return sigla; }

    public void setSigla(String sigla) { this.sigla = sigla; }

    @Column(nullable = false)
    public int getCargaHoraria() { return cargaHoraria; }

    public void setCargaHoraria(int carga_horaria) { this.cargaHoraria = carga_horaria; }

    @OneToMany(mappedBy = "curso")
    public List<Turma> getTurmas() { return turmas; }

    public void setTurmas(List<Turma> turmas) { this.turmas = turmas; }

    @Override
    public String toString() { return nome; }
}
