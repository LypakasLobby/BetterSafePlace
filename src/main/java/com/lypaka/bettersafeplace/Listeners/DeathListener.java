package com.lypaka.bettersafeplace.Listeners;

import com.lypaka.bettersafeplace.ConfigGetters;
import com.lypaka.lypakautils.MiscHandlers.WorldHelpers;
import com.pixelmonmod.pixelmon.api.events.battles.BattleEndEvent;
import com.pixelmonmod.pixelmon.battles.controller.BattleController;
import com.pixelmonmod.pixelmon.battles.controller.participants.PlayerParticipant;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeathListener {

    @SubscribeEvent
    public void onLose (BattleEndEvent event) {

        BattleController bc = event.getBattleController();
        for (PlayerParticipant pp : bc.getPlayers()) {

            ServerPlayerEntity player = pp.player;
            if (ConfigGetters.safePlaces.containsKey(player.getUniqueID().toString())) {

                String[] location = ConfigGetters.safePlaces.get(player.getUniqueID().toString()).split(",");
                String world = location[0];
                int x = Integer.parseInt(location[1]);
                int y = Integer.parseInt(location[2]);
                int z = Integer.parseInt(location[3]);
                WorldHelpers.teleportPlayer(player, world, x, y, z, player.rotationYaw, player.rotationPitch);

            }

        }

    }

}
