package xyz.nuark.mcmodlistdumper.utils;

import net.minecraftforge.fml.common.Loader;
import org.apache.commons.io.IOUtils;
import xyz.nuark.mcmodlistdumper.MCMLDMod;

import java.awt.*;
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
    private static String _mcFolder = "";

    private static String readTemplateFromAssets() throws IOException {
        InputStream stream = MCMLDMod.class.getClassLoader().getResourceAsStream(String.format("assets/%s/report_tpl.html", MCMLDMod.MODID));
        return IOUtils.toString(Objects.requireNonNull(stream), StandardCharsets.UTF_8);
    }

    public static String dump() throws Exception {
        if (_mcFolder.isEmpty()) {
            throw new Exception("Did not received MC root folder!");
        }

        final StringBuilder mods = new StringBuilder();
        Loader.instance().getIndexedModList().forEach((name, container) -> {
            String modId = container.getModId();
            String modName = container.getName();
            String modDisplayVersion = container.getDisplayVersion();
            String modAuthors = container.getMetadata().getAuthorList();
            String modDescription = container.getMetadata().description;

            mods.append(String.format(
                    "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>\n",
                    modId, modName, modDescription, modDisplayVersion, modAuthors
            ));
        });

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Path folderPath = Paths.get(Dumper._mcFolder, "mod_lists");
        if (Files.notExists(folderPath) || !Files.isDirectory(folderPath)) {
            Files.createDirectories(folderPath);
        }

        Path filePath = Paths.get(
                folderPath.toAbsolutePath().toString(),
                String.format("dump-%s.html", dtf.format(LocalDateTime.now()))
        );

        String template = Dumper.readTemplateFromAssets();
        try (PrintWriter out = new PrintWriter(filePath.toAbsolutePath().toString())) {
            out.println(MessageFormat.format(template, MCMLDMod.NAME, MCMLDMod.VERSION, MCMLDMod.AUTHOR, mods));
        }

        Desktop.getDesktop().open(filePath.toFile());
        return filePath.toAbsolutePath().toString();
    }

    public static void setMCFolder(String _mcFolder) {
        Dumper._mcFolder = _mcFolder;
    }
}
