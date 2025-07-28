package gtb.api.unification.materials;

import gregtech.api.unification.material.Material;

import gtb.api.unification.materials.info.GTBMaterialIconSet;

public class GTBMaterials {

    public static Material NuclearWaste;

    // Fantasy Materials
    public static Material AwakenedDraconium;
    public static Material Adamantium;
    public static Material Infinity;
    public static Material CosmicNeutronium;
    public static Material Eternal;
    public static Material CataclysmInducedMixedMatter;
    public static Material TemporallyConstrainedAntiMatter;
    public static Material Pikyonium;
    public static Material Tenannite;
    public static Material Xenotime;
    public static Material SilverAlloy;
    public static Material Zircon;
    public static Material FissilePlutoniumDioxide;
    public static Material HighEnrichedUraniumDioxide;
    public static Material DepletedUraniumDioxide;
    public static Material HighEnrichedUraniumHexafluoride;
    public static Material Zirkelite;
    public static Material HeavyWater;
    public static Material Inconel718;
    public static Material Zircaloy;
    public static Material BoronCarbide;
    public static Material BoronTrioxide;
    public static Material Neptunium235;
    public static Material Neptunium236;
    public static Material Neptunium237;
    public static Material Neptunium239;
    public static Material HighPressureSteam;
    public static Material HighPressureHeavyWater;

    public static Material Plutonium; // Overrides CEu's Plutonium
    public static Material Plutonium238;
    public static Material Plutonium240;
    /* public static Material Plutonium241 = Materials.Plutonium241; */
    public static Material Plutonium242;
    public static Material Plutonium244;
    /* public static Material Uranium235 = Materials.Uranium235; */
    /* public static Material Uranium238 = Materials.Uranium238; */
    public static Material Uranium239;
    /* public static Material LowEnrichedUraniumHexafluoride = Materials.EnrichedUraniumHexafluoride; */

    public static Material LowEnrichedUraniumDioxide;
    public static Material ZirconiumDioxide;
    public static Material ZirconiumTetrachloride;
    public static Material HafniumDioxide;
    public static Material HafniumTetrachloride;

    public static Material SpentUraniumFuelSolution;
    public static Material RadonRichGasMixture;

    public static Material LEU235;
    public static Material HEU235;
    public static Material LowGradeMOX;
    public static Material HighGradeMOX;

    // Kev Coolers Materials
    public static Material LN;
    public static Material FLiBe;
    public static Material MNovec;
    public static Material BiphenylDiphenylEther;
    public static Material Fc_40;
    public static Material PerfluoropolyetherK;

    public static void init() {
        GTBFirstDegreeMaterial.init();
        GTBSecondDegreeMaterial.init();
        GTBUnknownCompositionMaterials.register();
        GTBFantasyMaterials.init();
        GTBKevCoolersMaterials.register();
        GTBMaterialPropertyAddition.init();
        GTBMaterialIconSet.set();
    }
}
