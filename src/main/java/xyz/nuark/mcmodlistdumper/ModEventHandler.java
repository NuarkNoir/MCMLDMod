package xyz.nuark.mcmodlistdumper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.nuark.mcmodlistdumper.gui.ButtonDumper;

import static net.minecraftforge.fml.relauncher.Side.CLIENT;

@Mod.EventBusSubscriber(CLIENT)
public class ModEventHandler {

    @SubscribeEvent
    public static void onInitGuiEvent(final GuiScreenEvent.InitGuiEvent.Post event) {
        final GuiScreen gui = event.getGui();
        if (gui instanceof GuiMainMenu) {
            GuiButton ref_btn = null;  // It will always be exit button
            for (GuiButton btn : event.getButtonList()) {
                if (btn.id == 4) {
                    ref_btn = btn;
                }
            }

            if (ref_btn == null) {
                MCMLDMod.getLogger().warn("Cannot find reference button!");
                return;
            }

            event.getButtonList().add(new ButtonDumper(gui, ref_btn));
        }
    }
}
