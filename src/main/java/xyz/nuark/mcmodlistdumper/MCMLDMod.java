package xyz.nuark.mcmodlistdumper;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(MCMLDMod.MODID)
public class MCMLDMod {
    public static final String MODID = "mcmodlistdumper";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MCMLDMod() {
    }
}
