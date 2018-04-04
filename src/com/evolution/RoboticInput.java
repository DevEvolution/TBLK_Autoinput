package com.evolution;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RoboticInput {
    Robot robot;
    private Keytable keytable;
    private Integer[] langSwitch = new Integer[2];
    private boolean isLangSwitched = false;

    public RoboticInput(LanguageSwitchMode mode) throws AWTException {
        robot = new Robot();
        keytable = new Keytable();

        switch(mode)
        {
            case ALT_SHIFT:
                langSwitch[0] = KeyEvent.VK_ALT;
                langSwitch[1] = KeyEvent.VK_SHIFT;
                break;
            case CONTROL_SHIFT:
                langSwitch[0] = KeyEvent.VK_CONTROL;
                langSwitch[1] = KeyEvent.VK_SHIFT;
                break;
            case WIN_SPACE:
                langSwitch[0] = KeyEvent.VK_WINDOWS;
                langSwitch[1] = KeyEvent.VK_SPACE;
                break;
        }
    }

    public void emulateKeyboard(String text) {
        isLangSwitched = false;
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            if(c.equals('$')) return;
            if(c.equals('#'))
            {
                robot.delay(RoboticSettings.delayTimeout);
                continue;
            }
            sendKey(c);
            if(c.equals('\n'))
                robot.delay(RoboticSettings.lineTimeout);
            else
                robot.delay(RoboticSettings.timeout);
        }
    }

    private void sendKey(Character c) {
        Integer[] result = keytable.get(c);
        if(c.toString().matches("[а-яА-Я]") && !isLangSwitched ||
                !c.toString().matches("[а-яА-Я]") && isLangSwitched)
        {
            robot.keyPress(langSwitch[0]);
            robot.keyPress(langSwitch[1]);
            robot.keyRelease(langSwitch[1]);
            robot.keyRelease(langSwitch[0]);
            isLangSwitched = !isLangSwitched;
        }

        if (Character.isUpperCase(c)) robot.keyPress(KeyEvent.VK_SHIFT);
        for (int i = 0; i < result.length; i++) {
            robot.keyPress(result[i]);
        }
        for (int i = result.length - 1; i >= 0; i--) {
            robot.keyRelease(result[i]);
        }
        if (Character.isUpperCase(c)) robot.keyRelease(KeyEvent.VK_SHIFT);

//        if(c.toString().matches("[а-яА-Я]"))
//        {
//            robot.keyPress(langSwitch[0]);
//            robot.keyPress(langSwitch[1]);
//            robot.keyRelease(langSwitch[1]);
//            robot.keyRelease(langSwitch[0]);
//        }
    }

    // ------------------------------------------------
    public enum LanguageSwitchMode
    {
        ALT_SHIFT,
        CONTROL_SHIFT,
        WIN_SPACE
    }
}