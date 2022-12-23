package net.lax1dude.eaglercraft.jello.fonts;

import net.lax1dude.eaglercraft.EaglerAdapter;
import net.lax1dude.eaglercraft.adapter.EaglerAdapterImpl2;
import net.lax1dude.eaglercraft.glemu.EaglerAdapterGL30;
import net.minecraft.client.Minecraft;
import org.teavm.jso.webgl.WebGLBuffer;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class GLUtils {
    private static final Random random;
    public static List textures;
    public static List vbos;
    public static void glScissor(float f, float f2, float f3, float f4) {
        int n = GLUtils.getScaleFactor();
        EaglerAdapter.webgl.scissor((int)((int)(f * (float)n)), (int)((int)((float)Minecraft.getMinecraft().displayHeight - f4 * (float)n)), (int)((int)((f3 - f) * (float)n)), (int)((int)((f4 - f2) * (float)n)));
    }

    public static boolean isHovered(int n, int n2, int n3, int n4, int n5, int n6) {
        return n5 >= n && n5 <= n + n3 && n6 >= n2 && n6 < n2 + n4;
    }

    static {
        random = new Random();
        vbos = new ArrayList();
        textures = new ArrayList();
    }

    public static int applyTexture(int n, int n2, int n3, ByteBuffer byteBuffer, int n4, int n5) {
        EaglerAdapter.glBindTexture((int)3553, (int)n);
        EaglerAdapter.glTexParameteri((int)3553, (int)10241, (int)n4);
        EaglerAdapter.glTexParameteri((int)3553, (int)10240, (int)n4);
        EaglerAdapter.glTexParameteri((int)3553, (int)10242, (int)n5);
        EaglerAdapter.glTexParameteri((int)3553, (int)10243, (int)n5);
        EaglerAdapter.webgl.pixelStorei((int)3317, (int)1);
        EaglerAdapter.glTexImage2D((int)3553, (int)0, (int)32856, (int)n2, (int)n3, (int)0, (int)6408, (int)5121, (ByteBuffer)byteBuffer);
        EaglerAdapter.glBindTexture((int)3553, (int)0);
        return n;
    }

    public static Color getRandomColor() {
        return GLUtils.getRandomColor(1000, 0.6f);
    }

    public static int getScaleFactor() {
        int n = 1;
        int n2 = Minecraft.getMinecraft().gameSettings.guiScale;
        if (n2 == 0) {
            n2 = 1000;
        }
        while (n < n2 && Minecraft.getMinecraft().displayWidth / (n + 1) >= 320 && Minecraft.getMinecraft().displayHeight / (n + 1) >= 240) {
            ++n;
        }
        return n;
    }

    public static void glColor(Color color) {
        EaglerAdapter.glColor4f((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
    }

    public static Color getHSBColor(float f, float f2, float f3) {
        return Color.getHSBColor(f, f2, f3);
    }
    public static WebGLBuffer genVBO() {
        WebGLBuffer n = EaglerAdapter.webgl.createBuffer();
        vbos.add(n);
        EaglerAdapter.webgl.bindBuffer(34962, n);
        return n;
    }

    public static void glScissor(int[] nArray) {
        GLUtils.glScissor(nArray[0], nArray[1], nArray[0] + nArray[2], nArray[1] + nArray[3]);
    }

    public static int getScreenHeight() {
        return Minecraft.getMinecraft().displayHeight / GLUtils.getScaleFactor();
    }

    public static int getScreenWidth() {
        return Minecraft.getMinecraft().displayWidth / GLUtils.getScaleFactor();
    }


    public static int getMouseX() {
        return EaglerAdapter.mouseGetX() * GLUtils.getScreenWidth() / Minecraft.getMinecraft().displayWidth;
    }

    public static void glColor(float f, float f2, float f3, float f4) {
        EaglerAdapter.glColor4f(f, f2, f3, f4);
    }

    public static Color getRandomColor(int n, float f) {
        float f2 = random.nextFloat();
        float f3 = (random.nextInt(n) + n) / n + n;
        return GLUtils.getHSBColor(f2, f3, f);
    }



}
