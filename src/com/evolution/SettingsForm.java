package com.evolution;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SettingsForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSlider timingSlider;
    private JLabel timingLbl;
    private JLabel timingSliderAdjLbl;
    private JSlider afterEnterSlider;
    private JLabel afterEnterAdjLbl;
    private JSlider initTimingSlider;
    private JLabel initTimingAdjLbl;
    private JSlider delayTimingSlider;
    private JLabel delayTimingAdjLabel;

    public SettingsForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(800,600);
        setTitle("ONYX settings");

        initializeUI();

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

        timingSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                timingSliderAdjLbl.setText( String.valueOf(((JSlider)changeEvent.getSource()).getValue()) );
            }
        });

        afterEnterSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                afterEnterAdjLbl.setText( String.valueOf(((JSlider)changeEvent.getSource()).getValue()) );
            }
        });

        initTimingSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                initTimingAdjLbl.setText( String.valueOf(((JSlider)changeEvent.getSource()).getValue()) );
            }
        });
        delayTimingSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                delayTimingAdjLabel.setText( String.valueOf(((JSlider)changeEvent.getSource()).getValue()));
            }
        });
    }

    private void onOK() {
        // add your code here
        RoboticSettings.timeout = timingSlider.getValue();
        RoboticSettings.lineTimeout = afterEnterSlider.getValue();
        RoboticSettings.startTimeout = initTimingSlider.getValue();
        RoboticSettings.delayTimeout = delayTimingSlider.getValue();

        try(ObjectOutputStream outstream = new ObjectOutputStream( new FileOutputStream("settings.onyxconfig")))
        {
            outstream.writeInt(RoboticSettings.versionId);
            outstream.writeInt(RoboticSettings.timeout);
            outstream.writeInt(RoboticSettings.lineTimeout);
            outstream.writeInt(RoboticSettings.startTimeout);
            outstream.writeInt(RoboticSettings.delayTimeout);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void initializeUI()
    {
        timingSlider.setValue(RoboticSettings.timeout);
        timingSliderAdjLbl.setText(String.valueOf(RoboticSettings.timeout));
        afterEnterSlider.setValue(RoboticSettings.lineTimeout);
        afterEnterAdjLbl.setText(String.valueOf(RoboticSettings.lineTimeout));
        initTimingSlider.setValue(RoboticSettings.startTimeout);
        initTimingAdjLbl.setText(String.valueOf(RoboticSettings.startTimeout));
        delayTimingSlider.setValue(RoboticSettings.delayTimeout);
        delayTimingAdjLabel.setText(String.valueOf(RoboticSettings.delayTimeout));
    }
}
