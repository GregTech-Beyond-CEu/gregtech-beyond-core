package gtb.api.recipes;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import scala.sys.process.ProcessBuilderImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GTBRecipeMaps {

    public static final RecipeMap<NoEnergyRecipeBuilder> SOLAR_THERMAL_CONCENTRATOR_RECIPES = new RecipeMap<>(
            "solar_thermal_concentrator_recipes",
            1, 0, 1, 1, new NoEnergyRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ASTRO_MINING_STATION_RECIPES = new RecipeMap<>(
            "astro_mining_station_recipes",
            4, 9, 1, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> VACUUM_FURNACE_RECIPES = new RecipeMap<>(
            "vacuum_furnace_recipes",
            2, 1, 2, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> BACTERIAL_VAT_RECIPES = new RecipeMap<>("bacterial_vat_recipes",
            4, 2, 6, 4, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> BEDROCK_PUMP_RECIPES = new RecipeMap<>("bedrock_pump_recipes",
            1, 0, 0, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> BLACK_HOLE_COMPRESSOR_RECIPES = new RecipeMap<>(
            "black_hole_compressor_recipes",
            2, 1, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CATALYTIC_REFORMATION_UNIT_RECIPES = new RecipeMap<>(
            "catalytic_reformation_unit_recipes",
            1, 1, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> SOLID_FUEL_GENERATOR_RECIPES = new RecipeMap<>(
            "solid_fuel_generator_recipes",
            2, 0, 1, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> COKER_RECIPES = new RecipeMap<>("coker_recipes",
            1, 1, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> VACUUM_DISTILLATION_TOWER_RECIPES = new RecipeMap<>(
            "vacuum_distillation_tower_recipes",
            1, 3, 1, 5, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> DILUTION_TANK_RECIPES = new RecipeMap<>("dilution_tank_recipes",
            1, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> FERMENTATION_VAT_RECIPES = new RecipeMap<>(
            "fermentation_vat_recipes",
            2, 1, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> NEUTRON_ACCELERATOR_RECIPES = new RecipeMap<>(
            "neutron_accelerator_recipes",
            2, 3, 2, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> FISCHER_TROPSCH_REACTOR_RECIPES = new RecipeMap<>(
            "fischer_tropsch_reactor_recipes",
            2, 1, 2, 9, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CRYSTALLIZATION_RECIPES = new RecipeMap<>(
            "crystallization_recipes",
            3, 3, 3, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> DEHYDRATOR_RECIPES = new RecipeMap<>(
            "dehydrator_recipes",
            3, 6, 3, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> MICROSCOPE_RECIPES = new RecipeMap<>(
            "microscope_recipes",
            1, 9, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> BIO_REACTOR_RECIPES = new RecipeMap<>(
            "bio_reactor_recipes",
            3, 3, 5, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ELECTROLYTIC_CELL_RECIPES = new RecipeMap<>(
            "electrolytic_cell_recipes",
            4, 4, 2, 6, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ROTARY_EVAPORATOR_RECIPES = new RecipeMap<>(
            "rotary_evaporator_recipes",
            1, 2, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> TEXTILE_FACTORY_RECIPES = new RecipeMap<>(
            "textile_factory_recipes",
            4, 1, 2, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ION_IMPLANTER_RECIPES = new RecipeMap<>("ion_implanter_recipes",
            4, 1, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CVD_UNIT_RECIPES = new RecipeMap<>("cvd_unit_recipes",
            3, 2, 2, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ROASTER_RECIPES = new RecipeMap<>("roaster_recipes",
            2, 4, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ENGRAVING_UNIT_RECIPES = new RecipeMap<>(
            "engraving_unit_recipes",
            2, 4, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CHEMICAL_PLANT_RECIPES = new RecipeMap<>(
            "chemical_plant_recipes",
            6, 4, 5, 4, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CLARIFIER_RECIPES = new RecipeMap<>("clarifier_recipes",
            1, 6, 1, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> FROTH_FLOTATION_UNIT_RECIPES = new RecipeMap<>(
            "froth_flotation_unit_recipes",
            4, 4, 4, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> DIGESTER_RECIPES = new RecipeMap<>("digester_recipes",
            4, 4, 4, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> MOLECULAR_BEAM_RECIPES = new RecipeMap<>(
            "molecular_beam_recipes_recipes",
            4, 1, 2, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> HIGH_TEMP_DISTILLATION_RECIPES = new RecipeMap<>(
            "high_temp_distillation_recipes",
            1, 1, 1, 12, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> POLYMERIZATION_TANK_RECIPES = new RecipeMap<>(
            "polymerization_tank_recipes",
            2, 2, 4, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> THERMAL_PRESS_RECIPES = new RecipeMap<>("thermal_press_recipes",
            4, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CONDENSATION_UNIT_RECIPES = new RecipeMap<>(
            "condensation_unit_recipes",
            1, 1, 2, 4, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CRYOGENIC_DISTILLATION_PLANT_RECIPES = new RecipeMap<>(
            "cryogenic_distillation_plant_recipes",
            1, 1, 9, 9, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> PRESSURE_SWING_ADSORBER_RECIPES = new RecipeMap<>(
            "pressure_swing_adsorber_recipes",
            1, 1, 1, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> RARE_GAS_STRIPPING_COLUMN_RECIPES = new RecipeMap<>(
            "rare_gas_stripping_column_recipes",
            0, 0, 3, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> PHASE_SEPARATOR_RECIPES = new RecipeMap<>(
            "phase_separator_recipes",
            1, 1, 1, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> DISSOLUTION_TANK_RECIPES = new RecipeMap<>(
            "dissolution_tank_recipes",
            1, 4, 2, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> DRYER_RECIPES = new RecipeMap<>("dryer_recipes",
            1, 4, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> BURNER_REACTOR_RECIPES = new RecipeMap<>(
            "burner_reactor_recipes",
            2, 2, 2, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> DILUTION_REFRIGERATOR_RECIPES = new RecipeMap<>(
            "dilution_refrigerator_recipes",
            2, 2, 2, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> SINTERING_OVEN_RECIPES = new RecipeMap<>(
            "sintering_oven_recipes",
            2, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> GRAVITY_SEPARATOR_RECIPES = new RecipeMap<>(
            "gravity_separator_recipes", 4, 4, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ELECTRON_BEAM_LITHOGRAPHER_RECIPES = new RecipeMap<>(
            "electron_beam_lithographer_recipes", 3, 1, 1, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ENZYMATIC_HYDROLYSIS_RECIPES = new RecipeMap<>(
            "enzymatic_hydrolysis_recipes", 1, 1, 1, 9, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> VACUUM_EJECTION_RECIPES = new RecipeMap<>(
            "vacuum_ejection_recipes", 2, 1, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CURING_OVEN_RECIPES = new RecipeMap<>(
            "curing_oven_recipes", 2, 1, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<KevRecipeBuilder> PARTICLE_ACCELERATOR_RECIPES = new RecipeMap<>(
            "particle_accelerator_recipes", 2, 1, 1, 1, new KevRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> VIS_BREAKER_RECIPES = new RecipeMap<>(
            "vis_breaker_recipes", 1, 1, 1, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> GRAVITY_SETTLER_RECIPES = new RecipeMap<>(
            "gravity_settler_recipes", 1, 1, 1, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> SEALED_REACTION_CHAMBER_RECIPES = new RecipeMap<>(
            "sealed_reaction_chamber_recipes", 1, 1, 1, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> VACUUM_METALIZER_RECIPES = new RecipeMap<>(
            "vacuum_metalizer_recipes", 4, 1, 0, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> OXIDATION_FURNACE_RECIPES = new RecipeMap<>(
            "oxidation_furnace_recipes", 2, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ION_EXCHANGE_RECIPES = new RecipeMap<>(
            "ion_exchange_recipes", 2, 2, 1, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> UV_LIGHT_RECIPES = new RecipeMap<>(
            "uv_light_recipes", 3, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> FLUID_COMPRESSOR_RECIPES = new RecipeMap<>(
            "fluid_compressor_recipes", 1, 1, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> PLASMA_ETCHER_RECIPES = new RecipeMap<>(
            "plasma_etcher_recipes", 3, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> MOCVD_UNIT_RECIPES = new RecipeMap<>(
            "mocvd_unit_recipes", 4, 2, 4, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> HIGH_TEMP_POLYMERIZATION_TANK_RECIPES = new RecipeMap<>(
            "high_temp_polymerization_tank_recipes",
            2, 2, 4, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ELECTROWINNING_CELL = new RecipeMap<>(
            "electrowinning_cell",
            2, 2, 4, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> COLD_TOWER = new RecipeMap<>(
            "cold_tower",
            1, 1, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> HOT_TOWER = new RecipeMap<>(
            "hot_tower",
            1, 1, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> METEORITE_PREDICTION_CALCULATION = new RecipeMap<>(
            "meteorite_prediction_calculation",
            4, 2, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> METEORITE_MINING = new RecipeMap<>(
            "meteorite_mining",
            1, 6, 0, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> FRACTIONAL_DISTILLATION_RECIPES = new RecipeMap<>(
            "fractional_distillation_recipes",
            1, 1, 2, 8, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> SPACE_COMPONENT_ASSEMBLING = new RecipeMap<>(
            "space_component_assembling",
            6, 1, 3, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> BEAM_SHRINKING_RECIPES = new RecipeMap<>(
            "beam_shrinking_recipes",
            2, 1, 0, 0, new SimpleRecipeBuilder(), false)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressWidget.MoveType.VERTICAL)
                    .setSlotOverlay(false, false, false, GuiTextures.HAMMER_OVERLAY);

    public static final RecipeMap<SimpleRecipeBuilder> INSCRIBER_RECIPES = new RecipeMap<>(
            "inscriber_recipes",
            3, 1, 0, 0, new SimpleRecipeBuilder(), false)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressWidget.MoveType.VERTICAL)
                    .setSlotOverlay(false, false, false, GuiTextures.HAMMER_OVERLAY);

    public static final RecipeMap<NoEnergyRecipeBuilder> BLOOMERY_RECIPES = new RecipeMap<>(
            "bloomery_recipes",
            2, 3, 0, 0, new NoEnergyRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> TUBE_IN_TUBE_REACTOR_RECIPES = new RecipeMap<>(
            "tube_in_tube_reactor_recipes",
            2, 3, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ADSORPTION_TOWER = new RecipeMap<>(
            "adsorption_tower",
            1, 0, 3, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> REACTIVE_ION_ETCHER_RECIPES = new RecipeMap<>(
            "reactive_ion_etcher",
            1, 1, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> PVD_UNIT_RECIPES = new RecipeMap<>(
            "pvd_unit_recipes",
            4, 1, 3, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> BLENDER_RECIPES = new RecipeMap<>(
            "blender_recipes",
            3, 1, 4, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> SPINCOATER_RECIPES = new RecipeMap<>(
            "spincoater_recipes",
            2, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> AUTOMATIC_WIRE_BONDER = new RecipeMap<>(
            "automatic_wire_bonder_recipes",
            2, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> SONICATOR_RECIPES = new RecipeMap<>(
            "sonicator",
            6, 4, 4, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ELECTRON_BEAM_LITHOGRAPHER_RECIPES_MULTI = new RecipeMap<>(
            "electron_beam_lithographer_recipes_multi", 4, 1, 2, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> HIGH_VACUUM_EVAPORATION_RECIPES = new RecipeMap<>(
            "high_vacuum_evaporation_recipes", 2, 1, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ATOMIC_LAYER_DEPOSITION_RECIPES = new RecipeMap<>(
            "atomic_layer_deposition", 4, 1, 2, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> DRYING_COLUMN_RECIPES = new RecipeMap<>(
            "drying_column", 1, 1, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> BATCH_REACTOR_RECIPES = new RecipeMap<>(
            "batch_reactor", 3, 3, 3, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> TRICKLE_BED_REACTOR_RECIPES = new RecipeMap<>(
            "trickle_bed_reactor", 3, 3, 3, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CONTINOUS_STIRRING_TANK_REACTOR_RECIPES = new RecipeMap<>(
            "continous_stirring_tank_reactor_recipes", 4, 3, 4, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> FLUIDIZED_BED_REACTOR_RECIPES = new RecipeMap<>(
            "fbr_recipes", 4, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> FLASH_CHROMATOGRAPHY_RECIPES = new RecipeMap<>(
            "flash_chromatography_recipes", 1, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> TUBE_FURNACE_RECIPES = new RecipeMap<>(
            "tube_furnace", 4, 4, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> PRECISION_LASER_RECIPES = new RecipeMap<>(
            "precision_laser", 4, 2, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ROCKET_LAUNCH_PAD_RECIPES = new RecipeMap<>(
            "rocket_launch_pad", 4, 2, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ROCKET_ASSEMBLY_RECIPES = new RecipeMap<>(
            "rocket_assembly_recipes", 16, 1, 3, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ALLOY_KILN = new RecipeMap<>(
            "alloy_kiln", 2, 2, 2, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CRUCIBLE_RECIPES = new RecipeMap<>(
            "crucible_recipes", 2, 2, 2, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ZONE_REFINER_RECIPES = new RecipeMap<>(
            "zone_refiner_recipes", 2, 2, 2, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ADVANCED_CRUCIBLE_RECIPES = new RecipeMap<>(
            "advanced_crucible", 2, 1, 1, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> VACUUM_DESICCATOR_RECIPES = new RecipeMap<>(
            "vacuum_desiccator_recipes", 2, 1, 2, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> KIT_ASSEMBLER = new RecipeMap<>(
            "kit_assembler", 3, 1, 1, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> INDUCTION_FURNACE_RECIPES = new RecipeMap<>(
            "induction_furnace", 9, 1, 3, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> THERMAL_DECOMPOSITION_RECIPES = new RecipeMap<>(
            "thermal_decomposition", 2, 2, 1, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ELECTROCHEMICAL_CELL_RECIPES = new RecipeMap<>(
            "electrochemical_recipes", 3, 2, 4, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ELECTRON_BEAM_MELTING_FURNACE_RECIPES = new RecipeMap<>(
            "electron_beam_melting_furnace_recipes", 4, 2, 4, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> GATE_ASSEMBLER_RECIPES = new RecipeMap<>(
            "gate_assembler_recipes", 6, 1, 2, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CNC_RECIPES = new RecipeMap<>(
            "cnc_recipes", 2, 1, 2, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> STIRRED_TANK_REACTOR_RECIPES = new RecipeMap<>(
            "stirred_tank_reactor_recipes", 3, 2, 3, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> MIXER_SETTLER_REACTOR_RECIPES = new RecipeMap<>(
            "mixer_settler_reactor_recipes", 4, 2, 3, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ROTARY_KILN_RECIPES = new RecipeMap<>(
            "rotary_kiln_recipes", 4, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> FIXED_BED_TUBULAR_REACTOR_RECIPES = new RecipeMap<>(
            "fixed_bed_tubular_reactor_recipes", 3, 2, 3, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> PLUG_FLOW_REACTOR_RECIPES = new RecipeMap<>(
            "plug_flow_reactor_recipes", 4, 3, 3, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> BUBBLE_COLUMN_REACTOR_RECIPES = new RecipeMap<>(
            "bubble_column_reactor_recipes", 3, 2, 3, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> FIXED_BED_REACTOR_RECIPES = new RecipeMap<>(
            "fixed_bed_reactor", 3, 2, 3, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> COMBUSTION_FURNACE_RECIPES = new RecipeMap<>(
            "combustion_furnace", 3, 2, 3, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> REACTION_FURNACE_RECIPES = new RecipeMap<>(
            "reaction_furnace", 3, 3, 3, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> HOLLOW_FIBER_CONTACTOR_RECIPES = new RecipeMap<>(
            "hollow_fiber_contactor", 2, 3, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> BALL_MILL_RECIPES = new RecipeMap<>(
            "ball_mill", 2, 3, 1, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ELUTION_COLUMN_RECIPES = new RecipeMap<>(
            "elution_column", 3, 3, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CATALYTIC_COMBUSTOR_RECIPES = new RecipeMap<>(
            "catalytic_combustor", 3, 3, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> REGENERATION_UNIT_RECIPES = new RecipeMap<>(
            "regeneration_unit", 3, 3, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> CATALYTIC_CONVERTER_RECIPES = new RecipeMap<>(
            "catalytic_converter", 2, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> FINAL_POLYCONDENSATION_REACTOR_RECIPES = new RecipeMap<>(
            "final_polycondensation_reactor", 2, 2, 3, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> VAR_FURNACE_RECIPES = new RecipeMap<>(
            "var_furnace_recipes", 4, 2, 3, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> BRIDGMAN_FURNACE_RECIPES = new RecipeMap<>(
            "bridgman_furnace", 4, 2, 2, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ELECTRIC_ARC_FURNACE_RECIPES = new RecipeMap<>(
            "electric_arc_furnace_recipes", 6, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> INGOT_CASTING_RECIPES = new RecipeMap<>(
            "ingot_casting_recipes", 1, 1, 2, 0, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> PELLETIZER_RECIPES = new RecipeMap<>(
            "pelletizer_recipes", 3, 2, 2, 1, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> INJECTION_MOLDING_RECIPES = new RecipeMap<>(
            "injection_molding_recipes", 3, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> COAGULATOR_RECIPES = new RecipeMap<>(
            "coagulator", 4, 2, 2, 3, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ELECTRIC_GLASS_MELTING_RECIPES = new RecipeMap<>(
            "electric_glass_melting_recipes", 2, 2, 2, 2, new SimpleRecipeBuilder(), false);

    public static final RecipeMap<SimpleRecipeBuilder> ISOSTATIC_PRESS_RECIPES = new RecipeMap<>(
            "isostatic_press", 2, 2, 1, 1, new SimpleRecipeBuilder(), false);





}
