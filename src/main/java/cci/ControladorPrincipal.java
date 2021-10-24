package cci;

import cgt.AplGerenciarCurso;
import ciu.JanCadCurso;
import ciu.JanPrincipal;

import javax.swing.*;

public class ControladorPrincipal {
    private JFrame janPrincipal;
    private JFrame janCadCurso;
    private final AplGerenciarCurso aplGerenciarCurso = new AplGerenciarCurso();

    { this.exibirJanPrincipal(); }

    public void exibirJanPrincipal() {
        if (janPrincipal == null) janPrincipal = new JanPrincipal(this);
        janPrincipal.setVisible(true);
    }

    public void exibirJanCadCurso() {
        if (janCadCurso == null) janCadCurso = new JanCadCurso(this);
        janPrincipal.setVisible(false);
        janCadCurso.setVisible(true);
    }

    public void cadastrarCurso(String nome, int ch) { aplGerenciarCurso.cadastrarCurso(nome,ch); }

    public static void main(String[] args) { new ControladorPrincipal(); }
}