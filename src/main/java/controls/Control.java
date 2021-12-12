package controls;

import models.Sexo;
import models.View;
import models.entitys.*;
import views.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static controls.ControlFactory.*;
import static models.Format.dateFormatter;
import static models.Format.timeFormatter;
import static models.View.*;

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
    private final MatriculaControl matriculaControl;
    private final ViewControl viewControl;

    public Control() {
        alunoControl = createAlunoControl(this);
        professorControl = createProfessorControl(this);
        cursoControl = createCursoControl(this);
        turmaControl = createTurmaControl(this);
        viewControl = createViewControl(this);
        matriculaControl = createMatriculaControl(this);
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
        long cpf;
        AlunoView alunoView = viewControl.getView(ALUNO_VIEW);
        try {
            cpf = alunoView.getListView().getSelectedValue().getCpf();
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
                    0 : Integer.parseInt(cursoView.getCargaHoraria());
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
        int id;
        LocalDate dataInicio;
        LocalDate dataFim;
        LocalTime horario;
        int limite;
        Curso curso;
        Professor responsavel;
        TurmaView turmaView = viewControl.getView(TURMA_VIEW);
        try {
            id = Integer.parseInt(turmaView.getId());
            dataInicio = LocalDate.parse(turmaView.getDataInicio(), dateFormatter);
            dataFim = LocalDate.parse(turmaView.getDataFim(), dateFormatter);
            horario = LocalTime.parse(turmaView.getHorario(), timeFormatter);
            limite = Integer.parseInt(turmaView.getLimite());
            curso = turmaView.getCurso();
            responsavel = turmaView.getResponsavel();
            turmaControl.save(id, dataInicio, dataFim, horario, limite, curso, responsavel);
        } catch (Exception e) {
            this.showMessage("Erro ao salvar turma");
        }
    }

    public void updateTurma() {
        int id;
        LocalDate dataInicio;
        LocalDate dataFim;
        LocalTime horario;
        int limite;
        Curso curso;
        Professor responsavel;
        TurmaView turmaView = viewControl.getView(TURMA_VIEW);
        try {
            id = Integer.parseInt(turmaView.getId());
            dataInicio = turmaView.getDataInicio().equals("__/__/____") ?
                    null : LocalDate.parse(turmaView.getDataInicio(), dateFormatter);
            dataFim = turmaView.getDataFim().equals("__/__/____") ?
                    null : LocalDate.parse(turmaView.getDataFim(), dateFormatter);
            horario = turmaView.getHorario().equals("__:__") ?
                    null : LocalTime.parse(turmaView.getHorario(), timeFormatter);
            limite = turmaView.getLimite().equals("00") ?
                    0 : Integer.parseInt(turmaView.getLimite());
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
            int id = turma.getId();
            turmaControl.delete(id);
        } catch (Exception e) {
            this.showMessage("Erro ao deletar turma");
        }
    }

    public void closeTurma() {
        Turma turma;
        NotasView notasView = viewControl.getView(NOTAS_VIEW);
        try {
            turma = notasView.getTurma();
            int id = turma.getId();
            turmaControl.close(id);
        } catch (Exception e) {
            this.showMessage("Erro ao fechar turma");
        }
    }

    public void saveMatricula() {
        Aluno aluno;
        Turma turma;
        LocalDate dataMatricula;
        MatriculaView matriculaView = viewControl.getView(MATRICULA_VIEW);
        try {
            aluno = matriculaView.getAluno();
            turma = matriculaView.getTurma();
            dataMatricula = matriculaView.getDataMatricula().equals("__/__/____") ?
                    LocalDate.now() : LocalDate.parse(matriculaView.getDataMatricula(), dateFormatter);
            matriculaControl.save(aluno, turma, dataMatricula);
        } catch (Exception e) {
            this.showMessage("Erro ao salvar matricula");
        }
    }

    public void updateMatricula() {
        Aluno aluno;
        Turma turma;
        LocalDate dataMatricula;
        MatriculaView matriculaView = viewControl.getView(MATRICULA_VIEW);
        try {
            aluno = matriculaView.getAluno();
            turma = matriculaView.getTurma();
            dataMatricula = matriculaView.getDataMatricula().equals("__/__/____") ?
                    null : LocalDate.parse(matriculaView.getDataMatricula(), dateFormatter);
            matriculaControl.update(aluno, turma, dataMatricula);
        } catch (Exception e) {
            this.showMessage("Erro ao atualizar matricula");
        }
    }

    public void setNota() {
        Aluno aluno;
        Turma turma;
        double nota;
        NotasView notasView = viewControl.getView(NOTAS_VIEW);
        try {
            aluno = notasView.getAluno();
            turma = notasView.getTurma();
            nota = Double.parseDouble(notasView.getNota());
            matriculaControl.update(aluno, turma, nota);
        } catch (Exception e) {
            this.showMessage("Erro ao atualizar nota");
        }
    }

    public void deleteMatricula() {
        Matricula matricula;
        MatriculaView matriculaView = viewControl.getView(MATRICULA_VIEW);
        try {
            matricula = matriculaView.getListView().getSelectedValue();
            Aluno aluno = matricula.getAluno();
            Turma turma = matricula.getTurma();
            matriculaControl.delete(aluno, turma);
        } catch (Exception e) {
            this.showMessage("Erro ao deletar matricula");
        }
    }

    public AlunoControl getAlunoControl() { return alunoControl; }

    public ProfessorControl getProfessorControl() { return professorControl; }

    public CursoControl getCursoControl() { return cursoControl; }

    public TurmaControl getTurmaControl() { return turmaControl; }

    public MatriculaControl getMatriculaControl() { return matriculaControl; }
}
