package controls;

import models.Sexo;
import models.entitys.Aluno;
import models.entitys.Curso;
import models.entitys.Professor;
import models.entitys.Turma;
import views.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static controls.ControlFactory.*;
import static models.Format.dateFormatter;
import static models.Format.timeFormatter;
import static views.View.*;

/**
 * Controller Class
 *
 * @author Paulo Sergio
 * @author Breno Haese
 * @author Nycolas Monjardim
 */
public final class Control {
    private final AlunoControl alunoControl;
    private final ProfessorControl professorControl;
    private final CursoControl cursoControl;
    private final TurmaControl turmaControl;
    private final ViewControl viewControl;

    public Control() {
        alunoControl = createAlunoControl(this);
        professorControl = createProfessorControl(this);
        cursoControl = createCursoControl(this);
        turmaControl = createTurmaControl(this);
        viewControl = createViewControl(this);
    }

    public static void main(String[] args) { new Control(); }

    public void changeView(View view) { viewControl.changeView(view); }

    public void showMessage(String message) { viewControl.showMessage(message); }

    public void updateListViewer(View view) { viewControl.updateListViewer(view); }

    public void clearFields(View view) { viewControl.clearFields(view); }

    public void saveAluno() {
        long cpf;
        String nome;
        Sexo sexo;
        LocalDate dataNascimento;
        AlunoView alunoView = viewControl.getView(ALUNO_VIEW);
        try {
            cpf = Long.parseLong(alunoView.getCpf().replaceAll("\\D", ""));
            nome = alunoView.getNome();
            sexo = alunoView.getSexo();
            dataNascimento = LocalDate.parse(alunoView.getDataNascimento(), dateFormatter);
            alunoControl.save(cpf, nome, sexo, dataNascimento);
        } catch (Exception e) {
            this.showMessage("Erro ao salvar aluno");
        }
    }

    public void updateAluno() {
        long cpf;
        String nome;
        Sexo sexo;
        LocalDate dataNascimento;
        AlunoView alunoView = viewControl.getView(ALUNO_VIEW);
        try {
            cpf = Long.parseLong(alunoView.getCpf().replaceAll("\\D", ""));
            nome = (alunoView.getNome().isEmpty())
                    ? null : alunoView.getNome();
            sexo = (alunoView.getSexo() == null)
                    ? null : alunoView.getSexo();
            dataNascimento = alunoView.getDataNascimento().equals("__/__/____")
                    ? null : LocalDate.parse(alunoView.getDataNascimento(), dateFormatter);
            alunoControl.update(cpf, nome, sexo, dataNascimento);
        } catch (Exception e) {
            this.showMessage("Erro ao atualizar aluno");
        }
    }

    public void deleteAluno() {
        Aluno aluno;
        AlunoView alunoView = viewControl.getView(ALUNO_VIEW);
        try {
            aluno = alunoView.getListView().getSelectedValue();
            long cpf = aluno.getCpf();
            alunoControl.delete(cpf);
        } catch (Exception e) {
            this.showMessage("Erro ao deletar aluno");
        }
    }

    public void saveCurso() {
        int id;
        String nome;
        String sigla;
        int cargaHoraria;
        CursoView cursoView = viewControl.getView(CURSO_VIEW);
        try {
            id = Integer.parseInt(cursoView.getId());
            nome = cursoView.getNome();
            cargaHoraria = Integer.parseInt(cursoView.getCargaHoraria());
            sigla = cursoView.getSigla();
            cursoControl.save(id, nome, sigla, cargaHoraria);
        } catch (Exception e) {
            this.showMessage("Erro ao salvar curso");
        }
    }

    public void updateCurso() {
        int id;
        String nome;
        String sigla;
        int cargaHoraria;
        CursoView cursoView = viewControl.getView(CURSO_VIEW);
        try {
            id = Integer.parseInt(cursoView.getId());
            nome = (cursoView.getNome().isEmpty())
                    ? null : cursoView.getNome();
            cargaHoraria = (cursoView.getCargaHoraria().isEmpty()) ?
                    - 1 : Integer.parseInt(cursoView.getCargaHoraria());
            sigla = (cursoView.getSigla().isEmpty())
                    ? null : cursoView.getSigla();
            cursoControl.update(id, nome, sigla, cargaHoraria);
        } catch (Exception e) {
            this.showMessage("Erro ao atualizar curso");
        }
    }

    public void deleteCurso() {
        Curso curso;
        CursoView cursoView = viewControl.getView(CURSO_VIEW);
        try {
            curso = cursoView.getListView().getSelectedValue();
            int id = curso.getId();
            cursoControl.delete(id);
        } catch (Exception e) {
            this.showMessage("Erro ao deletar curso");
        }
    }

