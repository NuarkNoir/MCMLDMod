package xyz.nuark.mcmodlistdumper.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.widget.ExtendedButton;

public class DumpResultScreen extends Screen {
    static final int PADDING = 6;
    static final int OK_WIDTH = 80;
    static final int OK_HEIGHT = 20;
    static final ITextComponent SCREEN_TITLE = new StringTextComponent("Mods list dump result");

    Screen parentScreen;
    ITextComponent message;

    protected DumpResultScreen(Screen parent, boolean ok, String message) {
        super(SCREEN_TITLE);
        this.parentScreen = parent;

        this.message = new StringTextComponent("Dump " + (ok? "done" : "failed: ") + message);
    }

    @Override
    public void init(Minecraft minecraft, int width, int height) {
        super.init(minecraft, width, height);

        this.addButton(new ExtendedButton(
                ((this.width - OK_WIDTH) / 2), this.height - 20 - PADDING,
                OK_WIDTH, OK_HEIGHT,
                new StringTextComponent("OK"),
                b -> DumpResultScreen.this.closeScreen()
        ));
    }

    @Override
    public void render(MatrixStack mStack, int mouseX, int mouseY, float partialTicks) {
        this.renderDirtBackground(0);

        int x;
        int y;

        y = 20 - font.FONT_HEIGHT;
        x = (this.width / 2) - (font.getStringPropertyWidth(SCREEN_TITLE) / 2);
        font.func_238422_b_(mStack, SCREEN_TITLE.func_241878_f(), x, y, 0xFFFFFF);

        y += 100;
        x = (this.width / 3) - (font.getStringPropertyWidth(this.message) / 2);
        font.func_238422_b_(mStack, this.message.func_241878_f(), x, y, 0xC4C4C4);

        super.render(mStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void closeScreen() {
        Minecraft.getInstance().displayGuiScreen(this.parentScreen);
    }

    //    protected String messageTitle;
//    private final String messageThereIsFile;
//    private final List<String> listLines = Lists.newArrayList();
////    protected String confirmButtonText;  // TODO: I need to get to know how to close current menu!
//
//    public DumpResultScreen(String messageTitle, String messageThereIsFile) {
//        this.messageTitle = messageTitle;
//        this.messageThereIsFile = messageThereIsFile;
////        this.confirmButtonText = "OK";
//    }
//
//    @Override
//    public void initGui() {
////        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 96, this.confirmButtonText));
//        this.listLines.clear();
//        this.listLines.addAll(this.fontRenderer.listFormattedStringToWidth(this.messageThereIsFile, this.width - 50));
//        this.listLines.addAll(this.fontRenderer.listFormattedStringToWidth("Press ESC to exit", this.width - 50));
//    }
//
//    @Override
//    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//        this.drawDefaultBackground();
//        this.drawCenteredString(this.fontRenderer, this.messageTitle, this.width / 2, 70, 16777215);
//        int i = 90;
//
//        for (String s : this.listLines) {
//            this.drawCenteredString(this.fontRenderer, s, this.width / 2, i, 16777215);
//            i += this.fontRenderer.FONT_HEIGHT;
//        }
//
//        super.drawScreen(mouseX, mouseY, partialTicks);
//    }
}