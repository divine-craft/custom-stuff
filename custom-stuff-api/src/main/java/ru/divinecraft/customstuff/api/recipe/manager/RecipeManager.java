package ru.divinecraft.customstuff.api.recipe.manager;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

/**
 * Manager of {@link org.bukkit.inventory.Recipe Bukkit's recipes} guaranteeing persistence between
 * calls to {@link Server#reloadData()}.
 * This is a recommended alternative to the corresponding methods of {@link Server}.
 */
public interface RecipeManager {

    /**
     * Registers a new recipe.
     *
     * @param recipe registered recipe
     * @param <R> type of the registered recipe
     */
    <R extends Recipe & Keyed> void registerRecipe(R recipe);

    /**
     * Unregisters the recipe by the given key.
     *
     * @param key key corresponding to the recipe
     */
    void unregisterRecipe(@NotNull NamespacedKey key);

    /**
     * Gets thee recipe by the given key.
     *
     * @param key key corresponding to the recipe
     * @return recipe by the given key or {@code null} if there is none
     */
    @Nullable Recipe getRecipe(@NotNull NamespacedKey key);

    /**
     * Returns an {@link Iterator iterator} over all available {@link Recipe recipes}.
     *
     * @return iterator over all available recipes
     */
    @NotNull Iterator<@NotNull Recipe> recipeIterator();

    /**
     * Clears all recipes.
     */
    void clearRecipes();

    /**
     * Resets the recipes to defaults.
     */
    void resetRecipes();
}
