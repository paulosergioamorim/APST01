package controls;

import models.Sexo;
import models.entitys.Aluno;
import models.entitys.Curso;
import views.AlunoView;
import views.CursoView;
import views.View;

import java.time.LocalDate;

import static controls.ControlFactory.*;
import static models.Format.dateFormatter;
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
	private final ViewControl viewControl;

	public Control() {
		alunoControl = createAlunoControl(this);
		professorControl = createProfessorControl(this);
		cursoControl = createCursoControl(this);
		viewControl = createViewControl(this);
	}

	public void changeView(View view) { viewControl.changeView(view); }

	public void showMessage(String message) { viewControl.showMessage(message); }

	public void updateListViewer(View view) { viewControl.updateListViewer(view); }

	public void clearFields(View view) { viewControl.clearFields(view); }

	public static void main(String[] args) { new Control(); }

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
			nome = (alunoView.getNome().isEmpty()) ?
					null : alunoView.getNome();
			sexo = (alunoView.getSexo() == null) ?
					null : alunoView.getSexo();
			dataNascimento = alunoView.getDataNascimento().equals("__/__/____") ?
					null : LocalDate.parse(alunoView.getDataNascimento(), dateFormatter);
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
			nome = (cursoView.getNome().isEmpty()) ?
					null : cursoView.getNome();
			cargaHoraria = (cursoView.getCargaHoraria().isEmpty()) ?
					-1 : Integer.parseInt(cursoView.getCargaHoraria());
			sigla = (cursoView.getSigla().isEmpty()) ?
					null : cursoView.getSigla();
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

	}

	public void updateProfessor() {

	}

	public void deleteProfessor() {

	}

	public void saveTurma() {

	}

	public void updateTurma() {

	}

	public void deleteTurma() {

	}

	public void saveMatricula() {

	}

	public void updateMatricula() {

	}

	public void deleteMatricula() {

	}

	public AlunoControl getAlunoControl() { return alunoControl; }

	public ProfessorControl getProfessorControl() { return professorControl; }

	public CursoControl getCursoControl() { return cursoControl;}
}
