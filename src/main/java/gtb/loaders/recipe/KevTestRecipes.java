package gtb.loaders.recipe;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;

import gtb.api.recipes.GTBRecipeMaps;
import gtb.api.unification.materials.GTBMaterials;

public class KevTestRecipes {

    public static void register() {
        GTBRecipeMaps.PARTICLE_ACCELERATOR_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, GTBMaterials.Iron54)
                .output(OrePrefix.dust, GTBMaterials.Manganese54)
                .kEv(800)
                .EUt(32000)
                .duration(800)
                .buildAndRegister();
    }
}
