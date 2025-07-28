package gtb.api.unification.materials;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static gtb.api.unification.materials.GTBMaterials.*;
import gregtech.api.unification.material.Material;

public class GTBSecondDegreeMaterial {

    // Range 25001-25200
    private static int startId = 30300;
    private static final int END_ID = startId + 400;

    public static void init() {

        FissilePlutoniumDioxide = new Material.Builder(getMaterialsId(), gregtechId("fissile_plutonium_dioxide"))
                .dust(3)
                .color(0xF03232).iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION)
                .components(Plutonium, 1, Oxygen, 2)
                .build();

        HighEnrichedUraniumDioxide = new Material.Builder(getMaterialsId(), gregtechId("high_enriched_uranium_dioxide"))
                .dust(3)
                .color(0x53E353).iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION)
                .components(Uranium235, 1, Oxygen, 2)
                .build()
                .setFormula("UO2", true);

        DepletedUraniumDioxide = new Material.Builder(getMaterialsId(), gregtechId("depleted_uranium_dioxide"))
                .dust(3)
                .color(0x335323).iconSet(DULL)
                .build()
                .setFormula("UO2", true);

    }

    private static int getMaterialsId() {
        if (startId < END_ID) {
            return startId++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
