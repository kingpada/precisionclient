package net.minecraft.src.network.interfaces;

public interface IThreadedFileIO {
    /**
     * Returns a boolean stating if the write was unsuccessful.
     */
    boolean writeNextIO();
}
