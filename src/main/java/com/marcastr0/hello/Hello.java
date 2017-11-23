package com.marcastr0.hello;

import cz.adamh.utils.NativeUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Hello extends JPanel implements ActionListener{
    public native String sayHi(String who);

    static {
        try {
            NativeUtils.loadLibraryFromJar("/libHelloImpl.jnilib");
        } catch (IOException e) {
            // This is probably not the best way to handle exception :-)
            e.printStackTrace();
        }
    }

    private static String OK = "ok";

    private JFrame controllingFrame;
    private JTextField textField;

    public Hello(JFrame f) {
        // Use the default FlowLayout.
        controllingFrame = f;

        // Create everything.
        textField = new JTextField(10);
        textField.setActionCommand(OK);
        textField.addActionListener(this);

        JLabel label = new JLabel("Enter your name: ");
        label.setLabelFor(textField);

        JComponent buttonPane = createButtonPanel();

        // Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(label);
        textPane.add(textField);

        add(textPane);
        add(buttonPane);
    }

    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0,1));
        JButton okButton = new JButton("OK");

        okButton.setActionCommand(OK);
        okButton.addActionListener(this);

        p.add(okButton);

        return p;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (OK.equals(cmd)) {
            String input = textField.getText();
            if (!sayHi(input).equals("")) {
                JOptionPane.showMessageDialog(controllingFrame, "Hello " + input);
            } else {
                JOptionPane.showMessageDialog(controllingFrame, "Please enter a name", "Error message", JOptionPane.ERROR_MESSAGE);
            }

            textField.selectAll();
            resetFocus();
        } else {
            // do nothing
        }
    }

    protected void resetFocus() {
        textField.requestFocusInWindow();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Hello World");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Hello newContentPane = new Hello(frame);
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
