package cci;

import cgt.AplGerenciarCurso;
import cgt.AplGerenciarPessoa;
import ciu.JanCadCurso;
import ciu.JanCadProfessor;
import ciu.JanPrincipal;

import javax.swing.*;
import java.time.LocalDate;

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

    public ControladorPrincipal() { this.exibirJanPrincipal(); }

    /**
     * Exibe a janela principal
     */
    public void exibirJanPrincipal() {
        janPrincipal = (janPrincipal == null) ? new JanPrincipal(this) : janPrincipal;
        janPrincipal.setVisible(true);
        if (janCadCurso != null) janCadCurso.dispose();
        if (janCadProfessor != null) janCadProfessor.dispose();
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
                janCadCurso.getNome().setText("");
                janCadCurso.getCh().setText("");
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
                janCadProfessor.getCpf().setText("");
                break;
            case 2:
                JOptionPane.showMessageDialog(janCadProfessor, "Esse nome já foi cadastrado");
                janCadProfessor.getNome().setText("");
                break;
            case 3:
                JOptionPane.showMessageDialog(janCadProfessor, "Essa data é futura");
                janCadProfessor.getDataNascimento().setText("");
                break;
            case 4:
                JOptionPane.showMessageDialog(janCadProfessor, "Ocorreu um erro");
                janCadProfessor.getCpf().setText("");
                janCadProfessor.getNome().setText("");
                janCadProfessor.getDataNascimento().setText("");
                break;
        }
    }

    public static void main(String[] args) { new ControladorPrincipal(); }

}
