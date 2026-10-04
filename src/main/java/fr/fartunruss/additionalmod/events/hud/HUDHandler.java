/*package fr.fartunruss.halloweenmod.events.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.fartunruss.halloweenmod.HalloweenMod;
import fr.fartunruss.halloweenmod.events.OnEventCCC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
public class HUDHandler extends AbstractGui {
    public final Minecraft minecraft=Minecraft.getInstance();

    @SubscribeEvent
    public void renderOverlay(TickEvent.RenderTickEvent event) {
        System.out.println("FDF@@##@#@#$23456789#$#@#$");
        if (event.phase == TickEvent.Phase.END) {
            System.out.println("FDF@@##@#@#$#$#@#$");
            if (OnEventCCC.showScreamer && !minecraft.gameSettings.hideGUI) {
                System.out.println("DFEW#$#@$");
                if (minecraft.player != null) {
                    System.out.println("FDF");
                    blit(new MatrixStack(), 34, 24, 32, 32, 32, 32);
                    minecraft.getTextureManager().bindTexture(new ResourceLocation(HalloweenMod.MODID, "gui/screamer1.png"));
                }
            }
        }
    }
}
*/