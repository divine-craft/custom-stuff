package ru.divinecraft.customstuff.api.util;

import lombok.experimental.UtilityClass;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class BlockUtil {

    /**
     * Checks if the given block can be clicked leading to some result such as opening its inventory.
     *
     * @param block block to be clicked
     * @return {@code true} if and only if the given block can be clicked in game
     */
    public boolean isClickable(final @NotNull Block block) {
        switch (block.getType()) {
            //<editor-fold desc="case <Blocks overriding #interact(...) in NMS>: return true;" defaultstate="collapsed">
            case ANVIL:
            case BEACON:
            case BED:
            case BREWING_STAND:
            case WOOD_BUTTON:
            case STONE_BUTTON:
            case CAKE_BLOCK:
            case CAULDRON:
            case CHEST:
            case TRAPPED_CHEST:
            case COMMAND:
            case COMMAND_CHAIN:
            case COMMAND_REPEATING:
            case DAYLIGHT_DETECTOR:
            case DAYLIGHT_DETECTOR_INVERTED:
            case DISPENSER:
            case ACACIA_FENCE:
            case SPRUCE_DOOR:
            case BIRCH_DOOR:
            case JUNGLE_DOOR:
            case ACACIA_DOOR:
            case DARK_OAK_DOOR:
            case DRAGON_EGG:
            case ENCHANTMENT_TABLE:
            case ENDER_CHEST:
            case FENCE:
            case FENCE_GATE:
            case FLOWER_POT:
            case FURNACE:
            case BURNING_FURNACE:
            case HOPPER:
            case JUKEBOX:
            case LEVER:
            case NOTE_BLOCK:
                // case PISTON_MOVING_PIECE: seems to be unrelated
                // case PUMPKIN: may be needed in future for curving
            case REDSTONE_COMPARATOR_ON:
            case REDSTONE_COMPARATOR_OFF:
            case REDSTONE_ORE:
            case GLOWING_REDSTONE_ORE:
            case DIODE_BLOCK_ON:
            case DIODE_BLOCK_OFF:
            case WHITE_SHULKER_BOX:
            case ORANGE_SHULKER_BOX:
            case MAGENTA_SHULKER_BOX:
            case LIGHT_BLUE_SHULKER_BOX:
            case YELLOW_SHULKER_BOX:
            case LIME_SHULKER_BOX:
            case PINK_SHULKER_BOX:
            case GRAY_SHULKER_BOX:
            case SILVER_SHULKER_BOX:
            case CYAN_SHULKER_BOX:
            case PURPLE_SHULKER_BOX:
            case BLUE_SHULKER_BOX:
            case BROWN_SHULKER_BOX:
            case GREEN_SHULKER_BOX:
            case RED_SHULKER_BOX:
            case BLACK_SHULKER_BOX:
            case SIGN_POST:
            case WALL_SIGN:
                // case <STAIRS>: black magic
            case STRUCTURE_BLOCK:
            case TNT:
            case TRAP_DOOR:
            case WORKBENCH: return true;
            //</editor-fold>
            default: return false;
        }
    }
}
