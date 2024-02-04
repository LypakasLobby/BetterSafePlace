package com.lypaka.bettersafeplace.Listeners;

import com.lypaka.bettersafeplace.ConfigGetters;
import com.lypaka.lypakautils.WorldStuff.WorldMap;
import com.pixelmonmod.pixelmon.api.events.npc.NPCEvent;
import com.pixelmonmod.pixelmon.enums.EnumNPCType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class HealListener {

    @SubscribeEvent
    public void onHeal (NPCEvent.Interact event) {

        if (event.type == EnumNPCType.NurseJoy) {

            ServerPlayerEntity player = (ServerPlayerEntity) event.player;
            String worldName = WorldMap.getWorldName(player);
            String location = worldName + "," + event.npc.getEntity().getPosition().getX() + "," +
                    event.npc.getEntity().getPosition().getY() + "," + event.npc.getEntity().getPosition().getZ();

            if (ConfigGetters.locationBlacklist.contains(location)) return;

            String playerLocation = worldName + "," + player.getPosition().getX() + "," + player.getPosition().getY() + "," + player.getPosition().getZ();
            ConfigGetters.safePlaces.put(player.getUniqueID().toString(), playerLocation);

        }

    }

    @SubscribeEvent
    public void onHealerUse (PlayerInteractEvent.RightClickBlock event) {

        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        if (event.getSide() == LogicalSide.CLIENT) return;
        if (event.getHand() == Hand.OFF_HAND) return;

        String blockID = player.world.getBlockState(event.getPos()).getBlock().getRegistryName().toString();
        if (blockID.contains("pixelmon") && blockID.contains("healer")) {

            String worldName = WorldMap.getWorldName(player);
            String location = worldName + "," + event.getPos().getX() + "," + event.getPos().getY() + "," + event.getPos().getZ();
            if (ConfigGetters.locationBlacklist.contains(location)) return;

            String playerLocation = worldName + "," + player.getPosition().getX() + "," + player.getPosition().getY() + "," + player.getPosition().getZ();
            ConfigGetters.safePlaces.put(player.getUniqueID().toString(), playerLocation);

        }

    }

}
