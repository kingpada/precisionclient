package net.minecraft.src.stats;

public final class StatTypeSimple implements IStatType {
    /**
     * Formats a given stat for human consumption.
     */
    public String format(int par1) {
        return StatBase.getNumberFormat().format(par1);
    }
}
