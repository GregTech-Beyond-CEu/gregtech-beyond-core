package gtb.api.unification.materials;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static gtb.api.unification.materials.GTBMaterials.*;

import gregtech.api.unification.material.Material;

public class GTBUnknownCompositionMaterials {

    // Range 25201-25400
    private static int startId = 3300;
    private static final int END_ID = startId + 1000;

    public static void register() {
        NuclearWaste = new Material.Builder(getMaterialsId(), gregtechId("nuclear_waste"))
                .dust()
                .color(0x00DC52).build();
    }

    private static int getMaterialsId() {
        if (startId < END_ID) {
            return startId++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
