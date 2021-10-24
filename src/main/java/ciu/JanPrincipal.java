package ciu;

import cci.ControladorPrincipal;

import javax.swing.*;
import java.awt.*;

public class JanPrincipal extends JFrame {
    private final ControladorPrincipal controlador;

    public JanPrincipal(ControladorPrincipal controlador) {
        super("Título");
        this.controlador = controlador;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(false);

        this.panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        this.setContentPane(panel);

        this.addCurso = new JButton("Cadastrar Button");
        addCurso.setFocusable(false);
        addCurso.setBorderPainted(false);

        this.addCurso.addActionListener(e -> controlador.exibirJanCadCurso());
        this.add(this.addCurso); //, BorderLayout.CENTER);
    }
}
