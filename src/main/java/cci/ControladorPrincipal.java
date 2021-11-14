package cci;

import cdp.*;
import cgt.AplGerenciarCurso;
import cgt.AplGerenciarPessoas;
import ciu.JanPrincipal;
import ciu.cadastros.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * Controller Class
 * @author Paulo Sergio
 * @author Breno Haese
 * @author Nycolas Monjardim
 */
public class ControladorPrincipal {
    private final AplGerenciarCurso aplGerenciarCurso;
    private final AplGerenciarPessoas aplGerenciarPessoa;

    private JanPrincipal janPrincipal;
    private JanCadCurso janCadCurso;
    private JanCadProfessor janCadProfessor;
    private JanCadTurma janCadTurma;
    private JanCadAluno janCadAluno;
    private JanCadMatricula janCadMatricula;

    public ControladorPrincipal() {
        this.exibirJanPrincipal();
        this.aplGerenciarCurso = new AplGerenciarCurso();
        this.aplGerenciarPessoa = new AplGerenciarPessoas();
    }

    /**
     * Exibe a janela principal.
     * Caso as outras janelas estejam abertas, elas são fechadas.
     */
    public void exibirJanPrincipal() {
        janPrincipal = (janPrincipal == null) ? new JanPrincipal(this) : janPrincipal;
        janPrincipal.setVisible(true);
        this.closeAll();
    }

    /**
     * Os métodos abaixo servem para exibir as janelas de cadastro de cursos,
     * professores, turmas, alunos e matrículas, e fecham a janela principal.
     */

    public void exibirJanCadCurso() {
        janCadCurso = (janCadCurso == null) ? new JanCadCurso(this) : janCadCurso;
        janPrincipal.setVisible(false);
        janCadCurso.setVisible(true);
    }

    public void exibirJanCadProfessor() {
        janCadProfessor = (janCadProfessor == null) ? new JanCadProfessor(this) : janCadProfessor;
        janPrincipal.setVisible(false);
        janCadProfessor.setVisible(true);
    }

    public void exibirJanCadTurma() {
        janCadTurma = (janCadTurma == null) ? new JanCadTurma(this) : janCadTurma;
        janPrincipal.setVisible(false);
        janCadTurma.setVisible(true);
    }

    public void exibirJanCadAluno() {
        janCadAluno = (janCadAluno == null) ? new JanCadAluno(this) : janCadAluno;
        janPrincipal.setVisible(false);
        janCadAluno.setVisible(true);
    }

    public void exibirJanCadMatricula() {
        janCadMatricula = (janCadMatricula == null) ? new JanCadMatricula(this) : janCadMatricula;
        janPrincipal.setVisible(false);
        janCadMatricula.setVisible(true);
    }

    /**
     * Fecha todas as janelas exceto a principal, como o método de fechar essas janelas é
     * DISPOSE_ON_CLOSE, ou seja, a janela é fechada, mas o código ainda continua rodando,
     * para encerrar o código também devemos anular o valor delas.
     */
    public void closeAll() {
        janCadCurso = null;
        janCadProfessor = null;
        janCadTurma = null;
        janCadAluno = null;
        janCadMatricula = null;
    }

    /**
     * Métodos de cadastro de cursos, professores, turmas, alunos e matrículas.
     */

    public void cadastrarCurso(String nome, int ch) {
        int response = aplGerenciarCurso.cadastrarCurso(nome,ch);
        switch (response) {
            case 0:
                JOptionPane.showMessageDialog(janCadCurso, "Curso cadastrado com sucesso");
                janCadCurso.getNome().setText(null);
                janCadCurso.getCh().setText(null);
                break;
            case 1: JOptionPane.showMessageDialog(janCadCurso, "Preencha os campos para cadastrar o curso"); break;
            case 2: JOptionPane.showMessageDialog(janCadCurso, "Um curso com esse nome já existe"); break;
        }
    }

