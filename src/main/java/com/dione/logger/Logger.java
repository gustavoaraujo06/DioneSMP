package com.dione.logger;

import com.dione.main.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class Logger {
    public static void Log(String text){
        Main.getInstance().getLogger().info(text);
    }
    public static void Log(Component text){
        String convertedText = PlainTextComponentSerializer.plainText().serialize(text);
        Main.getInstance().getLogger().info(convertedText);
    }

    public static String componentToString(Component text){
        return PlainTextComponentSerializer.plainText().serialize(text);
    }
}
