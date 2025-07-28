package gtb.api.unification.materials;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.Elements;
import gregtech.api.unification.material.Material;
import supercritical.api.unification.SCElements;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static gtb.api.unification.materials.GTBMaterials.*;

public class GTBFirstDegreeMaterial {

    // Range 25001-25200
    private static int startId = 30000;
    private static final int END_ID = startId + 200;

    public static void init() {


        HighPressureSteam = new Material.Builder(getMaterialsId(), gregtechId("high_pressure_steam"))
                .gas(new FluidBuilder()
                        .temperature(500)
                        .customStill())
                .color(0xC4C4C4)
                .flags(DISABLE_DECOMPOSITION)
                .components(Hydrogen, 2, Oxygen, 1)
                .build();

        Uranium239 = new Material.Builder(getMaterialsId(), gregtechId("uranium_239"))
                .color(0x46FA46).iconSet(SHINY)
                .element(SCElements.U239)
                .build();

        Neptunium235 = new Material.Builder(getMaterialsId(), gregtechId("neptunium_235"))
                .color(0x284D7B)
                .iconSet(METALLIC)
                .element(SCElements.Np235)
                .build();

        Neptunium236 = new Material.Builder(getMaterialsId(), gregtechId("neptunium_236"))
                .color(0x284D7B).iconSet(METALLIC)
                .element(SCElements.Np236)
                .build();

        Neptunium237 = new Material.Builder(getMaterialsId(), gregtechId("neptunium_237"))
                .color(0x284D7B).iconSet(METALLIC)
                .element(SCElements.Np237)
                .build();

        Neptunium239 = new Material.Builder(getMaterialsId(), gregtechId("neptunium_239"))
                .color(0x284D7B).iconSet(METALLIC)
                .element(SCElements.Np239)
                .build();

        Plutonium238 = new Material.Builder(getMaterialsId(), gregtechId("plutonium_238"))
                .liquid(new FluidBuilder().temperature(913))
                .color(0xF03232).iconSet(METALLIC)
                .element(SCElements.Pu238)
                .build();

        Plutonium240 = new Material.Builder(getMaterialsId(), gregtechId("plutonium_240"))
                .liquid(new FluidBuilder().temperature(913))
                .color(0xF03232).iconSet(METALLIC)
                .element(SCElements.Pu240)
                .build();

        Plutonium242 = new Material.Builder(getMaterialsId(), gregtechId("plutonium_242"))
                .liquid(new FluidBuilder().temperature(913))
                .color(0xF03232).iconSet(METALLIC)
                .element(SCElements.Pu242)
                .build();

        Plutonium239 = new Material.Builder(getMaterialsId(), gregtechId("plutonium_239"))
                .liquid(new FluidBuilder().temperature(913))
                .color(0xF03232).iconSet(METALLIC)
                .element(SCElements.Pu242)
                .build();

        Plutonium244 = new Material.Builder(getMaterialsId(), gregtechId("plutonium_244"))
                .liquid(new FluidBuilder().temperature(913))
                .color(0xF03232).iconSet(METALLIC)
                .element(SCElements.Pu244)
                .flags(GENERATE_FINE_WIRE, GENERATE_PLATE)
                .build();

        Plutonium = new Material.Builder(getMaterialsId(), gregtechId("plutonium"))
                .color(0xF03232).iconSet(METALLIC)
                .element(Elements.Pu)
                .build();

    }

    private static int getMaterialsId() {
        if (startId < END_ID) {
            return startId++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
