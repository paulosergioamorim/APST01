package cci;

import cgt.AplGerenciarCurso;
import ciu.JanCadCurso;
import ciu.JanCadProfessor;
import ciu.JanPrincipal;

import javax.swing.*;

/**
 * Controller Class
 * @author Paulo Sergio
 * @author Breno Haese
 * @author Nycolas Monjardim
 */

public class ControladorPrincipal {
    private final AplGerenciarCurso aplGerenciarCurso;
    private JanPrincipal janPrincipal;
    private JanCadCurso janCadCurso;
    private JanCadProfessor janCadProfessor;

    public ControladorPrincipal() {
        this.aplGerenciarCurso = new AplGerenciarCurso();
        this.exibirJanPrincipal();
    }

    public void exibirJanPrincipal() {
        janPrincipal = (janPrincipal == null) ? new JanPrincipal(this) : janPrincipal;
        janPrincipal.setVisible(true);
        if (janCadCurso != null) janCadCurso.dispose();
        if (janCadProfessor != null) janCadProfessor.dispose();
    }

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

    public static void main(String[] args) { new ControladorPrincipal(); }

}
