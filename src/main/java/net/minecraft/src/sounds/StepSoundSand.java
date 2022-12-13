package net.minecraft.src.sounds;

public final class StepSoundSand extends StepSound {
    public StepSoundSand(String par1Str, float par2, float par3) {
        super(par1Str, par2, par3);
    }

    /**
     * Used when a block breaks, EXA: Player break, Shep eating grass, etc..
     */
    public String getBreakSound() {
        return "dig.wood";
    }
}
