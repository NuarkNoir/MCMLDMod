package xyz.nuark.mcmodlistdumper.gui;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiOk extends GuiScreen {
    protected String messageTitle;
    private final String messageThereIsFile;
    private final List<String> listLines = Lists.newArrayList();
//    protected String confirmButtonText;  // TODO: I need to get to know how to close current menu!

    public GuiOk(String messageTitle, String messageThereIsFile) {
        this.messageTitle = messageTitle;
        this.messageThereIsFile = messageThereIsFile;
//        this.confirmButtonText = "OK";
    }

    @Override
    public void initGui() {
//        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 96, this.confirmButtonText));
        this.listLines.clear();
        this.listLines.addAll(this.fontRenderer.listFormattedStringToWidth(this.messageThereIsFile, this.width - 50));
        this.listLines.addAll(this.fontRenderer.listFormattedStringToWidth("Press ESC to exit", this.width - 50));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.messageTitle, this.width / 2, 70, 16777215);
        int i = 90;

        for (String s : this.listLines) {
            this.drawCenteredString(this.fontRenderer, s, this.width / 2, i, 16777215);
            i += this.fontRenderer.FONT_HEIGHT;
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}