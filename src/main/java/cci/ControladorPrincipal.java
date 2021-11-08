package cci;

import cgt.AplGerenciarCurso;
import ciu.JanCadCurso;
import ciu.JanPrincipal;

import javax.swing.*;

/**
 * Controller Class
 * @author Paulo Sergio
 * @author Breno Haese
 * @author Nycolas Monjardim
 */

public class ControladorPrincipal {
    private JFrame janPrincipal;
    private JFrame janCadCurso;
    private final AplGerenciarCurso aplGerenciarCurso;

    public ControladorPrincipal() {
        this.aplGerenciarCurso = new AplGerenciarCurso();
        this.exibirJanPrincipal();
    }

    public void exibirJanPrincipal() {
        if (janPrincipal == null) janPrincipal = new JanPrincipal(this);
        janPrincipal.setVisible(true);
    }

    public void exibirJanCadCurso() {
        if (janCadCurso == null) janCadCurso = new JanCadCurso(this);
        janPrincipal.setVisible(false);
        janCadCurso.setVisible(true);
    }

    public int cadastrarCurso(String nome, int ch) {
        aplGerenciarCurso.cadastrarCurso(nome,ch);
        janPrincipal.setVisible(true);
        return 0;
    }

    public static void main(String[] args) { new ControladorPrincipal(); }
}
