package net.lax1dude.eaglercraft;

import net.minecraft.src.GuiScreen;

public class GuiScreenLicenseDeclined extends GuiScreen {

    public void drawScreen(int mx, int my, float par3) {
        this.drawDefaultBackground();
        drawCenteredString(fontRenderer, "Terms of Service Declined", width / 2, height / 3 - 10, 0xFFFFFF);
        drawCenteredString(fontRenderer, "you cannot play this game if you do not accept", width / 2, height / 3 + 18, 0xFF7777);
        drawCenteredString(fontRenderer, "refresh the page to try again", width / 2, height / 3 + 35, 0x666666);
    }
}
