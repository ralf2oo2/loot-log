package ralf2oo2.lootlog.config;

public enum VerticalAnchor {
    TOP("gui.config.lootlog.vertical_anchor.top"),
    BOTTOM("gui.config.lootlog.vertical_anchor.bottom");

    public final String translationKey;

    private VerticalAnchor(String translationKey) {
        this.translationKey = translationKey;
    }

    public String toString() {
        return this.translationKey;
    }
}
