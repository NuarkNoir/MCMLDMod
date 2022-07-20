package xyz.nuark.mcmodlistdumper.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.ModListScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.nuark.mcmodlistdumper.utils.Dumper;

import java.nio.file.Path;
import java.util.List;

import static xyz.nuark.mcmodlistdumper.MCMLDMod.LOGGER;

@Mixin(ModListScreen.class)
public abstract class MixinModListScreen {
    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        LOGGER.info("ModListScreen.init()");

        Screen scr = (Screen) (Object) this;

        int btnPanelWidth = scr.width - this.listWidth - (PADDING * 2);
        int btnWidth = btnPanelWidth / 2 - PADDING;
        int btnMountX = scr.width - btnPanelWidth;
        doneButton.x = btnMountX;
        doneButton.setWidth(btnWidth);

        Component buttonText = Component.literal("Dump mods list")/*Component.translatable("gui.done")*/;
        Button dumpButton = new Button(btnMountX + btnWidth + PADDING, doneButton.y, btnWidth, doneButton.getHeight(), buttonText, b -> {
            System.out.println("Dump button pressed");
            try {
                Path dumpedInto = Dumper.dump();
                Util.getPlatform().openFile(dumpedInto.toFile());
                b.setMessage(Component.literal("Success").withStyle(ChatFormatting.GREEN));
                LOGGER.info("Dumped into: {}", dumpedInto);
            } catch (Exception e) {
                buttonText.getSiblings().clear();
                b.setMessage(Component.literal("Failed").withStyle(ChatFormatting.RED));
                LOGGER.error("Failed to dump mods list", e);
            }
        });
        scr.renderables.add(dumpButton);
        ((List<GuiEventListener>) scr.children()).add(dumpButton);
    }

    @Shadow
    private int listWidth;
    @Final
    @Shadow
    private static int PADDING;
    @Shadow
    private Button doneButton;
}
