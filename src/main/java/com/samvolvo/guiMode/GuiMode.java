package com.samvolvo.guiMode;

import com.samvolvo.enums.Gebruiker;
import com.samvolvo.enums.Item;
import com.samvolvo.kassaSysteem.Rekening;

import javax.swing.*;
import java.awt.*;

public class GuiMode {

    public GuiMode() {
        SwingUtilities.invokeLater(this::showLogin);
    }

    private void showLogin() {
        JFrame frame = new JFrame("KassaOS Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 2));

        JTextField userField = new JTextField();
        JPasswordField pinField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        frame.add(new JLabel("User:"));
        frame.add(userField);
        frame.add(new JLabel("Pin:"));
        frame.add(pinField);
        frame.add(new JLabel());
        frame.add(loginButton);

        Runnable loginAction = () -> {
            try {
                int userId = Integer.parseInt(userField.getText());
                int pin = Integer.parseInt(new String(pinField.getPassword()));
                Gebruiker gebruiker = Gebruiker.getGebruikerDoorId(userId);
                if (gebruiker != null && pin == gebruiker.getCode()) {
                    frame.dispose();
                    showRegister(gebruiker);
                } else {
                    JOptionPane.showMessageDialog(frame, "Onjuiste gegevens!", "Fout", JOptionPane.ERROR_MESSAGE);
                    userField.setText("");
                    pinField.setText("");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Onjuiste gegevens!", "Fout", JOptionPane.ERROR_MESSAGE);
            }
        };

        loginButton.addActionListener(e -> loginAction.run());
        pinField.addActionListener(e -> loginAction.run());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showRegister(Gebruiker gebruiker) {
        JFrame frame = new JFrame("KassaOS - " + gebruiker.name().toLowerCase());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        Rekening rekening = new Rekening();

        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        frame.add(new JScrollPane(receiptArea), BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton breakButton = new JButton("Break");
        JButton logoffButton = new JButton("Logoff");
        topPanel.add(breakButton);
        topPanel.add(logoffButton);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JTextField codeField = new JTextField();
        bottomPanel.add(codeField, BorderLayout.CENTER);

        JLabel totalLabel = new JLabel("Totaal: 0.00");
        bottomPanel.add(totalLabel, BorderLayout.NORTH);

        JPanel keypad = new JPanel(new GridLayout(4, 3));
        for (int i = 1; i <= 9; i++) {
            keypad.add(createDigitButton(String.valueOf(i), codeField));
        }
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> codeField.setText(""));
        keypad.add(clearButton);

        keypad.add(createDigitButton("0", codeField));

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> processCode(codeField, rekening, receiptArea, totalLabel));
        keypad.add(enterButton);

        bottomPanel.add(keypad, BorderLayout.EAST);

        Action loginAction = new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                processCode(codeField, rekening, receiptArea, totalLabel);
            }
        };
        codeField.addActionListener(loginAction);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        breakButton.addActionListener(e -> showBreakDialog(frame));
        logoffButton.addActionListener(e -> {
            frame.dispose();
            showLogin();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createDigitButton(String digit, JTextField field) {
        JButton button = new JButton(digit);
        button.addActionListener(e -> field.setText(field.getText() + digit));
        return button;
    }

    private void processCode(JTextField field, Rekening rekening, JTextArea area, JLabel totalLabel) {
        String code = field.getText();
        Item item = rekening.addProduct(code);
        if (item != null) {
            area.append(item.name().toLowerCase() + " - " + item.getPrice() + "\n");
            totalLabel.setText(String.format("Totaal: %.2f", rekening.getTotaal()));
            field.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Onbekende PLU nummer", "Fout", JOptionPane.ERROR_MESSAGE);
            field.setText("");
        }
    }

    private void showBreakDialog(JFrame parent) {
        final JDialog dialog = new JDialog(parent, "Break", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(new JLabel("Pauze" , SwingConstants.CENTER), BorderLayout.CENTER);
        JButton resume = new JButton("Resume");
        resume.addActionListener(e -> dialog.dispose());
        dialog.add(resume, BorderLayout.SOUTH);
        dialog.setSize(200, 100);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}

