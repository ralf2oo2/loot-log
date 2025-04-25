package ralf2oo2.lootlog.config;

public enum HorizontalAnchor {
    LEFT("gui.config.lootlog.horizontal_anchor.left"),
    RIGHT("gui.config.lootlog.horizontal_anchor.right");

    public final String translationKey;

    private HorizontalAnchor(String translationKey) {
        this.translationKey = translationKey;
    }

    public String toString() {
        return this.translationKey;
    }
}
