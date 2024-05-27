package gtb.loaders.recipe.polymerLines;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Color.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gtb.api.recipes.GTBRecipeMaps.*;
import static gtb.api.unification.materials.GTBMaterials.*;
import static gtb.common.item.GTBMetaItems.*;

public class KaptonLine {

    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(ParaXylene.getFluid(1000))
                .fluidInputs(Chloromethane.getFluid(2000))
                .output(dust, Durene, 24)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .duration(120).EUt(VA[MV]).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Durene, 4)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, PyromelliticDianhydride, 3)
                .fluidOutputs(Water.getFluid(1000))
                .duration(30).EUt(VA[HV]).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Nitrobenzene.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(4000))
                .circuitMeta(1)
                .fluidOutputs(Aminophenol.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .duration(300).EUt(VA[LV]).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Tin)
                .fluidInputs(Aniline.getFluid(2000))
                .fluidInputs(Phenol.getFluid(1000))
                .notConsumable(HydrochloricAcid.getFluid())
                .output(dust, Oxydianiline, 27)
                .fluidOutputs(Methane.getFluid(2000))
                .duration(100).EUt(VA[ZPM]).buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Saltpeter)
                .fluidInputs(Aminophenol.getFluid(1000))
                .fluidInputs(Nitrochlorobenzene.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .notConsumable(Dimethylformamide.getFluid())
                .output(dust, Oxydianiline, 27)
                .fluidOutputs(Oxygen.getFluid(3000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .duration(200).EUt(VA[LV]).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PhthalicAnhydride, 13)
                .notConsumable(dust, Palladium)
                .output(dust, BiphenylTetracarboxylicAcidDianhydride, 28)
                .fluidOutputs(Hydrogen.getFluid(2000))
                .duration(200).EUt(VA[HV]).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Nitrochlorobenzene.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidOutputs(Nitroaniline.getFluid(1000))
                .output(dust, AmmoniumChloride, 2)
                .duration(100).EUt(VA[HV]).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Nitroaniline.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(6000))
                .output(dust, ParaPhenylenediamine, 16)
                .fluidOutputs(Water.getFluid(2000))
                .duration(200).EUt(VA[IV]).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PyromelliticDianhydride, 2)
                .input(dust, Oxydianiline, 3)
                .fluidOutputs(KaptonK.getFluid(L))
                .duration(30).EUt(VA[IV]).buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, BiphenylTetracarboxylicAcidDianhydride, 2)
                .input(dust, ParaPhenylenediamine)
                .fluidInputs(KaptonK.getFluid(L))
                .fluidOutputs(KaptonE.getFluid(L))
                .duration(30).EUt(VA[ZPM]).buildAndRegister();
    }
}