    public void cadastrarProfessor(String nome, LocalDate dataNascimento, long cpf, String titulacao) {
        int response = aplGerenciarPessoa.cadastrarProfessor(nome,dataNascimento,cpf,titulacao);
        switch (response) {
            case 0:
                JOptionPane.showMessageDialog(janCadProfessor, "Professor cadastrado com sucesso");
                janCadProfessor.getCpf().setText(null);
                janCadProfessor.getNome().setText(null);
                janCadProfessor.getDataNascimento().setText(null);
                janCadProfessor.getTitulacao().setText(null);
                break;
            case 1: JOptionPane.showMessageDialog(janCadProfessor, "Esse cpf já foi cadastrado"); break;
            case 2: JOptionPane.showMessageDialog(janCadProfessor, "A data de nascimento é futura"); break;
        }
    }

    public void cadastrarTurma(LocalDate dataInicio, LocalDate dataFim, LocalTime horario, int limiteAlunos, Curso curso, Professor professor) {
        int response = aplGerenciarCurso.cadastrarTurma(dataInicio,dataFim,horario,limiteAlunos,curso,professor);
        switch (response) {
            case 0:
                JOptionPane.showMessageDialog(janCadTurma, "Turma cadastrada com sucesso");
                janCadTurma.getDataInicio().setText(null);
                janCadTurma.getDataFim().setText(null);
                janCadTurma.getHorario().setText(null);
                janCadTurma.getLimiteAlunos().setText(null);
                janCadTurma.getCurso().setSelectedItem(null);
                janCadTurma.getProfessor().setSelectedItem(null);
                break;
            case 1: JOptionPane.showMessageDialog(janCadTurma, "A data final deve ser posterior à data de início"); break;
            case 2: JOptionPane.showMessageDialog(janCadTurma, "O limite de alunos deve ser maior que 0"); break;
            case 3: JOptionPane.showMessageDialog(janCadTurma, "O curso fornecido não existe"); break;
            case 4: JOptionPane.showMessageDialog(janCadTurma, "O professor não existe"); break;
        }
    }

    public void cadastrarAluno(String nome, LocalDate dataNascimento, long cpf) {
        int response = aplGerenciarPessoa.cadastrarAluno(nome,dataNascimento,cpf);
        switch (response) {
            case 0:
                JOptionPane.showMessageDialog(janCadAluno, "Aluno cadastrado com sucesso");
                janCadAluno.getNome().setText(null);
                janCadAluno.getDataNascimento().setText(null);
                janCadAluno.getCpf().setText(null);
                break;
            case 1: JOptionPane.showMessageDialog(janCadAluno,"Esse cpf já foi cadastrado"); break;
            case 2: JOptionPane.showMessageDialog(janCadAluno,"A data de nascimento é futura"); break;
        }
    }

    /**
     * Efetua a matrícula de um aluno em uma turma
     * @param aluno Aluno a ser matriculado
     * @param turma Turma da qual o aluno será matriculado
     */
    public void efetuarMatricula(Aluno aluno, Turma turma) {
        String estadoTurma = turma.obterEstado();
        switch (estadoTurma) {
            case "Em Andamento": JOptionPane.showMessageDialog(janCadMatricula, "A matrícula não pode ser realizada pois a turma está em andamento"); return;
            case "Matriculas Encerradas": JOptionPane.showMessageDialog(janCadMatricula, "Não há vagas disponíveis nessa turma"); return;
            case "Aulas Encerradas": JOptionPane.showMessageDialog(janCadMatricula, "Não há mais aulas disponíveis nessa turma"); return;
            case "Fechada": JOptionPane.showMessageDialog(janCadMatricula, "A matrícula não pode ser realizada pois a turma está fechada"); return;
        }
        if (Arrays.stream(turma.getMatriculas())
                .filter(Objects::nonNull)
                .anyMatch(matricula -> matricula.getAluno().equals(aluno))
        ) {
            JOptionPane.showMessageDialog(janCadMatricula, "O aluno já está matriculado nessa turma");
            return;
        }
        Matricula matricula = new Matricula(aluno, turma);
        turma.addMatricula(matricula);
        JOptionPane.showMessageDialog(janCadAluno, "Aluno matriculado com sucesso");
    }

    public static void main(String[] args) { new ControladorPrincipal(); }

}
