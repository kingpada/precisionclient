package net.minecraft.src.materials;

import net.minecraft.src.map.MapColor;

public final class MaterialWeb extends Material {
    MaterialWeb(MapColor par1MapColor) {
        super(par1MapColor);
    }

    /**
     * Returns if this material is considered solid or not
     */
    public boolean blocksMovement() {
        return false;
    }
}
