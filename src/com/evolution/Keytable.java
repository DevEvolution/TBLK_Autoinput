package com.evolution;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Keytable {
    public Map<String, Integer[]> keytable = new HashMap<String, Integer[]>();

    public Integer[] get(Character c) {
        String ch = Character.toString(c).toLowerCase();
        if (keytable.containsKey(ch))
            return keytable.get(ch);
        else
            throw new IllegalArgumentException("Keytable does not contain passed character: " + ch);
    }

    //public Keytable()
    public Keytable() {
        keytable.put(" ", new Integer[]{KeyEvent.VK_SPACE});
        keytable.put("\n", new Integer[]{KeyEvent.VK_ENTER});

        keytable.put("`", new Integer[]{KeyEvent.VK_BACK_QUOTE});
        keytable.put("~", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE});
        keytable.put("!", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_1});
        keytable.put("@", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_2});
        keytable.put("#", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_3});
        keytable.put("$", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_4});
        keytable.put("%", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_5});
        keytable.put("^", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_6});
        keytable.put("&", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_7});
        keytable.put("*", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_8});
        keytable.put("(", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_9});
        keytable.put(")", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_0});
        keytable.put("-", new Integer[]{KeyEvent.VK_MINUS});
        keytable.put("_", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS});
        keytable.put("=", new Integer[]{KeyEvent.VK_EQUALS});
        keytable.put("+", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_EQUALS});
        keytable.put("\t", new Integer[]{KeyEvent.VK_TAB});
        keytable.put("[", new Integer[]{KeyEvent.VK_OPEN_BRACKET});
        keytable.put("]", new Integer[]{KeyEvent.VK_CLOSE_BRACKET});
        keytable.put("{", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET});
        keytable.put("}", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET});
        keytable.put(";", new Integer[]{KeyEvent.VK_SEMICOLON});
        keytable.put(":", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_SEMICOLON});
        keytable.put("'", new Integer[]{KeyEvent.VK_QUOTE});
        keytable.put("\"", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_QUOTE});
        keytable.put(",", new Integer[]{KeyEvent.VK_COMMA});
        keytable.put(".", new Integer[]{KeyEvent.VK_PERIOD});
        keytable.put("<", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA});
        keytable.put(">", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD});
        keytable.put("/", new Integer[]{KeyEvent.VK_SLASH});
        keytable.put("?", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH});
        keytable.put("\\", new Integer[]{KeyEvent.VK_BACK_SLASH});
        keytable.put("|", new Integer[]{KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH});

        keytable.put("1", new Integer[]{KeyEvent.VK_1});
        keytable.put("2", new Integer[]{KeyEvent.VK_2});
        keytable.put("3", new Integer[]{KeyEvent.VK_3});
        keytable.put("4", new Integer[]{KeyEvent.VK_4});
        keytable.put("5", new Integer[]{KeyEvent.VK_5});
        keytable.put("6", new Integer[]{KeyEvent.VK_6});
        keytable.put("7", new Integer[]{KeyEvent.VK_7});
        keytable.put("8", new Integer[]{KeyEvent.VK_8});
        keytable.put("9", new Integer[]{KeyEvent.VK_9});
        keytable.put("0", new Integer[]{KeyEvent.VK_0});

        keytable.put("a", new Integer[]{KeyEvent.VK_A});
        keytable.put("b", new Integer[]{KeyEvent.VK_B});
        keytable.put("c", new Integer[]{KeyEvent.VK_C});
        keytable.put("d", new Integer[]{KeyEvent.VK_D});
        keytable.put("e", new Integer[]{KeyEvent.VK_E});
        keytable.put("f", new Integer[]{KeyEvent.VK_F});
        keytable.put("g", new Integer[]{KeyEvent.VK_G});
        keytable.put("h", new Integer[]{KeyEvent.VK_H});
        keytable.put("i", new Integer[]{KeyEvent.VK_I});
        keytable.put("j", new Integer[]{KeyEvent.VK_J});
        keytable.put("k", new Integer[]{KeyEvent.VK_K});
        keytable.put("l", new Integer[]{KeyEvent.VK_L});
        keytable.put("m", new Integer[]{KeyEvent.VK_M});
        keytable.put("n", new Integer[]{KeyEvent.VK_N});
        keytable.put("o", new Integer[]{KeyEvent.VK_O});
        keytable.put("p", new Integer[]{KeyEvent.VK_P});
        keytable.put("q", new Integer[]{KeyEvent.VK_Q});
        keytable.put("r", new Integer[]{KeyEvent.VK_R});
        keytable.put("s", new Integer[]{KeyEvent.VK_S});
        keytable.put("t", new Integer[]{KeyEvent.VK_T});
        keytable.put("u", new Integer[]{KeyEvent.VK_U});
        keytable.put("v", new Integer[]{KeyEvent.VK_V});
        keytable.put("w", new Integer[]{KeyEvent.VK_W});
        keytable.put("x", new Integer[]{KeyEvent.VK_X});
        keytable.put("y", new Integer[]{KeyEvent.VK_Y});
        keytable.put("z", new Integer[]{KeyEvent.VK_Z});

        keytable.put("а", new Integer[]{KeyEvent.VK_F});
        keytable.put("б", new Integer[]{KeyEvent.VK_COMMA});
        keytable.put("в", new Integer[]{KeyEvent.VK_D});
        keytable.put("г", new Integer[]{KeyEvent.VK_U});
        keytable.put("д", new Integer[]{KeyEvent.VK_L});
        keytable.put("е", new Integer[]{KeyEvent.VK_T});
        keytable.put("ё", new Integer[]{KeyEvent.VK_BACK_QUOTE});
        keytable.put("ж", new Integer[]{KeyEvent.VK_SEMICOLON});
        keytable.put("з", new Integer[]{KeyEvent.VK_P});
        keytable.put("и", new Integer[]{KeyEvent.VK_B});
        keytable.put("й", new Integer[]{KeyEvent.VK_Q});
        keytable.put("к", new Integer[]{KeyEvent.VK_R});
        keytable.put("л", new Integer[]{KeyEvent.VK_K});
        keytable.put("м", new Integer[]{KeyEvent.VK_V});
        keytable.put("н", new Integer[]{KeyEvent.VK_Y});
        keytable.put("о", new Integer[]{KeyEvent.VK_J});
        keytable.put("п", new Integer[]{KeyEvent.VK_G});
        keytable.put("р", new Integer[]{KeyEvent.VK_H});
        keytable.put("с", new Integer[]{KeyEvent.VK_C});
        keytable.put("т", new Integer[]{KeyEvent.VK_N});
        keytable.put("у", new Integer[]{KeyEvent.VK_E});
        keytable.put("ф", new Integer[]{KeyEvent.VK_A});
        keytable.put("х", new Integer[]{KeyEvent.VK_OPEN_BRACKET});
        keytable.put("ц", new Integer[]{KeyEvent.VK_W});
        keytable.put("ч", new Integer[]{KeyEvent.VK_X});
        keytable.put("ш", new Integer[]{KeyEvent.VK_I});
        keytable.put("щ", new Integer[]{KeyEvent.VK_O});
        keytable.put("ь", new Integer[]{KeyEvent.VK_M});
        keytable.put("ы", new Integer[]{KeyEvent.VK_S});
        keytable.put("ъ", new Integer[]{KeyEvent.VK_CLOSE_BRACKET});
        keytable.put("э", new Integer[]{KeyEvent.VK_QUOTE});
        keytable.put("ю", new Integer[]{KeyEvent.VK_PERIOD});
        keytable.put("я", new Integer[]{KeyEvent.VK_Z});
    }
}
