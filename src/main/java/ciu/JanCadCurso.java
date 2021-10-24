package ciu;

import cci.ControladorPrincipal;
import cdp.RegexFilter;

import javax.swing.*;
import java.awt.*;

public class JanCadCurso extends JFrame {
    private final ControladorPrincipal controlador;
    private final JPanel panel;

    private final JPanel fieldsPanel;
    private final JPanel buttonsPanel;

    private final JTextField fieldNomeCurso;
    private final JFormattedTextField fieldCHCurso;

    private final JButton adicionarButton;

    public JanCadCurso(ControladorPrincipal controlador) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(300,600);

        this.controlador = controlador;
        this.panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.DARK_GRAY);
        this.setContentPane(panel);

        fieldsPanel = new JPanel();
        fieldsPanel.setBackground(Color.DARK_GRAY);
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.DARK_GRAY);
        adicionarButton = new JButton("Adicionar Curso");
        fieldNomeCurso = new JTextField(20);
        fieldCHCurso = new JFormattedTextField();
        fieldCHCurso.setPreferredSize(new Dimension(100,30));

        Pattern chPattern = Pattern.compile("[\\d]+ horas");
        fieldCHCurso.getDocument()
                .addDocumentListener(new RegexFilterListener(adicionarButton,
                        new RegexFilter(chPattern, fieldCHCurso)));

        this.buttonsPanel.add(adicionarButton);
        this.fieldsPanel.add(fieldNomeCurso);
        this.fieldsPanel.add(fieldCHCurso);
        this.panel.add(fieldsPanel, BorderLayout.CENTER);
        this.panel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private static class RegexFilterListener implements DocumentListener {
        RegexFilter filter;
        JButton     button;

        public RegexFilterListener(JButton button, RegexFilter filter) {
            this.button  = button;
            this.filter = filter;
        }

        private void checkContent() {
            this.button.setEnabled(this.filter.isExactMatch());
        }

        @Override
        public void insertUpdate(final DocumentEvent e) { this.checkContent(); }

        @Override
        public void removeUpdate(final DocumentEvent e) { this.checkContent(); }

        @Override
        public void changedUpdate(final DocumentEvent e) { this.checkContent(); }
    }
}
