package com.evolution;

import OnyxTCP.OnyxTCPParser;
import OnyxTCP.OnyxTCPProtocol;
import org.omg.IOP.Encoding;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class MainForm extends JFrame {
    private JButton buttonGo;
    private JEditorPane textEditorPane;
    private JPanel rootPanel;
    private JLabel statusLabel;
    private JRadioButton ALTSHIFTRadioButton;
    private JRadioButton CTRLSHIFTRadioButton;
    private JRadioButton WINSPACERadioButton;
    private JButton buttonSettings;
    private JButton getViaTCPButton;

    protected int portNumber = 10486;

    public MainForm() {
        //setVisible(true);
        setContentPane(rootPanel);
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        RoboticSettings.LoadSettings();

        buttonGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String text = textEditorPane.getText();

                RoboticInput.LanguageSwitchMode switchMode;
                if (ALTSHIFTRadioButton.isSelected())
                    switchMode = RoboticInput.LanguageSwitchMode.ALT_SHIFT;
                else if (CTRLSHIFTRadioButton.isSelected())
                    switchMode = RoboticInput.LanguageSwitchMode.CONTROL_SHIFT;
                else
                    switchMode = RoboticInput.LanguageSwitchMode.WIN_SPACE;

                try {
                    statusLabel.setText("Status: Select application you want to redirect input");
                    Thread.sleep(RoboticSettings.startTimeout);
                    statusLabel.setText("Status: Application emulates keyboard input");
                    RoboticInput input = new RoboticInput(switchMode);
                    input.emulateKeyboard(text);
                } catch (AWTException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                statusLabel.setText("Status: Application finished emulation");

                textEditorPane.grabFocus();
                textEditorPane.select(textEditorPane.getText().indexOf("$$$", 0), textEditorPane.getText().indexOf("$$$", 0) + 3);
            }
        });

        buttonSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SettingsForm form = new SettingsForm();
                form.setVisible(true);
            }
        });
        getViaTCPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OnyxTCPProtocol protocol = new OnyxTCPProtocol();
                protocol.recieve(portNumber);
                OnyxTCPParser parser = new OnyxTCPParser(protocol);
                textEditorPane.setText(parser.outputData);
                statusLabel.setText("Input get via TCP (length "+protocol.count.toString()+" pairs)");
            }
        });
    }

    private int bytesToInt(byte[] bytes) throws IllegalArgumentException
    {
        if(bytes.length!=4) throw new IllegalArgumentException("Cannot parse bytes to int because of incorrect byte[length]");
        int value = bytes[3] & 0xFF;

        value = (value<<8) + (bytes[2] & 0xFF);
        value = (value<<8) + (bytes[1] & 0xFF);
        value = (value<<8) + (bytes[0] & 0xFF);
        return value;
    }
}
