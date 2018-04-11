package com.evolution;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SendByTCPDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldAddress;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JList listFiles;
    private DefaultListModel listModel = new DefaultListModel();

    public String ipAddress = "";
    public ArrayList<String> filesToSend = new ArrayList<>();
    public boolean dialogResult = false;

    public SendByTCPDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(800,600);
        setTitle("Send by ONYX over TCP protocol");
        listFiles.setModel(listModel);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                if(chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION)
                {
                    String path = chooser.getSelectedFile().getAbsolutePath();
                    listModel.addElement(path);
                }
            }
        });
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(listFiles.getSelectedIndex()!=-1)
                {
                    listModel.remove(listFiles.getSelectedIndex());
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        dialogResult = true;
        ipAddress = textFieldAddress.getText();
        for(int i=0;i<listModel.size();i++)
            filesToSend.add(listModel.getElementAt(i).toString());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
