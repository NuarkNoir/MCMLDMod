package xyz.nuark.mcmodlistdumper.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import xyz.nuark.mcmodlistdumper.utils.Dumper;

public class ButtonDumper extends GuiButton {
    public ButtonDumper(GuiScreen parent, GuiButton reference) {
        super(4356789, reference.x + reference.width + 8, reference.y, "Dump mods");
        this.width = 100;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        String title = "Mods dump saved";
        String message = "Location: ";

        try {
            String location = Dumper.dump();
            message += location;
        }
        catch (Exception e) {
            e.fillInStackTrace();
            title = "Cannot save mods dump";
            message = e.getMessage();
        }

        Minecraft.getMinecraft().displayGuiScreen(new GuiOk(title, message));
        super.mouseReleased(mouseX, mouseY);
    }
}
