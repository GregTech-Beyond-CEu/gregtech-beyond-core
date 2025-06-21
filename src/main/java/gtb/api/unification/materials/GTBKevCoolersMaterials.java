package gtb.api.unification.materials;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKey;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import net.minecraftforge.fluids.Fluid;

import static gregtech.api.util.GTUtility.gregtechId;
import static gtb.api.unification.materials.GTBMaterials.*;

public class GTBKevCoolersMaterials {

    // Range 25601-25800
    private static int startId = 25801;
    private static final int END_ID = startId + 200;

    public static void register() {
        LN = new Material.Builder(getMaterialsId(), gregtechId("ln"))
                .dust()
                .color(0x6B8D6E)
                .build();

        //See https://en.wikipedia.org/wiki/FLiBe
        FLiBe = new Material.Builder(getMaterialsId(), gregtechId("f_li_be"))
                .liquid(new FluidBuilder().temperature(459 + 273))
                .colorAverage()
                .components(Materials.Fluorine, 4, Materials.Lithium, 2, Materials.Beryllium, 1)
                .build()
                .setFormula("Li2(BeF4)", true);

        //See https://www.sigmaaldrich.com/FR/fr/product/sigma/shh0002
        //See could be called methoxy-nonafluorobutane
        MNovec = new Material.Builder(getMaterialsId(),
                gregtechId("mnovec"))
                        .fluid()
                .components(Materials.Carbon, 5, Materials.Hydrogen, 3, Materials.Oxygen, 1, Materials.Fluorine, 9)
                        .color(0x376B3B)
                        .build()
                .setFormula("CF3(CF2)3OCH3", true);

        PerfluoropolyetherK = new Material.Builder(getMaterialsId(), gregtechId("perfluoropolyether_k"))
                .fluid()
                .components(Materials.Carbon, 3, Materials.Oxygen, 1, Materials.Fluorine, 5)
                .colorAverage()
                .build()
                .setFormula("(CF(CF3)CF2O)n", true);

        Fc_40 = new Material.Builder(getMaterialsId(), gregtechId("fc_40"))
                .fluid()
                .components(Materials.Carbon, 21, Materials.Fluorine, 21, Materials.Nitrogen, 2)
                .colorAverage()
                .build();

        BiphenylDiphenylEther = new Material.Builder(getMaterialsId(), gregtechId("biphenyl_diphenyl_ether"))
                .fluid()
                .components(Materials.Carbon, 24, Materials.Hydrogen, 20, Materials.Oxygen, 1)
                .colorAverage()
                .build();
    }

    private static int getMaterialsId() {
        if (startId < END_ID) {
            return startId++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
