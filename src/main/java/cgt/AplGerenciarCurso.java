package cgt;

import cdp.Curso;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AplGerenciarCurso {
    private final List<Curso> lstCursos = new ArrayList<>();

    public void cadastrarCurso(String nome, int ch) {
        var curso = new Curso(nome,ch);
        lstCursos.add(curso);
        JOptionPane.showMessageDialog(null,"Curso adicionado com sucesso!");
    }
}
