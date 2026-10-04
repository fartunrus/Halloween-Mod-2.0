package fr.fartunruss.additionalmod.events;

import fr.fartunruss.additionalmod.setup.RegistriesEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Arrays;
import java.util.List;

public class HalloweenEntityGenerations {
    public static void onEntitySpawn(BiomeLoadingEvent event){
        addEntityToAllBiomes(event.getSpawns(), RegistriesEntities.GHOST.get(), 200, 4, 20);
    }
    private static void addEntityToBiomesExceptThis(BiomeLoadingEvent event, EntityType<?> type,
                                          int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes){
        boolean isBimeSelected = Arrays.stream(biomes).map(RegistryKey::getLocation)
                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if(!isBimeSelected){
            addEntityToAllBiomes(event.getSpawns(),type,weight,minCount,maxCount);
        }
    }

    private static void addEntityToSpecificBiome(BiomeLoadingEvent event,EntityType<?>type,
                                                 int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes){
            boolean isBimeSelected = Arrays.stream(biomes).map(RegistryKey::getLocation)
                    .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

            if(isBimeSelected){
                addEntityToAllBiomes(event.getSpawns(),type,weight,minCount,maxCount);
            }
    }
    private static void addEntityToAllBiomes(MobSpawnInfoBuilder spawns, EntityType<?>type,
                                             int weight, int minCount, int maxCount){
        List<MobSpawnInfo.Spawners> base = spawns.getSpawner(type.getClassification());
        base.add(new MobSpawnInfo.Spawners(type, weight, minCount, maxCount));
    }
}
