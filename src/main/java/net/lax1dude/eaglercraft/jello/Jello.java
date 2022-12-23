package net.lax1dude.eaglercraft.jello;

import net.minecraft.client.Minecraft;
import net.minecraft.src.network.ScaledResolution;

import java.io.File;
import java.util.ArrayList;


public class Jello {
    public static ArrayList clickguiarray;
    public static String version;
    private static File directory;
    public static double fontScaleOffset;
    public static ArrayList mods;
    public static JCore core;
    public static Object theClient;
    private static ScaledResolution s;

    public static ArrayList getModules() {
        return mods;
    }

    public static double round(double d, int n) {
        int n2 = (int)Math.pow(10.0, n);
        return (double)Math.round(d * (double)n2) / (double)n2;
    }

    public static boolean onSendChatMessage(String string) {
        return true;
    }

    public static void sendChatMessage(String string) {
        core.player().sendChatMessage(string);
    }

    static {
        version = "1.2.1";
        mods = new ArrayList();
        s = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        fontScaleOffset = 1.0;
        clickguiarray = new ArrayList();
    }
}
