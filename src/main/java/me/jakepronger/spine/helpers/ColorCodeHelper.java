package me.jakepronger.spine.helpers;

import net.kyori.adventure.text.format.TextColor;

import java.util.Map;

public class ColorCodeHelper {

    private static final Map<Character, TextColor> COLOR_MAP = Map.ofEntries(
            Map.entry('0', TextColor.color(0x000000)), // black
            Map.entry('1', TextColor.color(0x0000AA)), // dark blue
            Map.entry('2', TextColor.color(0x00AA00)), // dark green
            Map.entry('3', TextColor.color(0x00AAAA)), // dark aqua
            Map.entry('4', TextColor.color(0xAA0000)), // dark red
            Map.entry('5', TextColor.color(0xAA00AA)), // dark purple
            Map.entry('6', TextColor.color(0xFFAA00)), // gold
            Map.entry('7', TextColor.color(0xAAAAAA)), // gray
            Map.entry('8', TextColor.color(0x555555)), // dark gray
            Map.entry('9', TextColor.color(0x5555FF)), // blue
            Map.entry('a', TextColor.color(0x55FF55)), // green
            Map.entry('b', TextColor.color(0x55FFFF)), // aqua
            Map.entry('c', TextColor.color(0xFF5555)), // red
            Map.entry('d', TextColor.color(0xFF55FF)), // light purple
            Map.entry('e', TextColor.color(0xFFFF55)), // yellow
            Map.entry('f', TextColor.color(0xFFFFFF)) // white
    );

    /**
     * Get a color from a color code
     * @param code Color code e.g. '&a'
     * @return TextColor
     */
    public static TextColor getColorFromCode(String code) {
        if (code == null || code.length() < 2 || code.charAt(0) != '&')
            throw new IllegalArgumentException("Invalid color code format: " + code);
        return COLOR_MAP.getOrDefault(code.charAt(1), TextColor.color(0xAAAAAA));
    }

}
