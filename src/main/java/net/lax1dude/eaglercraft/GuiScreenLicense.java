package net.lax1dude.eaglercraft;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class GuiScreenLicense extends GuiScreen {

    private final GuiScreen continueScreen;
    private boolean hasCheckedBox = false;
    private int beginOffset = 0;
    private int newOffset;

    private GuiButton acceptButton;

    public GuiScreenLicense(GuiScreen scr)  {
        continueScreen = scr;
    }

    public void initGui() {
        beginOffset = this.height / 2 - 100;
        if (beginOffset < 5) {
            beginOffset = 5;
        }
        this.buttonList.add(new GuiButton(1, this.width / 2 - 120, beginOffset + 180, 115, 20, "Decline"));
        this.buttonList.add(acceptButton = new GuiButton(2, this.width / 2 + 5, beginOffset + 180, 115, 20, "Accept"));
        acceptButton.enabled = false;
    }

    protected void actionPerformed(GuiButton par1GuiButton) {
        if (par1GuiButton.id == 2) {
            LocalStorageManager.profileSettingsStorage.setBoolean("acceptLicense", true);
            LocalStorageManager.saveStorageP();
            mc.displayGuiScreen(continueScreen);
        } else if (par1GuiButton.id == 1) {
            mc.displayGuiScreen(new GuiScreenLicenseDeclined());
        }
    }

    private static final TextureLocation beaconx = new TextureLocation("/gui/beacon.png");


    public void drawScreen(int mx, int my, float par3) {
        drawDefaultBackground();
        acceptButton.enabled = hasCheckedBox;
        super.drawScreen(mx, my, par3);

        EaglerAdapter.glPushMatrix();
        EaglerAdapter.glScalef(1.33f, 1.33f, 1.33f);
        drawCenteredString(fontRenderer, "Dear All MCA Students:", width * 3 / 8, beginOffset * 3 / 4, 0xDDDD55);
        EaglerAdapter.glPopMatrix();

        byte[] ColoredName = new byte[] {-62, -89, 99, 109, 99, 97, -62, -89, 102, 99, 114, 97, 102, 116, -62, -89, 114};
        byte[] byteArr = new byte[] {-62, -89, 100, 102, 114, 101, 101, 32, 115, 111, 102, 116, 119, 97, 114, 101, -62, -89, 114}; //§dfree software§r

        drawCenteredString(fontRenderer, new String(ColoredName) + " is a game dedicated to the students at", width / 2, beginOffset + 22, 0xFF7777);
        drawCenteredString(fontRenderer, "MCA, But to keep mcacraft fun and alive, you must follow these ", width / 2, beginOffset + 33, 0xFF7777);
        drawCenteredString(fontRenderer, "rules for the safety of the game and the website. ", width / 2, beginOffset + 44, 0xFF7777);

//        drawCenteredString(fontRenderer, "Click 'Fork on Github' on the main menu to access the offical", width / 2, beginOffset + 62, 0x448844);
//        drawCenteredString(fontRenderer, "source code to download this educational project legitimately", width / 2, beginOffset + 71, 0x448844);
        drawCenteredString(fontRenderer, "1. Please do not play in class or when you are not supposed to be playing.", width / 2, beginOffset + 62, 0x448844);
        drawCenteredString(fontRenderer, "2. Do not share server passwords and do not attempt to steal server passwords either.", width / 2, beginOffset + 71, 0x448844);
        drawCenteredString(fontRenderer, "3. Just don't be stupid and use internet safety.", width / 2, beginOffset + 80, 0x448844);

        newOffset = beginOffset + 9;

        EaglerAdapter.glPushMatrix();
        EaglerAdapter.glScalef(0.75f, 0.75f, 0.75f);
//        drawCenteredString(fontRenderer, "I am aware that this project violated Mojang's Terms of Service", width * 4 / 6, (newOffset + 89) * 4 / 3, 0x666666);
//        drawCenteredString(fontRenderer, "This is going to change in a few weeks, when I convert this game", width * 4 / 6, (newOffset + 97) * 4 / 3, 0x999999);
//        drawCenteredString(fontRenderer, "into an online-mode plugin that requires a microsoft account.", width * 4 / 6, (newOffset + 105) * 4 / 3, 0x999999);

        drawCenteredString(fontRenderer, "Please do not do anything that will get the school to take this", width * 4 / 6, (newOffset + 89) * 4 / 3, 0x999999);
        drawCenteredString(fontRenderer, "project down. I worked really hard on mcacraft, so for the sake of", width * 4 / 6, (newOffset + 97) * 4 / 3, 0x999999);
        drawCenteredString(fontRenderer, "me and the community, follow these rules and don't get this taken down.", width * 4 / 6, (newOffset + 105) * 4 / 3, 0x999999);


        EaglerAdapter.glPopMatrix();

        drawCenteredString(fontRenderer, "From, Mick Tefera (KingPada)", width / 2, newOffset + 120, 0xFF7777);

        boolean mouseOverCheck = width / 2 - 100 < mx && width / 2 - 83 > mx && newOffset + 142 < my && newOffset + 159 > my;

        if (mouseOverCheck) {
            EaglerAdapter.glColor4f(0.7f, 0.7f, 1.0f, 1.0f);
        } else {
            EaglerAdapter.glColor4f(0.6f, 0.6f, 0.6f, 1.0f);
        }

        beaconx.bindTexture();

        EaglerAdapter.glPushMatrix();
        EaglerAdapter.glScalef(0.75f, 0.75f, 0.75f);
        drawTexturedModalRect((width / 2 - 100) * 4 / 3, (newOffset + 142) * 4 / 3, 22, 219, 22, 22);
        EaglerAdapter.glPopMatrix();

        if (hasCheckedBox) {
            EaglerAdapter.glPushMatrix();
            EaglerAdapter.glColor4f(1.1f, 1.1f, 1.1f, 1.0f);
            EaglerAdapter.glTranslatef(0.5f, 0.5f, 0.0f);
            drawTexturedModalRect((width / 2 - 100), (newOffset + 142), 90, 222, 16, 16);
            EaglerAdapter.glPopMatrix();
        }

        EaglerAdapter.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        drawString(fontRenderer, "I will follow these rules and have fun!", width / 2 - 75, newOffset + 147, 0xEEEEEE);

    }

    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
        if (width / 2 - 100 < par1 && width / 2 - 83 > par1 && newOffset + 142 < par2 && newOffset + 159 > par2) {
            this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
            hasCheckedBox = !hasCheckedBox;
        }
    }

}
