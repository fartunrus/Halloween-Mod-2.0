package fr.fartunruss.additionalmod.events;


import fr.fartunruss.additionalmod.HalloweenMod;
import fr.fartunruss.additionalmod.entities.GhostEntity;
import fr.fartunruss.additionalmod.setup.RegistriesItems;
import fr.fartunruss.additionalmod.setup.RegistriesParticles;
import fr.fartunruss.additionalmod.setup.RegistriesSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

@Mod.EventBusSubscriber(modid = HalloweenMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class ServerEventCCC {
    //public static int eventTime=0;
    public static int lightning=0;
    public static boolean lightningb=false;
    //public static boolean eventstop=false;
    public static int malusRevivus=0;
    public static ServerPlayerEntity malusPlayer;
    //public static int timeBeforeEternalLife=0;
    @SubscribeEvent
    public static void OnServerTickEvent(TickEvent.ServerTickEvent event){
        /*if(timeBeforeEternalLife==0&&!eventstop) {
            Random random = new Random();
            timeBeforeEternalLife = random.nextInt(30 * 60 * 20)+30*60*20;
        }
        //System.out.println(eventstop);
        if(!eventstop) {
            //System.out.println(timeBeforeEternalLife);
            --timeBeforeEternalLife;
        }
        if(timeBeforeEternalLife==0){
            eventstop=true;
            if(eventTime==0) {
                eventTime = (new Random()).nextInt(20 * 60 * 20) + 20 * 60 * 20;
                lightning=40;
                lightningb=true;
                for (ServerPlayerEntity player: ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
                    player.sendMessage(new TranslationTextComponent(("text."+HalloweenMod.MODID+".eternalnight.start")), Util.DUMMY_UUID);
                    player.heal(8f);
                    LightningBoltEntity lightningBoltEntity=new LightningBoltEntity(EntityType.LIGHTNING_BOLT, player.world);
                    lightningBoltEntity.setPosition(player.getPositionVec().x,player.getPositionVec().y,player.getPositionVec().z);
                    player.world.addEntity(lightningBoltEntity);
                    lightningBoltEntity.setDamage(0f);
                    player.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 1f);
                    player.setFire(1);
                    player.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);
                }
            }
            --eventTime;
            MinecraftServer server=ServerLifecycleHooks.getCurrentServer();
            ServerWorld serveroverworld=server.getWorld(World.OVERWORLD);
            serveroverworld.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(false, server);
            long timebefore =(24000*8-(serveroverworld.getGameTime() % (24000L*8)))%(24000*8);
            serveroverworld.setDayTime(timebefore+18000);
            if(eventTime==0){
                serveroverworld.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(true, server);
                eventstop=false;
                for (ServerPlayerEntity player:ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
                    player.sendMessage(new TranslationTextComponent(("text."+HalloweenMod.MODID+".eternalnight.end")), Util.DUMMY_UUID);
                }
            }
        }*/
        if(lightningb) {
            if (lightning == 0) {
                for (ServerPlayerEntity player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
                    RemoveFire(player);
                }
                lightningb = false;
            } else if (lightning > 0) lightning--;
        }
        if(malusRevivus==1){
            lightning=40;
            lightningb=true;
            for(ServerPlayerEntity sp:ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()){
                sp.sendMessage(new TranslationTextComponent("text."+HalloweenMod.MODID+".averevivus-shangrin"), sp.getUniqueID());
                sp.sendMessage(new TranslationTextComponent("text."+HalloweenMod.MODID+".averevivus-me"), sp.getUniqueID());
                //sp.sendMessage(new TranslationTextComponent("text.halloweenmod.averevivus-player"), uuid);
                sp.heal(8f);
                LightningBoltEntity lbe=new LightningBoltEntity(EntityType.LIGHTNING_BOLT, sp.world);
                lbe.setPosition(sp.getPositionVec().x,sp.getPositionVec().y,sp.getPositionVec().z);
                sp.world.addEntity(lbe);
                lbe.setDamage(0f);
                sp.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 1f);
                sp.setFire(1);
                //sp.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);
            }
            malusRevivus=0;
        }else if(malusRevivus==2) {
            for (ServerPlayerEntity sp : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
                if (Math.sqrt(sp.getDistanceSq(malusPlayer.getPositionVec())) < 20.0) {
                    sp.sendMessage(new TranslationTextComponent("text."+HalloweenMod.MODID+".malus"), malusPlayer.getUniqueID());
                    MalusParticles(malusPlayer);
                    malusPlayer.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 60 * 20, 255, true, false, false));
                    malusPlayer.playSound(RegistriesSoundEvents.MALUS_SOUND.get(), SoundCategory.PLAYERS, 1f, 1f);
                    malusRevivus = 0;
                }
            }
        }
    }
    public static void RemoveFire(PlayerEntity player) {
        for (int i = -4; i < 5; i++)
            for (int j = -4; j < 5; j++)
                for (int g = -4; g < 5; g++) {
                    BlockState bs = player.world.getBlockState(player.getPosition().add(i, j, g));
                    if (bs.getBlock().equals(Blocks.FIRE) || player.world.getBlockState(player.getPosition().add(i, j, g)).getBlock().equals(Blocks.SOUL_FIRE))
                        player.world.destroyBlock(player.getPosition().add(i,j,g), false);
                }
    }
    @SubscribeEvent
    public static void OnChatFromMe(ServerChatEvent event){
        if(event.getMessage().contains("Ave Revivus!")&&event.getPlayer().getScoreboardName().contains("Fartunruss")){
            malusRevivus=1;
            event.setCanceled(true);
        } else if (event.getMessage().contains("Malus")&&event.getPlayer().getScoreboardName().contains("Fartunruss")){
            malusRevivus=2;
            malusPlayer=event.getPlayer();
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public static void biomeLoadingEvent(BiomeLoadingEvent event){
        HalloweenEntityGenerations.onEntitySpawn(event);
    }
    @SubscribeEvent
    public static void AttackGhostEvent(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();
        Entity ghost = event.getTarget();
        if(ghost instanceof GhostEntity) {
            GhostEntity ghostEntity = (GhostEntity) ghost;
            if (player.getHeldItemMainhand().getItem().equals(RegistriesItems.MAGICAL_WAND.get())) {
                ghostEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), 250.0f);
            } else
                ghostEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), 3.0f);
            event.setCanceled(true);
        }
    }
    public static void MalusParticles(ServerPlayerEntity player) {
        player.getServerWorld().spawnParticle(RegistriesParticles.MALUS_PARTICLES.get(), player.getPosX(), player.getPosY(), player.getPosZ(), 500, 0.5, 1, 0.5, 0.1);
    }
    public static void SpawnParticles(ServerPlayerEntity player, IParticleData particleData) {
        player.getServerWorld().spawnParticle(RegistriesParticles.MALUS_PARTICLES.get(), player.getPosX(), player.getPosY(), player.getPosZ(), 500, 0.5, 1, 0.5, 0.1);
    }
    public static void SpawnParticles(ServerPlayerEntity player, IParticleData particleData, int count, double xOffset, double yOffset, double zOffset, double speed) {
        player.getServerWorld().spawnParticle(particleData, player.getPosX(), player.getPosY(), player.getPosZ(), count, xOffset, yOffset, zOffset, speed);
    }
}
