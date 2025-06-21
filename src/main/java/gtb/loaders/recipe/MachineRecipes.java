package gtb.loaders.recipe;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.MetaBlocks.MACHINE_CASING;
import static gregtech.common.items.MetaItems.*;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.*;
import static gtb.api.unification.materials.GTBMaterials.*;
import static gtb.common.block.blocks.GTBMultiblockCasing.CasingType.*;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.*;

import gtb.common.metatileentities.GTBMetaTileEntities;

public class MachineRecipes {

    public static void init() {
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, StainlessSteel, 4)
                .input(circuit, MarkerMaterials.Tier.EV, 2)
                .input(ELECTRIC_MOTOR_IV, 4)
                .input(ELECTRIC_PUMP_IV, 4)
                .input(frameGt, StainlessSteel, 2)
                .input(pipeLargeFluid, StainlessSteel, 4)
                .input(gear, Steel, 2)
                .outputs(GTBMetaTileEntities.BACTERIAL_VAT.getStackForm(1))
                .duration(1200)
                .EUt(800)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(foil, StainlessSteel, 4)
                .input(frameGt, StainlessSteel)
                .input(circuit, MarkerMaterials.Tier.EV, 2)
                .input(ELECTRIC_PUMP_EV, 2)
                .outputs(GTBMetaTileEntities.DILUTION_TANK.getStackForm(1))
                .duration(1200)
                .EUt(800)
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(foil, Polybenzimidazole, 32)
                .input(plate, Grisium, 32)
                .input(stickLong, Cupronickel, 32)
                .input(pipeLargeFluid, Polytetrafluoroethylene, 8)
                .input(MetaBlocks.BOILER_CASING
                        .getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE).getBlock())
                .input(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING).getBlock())
                .input(circuit, MarkerMaterials.Tier.IV, 4)
                .input(ELECTRIC_MOTOR_IV, 4)
                .fluidInputs(Polytetrafluoroethylene.getFluid(2304))
                .fluidInputs(SolderingAlloy.getFluid(2304))
                .output(GTBMetaTileEntities.CHEMICAL_PLANT)
                .duration(1200)
                .EUt(800)
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .fluidInputs(Polytetrafluoroethylene.getFluid(2304))
                .fluidInputs(SolderingAlloy.getFluid(2304))
                .input(circuit, MarkerMaterials.Tier.IV, 4)
                .input(foil, Polybenzimidazole, 32)
                .input(frameGt, Titanium, 16)
                .input(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN).getBlock())
                .output(GTBMetaTileEntities.ENGRAVING_UNIT)
                .duration(120)
                .EUt(800)
                .buildAndRegister();

        ModHandler.addShapedRecipe("rare_gas_stripping_unit_machine",
                GTBMetaTileEntities.RARE_GAS_STRIPPING_COLUMN.getStackForm(),
                "PCP", "UMU", "PCP",
                'M', BlockMachineCasing.MachineCasingType.HV,
                'U', ELECTRIC_PUMP_HV.getStackForm(),
                'P', new UnificationEntry(circuit, MarkerMaterials.Tier.HV),
                'C', FLUID_FILTER.getStackForm());

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(circuit, MarkerMaterials.Tier.LuV, 4)
                .input(frameGt, TungstenSteel, 2)
                .input(ELECTRIC_PUMP_LuV, 4)
                .input(plate, TungstenSteel, 4)
                .input(ROBOT_ARM_LuV)
                .output(GTBMetaTileEntities.BEDROCK_PUMP)
                .duration(200)
                .EUt(7800)
                .buildAndRegister();

        ModHandler.addShapedRecipe("condensation_unit_machine", GTBMetaTileEntities.CONDENSATION_UNIT.getStackForm(),
                "PCP", "CMC", "PCP",
                'M', BlockMachineCasing.MachineCasingType.HV,
                'P', new UnificationEntry(plate, Aluminium),
                'C', ELECTRIC_PUMP_HV.getStackForm());

        ModHandler.addShapedRecipe("dilution_tank_machine", GTBMetaTileEntities.DILUTION_TANK.getStackForm(),
                "PHP", "CMC", "PRP",
                'M', BlockMachineCasing.MachineCasingType.EV,
                'P', ELECTRIC_PUMP_EV.getStackForm(),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'R', ROBOT_ARM_EV.getStackForm(),
                'H', ELECTRIC_MOTOR_EV.getStackForm());

        ModHandler.addShapedRecipe("electrolytic_cell_machine", GTBMetaTileEntities.ELECTROLYTIC_CELL.getStackForm(),
                "PPP", "CMC", "PPP",
                'M', BlockMachineCasing.MachineCasingType.MV,
                'P', new UnificationEntry(plate, BlackSteel),
                'C', ELECTRIC_PUMP_MV.getStackForm());

        ModHandler.addShapedRecipe("fermentation_vat_machine", GTBMetaTileEntities.FERMENTATION_VAT.getStackForm(),
                "PCP", "CMC", "RWR",
                'M', BlockMachineCasing.MachineCasingType.MV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.MV),
                'P', ELECTRIC_PUMP_MV.getStackForm(),
                'R', ROBOT_ARM_MV.getStackForm(),
                'W', new UnificationEntry(wireGtSingle, Aluminium));

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(circuit, MarkerMaterials.Tier.EV, 2)
                .input(MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.HV).getBlock())
                .inputs(ELECTRIC_PUMP_HV.getStackForm(2))
                .input(frameGt, StainlessSteel, 2)
                .input(cableGtQuadruple, BlackSteel, 4)
                .outputs(GTBMetaTileEntities.VACUUM_DISTILLATION_TOWER.getStackForm())
                .duration(200)
                .EUt(180)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(stick, Steel, 6)
                .input(circuit, MarkerMaterials.Tier.EV, 4)
                .input(MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.HV).getBlock())
                .inputs(ELECTRIC_PUMP_HV.getStackForm(2))
                .circuitMeta(1)
                .input(frameGt, BlackSteel)
                .outputs(GTBMetaTileEntities.COKER.getStackForm())
                .duration(1200)
                .EUt(60)
                .buildAndRegister();

        ModHandler.addShapedRecipe("solar_thermal_concentrate_machine",
                GTBMetaTileEntities.SOLAR_THERMAL_CONCENTRATOR.getStackForm(),
                "PLP", "BMB", "PBP",
                'B', new UnificationEntry(pipeSmallFluid, Bronze),
                'L', BlockMetalCasing.MetalCasingType.BRONZE_BRICKS,
                'M', new UnificationEntry(frameGt, Bronze),
                'P', new UnificationEntry(plate, Bronze));

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Steel, 4)
                .input(stickLong, Steel, 2)
                .input(VOLTAGE_COIL_LV, 4)
                .input(circuit, MarkerMaterials.Tier.MV, 2)
                .output(GTBMetaTileEntities.SINTERING_OVEN)
                .duration(200)
                .EUt(70)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .fluidInputs(TungstenSteel.getFluid(512))
                .input(MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.IV).getBlock())
                .inputs(ELECTRIC_PUMP_IV.getStackForm(2))
                .input(plateDense, Steel, 2)
                .input(rotor, TungstenSteel, 4)
                .input(circuit, MarkerMaterials.Tier.IV, 4)
                .output(GTBMetaTileEntities.ENGRAVING_UNIT)
                .duration(200)
                .EUt(700)
                .buildAndRegister();

        ModHandler.addShapedRecipe("pressure_swing_adsorber_machine",
                GTBMetaTileEntities.PRESSURE_SWING_ADSORBER.getStackForm(),
                "PCP", "ZMZ", "PCP",
                'M', BlockMachineCasing.MachineCasingType.MV,
                'Z', ELECTRIC_PUMP_MV,
                'C', FLUID_FILTER,
                'P', new UnificationEntry(plate, Aluminium));

        ModHandler.addShapedRecipe("thermal_press_machine", GTBMetaTileEntities.THERMAL_PRESS.getStackForm(),
                "SPS", "ZMZ", "SPS",
                'M', BlockMachineCasing.MachineCasingType.LV,
                'Z', ELECTRIC_PUMP_LV,
                'S', new UnificationEntry(plate, Steel),
                'P', ELECTRIC_PUMP_LV);

        ModHandler.addShapedRecipe("polymerization_tank_machine",
                GTBMetaTileEntities.POLYMERIZATION_TANK.getStackForm(),
                "CPC", "PMP", "RPR",
                'M', BlockMachineCasing.MachineCasingType.LV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.MV),
                'R', new UnificationEntry(rotor, Steel),
                'P', ELECTRIC_PUMP_LV);

        ModHandler.addShapedRecipe("clarifier_machine", GTBMetaTileEntities.CLARIFIER.getStackForm(),
                "PCP", "UMU", "PCP",
                'M', BlockMachineCasing.MachineCasingType.EV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.LV),
                'P', ELECTRIC_PISTON_EV.getStackForm(),
                'U', ELECTRIC_PUMP_EV.getStackForm());

        ModHandler.addShapedRecipe("cryogenic_distillation_plant_machine",
                GTBMetaTileEntities.CRYOGENIC_DISTILLATION_PLANT.getStackForm(),
                "PCP", "UMU", "PCP",
                'M', BlockMachineCasing.MachineCasingType.HV,
                'U', new UnificationEntry(circuit, MarkerMaterials.Tier.EV),
                'P', ELECTRIC_PUMP_EV.getStackForm(),
                'C', ELECTRIC_MOTOR_EV.getStackForm());

        ModHandler.addShapedRecipe("digester_machine", GTBMetaTileEntities.DIGESTER.getStackForm(),
                "TTT", "PMP", "TTT",
                'M', BlockMachineCasing.MachineCasingType.IV,
                'P', ELECTRIC_PISTON_EV.getStackForm(),
                'T', new UnificationEntry(plate, Titanium));

        ModHandler.addShapedRecipe("stc_machine", GTBMetaTileEntities.SOLAR_THERMAL_CONCENTRATOR.getStackForm(),
                "PBP", "SFS", "PSP",
                'F', new UnificationEntry(frameGt, Bronze),
                'S', new UnificationEntry(pipeSmallFluid, Steel),
                'B', BlockMachineCasing.MachineCasingType.ULV,
                'P', new UnificationEntry(plate, Bronze));

        ModHandler.addShapedRecipe("ffu_machine", GTBMetaTileEntities.FROTH_FLOTATION_UNIT.getStackForm(),
                "CPC", "PMP", "CPC",
                'M', BlockMachineCasing.MachineCasingType.EV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.EV),
                'P', ELECTRIC_PUMP_EV.getStackForm());

        ModHandler.addShapedRecipe("vac_furnace_machine", GTBMetaTileEntities.VACUUM_FURNACE.getStackForm(),
                "PCP", "CMC", "PCP",
                'M', VACUUM_FURNACE_CASING,
                'P', new UnificationEntry(plate, Steel),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.MV));

        registerMachineRecipe(GTBMetaTileEntities.ROTARY_EVAPORATOR,
                "MRM", "CMC", "PRP",
                'M', MOTOR,
                'R', ROTOR,
                'C', CIRCUIT,
                'P', PUMP,
                'M', HULL);

        registerMachineRecipe(GTBMetaTileEntities.ROASTER,
                "SCS", "UMU", "PCP",
                'C', CABLE,
                'S', MOTOR,
                'U', CIRCUIT,
                'M', HULL,
                'P', PISTON);

        registerMachineRecipe(GTBMetaTileEntities.ION_IMPLANTER,
                "PEP", "CGC", "PMP",
                'C', CIRCUIT,
                'E', EMITTER,
                'G', GLASS,
                'M', HULL,
                'P', PISTON);

        registerMachineRecipe(GTBMetaTileEntities.CVD_UNIT,
                "CGG",
                "KMP",
                "CHP",
                'C', CIRCUIT,
                'H', HULL,
                'G', GLASS,
                'M', MOTOR,
                'P', PUMP,
                'K', CABLE);

        registerMachineRecipe(GTBMetaTileEntities.MICROSCOPE,
                "MSM",
                "PCP",
                "PKP",
                'P', PLATE,
                'M', MOTOR,
                'S', SENSOR,
                'K', CABLE,
                'C', HULL);

        registerMachineRecipe(GTBMetaTileEntities.DEHYDRATORS,
                "KCK",
                "TMT",
                "PRP",
                'P', PLATE,
                'R', ROBOT_ARM,
                'C', CIRCUIT,
                'K', CABLE,
                'T', MOTOR,
                'M', HULL);

        registerMachineRecipe(GTBMetaTileEntities.CRYSTALLIZERS,
                "KCK",
                "CMC",
                "RKR",
                'R', ROBOT_ARM,
                'C', CIRCUIT,
                'K', CABLE,
                'M', HULL);

        registerMachineRecipe(GTBMetaTileEntities.FLUID_COMPRESSOR,
                "PCP",
                "UMU",
                "KCK",
                'P', PUMP,
                'C', CIRCUIT,
                'K', CABLE,
                'U', PISTON,
                'M', HULL);

        registerMachineRecipe(GTBMetaTileEntities.DRYER,
                "KRK",
                "CMC",
                "PCP",
                'P', PUMP,
                'C', CIRCUIT,
                'K', CABLE,
                'R', ROTOR,
                'M', HULL);

        registerMachineRecipe(GTBMetaTileEntities.BIO_REACTOR_SINGLE,
                "PVP",
                "CMC",
                "KVK",
                'P', PUMP,
                'V', CONVEYOR,
                'C', CIRCUIT,
                'K', CABLE,
                'M', HULL);

        registerMachineRecipe(GTBMetaTileEntities.ELECTRON_BEAM_LITHOGRAPHER,
                "OEO",
                "KMK",
                "PCP",
                'E', EMITTER,
                'P', PUMP,
                'O', MOTOR,
                'C', CIRCUIT,
                'K', CABLE,
                'M', HULL);

        registerMachineRecipe(GTBMetaTileEntities.ION_EXCHANGE_UNIT,
                "KEK",
                "PMU",
                "CEC",
                'U', CONVEYOR,
                'E', EMITTER,
                'P', PUMP,
                'C', CIRCUIT,
                'K', CABLE,
                'M', HULL);

        registerMachineRecipe(GTBMetaTileEntities.UV_LIGHT,
                "KEK",
                "PMU",
                "CZC",
                'U', CONVEYOR,
                'E', EMITTER,
                'P', PUMP,
                'Z', SENSOR,
                'C', CIRCUIT,
                'K', CABLE,
                'M', HULL);

        registerMachineRecipe(GTBMetaTileEntities.PLASMA_ETCHER,
                "KEK",
                "PMP",
                "CZC",
                'E', PUMP,
                'P', CIRCUIT,
                'Z', SENSOR,
                'C', EMITTER,
                'K', CABLE,
                'M', HULL);

        registerMachineRecipe(GTBMetaTileEntities.SPACE_COMPONENT_ASSEMBLER,
                "CSC",
                "VMV",
                "CEC",
                'E', EMITTER,
                'C', CIRCUIT,
                'S', SENSOR,
                'V', CABLE,
                'M', HULL);

        registerMachineRecipe(GTBMetaTileEntities.AUTOMATIC_WIRE_BONDER,
                "MCM",
                "ALA",
                "PCP",
                'M', MOTOR,
                'C', CIRCUIT,
                'P', PISTON,
                'A', CABLE,
                'L', HULL);

        registerMachineRecipe(GTBMetaTileEntities.SPINCOATER,
                "ARA",
                "PLP",
                "CMC",
                'M', MOTOR,
                'C', CIRCUIT,
                'P', PISTON,
                'A', CABLE,
                'R', ROTOR,
                'L', HULL);

        registerMachineRecipe(GTBMetaTileEntities.CONTINOUS_STIRRING_TANK_REACTOR,
                "GRG",
                "PMP",
                "CLC",
                'L', MOTOR,
                'C', CIRCUIT,
                'P', PUMP,
                'R', ROTOR,
                'M', HULL,
                'G', GLASS);

        registerMachineRecipe(GTBMetaTileEntities.TRICKLE_BED_REACTOR,
                "UPU",
                "CMC",
                "ZPZ",
                'U', PIPE_NORMAL,
                'C', CIRCUIT,
                'P', PUMP,
                'Z', MOTOR,
                'M', HULL);

        registerMachineRecipe(GTBMetaTileEntities.BATCH_REACTOR,
                "PCP",
                "LML",
                "ZCZ",
                'C', CIRCUIT,
                'P', PUMP,
                'Z', CONVEYOR,
                'M', HULL,
                'L', CABLE);
    }
}
