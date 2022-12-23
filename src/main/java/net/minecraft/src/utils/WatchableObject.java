package net.minecraft.src.utils;

public class WatchableObject {
    public final int objectType;

    /**
     * id of max 31
     */
    public final int dataValueId;
    public Object watchedObject;
    public boolean watched;

    public WatchableObject(int par1, int par2, Object par3Obj) {
        this.dataValueId = par2;
        this.watchedObject = par3Obj;
        this.objectType = par1;
        this.watched = true;
    }

    public int getDataValueId() {
        return this.dataValueId;
    }

    public void setObject(Object par1Obj) {
        this.watchedObject = par1Obj;
    }

    public Object getObject() {
        return this.watchedObject;
    }

    public int getObjectType() {
        return this.objectType;
    }

    public boolean isWatched() {
        return this.watched;
    }

    public void setWatched(boolean par1) {
        this.watched = par1;
    }

    /**
     * Set whether the specified watchable object is being watched.
     */
    public static boolean setWatchableObjectWatched(WatchableObject par0WatchableObject, boolean par1) {
        return par0WatchableObject.watched = par1;
    }
}