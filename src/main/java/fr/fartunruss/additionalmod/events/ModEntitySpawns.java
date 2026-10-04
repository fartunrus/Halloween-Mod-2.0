/*package fr.fartunruss.halloweenmod.events;

import fr.fartunruss.halloweenmod.HalloweenMod;
import fr.fartunruss.halloweenmod.setup.RegistriesEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = HalloweenMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntitySpawns {

    @SubscribeEvent
    public static void spawnEntities(FMLLoadCompleteEvent event){
        for(Biome biome: ForgeRegistries.BIOMES){
            biome.getMobSpawnInfo().getSpawners(EntityClassification.MONSTER)
                    .add(new MobSpawnInfo.Spawners(RegistriesEntities.GHOST.get(), 200, 3, 8));
        }
    }
}*/
