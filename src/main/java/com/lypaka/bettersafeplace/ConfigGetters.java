package com.lypaka.bettersafeplace;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.List;
import java.util.Map;

public class ConfigGetters {

    public static List<String> locationBlacklist;
    public static Map<String, String> safePlaces;

    public static void load() throws ObjectMappingException {

        locationBlacklist = BetterSafePlace.configManager.getConfigNode(0, "Blacklist").getList(TypeToken.of(String.class));
        safePlaces = BetterSafePlace.configManager.getConfigNode(1, "Safe-Places").getValue(new TypeToken<Map<String, String>>() {});

    }

}
