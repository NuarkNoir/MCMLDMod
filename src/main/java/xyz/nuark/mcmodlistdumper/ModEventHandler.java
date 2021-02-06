package xyz.nuark.mcmodlistdumper;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.gui.screen.ModListScreen;
import net.minecraftforge.fml.common.Mod;
import xyz.nuark.mcmodlistdumper.gui.ButtonDumper;

@Mod.EventBusSubscriber()
public class ModEventHandler {

    @SubscribeEvent
    public static void onInitGuiEvent(final GuiScreenEvent.InitGuiEvent.Post event) {
        final Screen gui = event.getGui();
        if (gui instanceof ModListScreen) {
            Widget ref_btn = null;
            for (Widget wgt : event.getWidgetList()) {
                String refKey = ((TranslationTextComponent) wgt.getMessage()).getKey();
                if (refKey.equals("gui.done")) {
                    ref_btn = wgt;
                    ref_btn.setWidth(80);
                    break;
                }
            }

            if (ref_btn == null) {
                MCMLDMod.getLogger().warn("Cannot find reference button!");
                return;
            }

            event.addWidget(new ButtonDumper(ref_btn));
        }
    }
}
