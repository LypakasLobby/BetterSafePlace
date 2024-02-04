package com.lypaka.bettersafeplace.Commands;

import com.google.common.reflect.TypeToken;
import com.lypaka.bettersafeplace.BetterSafePlace;
import com.lypaka.bettersafeplace.ConfigGetters;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class ReloadCommand {

    public ReloadCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : BetterSafePlaceCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("reload")
                                            .executes(c -> {

                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                    if (!PermissionHandler.hasPermission(player, "bettersafeplace.command.admin")) {

                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUniqueID());
                                                        return 0;

                                                    }

                                                }

                                                try {

                                                    BetterSafePlace.configManager.load();
                                                    ConfigGetters.locationBlacklist = BetterSafePlace.configManager.getConfigNode(0, "Blacklist").getList(TypeToken.of(String.class));
                                                    c.getSource().sendFeedback(FancyText.getFormattedText("&aSuccessfully reloaded BetterSafePlace!"), true);

                                                } catch (ObjectMappingException e) {

                                                    throw new RuntimeException(e);

                                                }

                                                return 1;

                                            })
                            )
            );

        }

    }

}
