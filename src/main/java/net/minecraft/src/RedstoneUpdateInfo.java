package net.minecraft.src;

public class RedstoneUpdateInfo {
    public int x;
    public int y;
    public int z;
    public long updateTime;

    public RedstoneUpdateInfo(int par1, int par2, int par3, long par4) {
        this.x = par1;
        this.y = par2;
        this.z = par3;
        this.updateTime = par4;
    }
}
