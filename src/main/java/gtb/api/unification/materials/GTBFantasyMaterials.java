package gtb.api.unification.materials;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static gtb.api.unification.materials.GTBMaterials.*;
import static gtb.api.unification.materials.info.GTBMaterialFlags.*;

import gregtech.api.GTValues;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.Elements;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty;
import supercritical.api.unification.SCElements;
import supercritical.api.unification.material.properties.CoolantProperty;
import supercritical.api.unification.material.properties.FissionFuelProperty;
import supercritical.api.unification.material.properties.SCPropertyKey;

public class GTBFantasyMaterials {

    // Range 25001-25200
    private static int startId = 30800;
    private static final int END_ID = startId + 200;

    public static void init() {
        AwakenedDraconium = new Material.Builder(getMaterialsId(), gregtechId("awakened_draconium"))
                .ingot()
                .color(0xDD8639)
                .build().setFormula("AwDr", true);

        Adamantium = new Material.Builder(getMaterialsId(), gregtechId("adamantium"))
                .ingot()
                .color(0x4068A7)
                .build().setFormula("Ad", true);

        CosmicNeutronium = new Material.Builder(getMaterialsId(), gregtechId("cosmic_neutronium"))
                .ingot()
                .color(0x505050)
                .build().setFormula("SpNt", true);

        Infinity = new Material.Builder(getMaterialsId(), gregtechId("infinity"))
                .ingot()
                .color(0xEFCED9)
                .build().setFormula("If", true);

        Eternal = new Material.Builder(getMaterialsId(), gregtechId("eternal"))
                .ingot()
                .color(0x424992)
                .build().setFormula("Et*", true);


        TemporallyConstrainedAntiMatter = new Material.Builder(getMaterialsId(),
                gregtechId("temporally_constrained_anti_matter"))
                        .ingot()
                        .color(0x010101)
                        .build().setFormula("Am**", true);

        CataclysmInducedMixedMatter = new Material.Builder(getMaterialsId(),
                gregtechId("cataclysm_induced_mixed_matter"))
                        .ingot()
                        .color(0x880E4F)
                        .build().setFormula("Mm***", true);

        Pikyonium = new Material.Builder(getMaterialsId(), gregtechId("pikyonium"))
                .ingot()
                .color(0x708DBC)
                .build().setFormula("(Ni2NbAl2(Ni4Cr))8((Fe4(FeAlCr)(Fe2Ni05)10SSiC)5?4Ce3Sb2Pt2Yb(FeW)4", true);

        Tenannite = new Material.Builder(getMaterialsId(), gregtechId("tennanite"))
                .dust()
                .ore()
                .flags(GENERATE_SIFTED)
                .color(0x767676)
                .build().setFormula("Cu12As4S13", true);

        Zirkelite = new Material.Builder(getMaterialsId(), gregtechId("zirkelite"))
                .dust()
                .ore()
                .flags(GENERATE_FLOATED, GENERATE_SIFTED, GENERATE_CONCENTRATE)
                .color(0x908492)
                .build().setFormula("Ca2Th2CeZr7(TiO2)6Nb4O10", true);

        Xenotime = new Material.Builder(getMaterialsId(), gregtechId("xenotime"))
                .dust()
                .ore()
                .flags(GENERATE_FLOATED, GENERATE_SIFTED, GENERATE_CONCENTRATE)
                .color(0xF98B69)
                .build().setFormula("REE", true);

        Zircon = new Material.Builder(getMaterialsId(), gregtechId("zircon"))
                .gem()
                .dust()
                .ore()
                .flags(GENERATE_FLOATED, GENERATE_SIFTED, GENERATE_CONCENTRATE)
                .color(0x9D5453)
                .build().setFormula("ZrSiO4", true);

        SilverAlloy = new Material.Builder(getMaterialsId(), gregtechId("silver_alloy"))
                .fluid()
                .dust()
                .ingot()
                .flags(GENERATE_FOIL, GENERATE_PLATE)
                .color(0x605856)
                .build().setFormula("AgCuAu", true);

        Inconel718 = new Material.Builder(getMaterialsId(), gregtechId("inconel_718"))
                .ingot().fluid()
                .color(0x7F8F75).iconSet(SHINY)
                .flags(GENERATE_DOUBLE_PLATE, GENERATE_SPRING, DISABLE_DECOMPOSITION)
                .components(Nickel, 5, Chrome, 2, Iron, 2, Niobium, 1, Molybdenum, 1)
                .fluidPipeProperties(2010, 175, true, true, true, false)
                .build();

        HighEnrichedUraniumHexafluoride = new Material.Builder(getMaterialsId(), gregtechId("high_enriched_uranium_hexafluoride"))
                .fluid()
                .color(0x86B155)
                .build().setFormula("UF6", true);

        SpentUraniumFuelSolution = new Material.Builder(getMaterialsId(), gregtechId("spent_uranium_fuel_solution"))
                .liquid()
                .color(0x384536).build();

        RadonRichGasMixture = new Material.Builder(getMaterialsId(), gregtechId("radon_rich_gas_mixture"))
                .gas()
                .color(0xd78dd9).build();

        LEU235 = new Material.Builder(getMaterialsId(), gregtechId("leu_235"))
                .dust(3)
                .color(0x232323).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(HighEnrichedUraniumDioxide, 1, DepletedUraniumDioxide, 19)
                .build()
                .setFormula("UO2", true);

        LEU235.setProperty(SCPropertyKey.FISSION_FUEL,
                FissionFuelProperty.builder(LEU235.getRegistryName(), 1500, 75000, 3.5)
                        .slowNeutronCaptureCrossSection(1.5)
                        .slowNeutronFissionCrossSection(1.5)
                        .slowNeutronCaptureCrossSection(1.5)
                        .slowNeutronFissionCrossSection(1.5)
                        .requiredNeutrons(1)
                        .releasedNeutrons(2.5)
                        .releasedHeatEnergy(0.01)
                        .decayRate(0.025)
                        .build());

        HEU235 = new Material.Builder(getMaterialsId(), gregtechId("heu_235"))
                .dust(3)
                .color(0x424845).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(HighEnrichedUraniumDioxide, 1, DepletedUraniumDioxide, 4)
                .build()
                .setFormula("UO2", true);

        HEU235.setProperty(SCPropertyKey.FISSION_FUEL,
                FissionFuelProperty.builder(HEU235.getRegistryName(), 1800, 60000, 2.5)
                        .slowNeutronCaptureCrossSection(2)
                        .slowNeutronFissionCrossSection(2)
                        .requiredNeutrons(1)
                        .releasedNeutrons(2.5)
                        .releasedHeatEnergy(0.01)
                        .decayRate(0.05)
                        .build());

        LowGradeMOX = new Material.Builder(getMaterialsId(), gregtechId("low_grade_mox"))
                .dust(3)
                .color(0x62C032).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(FissilePlutoniumDioxide, 1, Uraninite, 19)
                .build()
                .setFormula("(U,Pu)O2", true);

        LowGradeMOX.setProperty(SCPropertyKey.FISSION_FUEL,
                FissionFuelProperty.builder(LowGradeMOX.getRegistryName(), 1600, 50000, 1.5)
                        .slowNeutronCaptureCrossSection(2)
                        .slowNeutronFissionCrossSection(2)
                        .requiredNeutrons(1)
                        .releasedNeutrons(2.60)
                        .releasedHeatEnergy(0.02)
                        .decayRate(0.1)
                        .build());

        HighGradeMOX = new Material.Builder(getMaterialsId(), gregtechId("high_grade_mox"))
                .dust(3)
                .color(0x7EA432).iconSet(METALLIC)
                .flags(DISABLE_DECOMPOSITION)
                .components(FissilePlutoniumDioxide, 1, Uraninite, 4)
                .build()
                .setFormula("(U,Pu)O2", true);

        HighGradeMOX.setProperty(SCPropertyKey.FISSION_FUEL,
                FissionFuelProperty.builder(HighGradeMOX.getRegistryName(), 2000, 80000, 1)
                        .slowNeutronCaptureCrossSection(2.4)
                        .slowNeutronFissionCrossSection(2.4)
                        .requiredNeutrons(1)
                        .releasedNeutrons(2.80)
                        .releasedHeatEnergy(0.02)
                        .decayRate(0.2)
                        .build());

        Zircaloy = new Material.Builder(504, gregtechId("zircaloy"))
                .ingot()
                .color(0x566570).iconSet(METALLIC)
                .flags(GENERATE_RING, GENERATE_PLATE)
                .components(Zirconium, 16, Tin, 2, Chrome, 1)
                .blast(1700, BlastProperty.GasTier.LOW)
                .build();

        LowEnrichedUraniumDioxide = new Material.Builder(getMaterialsId(), gregtechId("low_enriched_uranium_dioxide"))
                .dust()
                .color(0x43A333)
                .flags(DISABLE_DECOMPOSITION)
                .components(Uranium235, 1, Oxygen, 2)
                .build()
                .setFormula("UO2", true);


        ZirconiumDioxide = new Material.Builder(getMaterialsId(), gregtechId("zirconium_dioxide"))
                .dust()
                .color(0x689F9F)
                .flags(DISABLE_DECOMPOSITION)
                .components(Zirconium, 1, Oxygen, 2)
                .build();

        ZirconiumTetrachloride = new Material.Builder(getMaterialsId(), gregtechId("zirconium_tetrachloride"))
                .dust()
                .color(0x689FBF)
                .flags(DISABLE_DECOMPOSITION)
                .components(Zirconium, 1, Chlorine, 4)
                .iconSet(SHINY)
                .build();

        HafniumDioxide = new Material.Builder(getMaterialsId(), gregtechId("hafnium_dioxide"))
                .dust()
                .color(0x39393A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hafnium, 1, Oxygen, 2)
                .build();

        HafniumTetrachloride = new Material.Builder(getMaterialsId(), gregtechId("hafnium_tetrachloride"))
                .dust()
                .color(0x69699A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hafnium, 1, Chlorine, 4)
                .iconSet(SHINY)
                .build();

        HighEnrichedUraniumHexafluoride = new Material.Builder(getMaterialsId(), gregtechId("high_enriched_uranium_hexafluoride"))
                .gas()
                .color(0x5BF93A)
                .flags(DISABLE_DECOMPOSITION)
                .components(Uranium235, 1, Fluorine, 6)
                .build();

        BoronTrioxide = new Material.Builder(getMaterialsId(), gregtechId("boron_trioxide"))
                .dust()
                .color(0xC1E9E1)
                .components(Boron, 2, Oxygen, 3)
                .iconSet(METALLIC)
                .build();

        BoronCarbide = new Material.Builder(getMaterialsId(), gregtechId("boron_carbide"))
                .ingot()
                .flags(GENERATE_ROD, DISABLE_DECOMPOSITION)
                .blast(2620)
                .color(0xC1E9C1)
                .components(Boron, 4, Carbon, 1)
                .iconSet(METALLIC)
                .build();

        HighPressureHeavyWater = new Material.Builder(getMaterialsId(), gregtechId("high_pressure_heavy_water"))
                .gas(new FluidBuilder().temperature(500))
                .color(0xCCD9F0)
                .flags(DISABLE_DECOMPOSITION)
                .components(Deuterium, 2, Oxygen, 1)
                .build();

        HeavyWater = new Material.Builder(getMaterialsId(), gregtechId("heavy_water"))
                .fluid()
                .color(0x3673D6)
                .components(Deuterium, 2, Oxygen, 1)
                .build();

        HeavyWater.setProperty(SCPropertyKey.COOLANT,
                new CoolantProperty(HeavyWater, HighPressureHeavyWater, FluidStorageKeys.LIQUID, 4., 1000,
                        374.4, 2064000, 4228.)
                        .setAccumulatesHydrogen(true));
    }

    private static int getMaterialsId() {
        if (startId < END_ID) {
            return startId++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
