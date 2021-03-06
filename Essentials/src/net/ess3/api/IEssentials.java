package net.ess3.api;

import com.earth2me.essentials.items.CustomItemResolver;
import net.ess3.provider.PotionMetaProvider;
import net.ess3.provider.SpawnEggProvider;

import java.util.Collection;

@SuppressWarnings("unused")
public interface IEssentials extends com.earth2me.essentials.IEssentials {

    Collection<String> getVanishedPlayersNew();

    SpawnEggProvider getSpawnEggProvider();

    PotionMetaProvider getPotionMetaProvider();

    CustomItemResolver getCustomItemResolver();
}
