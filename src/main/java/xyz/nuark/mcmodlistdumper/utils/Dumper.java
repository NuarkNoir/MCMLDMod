package xyz.nuark.mcmodlistdumper.utils;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import org.apache.commons.io.IOUtils;
import xyz.nuark.mcmodlistdumper.MCMLDMod;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Dumper {
    private static String readTemplateFromAssets() throws IOException {
        InputStream stream = MCMLDMod.class.getClassLoader().getResourceAsStream("assets/mcmodlistdumper/report_tpl.html");
        return IOUtils.toString(Objects.requireNonNull(stream), StandardCharsets.UTF_8);
    }

    public static Path dump() throws Exception {
        final StringBuilder mods = new StringBuilder();
        ModList.get().forEachModContainer((name, container) -> {
            String modId = container.getModId();
            ModInfo mi = (ModInfo) container.getModInfo();
            String modName = mi.getDisplayName();
            String modDisplayVersion = mi.getVersion().toString();
            String modAuthors = mi.getConfigElement("authors").orElse("Unknown").toString();
            String modDescription = mi.getDescription();

            mods.append(String.format(
                    "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>\n",
                    modId, modName, modDescription, modDisplayVersion, modAuthors
            ));
        });

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Path folderPath = Paths.get(Minecraft.getInstance().gameDir.getAbsolutePath(), "mod_lists");
        if (Files.notExists(folderPath) || !Files.isDirectory(folderPath)) {
            Files.createDirectories(folderPath);
        }

        Path filePath = Paths.get(
                folderPath.toAbsolutePath().toString(),
                String.format("dump-%s.html", dtf.format(LocalDateTime.now()))
        );

        String template = Dumper.readTemplateFromAssets();
        try (PrintWriter out = new PrintWriter(filePath.toAbsolutePath().toString())) {
            out.println(MessageFormat.format(template, mods));
        }

        return filePath;
    }
}
