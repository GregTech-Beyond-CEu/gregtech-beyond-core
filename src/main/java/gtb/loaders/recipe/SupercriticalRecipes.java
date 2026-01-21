package gtb.loaders.recipe;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.*;
import static gregtech.loaders.recipe.CraftingComponent.*;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;

import static gregtech.api.unification.ore.OrePrefix.plate;
import static gtb.api.unification.materials.GTBMaterials.*;
import static gtb.api.unification.materials.GTBMaterials.Zircon;
import static supercritical.api.recipes.SCRecipeMaps.*;
import static supercritical.api.unification.ore.SCOrePrefix.*;

import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gtb.api.unification.materials.GTBMaterials;
import supercritical.common.SCConfigHolder;
import supercritical.common.blocks.BlockFissionCasing;
import supercritical.common.blocks.BlockGasCentrifugeCasing;
import supercritical.common.blocks.BlockNuclearCasing;
import supercritical.common.blocks.SCMetaBlocks;
import supercritical.common.blocks.*;
import supercritical.common.item.SCMetaItems;
import supercritical.common.metatileentities.SCMetaTileEntities;

public class SupercriticalRecipes {

    public static void init() {

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(120)
                .input(plate, Steel, 4)
                .circuitMeta(16)
                .outputs(SCMetaBlocks.PANELLING.getItemVariant(
                        BlockPanelling.PanellingType.GRAY))
                .buildAndRegister();

        for (int i = 0; i < CHEMICAL_DYES.length; i++) {
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                    .inputs(SCMetaBlocks.PANELLING.getItemVariant(BlockPanelling.PanellingType.GRAY))
                    .fluidInputs(CHEMICAL_DYES[i].getFluid(9))
                    .outputs(SCMetaBlocks.PANELLING.getItemVariant(BlockPanelling.PanellingType.values()[i]))
                    .EUt(2).duration(10)
                    .buildAndRegister();
        }

        ASSEMBLER_RECIPES.recipeBuilder().EUt(48).duration(280)
                .input(plateDouble, Inconel718)
                .input(plate, Steel, 5)
                .input(frameGt, Steel)
                .outputs(SCMetaBlocks.FISSION_CASING.getItemVariant(
                        BlockFissionCasing.FissionCasingType.REACTOR_VESSEL, ConfigHolder.recipes.casingsPerCraft))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(48).duration(280)
                .input(pipeLargeFluid, Inconel718)
                .input(frameGt, Steel)
                .outputs(SCMetaBlocks.FISSION_CASING.getItemVariant(
                        BlockFissionCasing.FissionCasingType.COOLANT_CHANNEL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(48).duration(280)
                .input(stick, Zircaloy, 6)
                .input(ring, Zircaloy, 1)
                .circuitMeta(1)
                .outputs(SCMetaBlocks.FISSION_CASING.getItemVariant(
                        BlockFissionCasing.FissionCasingType.FUEL_CHANNEL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(48).duration(280)
                .input(stick, Zircaloy, 3)
                .input(ring, Zircaloy, 1)
                .circuitMeta(2)
                .outputs(SCMetaBlocks.FISSION_CASING.getItemVariant(
                        BlockFissionCasing.FissionCasingType.CONTROL_ROD_CHANNEL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(48).duration(200)
                .inputs(MetaBlocks.BOILER_CASING.getItemVariant(
                        BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE))
                .input(wireGtSingle, Nichrome, 4)
                .outputs(SCMetaBlocks.NUCLEAR_CASING.getItemVariant(
                        BlockNuclearCasing.NuclearCasingType.GAS_CENTRIFUGE_HEATER))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(48).duration(200)
                .input(pipeNormalFluid, Steel)
                .input(pipeTinyFluid, Steel, 3)
                .outputs(SCMetaBlocks.GAS_CENTRIFUGE_CASING.getItemVariant(
                        BlockGasCentrifugeCasing.GasCentrifugeCasingType.GAS_CENTRIFUGE_COLUMN))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(64).duration(200)
                .input(stick, BoronCarbide, 8)
                .outputs(SCMetaBlocks.NUCLEAR_CASING.getItemVariant(
                        BlockNuclearCasing.NuclearCasingType.SPENT_FUEL_CASING, ConfigHolder.recipes.casingsPerCraft))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Boron, 2)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, BoronTrioxide, 5)
                .EUt(VA[LV]).duration(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, BoronTrioxide, 10)
                .input(dust, Carbon, 7)
                .output(dust, BoronCarbide, 5)
                .fluidOutputs(CarbonMonoxide.getFluid(6000))
                .EUt(VA[MV]).duration(400)
                .buildAndRegister();

        GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(800).EUt(VA[HV])
                .fluidInputs(UraniumHexafluoride.getFluid(1000))
                .fluidOutputs(EnrichedUraniumHexafluoride.getFluid(100))
                .fluidOutputs(DepletedUraniumHexafluoride.getFluid(900))
                .buildAndRegister();

        GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(800).EUt(VA[HV])
                .fluidInputs(EnrichedUraniumHexafluoride.getFluid(1000))
                .fluidOutputs(HighEnrichedUraniumHexafluoride.getFluid(100))
                .fluidOutputs(DepletedUraniumHexafluoride.getFluid(900))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(400).EUt(VA[HV])
                .input(dust, FissilePlutoniumDioxide, 1)
                .input(dust, Uraninite, 19)
                .circuitMeta(1)
                .output(dust, LowGradeMOX, 20)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(400).EUt(VA[HV])
                .input(dust, FissilePlutoniumDioxide, 1)
                .input(dust, Uraninite, 4)
                .circuitMeta(2)
                .output(dust, HighGradeMOX, 5)
                .buildAndRegister();

        // Zircaloy
        BLAST_RECIPES.recipeBuilder().duration(200).EUt(VA[EV]).blastFurnaceTemp(2100)
                .input(dust, Zircon, 1)
                .output(dust, SiliconDioxide, 3)
                .chancedOutput(dust, ZirconiumDioxide, 3, 9000, 0)
                .chancedOutput(dust, HafniumDioxide, 3, 1000, 0)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(200).EUt(VA[EV]).blastFurnaceTemp(1400)
                .input(dust, ZirconiumDioxide, 3)
                .input(dust, Carbon, 1)
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .output(dust, ZirconiumTetrachloride, 5)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(200).EUt(VA[EV]).blastFurnaceTemp(1250)
                .input(dust, HafniumDioxide, 3)
                .input(dust, Carbon, 1)
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .output(dust, HafniumTetrachloride, 5)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(200).EUt(VA[EV]).blastFurnaceTemp(1150)
                .input(dust, ZirconiumTetrachloride, 5)
                .input(dust, Magnesium, 2)
                .output(dust, Zirconium, 1)
                .output(dust, MagnesiumChloride, 6)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(200).EUt(VA[EV]).blastFurnaceTemp(1150)
                .input(dust, HafniumTetrachloride, 5)
                .input(dust, Magnesium, 2)
                .output(dust, Hafnium, 1)
                .output(dust, MagnesiumChloride, 6)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(200).EUt(VA[EV])
                .input(dust, Zirconium, 16)
                .input(dust, Tin, 2)
                .input(dust, Chrome, 1)
                .circuitMeta(1)
                .output(dust, Zircaloy, 19)
                .buildAndRegister();

        // Inconel 718
        MIXER_RECIPES.recipeBuilder().duration(200).EUt(VA[EV])
                .input(dust, Nickel, 5)
                .input(dust, Chrome, 2)
                .input(dust, Iron, 2)
                .input(dust, Niobium)
                .input(dust, Molybdenum)
                .circuitMeta(4)
                .output(dust, Inconel718, 11)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .fluidInputs(DepletedUraniumHexafluoride.getFluid(1000))
                .fluidInputs(Water.getFluid(2000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .output(dust, DepletedUraniumDioxide, 3)
                .fluidOutputs(HydrofluoricAcid.getFluid(6000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dust, HighEnrichedUraniumDioxide, 1)
                .input(dust, DepletedUraniumDioxide, 19)
                .circuitMeta(1)
                .output(dust, LEU235, 20)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dust, LowEnrichedUraniumDioxide, 1) // Assuming 20% enrichment
                .input(dust, DepletedUraniumDioxide, 3)
                .circuitMeta(1)
                .output(dust, LEU235, 4)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dust, HighEnrichedUraniumDioxide, 1)
                .input(dust, DepletedUraniumDioxide, 4)
                .circuitMeta(2)
                .output(dust, HEU235, 5)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(VA[ULV])
                .input(dust, Plutonium239)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, FissilePlutoniumDioxide, 3)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(VA[ULV])
                .input(dust, Plutonium241)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, FissilePlutoniumDioxide, 3)
                .buildAndRegister();

        // U/Pu extraction

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(VA[LV])
                .input(ring, Titanium, 2)
                .input(stick, Titanium, 16)
                .output(SCMetaItems.ANODE_BASKET)
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(800).EUt(VA[EV])
                .notConsumable(SCMetaItems.ANODE_BASKET)
                .notConsumable(Salt.getFluid(1000))
                .input(fuelPelletDepleted, LEU235)
                .output(dustSpentFuel, LEU235)
                .output(dustBredFuel, LEU235)
                .chancedOutput(dustFissionByproduct, LEU235, 633, 0)
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(800).EUt(VA[EV])
                .notConsumable(SCMetaItems.ANODE_BASKET)
                .notConsumable(Salt.getFluid(1000))
                .input(fuelPelletDepleted, HEU235)
                .output(dustSpentFuel, HEU235)
                .output(dustBredFuel, HEU235)
                .chancedOutput(dustFissionByproduct, HEU235, 821, 0)
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(800).EUt(VA[EV])
                .notConsumable(SCMetaItems.ANODE_BASKET)
                .notConsumable(Salt.getFluid(1000))
                .input(fuelPelletDepleted, LowGradeMOX)
                .output(dustSpentFuel, LowGradeMOX)
                .output(dustBredFuel, LowGradeMOX)
                .chancedOutput(dustFissionByproduct, LowGradeMOX, 565, 0)
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(800).EUt(VA[EV])
                .notConsumable(SCMetaItems.ANODE_BASKET)
                .notConsumable(Salt.getFluid(1000))
                .input(fuelPelletDepleted, HighGradeMOX)
                .output(dustSpentFuel, HighGradeMOX)
                .output(dustBredFuel, HighGradeMOX)
                .chancedOutput(dustFissionByproduct, HighGradeMOX, 1141, 0)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dustSpentFuel, LEU235, 1)
                .fluidInputs(HydrofluoricAcid.getFluid(4000))
                .fluidInputs(Fluorine.getFluid(2000))
                .fluidOutputs(UraniumHexafluoride.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dustSpentFuel, HEU235, 1)
                .fluidInputs(HydrofluoricAcid.getFluid(4000))
                .fluidInputs(Fluorine.getFluid(2000))
                .fluidOutputs(EnrichedUraniumHexafluoride.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dustSpentFuel, LowGradeMOX, 1)
                .fluidInputs(HydrofluoricAcid.getFluid(4000))
                .fluidInputs(Fluorine.getFluid(2000))
                .fluidOutputs(DepletedUraniumHexafluoride.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dustSpentFuel, HighGradeMOX, 1)
                .fluidInputs(HydrofluoricAcid.getFluid(4000))
                .fluidInputs(Fluorine.getFluid(2000))
                .fluidOutputs(DepletedUraniumHexafluoride.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dustBredFuel, LEU235)
                .chancedOutput(dust, Plutonium239, 282, 0)
                .chancedOutput(dust, Plutonium240, 132, 0)
                .chancedOutput(dust, Plutonium241, 84, 0)
                .chancedOutput(dust, Neptunium239, 18, 0)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dustBredFuel, HEU235)
                .chancedOutput(dust, Plutonium239, 235, 0)
                .chancedOutput(dust, Plutonium240, 110, 0)
                .chancedOutput(dust, Plutonium241, 70, 0)
                .chancedOutput(dust, Neptunium239, 15, 0)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dustBredFuel, LowGradeMOX)
                .chancedOutput(dust, Plutonium240, 165, 0)
                .chancedOutput(dust, Plutonium241, 5, 0)
                .chancedOutput(dust, Neptunium239, 15, 0)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dustBredFuel, HighGradeMOX)
                .chancedOutput(dust, Plutonium240, 724, 0)
                .chancedOutput(dust, Plutonium241, 192, 0)
                .chancedOutput(dust, Plutonium242, 59, 0)
                .chancedOutput(dust, Neptunium239, 3, 0)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dustFissionByproduct, LEU235)
                .chancedOutput(dust, Zirconium, 1645, 0)
                .chancedOutput(dust, Molybdenum, 1169, 0)
                .chancedOutput(dust, Neodymium, 1030, 0)
                .chancedOutput(dust, Lead, 659, 0)
                .chancedOutput(dust, Ruthenium, 609, 0)
                .chancedOutput(dust, Technetium, 297, 0)
                .fluidOutputs(Krypton.getFluid(16), Xenon.getFluid(111), Radon.getFluid(125))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dustFissionByproduct, HEU235)
                .chancedOutput(dust, Zirconium, 1645, 0)
                .chancedOutput(dust, Molybdenum, 1182, 0)
                .chancedOutput(dust, Neodymium, 1031, 0)
                .chancedOutput(dust, Ruthenium, 600, 0)
                .chancedOutput(dust, Technetium, 300, 0)
                .chancedOutput(dust, Yttrium, 211, 0)
                .fluidOutputs(Krypton.getFluid(16), Xenon.getFluid(110), Radon.getFluid(129))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dustFissionByproduct, LowGradeMOX)
                .chancedOutput(dust, Neodymium, 1015, 0)
                .chancedOutput(dust, Molybdenum, 937, 0)
                .chancedOutput(dust, Zirconium, 863, 0)
                .chancedOutput(dust, Palladium, 738, 0)
                .chancedOutput(dust, Bismuth, 300, 0)
                .chancedOutput(dust, Tellurium, 188, 0)
                .fluidOutputs(Krypton.getFluid(6), Xenon.getFluid(126), Radon.getFluid(118))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(dustFissionByproduct, HighGradeMOX)
                .chancedOutput(dust, Neodymium, 1020, 0)
                .chancedOutput(dust, Molybdenum, 937, 0)
                .chancedOutput(dust, Zirconium, 863, 0)
                .chancedOutput(dust, Samarium, 319, 0)
                .chancedOutput(dust, Tellurium, 187, 0)
                .chancedOutput(dust, Promethium, 119, 0)
                .fluidOutputs(Krypton.getFluid(6), Xenon.getFluid(126), Radon.getFluid(114))
                .buildAndRegister();

        // Radon from uranium bearing ores

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(crushed, Uraninite)
                .fluidInputs(DilutedHydrochloricAcid.getFluid(100))
                .output(crushedPurified, Uraninite)
                .fluidOutputs(RadonRichGasMixture.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(200).EUt(VA[LV])
                .input(crushed, Pitchblende)
                .fluidInputs(DilutedHydrochloricAcid.getFluid(150))
                .output(crushedPurified, Pitchblende)
                .fluidOutputs(RadonRichGasMixture.getFluid(1500))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(1000).EUt(VHA[HV])
                .fluidInputs(RadonRichGasMixture.getFluid(3000))
                .fluidOutputs(Radon.getFluid(1000))
                .fluidOutputs(Helium.getFluid(2000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[MV])
                .input(plate, Zircaloy, 4)
                .input(spring, Inconel718, 1)
                .input(round, StainlessSteel, 2)
                .output(SCMetaItems.FUEL_CLADDING)
                .buildAndRegister();

        ModHandler.addShapedRecipe(true, "fission_reactor", SCMetaTileEntities.FISSION_REACTOR.getStackForm(), "CSC",
                "RHR", "CWC",
                'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.EV),
                'H', SCMetaBlocks.FISSION_CASING.getItemVariant(BlockFissionCasing.FissionCasingType.REACTOR_VESSEL),
                'S', SENSOR.getIngredient(GTValues.EV),
                'R', new UnificationEntry(OrePrefix.rotor, Materials.Steel),
                'W', CABLE_QUAD.getIngredient(GTValues.EV));

        if (SCConfigHolder.misc.enableHX) {
            ModHandler.addShapedRecipe(true, "heat_exchanger", SCMetaTileEntities.HEAT_EXCHANGER.getStackForm(),
                    "FFF",
                    "PCP", "FFF",
                    'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.HV),
                    'P', new UnificationEntry(OrePrefix.pipeLargeFluid, StainlessSteel),
                    'F', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID));
        }

        ModHandler.addShapedRecipe(true, "gas_centrifuge", SCMetaTileEntities.GAS_CENTRIFUGE.getStackForm(), "FFF",
                "WRW", "CCC",
                'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.EV),
                'R', new UnificationEntry(OrePrefix.rotor, Materials.Titanium),
                'F', MetaBlocks.METAL_CASING.getItemVariant(PTFE_INERT_CASING),
                'W', CABLE_QUAD.getIngredient(GTValues.EV));

        ModHandler.addShapedRecipe(true, "spent_fuel_pool", SCMetaTileEntities.SPENT_FUEL_POOL.getStackForm(), "PFP",
                "PCP", "PFP",
                'C', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.LV),
                'F', MetaBlocks.METAL_CASING.getItemVariant(STAINLESS_CLEAN),
                'P', new UnificationEntry(OrePrefix.plate, Materials.StainlessSteel));
    }
}
