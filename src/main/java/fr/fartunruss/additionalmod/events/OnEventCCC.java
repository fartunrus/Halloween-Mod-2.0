package fr.fartunruss.additionalmod.events;

import com.google.common.collect.ImmutableSet;
import fr.fartunruss.additionalmod.HalloweenMod;
import fr.fartunruss.additionalmod.entities.GhostEntity;
import fr.fartunruss.additionalmod.network.HalloweenNetworking;
import fr.fartunruss.additionalmod.network.ParticlePacket;
import fr.fartunruss.additionalmod.setup.RegistriesItems;
import fr.fartunruss.additionalmod.items.WandItem;
import fr.fartunruss.additionalmod.setup.RegistriesParticles;
import fr.fartunruss.additionalmod.setup.RegistriesSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.lwjgl.glfw.GLFW;

import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = HalloweenMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class OnEventCCC {
    @OnlyIn(Dist.CLIENT)
    public static KeyBinding RightClick;
    @OnlyIn(Dist.CLIENT)
    public static KeyBinding Improved;

    public static void clientSetup() {
        RightClick = new KeyBinding("key.wand_using.desc", GLFW.GLFW_KEY_X, "key."+HalloweenMod.MODID+".category");
        ClientRegistry.registerKeyBinding(RightClick);
        Improved = new KeyBinding("key.wand_using_improved.desc", GLFW.GLFW_KEY_Z, "key."+HalloweenMod.MODID+".category");
        ClientRegistry.registerKeyBinding(Improved);
    }

    public static double speed = 1;
    public static boolean reset=false;
    public static int lightning=0;
    public static boolean lightningb=false;
    //public static boolean showScreamer=false;
    public static int expRemove=0;
    @SubscribeEvent
    public static void OnPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        if (speed < 0.3) {
            speed = 0.3;
        }
        Item wand = player.getHeldItemMainhand().getItem();
        if (wand.equals(RegistriesItems.MAGICAL_WAND.get())) {
            WandItem wandItem = (WandItem) wand;
            if (Improved.isKeyDown()) {
                if (player.experienceTotal != 0) {
                    if (BlocksIsntAir(20, player)) {
                        wandItem.Fly(player, 0.4*1.5 * speed, false);
                        speed *= 0.99;
                    } else if (!BlockIsAir(-20.005, player)) {
                        wandItem.Fly(player, 0, true);
                        speed = 1;
                    } else if (BlockIsAir(-20.005, player)) {
                        if (BlockIsAir(-21, player)) {
                            wandItem.Fly(player, -0.2*1.5 * speed, false);
                            speed *= 0.99;
                        } else wandItem.Fly(player, -0.05*1.5, false);
                    }
                    if (!player.isInvulnerable()) player.setInvulnerable(true);
                    reset = true;
                    expRemove++;
                    if (expRemove % 20 == 0) {
                        ExpRemove(player, 2,expRemove);
                    }
                    SpawnParticles(player, ParticleTypes.PORTAL, 8, 0.5, 0.5, 0.5, -0.2);
                    HalloweenNetworking.INSTANCE.sendToServer(new ParticlePacket(8));
                }
            } else if (RightClick.isKeyDown()) {
                if (player.experienceTotal != 0) {
                    if (BlocksIsntAir(6, player)) {
                        wandItem.Fly(player, 0.4 * speed, false);
                        speed *= 0.99;
                    } else if (!BlockIsAir(-6.005, player)) {
                        wandItem.Fly(player, 0, true);
                        speed = 1;
                    } else if (BlockIsAir(-6.005, player)) {
                        if (BlockIsAir(-7, player)) {
                            wandItem.Fly(player, -0.2 * speed, false);
                            speed *= 0.99;
                        } else wandItem.Fly(player, -0.05, false);
                    }
                    if (!player.isInvulnerable()) player.setInvulnerable(true);
                    reset = true;
                    expRemove++;
                    if (expRemove % 20 == 0) {
                        ExpRemove(player, 1,expRemove);

                    }
                    SpawnParticles(player, ParticleTypes.PORTAL, 4, 0.5, 0.5, 0.5, -0.2);
                    HalloweenNetworking.INSTANCE.sendToServer(new ParticlePacket(4));
                }
            } else {
                if (reset) {
                    wandItem.idle = true;
                    player.setInvulnerable(false);
                    speed = 1;
                    reset = false;
                }
            }
        } else {
            if (reset) {
                if (player.inventory.hasAny(ImmutableSet.of(RegistriesItems.MAGICAL_WAND.get()))&&!player.world.isRemote)
                    ((WandItem) player.inventory.getStackInSlot(player.inventory.getSlotFor(new ItemStack(RegistriesItems.MAGICAL_WAND.get()))).getItem()).idle = true;
                player.setInvulnerable(false);
                speed = 1;
                reset = false;
            }
        }

        if(lightningb) {
            if (lightning == 0) {
                for (ServerPlayerEntity playerr : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
                    RemoveFire(playerr);
                }
                lightningb = false;
            } else if (lightning > 0) lightning--;
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
    public static void ExpRemove(PlayerEntity player, int amount, int ticker){
        player.giveExperiencePoints(-amount);
        ticker=1;
    }
    @SubscribeEvent
    public static void OnChatFromMe(ServerChatEvent event){
        if(event.getMessage().contains("Ave Revivus!")&&event.getPlayer().getScoreboardName().equals("Fartunruss")){
            lightning=40;
            lightningb=true;
            for(ServerPlayerEntity sp:ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()){
                UUID uuid = sp.getUniqueID();
                sp.sendMessage(new TranslationTextComponent("text."+HalloweenMod.MODID+".averevivus-shangrin"), uuid);
                sp.sendMessage(new TranslationTextComponent("text."+HalloweenMod.MODID+".averevivus-me"), uuid);
                //sp.sendMessage(new TranslationTextComponent("text.halloweenmod.averevivus-player"), uuid);
                sp.heal(8f);
                LightningBoltEntity lbe=new LightningBoltEntity(EntityType.LIGHTNING_BOLT, sp.world);
                lbe.setPosition(sp.getPositionVec().x,sp.getPositionVec().y,sp.getPositionVec().z);
                sp.world.addEntity(lbe);
                lbe.setDamage(0f);
                sp.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 1f);
                sp.setFire(1);
                //sp.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);
                event.setCanceled(true);
            }
        } else if (event.getMessage().contains("Malus")&&event.getPlayer().getScoreboardName().equals("Fartunruss")){
            event.setComponent(new TranslationTextComponent("text."+HalloweenMod.MODID+".malus"));
            MalusParticles(event.getPlayer());
            event.getPlayer().addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 60*20, 255, true, false, false));
            event.getPlayer().playSound(RegistriesSoundEvents.MALUS_SOUND.get(), SoundCategory.PLAYERS, 1f, 1f);
            event.setCanceled(false);
        }
    }
    public static void MalusParticles(ServerPlayerEntity player) {
        player.getServerWorld().spawnParticle(RegistriesParticles.MALUS_PARTICLES.get(), player.getPosX(), player.getPosY(), player.getPosZ(), 500, 0.5, 1, 0.5, 0.1);
    }
    public static void SpawnParticles(ServerPlayerEntity player, IParticleData particleData) {
        player.getServerWorld().spawnParticle(RegistriesParticles.MALUS_PARTICLES.get(), player.getPosX(), player.getPosY(), player.getPosZ(), 500, 0.5, 1, 0.5, 0.1);
    }
    public static void SpawnParticles(PlayerEntity player, IParticleData particleData, int count, double xOffset, double yOffset, double zOffset, double speed) {
        if(player instanceof ServerPlayerEntity)((ServerPlayerEntity)player).getServerWorld().spawnParticle(particleData, player.getPosX(), player.getPosY(), player.getPosZ(), count, xOffset, yOffset, zOffset, speed);
    }
    public static boolean BlocksIsntAir(double yRange, PlayerEntity player){
        boolean notair=false;
        for(int i = 1; i<yRange;i++){
            Block block = player.world.getBlockState(player.getPosition().add(0, -i, 0)).getBlock();
            if(!(block.equals(Blocks.AIR) || block.equals(Blocks.CAVE_AIR))) {
                notair = true;
                break;
            }
        }
        return notair;
    }
    public static boolean BlockIsAir(double y, PlayerEntity player){
        Block block = player.world.getBlockState(player.getPosition().add(0, y, 0)).getBlock();
        return block.equals(Blocks.AIR) || block.equals(Blocks.CAVE_AIR);
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
    @SubscribeEvent
    public static void biomeLoadingEvent(BiomeLoadingEvent event){
        HalloweenEntityGenerations.onEntitySpawn(event);
    }
    public static int eventTime=0;
    public static boolean eventstop=false;
    public static int timeBeforeEternalLife=0;
    @SubscribeEvent
    public static void OnServerTickEvent(TickEvent.ServerTickEvent event){
        if(timeBeforeEternalLife==0&&!eventstop) {
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
        }
        if(lightningb) {
            if (lightning == 0) {
                for (ServerPlayerEntity player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
                    RemoveFire(player);
                }
                lightningb = false;
            } else if (lightning > 0) lightning--;
        }
    }
}
