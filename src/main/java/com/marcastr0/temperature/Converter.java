package com.marcastr0.temperature;

import cz.adamh.utils.NativeUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Converter extends JPanel implements ActionListener{
    public native float toCelsius(float fahrenheit);
    public native float toFahrenheit(float celsius);

    static {
        try {
            NativeUtils.loadLibraryFromJar("/libTemperatureConverter.jnilib");
        } catch (IOException e) {
            // This is probably not the best way to handle exception :-)
            e.printStackTrace();
        }
    }

    private static String TO_CELSIUS = "temperatureField";
    private static String TO_FAHRENHEIT = "fahrenheit";

    private JFrame controllingFrame;
    private JTextField temperatureField;

    public Converter(JFrame f) {
        // Use the default FlowLayout.
        controllingFrame = f;

        // Create everything.
        temperatureField = new JTextField(10);

        JLabel celsiusLabel = new JLabel("Temperature: ");
        celsiusLabel.setLabelFor(temperatureField);

        JComponent buttonPane = createButtonPanel();

        // Lay out everything.
        JPanel textPane = new JPanel(new GridLayout(2, 2));
        textPane.add(celsiusLabel);
        textPane.add(temperatureField);

        add(textPane);
        add(buttonPane);
    }

    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(2,1));
        JButton toCelsiusButton = new JButton("Convert to Celsius");
        JButton toFahrenheitButton = new JButton("Convert to Fahrenheit");

        toCelsiusButton.setActionCommand(TO_CELSIUS);
        toCelsiusButton.addActionListener(this);
        toFahrenheitButton.setActionCommand(TO_FAHRENHEIT);
        toFahrenheitButton.addActionListener(this);

        p.add(toCelsiusButton);
        p.add(toFahrenheitButton);

        return p;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (TO_CELSIUS.equals(cmd)) {
            String input = temperatureField.getText();
            if (!input.equals("")) {
                float fahrenheit = Float.parseFloat(input);
                float celsius = toCelsius(fahrenheit);
                JOptionPane.showMessageDialog(controllingFrame, String.valueOf(celsius));
            } else {
                JOptionPane.showMessageDialog(controllingFrame, "Please enter a temperature", "Error message", JOptionPane.ERROR_MESSAGE);
            }

            temperatureField.selectAll();
            resetFocus();
        } else {
            String input = temperatureField.getText();
            if (!input.equals("")) {
                float celsius = Float.parseFloat(input);
                float fahrenheit = toFahrenheit(celsius);
                JOptionPane.showMessageDialog(controllingFrame, String.valueOf(fahrenheit));
            } else {
                JOptionPane.showMessageDialog(controllingFrame, "Please enter a temperature", "Error message", JOptionPane.ERROR_MESSAGE);
            }

            temperatureField.selectAll();
            resetFocus();
        }
    }

    protected void resetFocus() {
        temperatureField.requestFocusInWindow();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Converter newContentPane = new Converter(frame);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                newContentPane.resetFocus();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}
