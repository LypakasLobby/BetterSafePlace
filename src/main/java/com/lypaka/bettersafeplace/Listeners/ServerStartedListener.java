package com.lypaka.bettersafeplace.Listeners;

import com.lypaka.bettersafeplace.BetterSafePlace;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@Mod.EventBusSubscriber(modid = BetterSafePlace.MOD_ID)
public class ServerStartedListener {

    @SubscribeEvent
    public static void onServerStarted (FMLServerStartedEvent event) {

        Pixelmon.EVENT_BUS.register(new HealListener());
        Pixelmon.EVENT_BUS.register(new DeathListener());

        MinecraftForge.EVENT_BUS.register(new HealListener());

    }

}
