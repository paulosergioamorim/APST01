package cgt;

import cdp.Curso;

import java.util.ArrayList;
import java.util.List;

public class AplGerenciarCurso {
    private final List<Curso> lstCursos = new ArrayList<>();

    public int cadastrarCurso(String nome, int ch) {
        try {
            Curso curso = new Curso(nome,ch);
            lstCursos.add(curso);
            return 1; // success
        } catch (Exception e) {
            return 0; // error
        }
    }
}