    public void saveProfessor() {
        long cpf;
        String nome;
        Sexo sexo;
        LocalDate dataNascimento;
        String titulacao;
        ProfessorView professorView = viewControl.getView(PROFESSOR_VIEW);
        try {
            cpf = Long.parseLong(professorView.getCpf().replaceAll("\\D", ""));
            nome = professorView.getNome();
            sexo = professorView.getSexo();
            dataNascimento = LocalDate.parse(professorView.getDataNascimento(), dateFormatter);
            titulacao = professorView.getTitulacao();
            professorControl.save(cpf, nome, sexo, dataNascimento, titulacao);
        } catch (Exception e) {
            this.showMessage("Erro ao salvar professor");
        }
    }

    public void updateProfessor() {
        long cpf;
        String nome;
        Sexo sexo;
        LocalDate dataNascimento;
        String titulacao;
        ProfessorView professorView = viewControl.getView(PROFESSOR_VIEW);
        try {
            cpf = Long.parseLong(professorView.getCpf().replaceAll("\\D", ""));
            nome = (professorView.getNome().isEmpty()) ?
                    null : professorView.getNome();
            sexo = (professorView.getSexo() == null) ?
                    null : professorView.getSexo();
            dataNascimento = professorView.getDataNascimento().equals("__/__/____") ?
                    null : LocalDate.parse(professorView.getDataNascimento(), dateFormatter);
            titulacao = (professorView.getTitulacao().isEmpty()) ?
                    null : professorView.getTitulacao();
            professorControl.update(cpf, nome, sexo, dataNascimento, titulacao);
        } catch (Exception e) {
            this.showMessage("Erro ao atualizar professor");
        }
    }

    public void deleteProfessor() {
        Professor professor;
        ProfessorView professorView = viewControl.getView(PROFESSOR_VIEW);
        try {
            professor = professorView.getListView().getSelectedValue();
            long cpf = professor.getCpf();
            professorControl.delete(cpf);
        } catch (Exception e) {
            this.showMessage("Erro ao deletar professor");
        }
    }

    public void saveTurma() {
        String id;
        LocalDate dataInicio;
        LocalDate dataFim;
        LocalTime horario;
        int limite;
        Curso curso;
        Professor responsavel;
        TurmaView turmaView = viewControl.getView(TURMA_VIEW);
        try {
            id = turmaView.getId().replaceAll("\\s","");
            dataInicio = LocalDate.parse(turmaView.getDataInicio(), dateFormatter);
            dataFim = LocalDate.parse(turmaView.getDataFim(), dateFormatter);
            horario = LocalTime.parse(turmaView.getHorario(), timeFormatter);
            limite = turmaView.getLimite();
            curso = turmaView.getCurso();
            responsavel = turmaView.getResponsavel();
            turmaControl.save(id, dataInicio, dataFim, horario, limite, curso, responsavel);
        } catch (Exception e) {
            this.showMessage("Erro ao salvar turma");
        }
    }

    public void updateTurma() {
        String id;
        LocalDate dataInicio;
        LocalDate dataFim;
        LocalTime horario;
        int limite;
        Curso curso;
        Professor responsavel;
        TurmaView turmaView = viewControl.getView(TURMA_VIEW);
        try {
            id = turmaView.getId().trim();
            dataInicio = turmaView.getDataInicio().equals("__/__/____") ?
                    null : LocalDate.parse(turmaView.getDataInicio(), dateFormatter);
            dataFim = turmaView.getDataFim().equals("__/__/____") ?
                    null : LocalDate.parse(turmaView.getDataFim(), dateFormatter);
            horario = turmaView.getHorario().equals("__:__") ?
                    null : LocalTime.parse(turmaView.getHorario(), timeFormatter);
            limite = (turmaView.getLimite() == 0) ?
                    -1 : turmaView.getLimite();
            curso = turmaView.getCurso();
            responsavel = turmaView.getResponsavel();
            turmaControl.update(id, dataInicio, dataFim, horario, limite, curso, responsavel);
        } catch (Exception e) {
            this.showMessage("Erro ao atualizar turma");
        }
    }

    public void deleteTurma() {
        Turma turma;
        TurmaView turmaView = viewControl.getView(TURMA_VIEW);
        try {
            turma = turmaView.getListView().getSelectedValue();
            String id = turma.getId();
            turmaControl.delete(id);
        } catch (Exception e) {
            this.showMessage("Erro ao deletar turma");
        }
    }

    public void saveMatricula() {

    }

    public void updateMatricula() {

    }

    public void deleteMatricula() {

    }

    public AlunoControl getAlunoControl() { return alunoControl; }

    public ProfessorControl getProfessorControl() { return professorControl; }

    public CursoControl getCursoControl() { return cursoControl; }

    public TurmaControl getTurmaControl() { return turmaControl; }

    public ViewControl getViewControl() { return viewControl; }
}
