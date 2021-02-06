package xyz.nuark.mcmodlistdumper.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.widget.ExtendedButton;
import xyz.nuark.mcmodlistdumper.utils.Dumper;

import java.io.File;
import java.nio.file.Path;

public class ButtonDumper extends ExtendedButton {
    public ButtonDumper(Widget reference) {
        super(
                reference.x + reference.getWidth() + 8, reference.y,
                80, 20,
                new StringTextComponent("Dump list"),
                ButtonDumper::dump
        );
    }

    public static void dump(Button _ignored) {
        boolean ok = true;
        String message = "";

        try {
            Path location = Dumper.dump();
            Util.getOSType().openFile(location.toFile());
        }
        catch (Exception e) {
            ok = false;
            e.fillInStackTrace();
            message = e.getMessage();
        }

        Screen currScreen = Minecraft.getInstance().currentScreen;
        Minecraft.getInstance().displayGuiScreen(new DumpResultScreen(currScreen, ok, message));
    }
}
