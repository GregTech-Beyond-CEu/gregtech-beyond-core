package gtb.api.unification.materials;

import gregtech.api.unification.material.Material;

import gtb.api.unification.materials.info.GTBMaterialIconSet;

public class GTBMaterials {

    public static Material SilverAlloy;
    public static Material Grisium;
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
    public static Material Zirkelite;
    // SD Materials

    // Unknown Materials

    // Organic Chemistry Materials

    // Fantasy Materials

    // Kev Coolers Materials
    public static Material LN;
    public static Material FLiBe;
    public static Material MNovec;
    public static Material BiphenylDiphenylEther;
    public static Material Fc_40;
    public static Material PerfluoropolyetherK;

    public static void init() {
        GTBUnknownCompositionMaterials.register();
        GTBFantasyMaterials.init();
        GTBKevCoolersMaterials.register();
        GTBMaterialPropertyAddition.init();
        GTBMaterialIconSet.set();
        GTBFantasyMaterials.init();
    }
}
