package xyz.nuark.mcmodlistdumper;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import xyz.nuark.mcmodlistdumper.utils.Dumper;

@Mod(modid = MCMLDMod.MODID, name = MCMLDMod.NAME, version = MCMLDMod.VERSION, clientSideOnly = true)
public class MCMLDMod {
    public static final String MODID = "mcmodlistdumper";
    public static final String NAME = "MC Mod List Dumper";
    public static final String VERSION = "1.0";
    public static final String AUTHOR = "nuark";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Dumper.setMCFolder(event.getModConfigurationDirectory().getParent());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    public static Logger getLogger() {
        return logger;
    }
}
