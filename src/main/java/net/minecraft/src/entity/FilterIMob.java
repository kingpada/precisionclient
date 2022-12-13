package net.minecraft.src.entity;

public final class FilterIMob implements IEntitySelector {
    /**
     * Return whether the specified entity is applicable to this filter.
     */
    public boolean isEntityApplicable(Entity par1Entity) {
        return par1Entity instanceof IMob;
    }
}
