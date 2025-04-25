package ralf2oo2.lootlog.config;

import com.google.common.collect.ImmutableMap;
import net.glasslauncher.mods.alwaysmoreitems.config.OverlayMode;
import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.glasslauncher.mods.gcapi3.api.ConfigFactoryProvider;
import net.glasslauncher.mods.gcapi3.impl.SeptFunction;
import net.glasslauncher.mods.gcapi3.impl.factory.DefaultFactoryProvider;
import net.glasslauncher.mods.gcapi3.impl.object.ConfigEntryHandler;
import net.glasslauncher.mods.gcapi3.impl.object.entry.EnumConfigEntryHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.function.Function;

public class LootLogConfigFactories implements ConfigFactoryProvider {
    @Override
    public void provideLoadFactories(ImmutableMap.Builder<Type, SeptFunction<String, ConfigEntry, Field, Object, Boolean, Object, Object, ConfigEntryHandler<?>>> builder) {
        builder.put(VerticalAnchor.class, (SeptFunction)(id, configEntry, parentField, parentObject, isMultiplayerSynced, enumOrOrdinal, defaultEnum) -> new EnumConfigEntryHandler((String) id, (ConfigEntry) configEntry, (Field) parentField, parentObject, (Boolean) isMultiplayerSynced, DefaultFactoryProvider.enumOrOrdinalToOrdinal(enumOrOrdinal), ((VerticalAnchor)defaultEnum).ordinal(), VerticalAnchor.class));
        builder.put(HorizontalAnchor.class, (SeptFunction)(id, configEntry, parentField, parentObject, isMultiplayerSynced, enumOrOrdinal, defaultEnum) -> new EnumConfigEntryHandler((String) id, (ConfigEntry) configEntry, (Field) parentField, parentObject, (Boolean) isMultiplayerSynced, DefaultFactoryProvider.enumOrOrdinalToOrdinal(enumOrOrdinal), ((HorizontalAnchor)defaultEnum).ordinal(), HorizontalAnchor.class));
    }

    @Override
    public void provideSaveFactories(ImmutableMap.Builder<Type, Function<Object, Object>> builder) {
        builder.put(VerticalAnchor.class, (Function)(enumEntry) -> enumEntry);
        builder.put(HorizontalAnchor.class, (Function)(enumEntry) -> enumEntry);
    }
}
