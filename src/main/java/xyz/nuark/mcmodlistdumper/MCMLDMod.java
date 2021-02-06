package xyz.nuark.mcmodlistdumper;

import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mcmodlistdumper")
public class MCMLDMod {
    private static final Logger LOGGER = LogManager.getLogger();

    public MCMLDMod() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(
                () -> "", (rvs, nb) -> nb
        ));
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
