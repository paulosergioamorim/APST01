package cci;

import cdp.Curso;
import cdp.Professor;
import cgt.AplGerenciarCurso;
import cgt.AplGerenciarPessoa;
import ciu.JanCadCurso;
import ciu.JanCadProfessor;
import ciu.JanCadTurma;
import ciu.JanPrincipal;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Controller Class
 * @author Paulo Sergio
 * @author Breno Haese
 * @author Nycolas Monjardim
 */
public class ControladorPrincipal {
    private final AplGerenciarCurso aplGerenciarCurso = new AplGerenciarCurso();
    private final AplGerenciarPessoa aplGerenciarPessoa = new AplGerenciarPessoa();
    private JanPrincipal janPrincipal;
    private JanCadCurso janCadCurso;
    private JanCadProfessor janCadProfessor;
    private JanCadTurma janCadTurma;

    public ControladorPrincipal() { this.exibirJanPrincipal(); }

    /**
     * Exibe a janela principal
     */
    public void exibirJanPrincipal() {
        janPrincipal = (janPrincipal == null) ? new JanPrincipal(this) : janPrincipal;
        janPrincipal.setVisible(true);
        if (janCadCurso != null) janCadCurso = null;
        if (janCadProfessor != null) janCadProfessor = null;
        if (janCadTurma != null) janCadTurma = null;
    }

    /**
     * Exibe a tela de cadastro de curso
     */
    public void exibirJanCadCurso() {
        janCadCurso = (janCadCurso == null) ? new JanCadCurso(this) : janCadCurso;
        janPrincipal.setVisible(false);
        janCadCurso.setVisible(true);
    }

    /**
     * Exibe a tela de cadastro de professor
     */
    public void exibirJanCadProfessor() {
        janCadProfessor = (janCadProfessor == null) ? new JanCadProfessor(this) : janCadProfessor;
        janPrincipal.setVisible(false);
        janCadProfessor.setVisible(true);
    }

    /**
     * Exibe a tela de cadastro de turma
     */
    public void exibirJanCadTurma() {
        janCadTurma = (janCadTurma == null) ? new JanCadTurma(this) : janCadTurma;
        janPrincipal.setVisible(false);
        janCadTurma.setVisible(true);
    }

    /**
     * Cadastra um curso
     * @param nome Nome do curso
     * @param ch Carga horária do curso
     */
    public void cadastrarCurso(String nome, int ch) {
        int response = aplGerenciarCurso.cadastrarCurso(nome,ch);
        switch (response) {
            case 0:
                JOptionPane.showMessageDialog(janCadCurso, "Curso cadastrado com sucesso");
                this.janCadCurso.dispose();
                this.janPrincipal.setVisible(true);
                break;
            case 1:
                JOptionPane.showMessageDialog(janCadCurso, "Ocorreu um erro");
                janCadCurso.getNome().setText(null);
                janCadCurso.getCh().setText(null);
                break;
        }
    }

    /**
     * Cadastra um professor
     * @param nome Nome do professor
     * @param cpf CPF do professor
     * @param dataNascimento Data de nascimento do professor
     * @param titulacao Titulação do professor
     */
    public void cadastrarProfessor(String nome, LocalDate dataNascimento, long cpf, String titulacao) {
        int response = aplGerenciarPessoa.cadastrarProfessor(nome,dataNascimento,cpf,titulacao);
        switch (response) {
            case 0:
                JOptionPane.showMessageDialog(janCadProfessor, "Professor cadastrado com sucesso");
                this.janCadProfessor.dispose();
                this.janPrincipal.setVisible(true);
                break;
            case 1:
                JOptionPane.showMessageDialog(janCadProfessor, "Esse cpf já foi cadastrado");
                janCadProfessor.getCpf().setText(null);
                break;
            case 2:
                JOptionPane.showMessageDialog(janCadProfessor, "Esse nome já foi cadastrado");
                janCadProfessor.getNome().setText(null);
                break;
            case 3:
                JOptionPane.showMessageDialog(janCadProfessor, "Essa data é futura");
                janCadProfessor.getDataNascimento().setText(null);
                break;
            case 4:
                JOptionPane.showMessageDialog(janCadProfessor, "Ocorreu um erro");
                janCadProfessor.getCpf().setText(null);
                janCadProfessor.getNome().setText(null);
                janCadProfessor.getDataNascimento().setText(null);
                break;
        }
    }

    /**
     * Cadastra uma turma
     * @param dataInicio Data de início da turma
     * @param dataFim Data de fim da turma
     * @param horario Horário da turma
     * @param limiteAlunos Limite de alunos da turma
     * @param curso Curso da turma
     * @param professor Professor da turma
     */
    public void cadastrarTurma(LocalDate dataInicio, LocalDate dataFim, LocalTime horario, int limiteAlunos, Curso curso, Professor professor) {
        int response = aplGerenciarCurso.cadastrarTurma(dataInicio,dataFim,horario,limiteAlunos,curso,professor);
        switch (response) {
            case 0:
                JOptionPane.showMessageDialog(janCadTurma, "Turma cadastrada com sucesso");
                this.janCadTurma.dispose();
                this.janPrincipal.setVisible(true);
                break;
            case 1:
                JOptionPane.showMessageDialog(janCadTurma, "A data de início deve ser anterior à data atual");
                janCadTurma.getDataInicio().setText(null);
                break;
            case 2:
                JOptionPane.showMessageDialog(janCadTurma, "A data final deve ser posterior à data de início");
                janCadTurma.getDataFim().setText(null);
                break;
            case 3:
                JOptionPane.showMessageDialog(janCadTurma, "O limite de alunos deve ser maior que 0");
                janCadTurma.getLimiteAlunos().setText(null);
                break;
            case 4:
                JOptionPane.showMessageDialog(janCadTurma, "O curso fornecido não existe");
                janCadTurma.getCurso().setSelectedIndex(0);
                break;
            case 5:
                JOptionPane.showMessageDialog(janCadTurma, "O professor não existe");
                janCadTurma.getProfessor().setSelectedIndex(0);
                break;
            case 6:
                JOptionPane.showMessageDialog(janCadTurma, "Ocorreu um erro");
                janCadTurma.getDataInicio().setText(null);
                janCadTurma.getDataFim().setText(null);
                janCadTurma.getHorario().setText(null);
                janCadTurma.getLimiteAlunos().setText(null);
                janCadTurma.getCurso().setSelectedItem(null);
                janCadTurma.getProfessor().setSelectedItem(null);
                break;
        }
    }

    public static void main(String[] args) { new ControladorPrincipal(); }

}
