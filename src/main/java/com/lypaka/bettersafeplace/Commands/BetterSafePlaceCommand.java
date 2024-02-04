package com.lypaka.bettersafeplace.Commands;

import com.lypaka.bettersafeplace.BetterSafePlace;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = BetterSafePlace.MOD_ID)
public class BetterSafePlaceCommand {

    public static final List<String> ALIASES = Arrays.asList("bettersafeplace", "safeplace", "bsp");

    @SubscribeEvent
    public static void onCommandRegistration (RegisterCommandsEvent event) {

        new ReloadCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());

    }

}
