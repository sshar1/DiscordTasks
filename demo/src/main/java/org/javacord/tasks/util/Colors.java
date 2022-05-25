package org.javacord.tasks.util;

import java.awt.Color;

public enum Colors {

    BLACK("BLACK", Color.BLACK),
    BLUE("BLUE", Color.BLUE),
    RED("RED", Color.RED),
    PURPLE("PURPLE", Color.MAGENTA),
    ORANGE("ORANGE", Color.ORANGE),
    YELLOW("YELLOW", Color.YELLOW),
    GREEN("GREEN", Color.GREEN),
    WHITE("WHITE", Color.WHITE),
    PINK("PINK", Color.PINK);

    private final String strRep;
    private final Color color;

    private Colors(String strRep, Color color) {
        this.strRep = strRep;
        this.color = color;
    }

    public String stringValue() {
        return strRep;
    }

    public Color colorValue() {
        return color;
    }

    public static Color getColorFromString(String color, Colors defaultColor) {
        for (Colors c : Colors.values()) {
            if (c.strRep.equals(color)) {
                return c.color;
            }
        }
        return defaultColor.color;
    }

    public static String getColors() {
        String str = "";

        for (Colors c : Colors.values()) {
            str += c.strRep + ", ";
        }

        str = str.substring(0, str.length()-2);

        return str;
    }

    public static boolean colorValid(String color) {
        for (Colors c : Colors.values()) {
            if (color.equalsIgnoreCase(c.strRep)) {
                return true;
            }
        }
        return false;
    }
}
