package gtb.loaders.recipe;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;

import gtb.api.recipes.GTBRecipeMaps;

public class KevTestRecipes {

    public static void register() {
        GTBRecipeMaps.PARTICLE_ACCELERATOR_RECIPES.recipeBuilder()
                .input(OrePrefix.ingot, Materials.Steel)
                .output(OrePrefix.plate, Materials.Steel)
                .kEv(270)
                .EUt(100)
                .duration(100)
                .buildAndRegister();
    }
}
