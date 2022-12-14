package net.lax1dude.eaglercraft;

import net.lax1dude.eaglercraft.RelayQuery.VersionMismatch;
import net.lax1dude.eaglercraft.adapter.EaglerAdapterImpl2.RateLimit;
import net.lax1dude.eaglercraft.sp.relay.pkt.IPacket07LocalWorlds.LocalWorld;

import java.util.List;

public interface RelayWorldsQuery {

    boolean isQueryOpen();

    boolean isQueryFailed();

    RateLimit isQueryRateLimit();

    void close();

    List<LocalWorld> getWorlds();

    VersionMismatch getCompatible();

}
