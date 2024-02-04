package com.lypaka.bettersafeplace.Listeners;

import com.lypaka.bettersafeplace.BetterSafePlace;
import com.lypaka.bettersafeplace.ConfigGetters;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

@Mod.EventBusSubscriber(modid = BetterSafePlace.MOD_ID)
public class ServerShuttingDownListener {

    @SubscribeEvent
    public static void onServerShuttingDown (FMLServerStoppingEvent event) {

        BetterSafePlace.configManager.getConfigNode(1, "Safe-Places").setValue(ConfigGetters.safePlaces);
        BetterSafePlace.configManager.save();

    }

}
