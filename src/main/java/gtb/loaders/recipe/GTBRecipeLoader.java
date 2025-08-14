package gtb.loaders.recipe;

import gregtech.api.gui.GuiTextures;
import gregtech.api.recipes.RecipeMaps;
import gtb.loaders.recipe.handlers.FluidRecipeHandler;
import gtb.loaders.recipe.handlers.NuclearRecipeHandler;
import supercritical.common.SCConfigHolder;
import supercritical.loaders.recipe.SCRecipeModifications;

public final class GTBRecipeLoader {

    public static void init() {
        RecipeMaps.BLAST_RECIPES.setMaxInputs(4);
        RecipeMaps.BLAST_RECIPES.setMaxFluidInputs(2);
        RecipeMaps.CENTRIFUGE_RECIPES.setMaxFluidInputs(3);
        RecipeMaps.CENTRIFUGE_RECIPES.setSlotOverlay(false, true, false, GuiTextures.CENTRIFUGE_OVERLAY);
        RecipeMaps.CHEMICAL_BATH_RECIPES.setMaxFluidInputs(2);
        RecipeMaps.CHEMICAL_BATH_RECIPES.setMaxFluidOutputs(3);
        RecipeMaps.MIXER_RECIPES.setMaxFluidInputs(4);
        RecipeMaps.EXTRACTOR_RECIPES.setMaxInputs(2);
        RecipeMaps.CHEMICAL_RECIPES.setMaxOutputs(3);
        RecipeMaps.LASER_ENGRAVER_RECIPES.setMaxFluidOutputs(1);
        RecipeMaps.LASER_ENGRAVER_RECIPES.setMaxFluidInputs(1);
        RecipeMaps.LARGE_CHEMICAL_RECIPES.setMaxInputs(4);
        RecipeMaps.SIFTER_RECIPES.setMaxFluidInputs(1);
        RecipeMaps.SIFTER_RECIPES.setMaxFluidOutputs(1);
        RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.setMaxInputs(9);
        RecipeMaps.VACUUM_RECIPES.setMaxFluidOutputs(2);
        RecipeMaps.CHEMICAL_RECIPES.setMaxFluidOutputs(3);
        RecipeMaps.CHEMICAL_RECIPES.setMaxInputs(3);
        RecipeMaps.CRACKING_RECIPES.setMaxInputs(2);
        RecipeMaps.CRACKING_RECIPES.setMaxOutputs(1);
        RecipeMaps.ARC_FURNACE_RECIPES.setMaxInputs(2);
        RecipeMaps.ARC_FURNACE_RECIPES.setMaxFluidOutputs(3);
        RecipeMaps.ELECTROLYZER_RECIPES.setMaxInputs(3);
        RecipeMaps.BLAST_RECIPES.setMaxFluidOutputs(2);
        RecipeMaps.PYROLYSE_RECIPES.setMaxFluidOutputs(2);
        RecipeMaps.PYROLYSE_RECIPES.setMaxFluidInputs(2);


        MachineRecipes.init();
        SupercriticalRecipes.init();
        NuclearRecipeHandler.register();
        KevTestRecipes.register();
    }
    public static void loadLatest() {
        SCRecipeModifications.load();
        if (SCConfigHolder.misc.enableHX) {
            FluidRecipeHandler.runRecipeGeneration();
        }
    }
}
