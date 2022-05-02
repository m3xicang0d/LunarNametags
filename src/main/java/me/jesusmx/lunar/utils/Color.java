package me.jesusmx.lunar.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Color {
    
    public static String translate(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static List<String> translate(List<String> lines) {
        List<String> toReturn = new ArrayList<String>();
        for (String line : lines) {
            toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        return toReturn;
    }


}
