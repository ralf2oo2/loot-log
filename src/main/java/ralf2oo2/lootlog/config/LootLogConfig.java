package ralf2oo2.lootlog.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;

public class LootLogConfig {
    @ConfigRoot(value = "config", visibleName = "Loot Log Config")
    public static ConfigFields config = new ConfigFields();
    public static class ConfigFields{
        @ConfigEntry(
                name = "Horizontal Anchor",
                description = "Set whether the log is attached to the right or left side of the screen"
        )
        public HorizontalAnchor horizontalAnchor = HorizontalAnchor.RIGHT;

        @ConfigEntry(
                name = "Vertical Anchor",
                description = "Set whether the log is attached to the top or bottom side of the screen"
        )
        public VerticalAnchor verticalAnchor = VerticalAnchor.TOP;

        @ConfigEntry(
                name = "Scale",
                description = "Set the scale of the log",
                minLength = 0,
                maxLength = 3
        )
        public Float scale = 1.0f;
    }
}
