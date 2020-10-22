package ru.divinecraft.customstuff.api.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import ru.progrm_jarvis.javacommons.map.MapUtil;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

@UtilityClass
public class FuelUtil {

    private final Integer ZERO = 0;

    private final @NotNull Map<@NotNull Material, @NotNull Integer> FUEL_MATERIALS = MapUtil
            .mapFiller(new EnumMap<@NotNull Material, @NotNull Integer>(Material.class))
            .put(Material.LAVA_BUCKET, 20000)
            .put(Material.COAL_BLOCK, 16000)
            .put(Material.BLAZE_ROD, 2400)
            .put(Material.COAL, 1600)
            .put(Material.BOAT, 1200)
            .put(Material.BOAT_ACACIA, 1200)
            .put(Material.BOAT_BIRCH, 1200)
            .put(Material.BOAT_DARK_OAK, 1200)
            .put(Material.BOAT_JUNGLE, 1200)
            .put(Material.BOAT_SPRUCE, 1200)
            .put(Material.LOG, 300)
            .put(Material.LOG_2, 300)
            .put(Material.WOOD, 300)
            .put(Material.WOOD_PLATE, 300)
            .put(Material.FENCE, 300)
            .put(Material.ACACIA_FENCE, 300)
            .put(Material.BIRCH_FENCE, 300)
            .put(Material.DARK_OAK_FENCE, 300)
            .put(Material.JUNGLE_FENCE, 300)
            .put(Material.SPRUCE_FENCE, 300)
            .put(Material.FENCE_GATE, 300)
            .put(Material.ACACIA_FENCE_GATE, 300)
            .put(Material.BIRCH_FENCE_GATE, 300)
            .put(Material.DARK_OAK_FENCE_GATE, 300)
            .put(Material.JUNGLE_FENCE_GATE, 300)
            .put(Material.SPRUCE_FENCE_GATE, 300)
            .put(Material.WOOD_STAIRS, 300)
            .put(Material.ACACIA_STAIRS, 300)
            .put(Material.BIRCH_WOOD_STAIRS, 300)
            .put(Material.DARK_OAK_STAIRS, 300)
            .put(Material.JUNGLE_WOOD_STAIRS, 300)
            .put(Material.SPRUCE_WOOD_STAIRS, 300)
            .put(Material.TRAP_DOOR, 300)
            .put(Material.WORKBENCH, 300)
            .put(Material.BOOKSHELF, 300)
            .put(Material.CHEST, 300)
            .put(Material.TRAPPED_CHEST, 300)
            .put(Material.DAYLIGHT_DETECTOR, 300)
            .put(Material.JUKEBOX, 300)
            .put(Material.NOTE_BLOCK, 300)
            .put(Material.HUGE_MUSHROOM_1, 300)
            .put(Material.HUGE_MUSHROOM_2, 300)
            .put(Material.BANNER, 300)
            .put(Material.FISHING_ROD, 300)
            .put(Material.LADDER, 300)
            .put(Material.WOOD_SWORD, 200)
            .put(Material.WOOD_PICKAXE, 200)
            .put(Material.WOOD_AXE, 200)
            .put(Material.WOOD_SPADE, 200)
            .put(Material.WOOD_HOE, 200)
            .put(Material.BOW, 300)
            .put(Material.SIGN, 200)
            .put(Material.WOOD_DOOR, 200)
            .put(Material.ACACIA_DOOR_ITEM, 200)
            .put(Material.DARK_OAK_DOOR_ITEM, 200)
            .put(Material.JUNGLE_DOOR_ITEM, 200)
            .put(Material.SPRUCE_DOOR_ITEM, 200)
            .put(Material.WOOD_STEP, 200)
            .put(Material.SAPLING, 100)
            .put(Material.BOWL, 100)
            .put(Material.STICK, 100)
            .put(Material.WOOD_BUTTON, 100)
            .put(Material.WOOL, 100)
            .put(Material.CARPET, 67)
            // extra:
            .put(Material.DEAD_BUSH, 100)
            .map();
    private final @Unmodifiable @NotNull Map<@NotNull Material, @NotNull Integer> FUEL_MATERIALS_VIEW
            = Collections.unmodifiableMap(FUEL_MATERIALS);

    public int getFuelTicks(final Material material) {
        return FUEL_MATERIALS.getOrDefault(material, ZERO);
    }

    public @Unmodifiable @NotNull Map<@NotNull Material, @NotNull Integer> getFuelMaterials() {
        return FUEL_MATERIALS_VIEW;
    }
}
